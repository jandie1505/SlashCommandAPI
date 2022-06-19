package net.jandie1505.slashcommandapi;

import net.dv8tion.jda.api.Permission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

    public SlashCommandSubcommandBuilder addPermissions(Permission... permissions) {
        this.permissions.add(new ArrayList<>(Arrays.asList(permissions)));
        return this;
    }

    public SlashCommandSubcommandBuilder addPermissions(List<Permission> permissions) {
        this.permissions.add(new ArrayList<>(permissions));
        return this;
    }

    public SlashCommandBuilder build() {

    }
}
