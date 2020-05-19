package org.legitzxdevelopment.discordbot.commands;

/*
Made by: LEGITZX
Legitzx Development © 2020
 */

public interface ICommand {
    void handle(CommandContext event);

    String getName();

    boolean isActive(); // True = Enabled, False = Disabled
}
