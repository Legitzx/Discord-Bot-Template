package org.legitzxdevelopment.discordbot.commands;

/*
Made by: Luciano K
Legitzx Development © 2020
 */

import java.util.List;

public interface ICommand {
    void handle(CommandContext event);

    List<String> getName();

    String getUsage(); // Used when building help embed

    boolean isActive(); // True = Enabled, False = Disabled
}
