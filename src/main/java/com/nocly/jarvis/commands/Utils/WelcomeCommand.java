package com.nocly.jarvis.commands.Utils;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.List;

public class WelcomeCommand {

    public WelcomeCommand(SlashCommandInteraction event){
        String userTag = event.getUser().getAsMention();
        event.reply("Welcome to the server **"+ userTag+"**!").setEphemeral(true).queue();
    }

    public WelcomeCommand(GuildReadyEvent event, List<CommandData> commandData){
        commandData.add(Commands.slash("welcome","welcome by the bot."));

    }
}
