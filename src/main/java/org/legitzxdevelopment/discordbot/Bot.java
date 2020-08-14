package org.legitzxdevelopment.discordbot;

import net.dv8tion.jda.api.JDABuilder;
import org.legitzxdevelopment.discordbot.commands.CommandDispatcher;
import org.legitzxdevelopment.discordbot.utils.Config;

import javax.security.auth.login.LoginException;

/*
Made by: Luciano K
Legitzx Development © 2020
 */

public class Bot {

    public Bot() throws LoginException {
        new JDABuilder()
                .setToken(Config.get("token"))
                .addEventListeners(new CommandDispatcher(this))
                .build();
    }

    public static void main(String[] args) throws LoginException {
        new Bot();
    }
}
