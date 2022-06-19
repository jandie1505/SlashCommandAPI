package net.jandie1505.slashcommandapi.command;

import net.dv8tion.jda.api.Permission;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandExecutor;

import java.util.HashMap;
import java.util.Map;

public class SlashCommandBuilder {

    private SlashCommandExecutor basicExecutor;
    private Permission globalPermission;
    private final Map<String, SlashCommandExecutor> subcommandExecutors;

    public SlashCommandBuilder() {
        this.basicExecutor = null;
        this.globalPermission = null;
        this.subcommandExecutors = new HashMap<>();
    }

    public SlashCommandBuilder executesDefault(SlashCommandExecutor executor) {
        this.basicExecutor = executor;
        return this;
    }

    public SlashCommandBuilder withPermission(Permission permission) {
        this.globalPermission = permission;
        return this;
    }


}
