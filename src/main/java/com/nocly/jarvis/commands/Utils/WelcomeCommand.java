package com.nocly.jarvis.commands.Utils;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

public class WelcomeCommand {

    public WelcomeCommand(SlashCommandInteraction event){
        String userTag = event.getUser().getAsMention();
        event.reply("Welcome to the server **"+ userTag+"**!").setEphemeral(true).queue();
    }
}
