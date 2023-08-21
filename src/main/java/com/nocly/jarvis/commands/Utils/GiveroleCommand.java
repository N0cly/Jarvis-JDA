package com.nocly.jarvis.commands.Utils;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class GiveroleCommand {


    public GiveroleCommand(SlashCommandInteractionEvent event){

        String command = event.getName();

        if (command.equals("giverole")){
            Member member = event.getOption("user").getAsMember();
            Role role = event.getOption("role").getAsRole();

            event.getGuild().addRoleToMember(member, role).queue();
            event.reply(member.getAsMention() + " has been given the "+ role.getAsMention() +" role!").queue();
        }

    }

    public GiveroleCommand(GuildReadyEvent event, List<CommandData> commandData){
        OptionData opt4 = new OptionData(OptionType.USER, "user", "the user to give the role to", true);
        OptionData opt5 = new OptionData(OptionType.ROLE, "role","the role to be given", true);
        commandData.add(Commands.slash("giverole","Give a user a role").addOptions(opt4,opt5));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

}
