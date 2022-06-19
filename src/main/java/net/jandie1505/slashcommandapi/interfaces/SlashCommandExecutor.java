package net.jandie1505.slashcommandapi.interfaces;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

public interface SlashCommandExecutor {

    void onSlashCommand(SlashCommandInteraction interaction);
}
