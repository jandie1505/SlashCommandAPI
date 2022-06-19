package net.jandie1505.slashcommandapi;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandSubcommand {

    private SlashCommandExecutor executor;
    private SlashCommandExecutor noPermissionExecutor;
    private SlashCommandPermissionRequest permissionRequest;
    private boolean requireGuild;
    private List<List<Permission>> permissions;

    public SlashCommandSubcommand(SlashCommandExecutor slashCommandExecutor, SlashCommandExecutor noPermissionExecutor, SlashCommandPermissionRequest permissionRequest, List<List<Permission>> permissions) {
        this.executor = slashCommandExecutor;
        this.noPermissionExecutor = noPermissionExecutor;

        if(permissionRequest != null) {
            this.permissionRequest = permissionRequest;
        } else {
            this.permissionRequest = interaction -> false;
        }

        this.permissions = new ArrayList<>();

        for(List<Permission> permissions1 : permissions) {
            List<Permission> permissions2 = new ArrayList<>(permissions1);
            this.permissions.add(permissions2);
        }
    }

    public void onCommand(SlashCommandInteraction interaction) {

        if(requireGuild && interaction.getGuild() != null) {

            if(this.permissionRequest.hasPermission(interaction)) {
                this.executor.onSlashCommand(interaction);
            } else {
                this.noPermissionExecutor.onSlashCommand(interaction);
            }

        }

    }
}
