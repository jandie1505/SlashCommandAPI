package net.jandie1505.slashcommandapi.subcommand;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.jandie1505.slashcommandapi.base.SlashCommandBase;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandExecutor;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandPermissionRequest;

public class SlashCommandSubcommand extends SlashCommandBase {

    public SlashCommandSubcommand(SlashCommandExecutor executor, SlashCommandExecutor noPermissionExecutor, SlashCommandPermissionRequest permissionRequest, boolean requireGuild) {
        super(executor, noPermissionExecutor, permissionRequest, requireGuild);
    }

    @Override
    public void onSlashCommand(SlashCommandInteraction interaction) {
        if(!this.requireGuild || interaction.getGuild() != null) {

            if(this.getPermissionRequest().hasPermission(interaction)) {
                this.getExecutor().onSlashCommand(interaction);
            } else {
                this.getNoPermissionExecutor().onSlashCommand(interaction);
            }

        }
    }
}
