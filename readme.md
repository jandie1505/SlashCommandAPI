# SlashCommandAPI for JDA

A simple library for creating slash commands for JDA  
![](https://img.shields.io/badge/Java-11-blue)
[![](https://jitpack.io/v/jandie1505/SlashCommandAPI.svg)](https://jitpack.io/#jandie1505/SlashCommandAPI)
[![CodeFactor](https://www.codefactor.io/repository/github/jandie1505/slashcommandapi/badge)](https://www.codefactor.io/repository/github/jandie1505/slashcommandapi)

## Import
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

```xml
<dependency>
    <groupId>com.github.jandie1505</groupId>
    <artifactId>SlashCommandAPI</artifactId>
    <version>1.0</version>
</dependency>
```

You also can import the project via Github Packages or via the JAR file.
    
Please note that JDA is not included and you need to import it into your project to use SlashCommandAPI.

## Example

```java
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;
import net.jandie1505.slashcommandapi.SlashCommandHandler;
import net.jandie1505.slashcommandapi.command.SlashCommandBuilder;
import net.jandie1505.slashcommandapi.utilities.DefaultPermissionRequests;

public class ExampleBot() {
    public static void main(String[] args) {
        // (1) Create JDA
        JDA jda = JDABuilder.createDefault("token");

        // (2) Create SlashCommandHandler
        SlashCommandHandler slashCommandHandler = new SlashCommandHandler();

        // (3) Register SlashCommandHandler as jda event listener
        jda.addEventListener(slashCommandHandler);

        // (4) Upsert slash command
        jda.upsertCommand(new CommandDataImpl("testcmd", "description"));

        // (5) Add slash command to SlashCommandHandler
        slashCommandHandler.registerSlashCommand(
                "testcommand",
                new SlashCommandBuilder()
                        .executes(() -> {
                            // What your command does
                        })
                        // Set permissions
                        .withPermissionRequest(DefaultPermissionRequests.publicCommand())
                        // Build command
                        .build()
        );
    }
}
```

## Usage

SlashCommandAPI consists of ``SlashCommandHandler``, ``SlashCommand`` with its ``SlashCommandBuilder``, ``SlashCommandSubcommand`` with its ``SlashCommandSubcommandBuilder``, and the interfaces ``SlashCommandExecutor`` and ``SlashCommandPermissionRequest``.  

### SlashCommandHandler
SlashCommandHandler is responsible for receiving the slash command events and executing the correct command.
    
The SlashCommandHandler is registered as a JDA event listener (Example: 3).
    
To register SlashCommands to the SlashCommandHandler, ``SlashCommandHandler#registerSlashCommand(String commandName, SlashCommand slashCommand)`` can be used.  
The `commandName` is the command name the SlashCommandInteraction will return with ``SlashCommandInteraction#getName()``.  
For the ``slashCommand``, see in section SlashCommand.  
You can also unregister SlashCommands with SlashCommandHandler#unregisterCommand(String commandName) and get the SlashCommands which are currently registered with SlashCommandHandler#getRegisteredCommands().
  
### SlashCommand and SlashCommandBuilder
The SlashCommand is one single SlashCommand. It can be registered to the SlashCommandHandler.  
To create a SlashCommand, you can manually use the constructor (not recommended) or use the SlashCommandBuilder (recommended).
    
With the SlashCommandBuilder you can create a SlashCommand easily (Example: 5).

```java
public class ExampleClass {
    public void exampleMethod() {
        SlashCommand slashCommand = new SlashCommandBuilder()
                .executes(new SlashCommandExecutor() {
                    @Override
                    public void onSlashCommand(SlashCommandInteraction interaction) {
                        /*
                                This method will be executed if a user runs the slash command and the PermissionRequest returns true.
                         */
                    }
                })
                .executesNoPermission(new SlashCommandExecutor() {
                    @Override
                    public void onSlashCommand(SlashCommandInteraction interaction) {
                        /*
                                This method will be executed if a user runs the slash command and the PermissionRequest returns false.
                         */
                    }
                })
                .withPermissionRequest(new SlashCommandPermissionRequest() {
                    @Override
                    public boolean hasPermission(SlashCommandInteraction interaction) {
                        /*
                                Here you can do your permission checks.
                                If this method returns true, the default CommandExecutor gets executed.
                                If this method returns false, the no permission CommandExecutor gets executed.
                                If you don't specify a PermissionRequest, every slash command execution will be handled as the user don't have the permission.
                         */
                        return false;
                    }
                })
                .requireGuild(false) // If this option enabled, the command only gets executed if it gets executed from a guild. Else, nothing will happen.
                .withSubcommand("exampleSubcommand", /*SubcommandData*/)
                .build(); // Build and return the SlashCommand
    }
}
```

## SlashCommandSubcommand and SlashCommandSubcommandBuilder
The SlashCommandSubcommand works like the SlashCommand with the difference that it is added to a SlashCommand and is not standalone command.  
The SlashCommandSubcommand has also a SlashCommandSubcommandBuilder.

```java
public class exampleClass {
    public void exampleMethod() {
        SlashCommandSubcommand subcommand = new SlashCommandSubcommandBuilder()
                .executes(new SlashCommandExecutor() {
                    @Override
                    public void onSlashCommand(SlashCommandInteraction interaction) {
                        // Same like SlashCommand
                    }
                })
                .executesNoPermission(new SlashCommandExecutor() {
                    @Override
                    public void onSlashCommand(SlashCommandInteraction interaction) {
                        // Same like SlashCommand
                    }
                })
                .withPermissionRequest(new SlashCommandPermissionRequest() {
                    @Override
                    public boolean hasPermission(SlashCommandInteraction interaction) {
                        // Same like SlashCommand
                        return false;
                    }
                })
                .requireGuild(false) // Same like SlashCommand
                .build(); // Build and return SlashCommandSubcommand
    }
}
```
Please note that executing a subcommand requires the permission of the SlashCommandSubcommand AND the permission of the SlashCommand the subcommand is added to.
