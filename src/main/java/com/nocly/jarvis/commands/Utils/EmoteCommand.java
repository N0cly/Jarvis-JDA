package com.nocly.jarvis.commands.Utils;

import com.nocly.jarvis.handler.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class EmoteCommand implements ICommand {

    @Override
    public String getName() {
        return "emote";
    }

    @Override
    public String getDescription() {
        return "Express your emotions through text.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(
                OptionType.STRING,
                "type",
                "The type of emotion to express",
                true)
                .addChoice("Hug", "hug")
                .addChoice("Laugh", "laugh")
                .addChoice("Cry", "cry"));
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

        String command = event.getName();

        if (command.equals("emote")) {
            OptionMapping option = event.getOption("type");
            String type = option.getAsString();

            String replyMessage = "";
            switch (type.toLowerCase()) {
                case "hug" -> {
                    replyMessage = "You hug the closest person to you";
                }
                case "laugh" -> {
                    replyMessage = "You laugh hysterically at everyone around you";
                }
                case "cry" -> {
                    replyMessage = "You can't stop cry ?";
                }
            }
            event.reply(replyMessage).queue();
        }
    }
}