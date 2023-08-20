package com.nocly.jarvis.commands.Utils;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinCommand {

    public JoinCommand(SlashCommandInteraction event){
        if (!event.isFromGuild()) return;
        if (event.getUser().isBot()) return;
        Guild guild = event.getGuild();

        VoiceChannel channel = guild.getVoiceChannelsByName("music", true).get(0);
        AudioManager manager = guild.getAudioManager();

        //manager.setSendingHandler(new MySendHandler());
        manager.openAudioConnection(channel);

        event.reply("Je suis la ;)").setEphemeral(true).queue();
    }
}
