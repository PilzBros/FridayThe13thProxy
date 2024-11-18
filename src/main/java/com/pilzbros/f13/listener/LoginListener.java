package com.pilzbros.f13.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.event.connection.PreLoginEvent;
import com.velocitypowered.api.network.ProtocolVersion;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.slf4j.Logger;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class LoginListener {

    private final static Set<ProtocolVersion> ALLOWED_VERSIONS = Set.of(ProtocolVersion.MINECRAFT_1_12_2,ProtocolVersion.MINECRAFT_1_13);

    private final Logger logger;

    public LoginListener(Logger logger) {
        this.logger = logger;
    }

    private final Component INVALID_DAY_COMPONENT = MiniMessage.miniMessage().deserialize(
            "<b><gradient:red:dark_red>╼ Friday the 13th Minecraft ╾</gradient></b>\n" +
                    "\n" +
                    "<dark_red><bold>OFFLINE</bold></dark_red>\n" +
                    "\n" +
                    "The Friday the 13th server is only live on Fridays that are the 13th of the month. Please re-join on a Friday the 13th to play with others and kill for mommy.\n" +
                    "\n" +
                    "In the meantime, join the <bold><rainbow><hover:show_text:'Click to visit the PilzBros Network website'><click:open_url:'https://pilzbros.com'>PilzBros Network</click></hover></rainbow></bold> (pilzbros.com) for always-live SMP. Join our <bold><blue><hover:show_text:'Click to join the F13 Discord Server'><click:open_url:'https://discord.com/invite/tBpkWTaSNk'>Discord Server</click></hover></blue></bold> (https://discord.com/invite/tBpkWTaSNk) to hang out and following along with F13 development." +
                    "\n\n\n" +
                    "Visit us @ f13mc.com"
    );

    private final Component INVALID_CLIENT_VERSION_COMPONENT = MiniMessage.miniMessage().deserialize("<b><gradient:red:dark_red>╼ Friday the 13th Minecraft ╾</gradient></b>\n" +
            "\n" +
            "<dark_red><bold>INVALID CLIENT VERSION</bold></dark_red>\n" +
            "\n" +
            "F13 requires Minecraft version <bold><yellow>1.12.2</yellow></bold> in order to play. Please reconnect to the server from the correct client version. We look forward to playing with you!");

    /**
     * Disconnect the user if they're attempting to connect with the wrong client version.
     */
    @Subscribe
    public void handleInvalidClientVersion(PreLoginEvent event) {

        if (!ALLOWED_VERSIONS.contains(event.getConnection().getProtocolVersion())) {
            // The player attempting to connect is not on 1.12.2, which is required.
            event.setResult(PreLoginEvent.PreLoginComponentResult.denied(INVALID_CLIENT_VERSION_COMPONENT));
            logger.error("Player attempted to connect with version {}", event.getConnection().getProtocolVersion().toString());
        }
    }

    /**
     * Disconnect the user if it's not a Friday the 13th.
     */
    @Subscribe
    public void handlePlayerLogin(LoginEvent event) {
        if (!(LocalDate.now().getDayOfMonth() == 13 && LocalDate.now().getDayOfWeek().equals(DayOfWeek.FRIDAY))) {
            // It's not a Friday the 13th, kick the player when they attempt to connect.
            event.getPlayer().disconnect(INVALID_DAY_COMPONENT);
        }
    }
}
