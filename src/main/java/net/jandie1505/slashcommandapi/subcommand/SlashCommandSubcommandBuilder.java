package net.jandie1505.slashcommandapi.subcommand;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.jandie1505.slashcommandapi.command.SlashCommandBuilder;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandExecutor;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandPermissionRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlashCommandSubcommandBuilder {

    private SlashCommandExecutor executor;
    private SlashCommandExecutor noPermissionExecutor;
    private SlashCommandExecutor missingOptionsExecutor;
    private SlashCommandPermissionRequest permissionRequest;
    private boolean requireGuild;
    private final Map<String, OptionType> requiredOptions;

    public SlashCommandSubcommandBuilder(SlashCommandBuilder builder) {
        this.executor = null;
        this.noPermissionExecutor = null;
        this.missingOptionsExecutor = null;
        this.permissionRequest = null;
        this.requireGuild = false;
        this.requiredOptions = new HashMap<>();
    }

    public SlashCommandSubcommandBuilder executes(SlashCommandExecutor executor) {
        this.executor = executor;
        return this;
    }

    public SlashCommandSubcommandBuilder executesNoPermission(SlashCommandExecutor executor) {
        this.noPermissionExecutor = executor;
        return this;
    }

    public SlashCommandSubcommandBuilder executesMissingOptions(SlashCommandExecutor executor) {
        this.missingOptionsExecutor = executor;
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

    public SlashCommandSubcommandBuilder requireOption(String name, OptionType optionType) {
        this.requiredOptions.put(name, optionType);
        return this;
    }

    public SlashCommandSubcommand build() {
        return new SlashCommandSubcommand(this.executor, this.noPermissionExecutor, this.missingOptionsExecutor, this.permissionRequest, this.requireGuild, this.requiredOptions);
    }
}
