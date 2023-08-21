package com.nocly.jarvis.commands.Utils;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.List;

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

    public RolesCommand(GuildReadyEvent event, List<CommandData> commandData){
        commandData.add(Commands.slash("roles","Display all roles on the server"));

    }
}
