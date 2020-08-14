package org.legitzxdevelopment.discordbot.commands;

import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.legitzxdevelopment.discordbot.Bot;
import org.legitzxdevelopment.discordbot.modules.IModule;
import org.legitzxdevelopment.discordbot.modules.ModuleManager;
import org.legitzxdevelopment.discordbot.utils.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.awt.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/*
Made by: Luciano K
Legitzx Development © 2020
 */

public class CommandDispatcher extends ListenerAdapter {
    // Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandDispatcher.class);

    // Module Manager
    private ModuleManager moduleManager;

    public CommandDispatcher(Bot bot) {
        moduleManager = new ModuleManager(bot);
    }

    /**
     * Description: Displays the status of the bot/status of each module
     * @param event
     */
    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        LOGGER.info("{} is ready", event.getJDA().getSelfUser().getAsTag());

        // Displays the status of each module/command
        LOGGER.info("-------------------------------------");
        for(IModule module : moduleManager.getModules()) {
            LOGGER.info(module.getName() + " : " + module.isActive());
            for(ICommand cmd : module.getCommands()) {
                LOGGER.info("   " + cmd.getName() + " : " + cmd.isActive());
            }
        }
        LOGGER.info("-------------------------------------");
    }

    // Main Message Event
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        User user = event.getAuthor();

        if(user.isBot() || event.isWebhookMessage()) {
            return;
        }

        String prefix = Config.get("prefix");
        String raw = event.getMessage().getContentRaw();

        // Auto Generated Help Command
        if(raw.equalsIgnoreCase(prefix + "help")) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Help");

            for(IModule module : moduleManager.getModules()) {
                StringBuilder stringBuilder = new StringBuilder();
                for(ICommand cmd : module.getCommands()) {
                    if(cmd.isActive()) {
                        stringBuilder.append(prefix + cmd.getUsage() + "\n");
                    }
                }

                builder.addField(module.getName(), stringBuilder.toString(), true);
            }

            builder.setColor(Color.CYAN);

            event.getChannel().sendMessage(builder.build()).queue();
        }

        // Shutdown command - Shuts down the bot
        if(raw.equalsIgnoreCase(prefix + "shutdown")
                && user.getId().equals(Config.get("owner_id"))) {
            //executor.shutdown();
            LOGGER.info("Shutting down");
            event.getJDA().shutdown();
            BotCommons.shutdown(event.getJDA());

            return;
        }

        // Info command - Displays Memory info
        if(raw.equalsIgnoreCase(prefix + "info")
                && user.getId().equals(Config.get("owner_id"))) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Developer Stats");

            int mb = 1024 * 1024;

            Runtime instance = Runtime.getRuntime();
            builder.addField("Total Memory: ", "" + (instance.totalMemory() / mb), true);
            builder.addField("Free Memory: ", "" + (instance.freeMemory() / mb), true);
            builder.addField("Used Memory: ", "" + ((instance.totalMemory() - instance.freeMemory()) / mb), true);
            builder.addField("Max Memory: ", "" + (instance.maxMemory() / mb), true);

            RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
            long uptime = runtimeMXBean.getUptime();
            long uptimeInSeconds = uptime / 1000;
            long numberOfHours = uptimeInSeconds / (60 * 60);
            long numberOfMinutes = (uptimeInSeconds / 60) - (numberOfHours * 60);
            long numberOfSeconds = uptimeInSeconds % 60;

            builder.addField("Uptime", numberOfHours + " hours, " + numberOfMinutes + " minutes, " + numberOfSeconds + " seconds." , true);
            builder.addField("Servers", "" + event.getJDA().getGuilds().size(), true);

            builder.setColor(Color.MAGENTA);
            builder.setFooter("Guess It", event.getJDA().getSelfUser().getAvatarUrl());

            event.getChannel().sendMessage(builder.build()).queue();
            return;
        }

        // Sends out requests to all the modules
        if(raw.startsWith(prefix)) {
            moduleManager.handle(event);
        }
    }
}
