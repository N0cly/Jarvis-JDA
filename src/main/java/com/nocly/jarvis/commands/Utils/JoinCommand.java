package com.nocly.jarvis.commands.Utils;

import com.nocly.jarvis.handler.ICommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.managers.AudioManager;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JoinCommand implements ICommand {

    @Override
    public String getName() {
        return "join";
    }

    @Override
    public String getDescription() {
        return "join voice channel";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {


        if (!event.isFromGuild()) return;
        if (event.getUser().isBot()) return;
        Guild guild = event.getGuild();


        VoiceChannel channel = guild.getVoiceChannelsByName("music", true).get(0);
        AudioManager manager = guild.getAudioManager();

        //manager.setSendingHandler(new MySendHandler());
        manager.openAudioConnection(channel);

        String path = "C:/Users/Ninja/Documents/01.Programme/Java/test.json";

        File file = new File(path);

        try {
            if (!file.exists())
                file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(event.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Erreur: impossible de créer le fichier '"
                    + path + "'");
        }

        event.reply("Je suis la ;)").setEphemeral(true).queue();

    }
}
