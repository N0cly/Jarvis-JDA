package com.nocly.jarvis.commands.Utils;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

public class SayCommand {

    public SayCommand(SlashCommandInteraction event){
        OptionMapping msgOption = event.getOption("message");
        String msg = msgOption.getAsString();

        MessageChannel channel;

        OptionMapping channelOption = event.getOption("channel");
        if (channelOption != null){

            channel = channelOption.getAsChannel().asGuildMessageChannel();
        }else{
            channel = event.getGuild().getDefaultChannel().asTextChannel();
        }

        channel.sendMessage(msg).queue();
        event.reply("message send successfully!").setEphemeral(true).queue();

    }
}
