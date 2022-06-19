package net.jandie1505.slashcommandapi.subcommand;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.jandie1505.slashcommandapi.base.SlashCommandBase;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandExecutor;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandPermissionRequest;

import java.util.List;
import java.util.Objects;

public class SlashCommandSubcommand extends SlashCommandBase {

    public SlashCommandSubcommand(SlashCommandExecutor executor, SlashCommandExecutor noPermissionExecutor, SlashCommandPermissionRequest permissionRequest, boolean requireGuild) {
        super(executor, noPermissionExecutor, permissionRequest, requireGuild);
    }

    @Override
    public void onSlashCommand(SlashCommandInteraction interaction) {

        if(this.requireGuild && interaction.getGuild() != null) {

            if(this.permissionRequest.hasPermission(interaction)) {
                this.executor.onSlashCommand(interaction);
            } else {
                this.noPermissionExecutor.onSlashCommand(interaction);
            }

        }

    }
}
