package net.jandie1505.slashcommandapi.command;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.jandie1505.slashcommandapi.base.SlashCommandBase;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandExecutor;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandPermissionRequest;
import net.jandie1505.slashcommandapi.subcommand.SlashCommandSubcommand;

import java.util.HashMap;
import java.util.Map;

public class SlashCommand extends SlashCommandBase {

    private final Map<String, SlashCommandSubcommand> subcommandExecutors;

    public SlashCommand(SlashCommandExecutor executor, SlashCommandExecutor noPermissionExecutor, Map<String, SlashCommandSubcommand> subcommandExecutors, SlashCommandPermissionRequest permissionRequest, boolean requireGuild) {
        super(executor, noPermissionExecutor, permissionRequest, requireGuild);

        this.subcommandExecutors = new HashMap<>(subcommandExecutors);
    }

    @Override
    public void onSlashCommand(SlashCommandInteraction interaction) {

    }

    public Map<String, SlashCommandSubcommand> getSubcommandExecutors() {
        return Map.copyOf(this.subcommandExecutors);
    }
}
