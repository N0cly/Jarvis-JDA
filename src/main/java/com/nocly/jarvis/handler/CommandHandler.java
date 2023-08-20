package com.nocly.jarvis.handler;

import com.nocly.jarvis.commands.Utils.*;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        String command = event.getName();

        if (command.equals("welcome")) {
            new WelcomeCommand(event);
        } else if (command.equals("roles")) {
            new RolesCommand(event);
        } else if (command.equals("join")) {
            new JoinCommand(event);
        } else if (command.equals("say")){
            new SayCommand(event);
        }
    }

    // Guild command -- instantly updated (max 100)

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("welcome","Get welcome by the bot."));
        commandData.add(Commands.slash("roles","Display all roles on the server"));
        commandData.add(Commands.slash("join","join voice channel"));

        OptionData opt1 = new OptionData(OptionType.STRING, "message", "the message you want the bot say",true);
        OptionData opt2 = new OptionData(OptionType.CHANNEL, "channel", "The channel you went to send this message", false);
        commandData.add(Commands.slash("say","Make the bot say a message").addOptions(opt1, opt2));

        event.getGuild().updateCommands().addCommands(commandData).queue();
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
