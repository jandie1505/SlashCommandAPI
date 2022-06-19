package net.jandie1505.slashcommandapi;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.jandie1505.slashcommandapi.command.SlashCommand;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SlashCommandHandler implements EventListener {
    private final Map<String, SlashCommand> slashCommands;

    public SlashCommandHandler() {
        this.slashCommands = new HashMap<>();
    }

    @Override
    public void onEvent(@NotNull GenericEvent e) {
        if(e instanceof SlashCommandInteractionEvent) {
            SlashCommandInteractionEvent event = (SlashCommandInteractionEvent) e;

            for(String cmd : this.slashCommands.keySet()) {
                if(cmd.equalsIgnoreCase(event.getName())) {
                    this.slashCommands.get(cmd).onSlashCommand(event.getInteraction());
                    break;
                }
            }
        }
    }

    /**
     * Register a slash command
     * @param command command name
     * @param slashCommand command object
     */
    public void registerSlashCommand(String command, SlashCommand slashCommand) {
        this.slashCommands.put(command, slashCommand);
    }

    /**
     * Removes a slash command
     * @param command command name
     */
    public void unregisterCommand(String command) {
        this.slashCommands.remove(command);
    }

    /**
     * Get a map of the slash commands
     * @return Map of command names and command objects
     */
    public Map<String, SlashCommand> getRegisteredCommands() {
        return Map.copyOf(this.slashCommands);
    }
}
