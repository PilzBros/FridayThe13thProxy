package com.pilzbros.f13;

import com.google.inject.Inject;
import com.pilzbros.f13.listener.LoginListener;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

/**
 * This is the proxy for PilzBros' Friday the 13th Minecraft. As the server has not been relevant for some time, it is not
 * often visited and incurs cost to keep running perpetually. However, there is a desire on actual Friday the 13ths to play.
 *
 * If it is **not** a Friday the 13th, players will not be allowed to connect to the proxy as the underlying server is
 * likely offline. This allows the proxy itself to remain online (as it requires little to no resources) and not give off
 * the impression that the server is offline forever.
 *
 * In the future, I'd also like to have this be responsible for booting the server up / shut down when it detects a Friday
 * the 13th is today. It could use the Pterodactyl API to handle the interactions.
 */
@Plugin(id = "fridaythe13thproxy", name = "F13Proxy", version = "1.0-SNAPSHOT", url = "https://pilzbros.com", description = "Friday the 13th Proxy")
public class FridayThe13thProxy {

    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public FridayThe13thProxy(ProxyServer proxyServer, Logger logger) {
        this.server = proxyServer;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getEventManager().register(this, new LoginListener(logger));
    }
}
