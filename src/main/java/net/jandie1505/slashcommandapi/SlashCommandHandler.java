package net.jandie1505.slashcommandapi;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class SlashCommandHandler implements EventListener {
    private Map<String, SlashCommand> slashCommands;

    @Override
    public void onEvent(@NotNull GenericEvent e) {
        if(e instanceof SlashCommandInteractionEvent) {
            SlashCommandInteractionEvent event = (SlashCommandInteractionEvent) e;

            for(String cmd : this.slashCommands.keySet()) {
                if(cmd.equalsIgnoreCase(event.getName())) {
                    this.slashCommands.get(cmd).onCommand(event.getInteraction());
                    break;
                }
            }
        }
    }
}
