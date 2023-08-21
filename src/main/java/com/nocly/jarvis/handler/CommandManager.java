package com.nocly.jarvis.handler;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {


    private List<ICommand> commands = new ArrayList<>();

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        for (ICommand command : commands) {
            if (command.getName().equals(event.getName())){
                command.execute(event);
                return;
            }

        }

    }

    // Global command -- up to an hour to update (unlimited)
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        for (Guild guild : event.getJDA().getGuilds()){
            for (ICommand command : commands){
                if (command.getOptions() != null){
                    guild.upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).queue();
                } else {
                    guild.upsertCommand(command.getName(), command.getDescription()).queue();

                }
            }
        }
    }

    // Guild command -- instantly updated (max 100)

    //@Override
    //public void onGuildReady(@NotNull GuildReadyEvent event) {


    //}

    //@Override
    //public void onGuildJoin(@NotNull GuildJoinEvent event) {

    //}

    public void add(ICommand command){
        commands.add(command);
    }
}
