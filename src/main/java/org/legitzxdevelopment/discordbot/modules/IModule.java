package org.legitzxdevelopment.discordbot.modules;


import org.legitzxdevelopment.discordbot.commands.ICommand;

import java.util.ArrayList;
import java.util.List;

public interface IModule {
    List<ICommand> commands = new ArrayList<>();

    String getName();

    List<ICommand> getCommands();
}
