package com.nocly.jarvis.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        String command = event.getName();
        if (command.equals("welcome")){ //command /welcome
            // run the '/welcome' command
            String userTag = event.getUser().getAsMention();
            event.reply("Welcome to the server **"+ userTag+"**!").setEphemeral(true).queue();

        } else if (command.equals("roles")) {
            event.deferReply().setEphemeral(true).queue();
            String response = "";
            for (Role role : event.getGuild().getRoles()) {
                response += role.getAsMention() + "\n";
            }
            event.getHook().sendMessage(response).queue();

        } else if (command.equals("music")) {
            if (!event.isFromGuild()) return;
            if (event.getUser().isBot()) return;
            Guild guild = event.getGuild();

            VoiceChannel channel = guild.getVoiceChannelsByName("music", true).get(0);
            AudioManager manager = guild.getAudioManager();

            //manager.setSendingHandler(new MySendHandler());
            manager.openAudioConnection(channel);

            event.reply("Je suis la ;)").setEphemeral(true).queue();
        } else if (command.equals("play")) {
        // Récupérez l'argument URL de la commande
        String trackUrl = event.getOption("url").getAsString();

        // Utilisez le gestionnaire audio pour charger et jouer la musique
       // audioPlayerManagerWrapper.loadAndPlay(trackUrl);

        event.reply("Now playing: " + trackUrl).queue();
    }

    }

    // Guild command -- instantly updated (max 100)

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("welcome","Get welcome by the bot."));
        commandData.add(Commands.slash("roles","Display all roles on the server"));
        commandData.add(Commands.slash("music","join voice channel"));
        event.getGuild().updateCommands().addCommands(commandData).queue();

        // Initialiser le gestionnaire audio

        //audioPlayerManagerWrapper = new AudioPlayerManagerWrapper();
    }

    //@Override
    //public void onGuildJoin(@NotNull GuildJoinEvent event) {
    //    List<CommandData> commandData = new ArrayList<>();
    //    commandData.add(Commands.slash("welcome","Get welcome by the bot."));
    //    event.getGuild().updateCommands().addCommands(commandData).queue();

        //if (event.getGuild().getId() == "881224503819137074"){
        //
        //}
    //}

    // Global command -- up to an hour to update (unlimited)


    //@Override
    //public void onReady(@NotNull ReadyEvent event) {
    //    List<CommandData> commandData = new ArrayList<>();
    //    commandData.add(Commands.slash("welcome","Get welcome by the bot."));
    //    event.getJDA().updateCommands().addCommands(commandData).queue();
    //}
}
