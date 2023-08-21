package com.nocly.jarvis.commands.Utils;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class EmoteCommand {
    public EmoteCommand(SlashCommandInteractionEvent event){

        String command = event.getName();

        if (command.equals("emote")){
            OptionMapping option = event.getOption("type");
            String type = option.getAsString();

            String replyMessage = "";
            switch (type.toLowerCase()){
                case "hug" -> {
                    replyMessage = "You hug the closest person to you";
                }
                case "laugh" ->{
                    replyMessage = "You laugh hysterically at everyone around you";
                }
                case "cry" -> {
                    replyMessage = "You can't stop cry ?";
                }
            }
            event.reply(replyMessage).queue();
        }



    }

    public EmoteCommand(GuildReadyEvent event, List<CommandData> commandData){
        OptionData opt3 = new OptionData(
                OptionType.STRING,
                "type",
                "The type of emotion to express",
                true)
                .addChoice("Hug","hug")
                .addChoice("Laugh","laugh")
                .addChoice("Cry","cry");

        commandData.add(Commands.slash("emote","Express yout emotios through text.").addOptions(opt3));
    }
}
