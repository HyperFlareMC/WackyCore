# WackyCore
### Current Plugin Version: devv1.3.2 (UNSTABLE)
### Current Spigot Version: v1.21.4

## WackyCore is a WIP (Work-in-Progress) plugin built using SpigotMC API! Below is a complete list of features, as well as a basic tutorial on plugin usage.
## Features:
- Disable Default Commands (Both Spigot and Vanilla Minecraft)
- Personalized player sessions, storing specific player data in their own YAML file
- Create as many GUI's as you'd like within the configuration file. Each can trigger on command
- Largely configurable
- Moderator Commands:
    - additem - Add as many items as you'd like to your own inventory, or a target player's inventory
        - Usage: /additem [player] {amount} {+items+}
        - Permission: additem
    - cgamemode - Change the gamemode of yourself or a target player
        - Usage: /cgamemode {a|s|c} [player]
        - Permission: cgamemode
    - ckick - Kick a target player from the server
        - Usage: /ckick [player]
        - Permission: ckick
    - modspec - Enter invisible mode and sneakily spectate the map
        - Usage: /modspec
        - Permission: modspec
    - setrank - Set or change the rank of a target player to one from the array of ranks within the config
        - Usage: /setrank [player] {rank}
        - Permission: setrank
    - clearinventory - Clear either the hand or entire inventory of yourself or a target player
        - Usage: /clearinventory {all|hand} [{self}|player]
        - Permission: clearinventory
- Other Commands:
    - menu - Open any menu GUI configured in the plugin's config.yml
        - Usage: /menu {menu-name}
        - Permission: menu

### If you'd like to see a feature implemented, or experience a bug while using this plugin, please report it on this repository under the "Issues" tab. Thanks for  checking out the plugin!