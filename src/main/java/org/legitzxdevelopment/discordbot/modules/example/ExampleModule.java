package org.legitzxdevelopment.discordbot.modules.example;

import org.legitzxdevelopment.discordbot.Bot;
import org.legitzxdevelopment.discordbot.commands.ICommand;
import org.legitzxdevelopment.discordbot.modules.IModule;
import org.legitzxdevelopment.discordbot.modules.ModuleManager;

import java.util.List;

/*
Made by: Luciano K
Legitzx Development © 2020
 */

public class ExampleModule implements IModule {
    public ExampleModule(Bot bot, ModuleManager moduleManager) {
        moduleManager.addCommand(this, new SomeCommand());
    }

    @Override
    public String getName() {
        return "Example Module";
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public List<ICommand> getCommands() {
        return commands;
    }
}
