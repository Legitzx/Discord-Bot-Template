package org.legitzxdevelopment.discordbot.modules.example;

import org.legitzxdevelopment.discordbot.commands.CommandContext;
import org.legitzxdevelopment.discordbot.commands.ICommand;

import java.util.Arrays;
import java.util.List;

/*
Made by: Luciano K
Legitzx Development © 2020
 */

public class SomeCommand implements ICommand {
    @Override
    public void handle(CommandContext event) {
        event.getChannel().sendMessage("Hello!").queue();
    }

    @Override
    public List<String> getName() {
        return Arrays.asList("example");
    }

    @Override
    public String getUsage() {
        return "example [@user] [reason]";
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
