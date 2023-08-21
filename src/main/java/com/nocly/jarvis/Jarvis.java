package com.nocly.jarvis;

import com.nocly.jarvis.commands.Utils.*;
import com.nocly.jarvis.handler.CommandManager;
import com.nocly.jarvis.listeners.EventListener;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

/**
 * JDA 5.0.0 bot Discord Jarvis
 *Main programme
 *
 * @author Nocly
 */

public class  Jarvis {

    private final Dotenv config;
    private final ShardManager shardManager;

    /**
     * Loads environment variables and builds the bot shard manager.
     * @throws LoginException occurs when bot token is invalid.
     */

    public Jarvis()  throws LoginException {

        config = Dotenv.configure().ignoreIfMissing().load();
        String token = config.get("TOKEN");


        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.listening("SUNLIGHTS"));
        builder.enableIntents(
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_INVITES,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_MESSAGES,
                //GatewayIntent.GUILD_MESSAGE_TYPING,
                GatewayIntent.GUILD_MODERATION,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.MESSAGE_CONTENT
        );
        builder.setMemberCachePolicy(MemberCachePolicy.ONLINE);
        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.enableCache(CacheFlag.ONLINE_STATUS);
        shardManager = builder.build();

        CommandManager manager = new CommandManager();
        manager.add(new WelcomeCommand());
        manager.add(new EmoteCommand());
        manager.add(new RolesCommand());
        manager.add(new GiveroleCommand());
        manager.add(new PingCommand());
        manager.add(new SayCommand());
        manager.add(new JoinCommand());
        manager.add(new PlayCommand());
        manager.add(new NowPlayingCommand());
        manager.add(new QueueCommand());
        manager.add(new RolesCommand());
        manager.add(new SkipCommand());
        manager.add(new StopCommand());



        //Register Listeners
        shardManager.addEventListener(
                new EventListener(),
                manager
        );

    }



    /**
     * Retrieves the bot config.
     * @return config file from .env
     */
    public Dotenv getConfig(){
        return config;
    }

    /**
     * Retrieves the bot shard manager.
     * @return the ShardManager instance for the bot
     */

    public ShardManager getShardManager(){
        return shardManager;
    }

    public static void main(String[] args){

        try {
            Jarvis bot = new Jarvis();

        }catch (LoginException e){
            System.out.println("ERROR: token invalide le sang");
        }
    }
}
