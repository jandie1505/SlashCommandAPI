package net.jandie1505.slashcommandapi.command;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandExecutor;

import java.util.HashMap;
import java.util.Map;

public class SlashCommand {

    private SlashCommandExecutor basicCommandExecutor;
    private Map<String, SlashCommandExecutor> subcommandExecutors;

    public SlashCommand(SlashCommandExecutor basicCommandExecutor, Map<String, SlashCommandExecutor> subcommandExecutors) {
        this.basicCommandExecutor = basicCommandExecutor;
        this.subcommandExecutors = new HashMap<>(subcommandExecutors);
    }

    public void onCommand(SlashCommandInteraction interaction) {
        if(interaction.getSubcommandName() != null) {
            for(String sc : this.subcommandExecutors.keySet()) {
                if(sc.equalsIgnoreCase(interaction.getSubcommandName())) {
                    subcommandExecutors.get(sc).onSlashCommand();
                }
            }
        } else {
            basicCommandExecutor.onSlashCommand();
        }
    }
}
