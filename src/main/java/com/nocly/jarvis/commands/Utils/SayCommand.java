package com.nocly.jarvis.commands.Utils;

import com.nocly.jarvis.handler.ICommand;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class SayCommand implements ICommand {

    @Override
    public String getName() {
        return "say";
    }

    @Override
    public String getDescription() {
        return "Make the bot say a message";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(
                OptionType.STRING,
                "message",
                "the message you want the bot say",
                true));
        data.add(new OptionData(
                OptionType.CHANNEL,
                "channel",
                "The channel you went to send this message",
                false)
                .setChannelTypes(
                        ChannelType.TEXT,
                        ChannelType.NEWS,
                        ChannelType.GUILD_PUBLIC_THREAD
                ));

        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

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
}
