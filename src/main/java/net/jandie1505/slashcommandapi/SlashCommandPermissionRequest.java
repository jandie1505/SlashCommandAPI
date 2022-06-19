package net.jandie1505.slashcommandapi;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

public interface SlashCommandPermissionRequest {

    boolean hasPermission(SlashCommandInteraction interaction);
}
