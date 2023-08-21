package com.nocly.jarvis.commands.Utils;

import com.nocly.jarvis.handler.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class WelcomeCommand implements ICommand {

    @Override
    public String getName() {
        return "welcome";
    }

    @Override
    public String getDescription() {
        return "welcome by the bot.";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equals("welcome")){
            String userTag = event.getUser().getAsMention();
            event.reply("Welcome to the server **"+ userTag+"**!").setEphemeral(true).queue();
        }
    }
}
