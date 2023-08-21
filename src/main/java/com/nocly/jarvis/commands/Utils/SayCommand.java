package com.nocly.jarvis.commands.Utils;

import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class SayCommand {

    public SayCommand(SlashCommandInteractionEvent event){

        String command = event.getName();

        if (command.equals("say")){
            OptionMapping msgOption = event.getOption("message");
            String msg = msgOption.getAsString();

            MessageChannel channel;

            OptionMapping channelOption = event.getOption("channel");
            if (channelOption != null){

                channel = channelOption.getAsChannel().asGuildMessageChannel();
            }       else{
                channel = event.getGuild().getDefaultChannel().asTextChannel();
            }

            channel.sendMessage(msg).queue();
            event.reply("message send successfully!").setEphemeral(true).queue();
        }


    }

    public SayCommand(GuildReadyEvent event, List<CommandData> commandData){
        OptionData opt1 = new OptionData(
                OptionType.STRING,
                "message",
                "the message you want the bot say",
                true);
        OptionData opt2 = new OptionData(
                OptionType.CHANNEL,
                "channel",
                "The channel you went to send this message",
                false)
                .setChannelTypes(
                        ChannelType.TEXT,
                        ChannelType.NEWS,
                        ChannelType.GUILD_PUBLIC_THREAD
                );
        commandData.add(Commands.slash("say","Make the bot say a message").addOptions(opt1, opt2));
    }
}
