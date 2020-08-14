package org.legitzxdevelopment.discordbot.utils;

import io.github.cdimascio.dotenv.Dotenv;

/*
Made by: Luciano K
Legitzx Development © 2020
 */

public class Config {
    // Loads Config
    private static final Dotenv dotenv = Dotenv.load();

    // Method to get config settings from .env
    public static String get(String key) {return dotenv.get(key.toUpperCase());}
}
