package net.jandie1505.slashcommandapi.utilities;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandExecutor;

import java.awt.*;

public class DefaultSlashCommandExecutors {

    private DefaultSlashCommandExecutors() {}

    /**
     * Returns an empty SlashCommandExecutor.
     * This is useless.
     * @return SlashCommandExecutor
     */
    public static SlashCommandExecutor emptyExecutor() {
        return interaction -> {};
    }

    /**
     * Returns a simple no permission embed message.
     * You can use this for the no permission executor.
     * @return SLashCommandExecutor
     */
    public static SlashCommandExecutor noPermissionExecutor() {
        return interaction -> interaction.reply(" ").addEmbeds(
                new EmbedBuilder()
                        .setDescription("No permission")
                        .setColor(Color.RED)
                        .build()
                ).queue();
    }
}
