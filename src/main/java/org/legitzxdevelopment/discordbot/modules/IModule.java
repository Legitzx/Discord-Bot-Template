package org.legitzxdevelopment.discordbot.modules;


import org.legitzxdevelopment.discordbot.commands.ICommand;

import java.util.ArrayList;
import java.util.List;

/*
Made by: Luciano K
Legitzx Development © 2020
 */

public interface IModule {
    List<ICommand> commands = new ArrayList<>();

    String getName();

    boolean isActive();  // True = Enabled, False = Disabled

    List<ICommand> getCommands(); // Gets the commands that are registered with this module
}
