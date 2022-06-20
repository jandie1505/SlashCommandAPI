package net.jandie1505.slashcommandapi.command;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.jandie1505.slashcommandapi.base.SlashCommandBase;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandExecutor;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandPermissionRequest;
import net.jandie1505.slashcommandapi.subcommand.SlashCommandSubcommand;

import java.util.HashMap;
import java.util.Map;

public class SlashCommand extends SlashCommandBase {

    private final Map<String, SlashCommandSubcommand> subcommands;

    public SlashCommand(SlashCommandExecutor executor, SlashCommandExecutor noPermissionExecutor, SlashCommandExecutor missingOptionsExecutor, Map<String, SlashCommandSubcommand> subcommands, SlashCommandPermissionRequest permissionRequest, boolean requireGuild, Map<String, OptionType> requiredOptions) {
        super(executor, noPermissionExecutor, missingOptionsExecutor, permissionRequest, requireGuild, requiredOptions);

        this.subcommands = new HashMap<>(subcommands);
    }

    @Override
    public void onSlashCommand(SlashCommandInteraction interaction) {
        // Check for requireGuild
        if(!this.isRequireGuild() || interaction.getGuild() != null) {

            // Check if user has the required permission
            if(this.getPermissionRequest().hasPermission(interaction)) {

                // Check for required options
                if(this.optionsCheck(interaction)) {

                    // Check for subcommand
                    if(interaction.getSubcommandName() != null) {

                        for(String subcommand : this.subcommands.keySet()) {
                            if(subcommand.equalsIgnoreCase(interaction.getSubcommandName())) {
                                this.subcommands.get(subcommand).onSlashCommand(interaction);
                                break;
                            }
                        }

                    } else {
                        this.getExecutor().onSlashCommand(interaction);
                    }

                } else {
                    this.getMissingOptionsExecutor().onSlashCommand(interaction);
                }

            } else {
                this.getNoPermissionExecutor().onSlashCommand(interaction);
            }

        }
    }

    public Map<String, SlashCommandSubcommand> getSubcommandExecutors() {
        return Map.copyOf(this.subcommands);
    }
}
