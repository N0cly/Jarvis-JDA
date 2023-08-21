package com.nocly.jarvis.commands.Utils;

import com.nocly.jarvis.handler.ICommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class GiveroleCommand implements ICommand {


    @Override
    public String getName() {
        return "giverole";
    }

    @Override
    public String getDescription() {
        return "Give a user a role";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.USER, "user", "the user to give the role to", true));
        data.add(new OptionData(OptionType.ROLE, "role","the role to be given", true));

        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equals("giverole")){
            Member member = event.getOption("user").getAsMember();
            Role role = event.getOption("role").getAsRole();

            event.getGuild().addRoleToMember(member, role).queue();
            event.reply(member.getAsMention() + " has been given the "+ role.getAsMention() +" role!").queue();
        }

    }
}
