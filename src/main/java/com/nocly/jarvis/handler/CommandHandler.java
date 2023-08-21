package com.nocly.jarvis.handler;

import com.nocly.jarvis.commands.Utils.*;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        new WelcomeCommand(event);
        new RolesCommand(event);
        new JoinCommand(event);
        new SayCommand(event);
        new EmoteCommand(event);
        new GiveroleCommand(event);

    }

    // Guild command -- instantly updated (max 100)

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();

        new WelcomeCommand(event, commandData);
        new RolesCommand(event, commandData);
        new JoinCommand(event, commandData);
        new SayCommand(event, commandData);
        new EmoteCommand(event, commandData);
        new GiveroleCommand(event, commandData);
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
