package com.nocly.jarvis.commands.Utils;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.List;

public class JoinCommand {

    public JoinCommand(SlashCommandInteractionEvent event){

        String command = event.getName();
        if (command.equals("join")){
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

    public JoinCommand(GuildReadyEvent event, List<CommandData> commandData){
        commandData.add(Commands.slash("join","join voice channel"));

    }
}
