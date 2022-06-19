package net.jandie1505.slashcommandapi;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

public interface SlashCommandExecutor {

    void onSlashCommand(SlashCommandInteraction interaction);
}
