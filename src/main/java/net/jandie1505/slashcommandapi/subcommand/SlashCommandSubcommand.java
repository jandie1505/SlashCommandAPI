package net.jandie1505.slashcommandapi.subcommand;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.jandie1505.slashcommandapi.base.SlashCommandBase;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandExecutor;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandPermissionRequest;

import java.util.Map;

public class SlashCommandSubcommand extends SlashCommandBase {

    public SlashCommandSubcommand(SlashCommandExecutor executor, SlashCommandExecutor noPermissionExecutor, SlashCommandExecutor missingOptionsExecutor, SlashCommandPermissionRequest permissionRequest, boolean requireGuild, Map<String, OptionType> requiredOptions) {
        super(executor, noPermissionExecutor, missingOptionsExecutor, permissionRequest, requireGuild, requiredOptions);
    }

    @Override
    public void onSlashCommand(SlashCommandInteraction interaction) {
        // Check for requireGuild
        if(!this.requireGuild || interaction.getGuild() != null) {

            // Check if user has the required permission
            if(this.getPermissionRequest().hasPermission(interaction)) {

                // Check for options
                if(this.optionsCheck(interaction)) {
                    this.getExecutor().onSlashCommand(interaction);
                } else {
                    this.getMissingOptionsExecutor().onSlashCommand(interaction);
                }

            } else {
                this.getNoPermissionExecutor().onSlashCommand(interaction);
            }

        }
    }
}
