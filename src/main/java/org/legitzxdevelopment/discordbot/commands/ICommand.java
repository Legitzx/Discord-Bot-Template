package org.legitzxdevelopment.discordbot.commands;

/*
Made by: LEGITZX
Legitzx Development © 2020
 */

public interface ICommand {
    void handle(CommandContext event);

    String getName();

    String getUsage(); // Used when building help embed

    boolean isActive(); // True = Enabled, False = Disabled
}
