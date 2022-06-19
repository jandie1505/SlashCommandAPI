package net.jandie1505.slashcommandapi.base;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandExecutor;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandPermissionRequest;

import java.util.Objects;

public abstract class SlashCommandBase {

    protected final SlashCommandExecutor executor;
    protected final SlashCommandExecutor noPermissionExecutor;
    protected final SlashCommandPermissionRequest permissionRequest;
    protected final boolean requireGuild;

    public SlashCommandBase(SlashCommandExecutor executor, SlashCommandExecutor noPermissionExecutor, SlashCommandPermissionRequest permissionRequest, boolean requireGuild) {
        this.executor = Objects.requireNonNullElseGet(executor, () -> interaction -> {});
        this.noPermissionExecutor = Objects.requireNonNullElseGet(noPermissionExecutor, () -> interaction -> {});
        this.permissionRequest = Objects.requireNonNullElseGet(permissionRequest, () -> interaction -> false);
        this.requireGuild = requireGuild;
    }

    public abstract void onSlashCommand(SlashCommandInteraction interaction);

    public SlashCommandExecutor getExecutor() {
        return this.executor;
    }

    public SlashCommandExecutor getNoPermissionExecutor() {
        return this.noPermissionExecutor;
    }

    public SlashCommandPermissionRequest getPermissionRequest() {
        return this.permissionRequest;
    }

    public boolean isRequireGuild() {
        return this.requireGuild;
    }
}
