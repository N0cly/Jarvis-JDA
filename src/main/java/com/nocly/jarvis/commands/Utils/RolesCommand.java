package com.nocly.jarvis.commands.Utils;

import com.nocly.jarvis.handler.ICommand;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

/**
 *
 */
public class RolesCommand implements ICommand {

    @Override
    public String getName() {
        return "roles";
    }

    @Override
    public String getDescription() {
        return "Display all roles on the server";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

        String command = event.getName();

        if (command.equals("roles")) {
            event.deferReply().setEphemeral(true).queue();
            String response = "";
            for (Role role : event.getGuild().getRoles()) {
                response += role.getAsMention() + "\n";
            }
            event.getHook().sendMessage(response).queue();
        }
    }
}
