package net.jandie1505.slashcommandapi.subcommand;

import net.jandie1505.slashcommandapi.command.SlashCommandBuilder;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandExecutor;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandPermissionRequest;

public class SlashCommandSubcommandBuilder {

    private final SlashCommandBuilder builder;
    private SlashCommandExecutor executor;
    private SlashCommandExecutor noPermissionExecutor;
    private SlashCommandPermissionRequest permissionRequest;
    private boolean requireGuild;

    public SlashCommandSubcommandBuilder(SlashCommandBuilder builder) {
        this.builder = builder;

        this.executor = null;
        this.noPermissionExecutor = null;
        this.permissionRequest = null;
        this.requireGuild = false;
    }

    public SlashCommandSubcommandBuilder executes(SlashCommandExecutor executor) {
        this.executor = executor;
        return this;
    }

    public SlashCommandSubcommandBuilder executesNoPermission(SlashCommandExecutor executor) {
        this.noPermissionExecutor = executor;
        return this;
    }

    public SlashCommandSubcommandBuilder withPermissionRequest(SlashCommandPermissionRequest permissionRequest) {
        this.permissionRequest = permissionRequest;
        return this;
    }

    public SlashCommandSubcommandBuilder requireGuild(boolean requireGuild) {
        this.requireGuild = requireGuild;
        return this;
    }

    public SlashCommandSubcommand build() {
        return new SlashCommandSubcommand(this.executor, this.noPermissionExecutor, this.permissionRequest, this.requireGuild);
    }
}
