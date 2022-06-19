package net.jandie1505.slashcommandapi.subcommand;

import net.dv8tion.jda.api.Permission;
import net.jandie1505.slashcommandapi.command.SlashCommandBuilder;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandExecutor;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandSubcommandBuilder {

    private SlashCommandBuilder builder;
    private SlashCommandExecutor executor;
    private final List<List<Permission>> permissions;

    public SlashCommandSubcommandBuilder() {
        this.executor = null;
        this.permissions = new ArrayList<>();
    }

    public SlashCommandSubcommandBuilder executes(SlashCommandExecutor executor) {
        this.executor = executor;
    }

    public SlashCommandBuilder build() {

    }
}
