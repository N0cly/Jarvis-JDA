package com.nocly.jarvis.commands.Utils;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.List;

public class PingCommand {

    public PingCommand(SlashCommandInteractionEvent event){
        String command = event.getName();

        if (command.equals("ping")){
            long startTime = System.currentTimeMillis();
            event.reply("Pinging...").queue(response -> {
                long endTime = System.currentTimeMillis();
                long ping = endTime - startTime;
                response.editOriginal("Pong! Bot's response time: " + ping + "ms").queue();
            });
        }
    }

    public PingCommand(GuildReadyEvent event, List<CommandData> commandData){
        commandData.add(Commands.slash("ping","Calculate bot's response time"));
    }
}
