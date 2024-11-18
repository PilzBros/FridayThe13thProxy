# F13 Proxy
This is a [Velocity Proxy](https://papermc.io/software/velocity) plugin for the PilzBros Network [Friday the 13th](https://f13mc.com) 
server. The F13 plugin is an original plugin to the PilzBros Network and is a Minecraft port of the popular PC and console
Friday the 13th game.

The F13 MC server (IP: f13mc.com) is still hosted today. To save on sever costs, it is only running on Fridays that are
the 13th day of the month. Additionally, it only supports Minecraft 1.12.2. 

This plugin runs on the always-on velocity proxy for F13. The plugin performs the following:
- When a player joins, if it's not a Friday the 13th, they're prevented from connecting and returned a message explaining when they may re-join.
- When a player joins, if they are not on Minecraft client 1.12.2, they are prevented from connecting and returned a message explaining how to correc the issue.

The main reason for proxifying this behavior is to allow the proxy to remain always-on and accept incoming player connections,
providing information on when they're able to play. Additionally, having the proxy always-on does not trigger Minecraft server
lists (most of them, at least) from considering the server offline and lowering their uptime ratio.

## Helpful Links
- [Velocity](https://papermc.io/software/velocity)
- [PilzBros Network](https://pilzbros.com)
- [Friday the 13th Minecraft](https://f13mc.com)