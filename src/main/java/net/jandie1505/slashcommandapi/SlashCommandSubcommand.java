package net.jandie1505.slashcommandapi;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

import java.util.List;
import java.util.Objects;

public class SlashCommandSubcommand {

    private final SlashCommandExecutor executor;
    private final SlashCommandExecutor noPermissionExecutor;
    private final SlashCommandPermissionRequest permissionRequest;
    private final boolean requireGuild;
    private List<List<Permission>> permissions;

    public SlashCommandSubcommand(SlashCommandExecutor slashCommandExecutor, SlashCommandExecutor noPermissionExecutor, SlashCommandPermissionRequest permissionRequest, boolean requireGuild, List<List<Permission>> permissions) {

        this.executor = Objects.requireNonNullElseGet(slashCommandExecutor, () -> interaction -> {});

        this.noPermissionExecutor = Objects.requireNonNullElseGet(noPermissionExecutor, () -> interaction -> {});

        this.permissionRequest = Objects.requireNonNullElseGet(permissionRequest, () -> interaction -> false);

        this.requireGuild = requireGuild;
    }

    public void onCommand(SlashCommandInteraction interaction) {

        if(this.requireGuild && interaction.getGuild() != null) {

            if(this.permissionRequest.hasPermission(interaction)) {
                this.executor.onSlashCommand(interaction);
            } else {
                this.noPermissionExecutor.onSlashCommand(interaction);
            }

        }

    }
}
