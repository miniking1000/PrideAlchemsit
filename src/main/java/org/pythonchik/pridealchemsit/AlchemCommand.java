package org.pythonchik.pridealchemsit;


import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

import java.util.Arrays;

public class AlchemCommand implements CommandExecutor {
    PrideAlchemsit plugin;
    public AlchemCommand(PrideAlchemsit plugin, FileConfiguration config){this.plugin = plugin;}
    public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {

        if (sender instanceof Player) {
            Menu.OpenMenu((Player) sender, plugin);
        }
        return true;
    }
}
