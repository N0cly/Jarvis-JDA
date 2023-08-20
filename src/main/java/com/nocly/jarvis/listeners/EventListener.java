package com.nocly.jarvis.listeners;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Listens for events ans reponds with our custom code
 *
 * @author Nocly
 */
public class EventListener extends ListenerAdapter {

    /**
     *
     * @param event
     */
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {

        // Nocly reacted to a message with "thubs up" in the #bot channel
        User user = event.getUser();
        String emoji = event.getReaction().getEmoji().getAsReactionCode();

        String channelMention = event.getChannel().getAsMention();

        String jumpLink = event.getJumpUrl();

        String msg = "`"+user.getName()+"`" + " a reagit au message avec " + emoji + " dans le channel " + channelMention;

        //event.getChannel().sendMessage(msg).queue();

        // send message in the default channel of the guild
        event.getGuild().getDefaultChannel().asTextChannel().sendMessage(msg).queue();
    }

    /**
     *
     * @param event
     */
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if (msg.contains("ping")){
            event.getChannel().sendMessage("pong").queue();
        }
        else if (event.isFromType(ChannelType.PRIVATE))
        {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContentDisplay());
        }
        else
        {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getChannel().getName(), event.getMember().getEffectiveName(),
                    event.getMessage().getContentDisplay());
        }
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        String avatar = event.getUser().getEffectiveAvatarUrl();
        System.out.println(avatar);

        String msg = event.getUser().getAsMention() + "a rejoint le serveur !";

        event.getGuild().getDefaultChannel().asTextChannel().sendMessage(msg).queue();
    }

    @Override
    public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {
        List<Member> members = event.getGuild().getMembers();
        int onlineMembers = 0;

        for (Member member : members){
            if(member.getOnlineStatus()== OnlineStatus.ONLINE){ //member.getOnlineStatus()== OnlineStatus.DO_NOT_DISTURB
                onlineMembers++;
            }
        }


        User user = event.getUser();
        //String msg = "`"+user.getName()+"`"+"** update their online status to** "+"`"+event.getNewOnlineStatus().getKey()+"`";

        String msg = "`"+user.getName()+"`"+"** update their online status to** "+"`"+event.getNewOnlineStatus().getKey()+"`"+"\n There are now **" + onlineMembers + " Users online in this guild**";

        event.getGuild().getDefaultChannel().asTextChannel().sendMessage(msg).queue();
    }
}