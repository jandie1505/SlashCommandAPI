package net.jandie1505.slashcommandapi.base;

import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandExecutor;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandPermissionRequest;

import java.util.*;

public abstract class SlashCommandBase {

    protected final SlashCommandExecutor executor;
    protected final SlashCommandExecutor noPermissionExecutor;
    protected final SlashCommandExecutor missingOptionsExecutor;
    protected final SlashCommandPermissionRequest permissionRequest;
    protected final boolean requireGuild;
    protected final Map<String, OptionType> requiredOptions;

    public SlashCommandBase(SlashCommandExecutor executor, SlashCommandExecutor noPermissionExecutor, SlashCommandExecutor missingOptionsExecutor, SlashCommandPermissionRequest permissionRequest, boolean requireGuild, Map<String, OptionType> requiredOptions) {
        this.executor = Objects.requireNonNullElseGet(executor, () -> interaction -> {});
        this.noPermissionExecutor = Objects.requireNonNullElseGet(noPermissionExecutor, () -> interaction -> {});
        this.missingOptionsExecutor = Objects.requireNonNullElseGet(missingOptionsExecutor, () -> interaction -> {});
        this.permissionRequest = Objects.requireNonNullElseGet(permissionRequest, () -> interaction -> false);
        this.requireGuild = requireGuild;
        this.requiredOptions = new HashMap<>(requiredOptions);
    }

    public abstract void onSlashCommand(SlashCommandInteraction interaction);

    protected boolean optionsCheck(SlashCommandInteraction interaction) {
        for(String option : this.requiredOptions.keySet()) {
            OptionMapping optionMapping = interaction.getOption(option);

            if(optionMapping == null) {
                return false;
            }

            if(optionMapping.getType() != this.requiredOptions.get(option)) {
                return false;
            }
        }

        return true;
    }

    public SlashCommandExecutor getExecutor() {
        return this.executor;
    }

    public SlashCommandExecutor getNoPermissionExecutor() {
        return this.noPermissionExecutor;
    }

    public SlashCommandExecutor getMissingOptionsExecutor() {
        return this.missingOptionsExecutor;
    }

    public SlashCommandPermissionRequest getPermissionRequest() {
        return this.permissionRequest;
    }

    public boolean isRequireGuild() {
        return this.requireGuild;
    }

    public Map<String, OptionType> getRequiredOptions() {
        return Map.copyOf(this.requiredOptions);
    }
}
