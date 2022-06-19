package net.jandie1505.slashcommandapi.command;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.jandie1505.slashcommandapi.base.SlashCommandBase;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandExecutor;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandPermissionRequest;
import net.jandie1505.slashcommandapi.subcommand.SlashCommandSubcommand;

import java.util.HashMap;
import java.util.Map;

public class SlashCommand extends SlashCommandBase {

    private final Map<String, SlashCommandSubcommand> subcommands;

    public SlashCommand(SlashCommandExecutor executor, SlashCommandExecutor noPermissionExecutor, Map<String, SlashCommandSubcommand> subcommands, SlashCommandPermissionRequest permissionRequest, boolean requireGuild) {
        super(executor, noPermissionExecutor, permissionRequest, requireGuild);

        this.subcommands = new HashMap<>(subcommands);
    }

    @Override
    public void onSlashCommand(SlashCommandInteraction interaction) {
        if(!this.isRequireGuild() || interaction.getGuild() != null) {

            if(this.getPermissionRequest().hasPermission(interaction)) {

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
                this.getNoPermissionExecutor().onSlashCommand(interaction);
            }

        }
    }

    public Map<String, SlashCommandSubcommand> getSubcommandExecutors() {
        return Map.copyOf(this.subcommands);
    }
}
