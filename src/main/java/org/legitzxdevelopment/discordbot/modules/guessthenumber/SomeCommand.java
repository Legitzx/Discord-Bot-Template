package org.legitzxdevelopment.discordbot.modules.guessthenumber;

import org.legitzxdevelopment.discordbot.commands.CommandContext;
import org.legitzxdevelopment.discordbot.commands.ICommand;

/*
Made by: LEGITZX
Legitzx Development © 2020
 */

public class SomeCommand implements ICommand {
    @Override
    public void handle(CommandContext event) {
        event.getChannel().sendMessage("Hello!").queue();
    }

    @Override
    public String getName() {
        return "example";
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
