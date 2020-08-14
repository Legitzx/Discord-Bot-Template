package org.legitzxdevelopment.discordbot.modules;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.legitzxdevelopment.discordbot.Bot;
import org.legitzxdevelopment.discordbot.commands.CommandContext;
import org.legitzxdevelopment.discordbot.commands.ICommand;
import org.legitzxdevelopment.discordbot.modules.example.ExampleModule;
import org.legitzxdevelopment.discordbot.utils.Config;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/*
Made by: Luciano K
Legitzx Development © 2020
 */

public class ModuleManager {
    // List of Modules
    private final List<IModule> modules = new ArrayList<>();

    // Register modules
    public ModuleManager(Bot bot) {
        addModule(new ExampleModule(bot, this));
    }

    /**
     * Description: Method that allows you to add modules
     * @param module        Module that is being added
     */
    private void addModule(IModule module) {
        boolean nameFound = this.modules.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(module.getName()));

        if (nameFound) {
            throw new IllegalArgumentException("A module with this name is already present");
        }

        modules.add(module);
    }

    /**
     * Description: Method that allows you to add commands to your module
     * @param module        Module that you're adding the command to
     * @param cmd           Command that you're adding
     */
    public void addCommand(IModule module, ICommand cmd) {
        module.getCommands().add(cmd);
    }

    /**
     * Description: Searches the through the different modules for a command.
     * @param search        Command that will be searched for
     * @return              Returns the command if present, else it will return null
     */
    @Nullable
    private ICommand getCommand(String[] search) {
        for(IModule module : modules) {
            if(module.isActive()) {
                for(ICommand cmd : module.getCommands()) {
                    if(cmd.isActive()) {
                        int length = cmd.getName().size();
                        int searchLength = search.length;

                        if(searchLength < length) {
                            continue;
                        }

                        boolean valid = false;
                        for(int i = 0; i < length; i++) {
                            if(cmd.getName().get(i).equalsIgnoreCase(search[i])) {
                                valid = true;
                            } else {
                                valid = false;
                                break;
                            }
                        }

                        if(valid) {
                            return cmd;
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * Description: This will handle command executing for all commands
     * @param event         JDA Event.
     */
    public void handle(GuildMessageReceivedEvent event) {
        String[] split = event.getMessage().getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote(Config.get("prefix")), "")
                .split("\\s+");

        ICommand cmd = this.getCommand(split);

        if (cmd != null) {
            event.getChannel().sendTyping().queue();
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);

            cmd.handle(ctx);
        }
    }

    public List<IModule> getModules() {
        return modules;
    }
}
