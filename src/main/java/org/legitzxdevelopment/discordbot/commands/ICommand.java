package org.legitzxdevelopment.discordbot.commands;

public interface ICommand {
    void handle(CommandContext event);

    String getName();
}
