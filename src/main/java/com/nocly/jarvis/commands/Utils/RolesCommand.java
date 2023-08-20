package com.nocly.jarvis.commands.Utils;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

/**
 *
 */
public class RolesCommand {

    /**
     *
     * @param event
     */
    public RolesCommand(SlashCommandInteraction event){
        event.deferReply().setEphemeral(true).queue();
        String response = "";
        for (Role role : event.getGuild().getRoles()) {
            response += role.getAsMention() + "\n";
        }
        event.getHook().sendMessage(response).queue();
    }
}
