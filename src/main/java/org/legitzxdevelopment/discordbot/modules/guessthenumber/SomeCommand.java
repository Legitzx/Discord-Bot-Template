package org.legitzxdevelopment.discordbot.modules.guessthenumber;

import org.legitzxdevelopment.discordbot.commands.CommandContext;
import org.legitzxdevelopment.discordbot.commands.ICommand;

public class SomeCommand implements ICommand {
    @Override
    public void handle(CommandContext event) {
        event.getChannel().sendMessage("Hello!").queue();
    }

    @Override
    public String getName() {
        return "example";
    }
}
