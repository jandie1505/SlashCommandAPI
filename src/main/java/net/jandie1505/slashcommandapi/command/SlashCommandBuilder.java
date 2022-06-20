package net.jandie1505.slashcommandapi.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandExecutor;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandPermissionRequest;
import net.jandie1505.slashcommandapi.subcommand.SlashCommandSubcommand;

import java.util.HashMap;
import java.util.Map;

public class SlashCommandBuilder {

    private SlashCommandExecutor executor;
    private SlashCommandExecutor noPermissionExecutor;
    private SlashCommandExecutor missingOptionsExecutor;
    private final Map<String, SlashCommandSubcommand> subcommands;
    private SlashCommandPermissionRequest permissionRequest;
    private boolean requireGuild;
    private final Map<String, OptionType> requiredOptions;

    public SlashCommandBuilder() {
        this.executor = null;
        this.noPermissionExecutor = null;
        this.missingOptionsExecutor = null;
        this.subcommands = new HashMap<>();
        this.permissionRequest = null;
        this.requireGuild = false;
        this.requiredOptions = new HashMap<>();
    }

    public SlashCommandBuilder executes(SlashCommandExecutor executor) {
        this.executor = executor;
        return this;
    }

    public SlashCommandBuilder executesNoPermission(SlashCommandExecutor executor) {
        this.noPermissionExecutor = executor;
        return this;
    }

    public SlashCommandBuilder executesMissingOptions(SlashCommandExecutor executor) {
        this.missingOptionsExecutor = executor;
        return this;
    }

    public SlashCommandBuilder withSubcommand(String command, SlashCommandSubcommand subcommand) {
        this.subcommands.put(command, subcommand);
        return this;
    }

    public SlashCommandBuilder withPermissionRequest(SlashCommandPermissionRequest permissionRequest) {
        this.permissionRequest = permissionRequest;
        return this;
    }

    public SlashCommandBuilder requireGuild(boolean requireGuild) {
        this.requireGuild = requireGuild;
        return this;
    }

    public SlashCommandBuilder requireOption(String name, OptionType optionType) {
        this.requiredOptions.put(name, optionType);
        return this;
    }

    public SlashCommand build() {
        return new SlashCommand(this.executor, this.noPermissionExecutor, this.missingOptionsExecutor, this.subcommands, this.permissionRequest, this.requireGuild, this.requiredOptions);
    }
}
