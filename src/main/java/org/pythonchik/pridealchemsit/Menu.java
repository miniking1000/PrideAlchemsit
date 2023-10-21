package org.pythonchik.pridealchemsit;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;


import java.util.ArrayList;
import java.util.List;

public class Menu {

    PrideAlchemsit plugin;
    static FileConfiguration config;

    public Menu(PrideAlchemsit plugin, FileConfiguration config){this.plugin = plugin;this.config=config;}

    public static void OpenMenu(Player player, PrideAlchemsit plugin){

        Inventory gui = Bukkit.createInventory(player, 9, ChatColor.translateAlternateColorCodes('&',"&6Алхимия"));
        ItemStack[] InventoryStack = new ItemStack[9];
        ItemStack itemStack1 = new ItemStack(Material.getMaterial(config.getString("merge-material")) == null ? Material.BARRIER : Material.getMaterial(config.getString("merge-material").toString()));
        ItemMeta meta1 = itemStack1.getItemMeta();
        List<String> lore1 = new ArrayList<>();
        XP xpclass = new XP();

        meta1.getPersistentDataContainer().set(new NamespacedKey(plugin, "I-tem"),PersistentDataType.STRING, "combine");

        meta1.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&r" + "&dАлхимичить!"));
        lore1.add(ChatColor.translateAlternateColorCodes('&', String.format("&7Нажмите сюда что-бы с шансом в &6%d%%&7 совместить зелья вместе",player.getPersistentDataContainer().get(new NamespacedKey(plugin, "chance"),PersistentDataType.INTEGER))));
        lore1.add(ChatColor.translateAlternateColorCodes('&', String.format("&7Сейчас у вас &6%d&7 едениц опыта",xpclass.getTotalExperience(player))));
        lore1.add(ChatColor.translateAlternateColorCodes('&', String.format("&7Попытка создания стоит ~&6%d&7 едениц опыта", config.getInt("merge-price"))));
        meta1.setLore(lore1);

        itemStack1.setItemMeta(meta1);
        InventoryStack[4] = itemStack1;
        //cauldron setup
        ItemStack itemStack2 = new ItemStack(Material.getMaterial(config.getString("upgrade-material")) == null ? Material.BARRIER : Material.getMaterial(config.getString("upgrade-material").toString()));
        ItemMeta meta2 = itemStack2.getItemMeta();
        meta2.getPersistentDataContainer().set(new NamespacedKey(plugin,"I-tem"),PersistentDataType.STRING,"upgrade");
        List<String> lore2 = new ArrayList<>();

        meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&dУвеличить шанс"));
        lore2.add(ChatColor.translateAlternateColorCodes('&',String.format("&7Нажмите сюда что-бы на &6%d%%&7 увеличить свой шанс на успех",config.getInt("buy-chance"))));
        lore2.add(ChatColor.translateAlternateColorCodes('&',String.format("&7Сейчас у вас шанс на успех &6%d%%&7 и &6%d&7 ед. опыта",player.getPersistentDataContainer().get(new NamespacedKey(plugin, "chance"),PersistentDataType.INTEGER),xpclass.getTotalExperience(player))));
        if (!config.getBoolean("up-static")) lore2.add(ChatColor.translateAlternateColorCodes('&', String.format("&7Следующие &6%d%%&7 стоят ~&6%d&7 единиц опыта", config.getInt("buy-chance"), (config.getInt("up-per-lvl") * ((player.getPersistentDataContainer().get(new NamespacedKey(plugin,"chance"),PersistentDataType.INTEGER) -config.getInt("basic-chance")) /config.getInt("buy-chance")) + config.getInt("up-price")))));
        else lore2.add(ChatColor.translateAlternateColorCodes('&', String.format("&7каждые &6%d%%&7 стоят ~&6%d&7 единиц опыта", config.getInt("buy-chance"),config.getInt("up-price"))));
        meta2.setLore(lore2);

        itemStack2.setItemMeta(meta2);
        InventoryStack[8] = itemStack2;
        //upgrade setup

        ItemStack itemStack3 = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemMeta meta3 = itemStack3.getItemMeta();
        meta3.setDisplayName(" ");
        meta3.getPersistentDataContainer().set(new NamespacedKey(plugin,"I-tem"),PersistentDataType.STRING,"null");
        itemStack3.setItemMeta(meta3);
        //null setup

        for (int i = 0; i < InventoryStack.length; i++) {
            if (InventoryStack[i] == null) {
                InventoryStack[i] = itemStack3;
            }
            if (i > 0 && InventoryStack[i-1] == itemStack1){
                InventoryStack[i] = new ItemStack(Material.AIR);
                InventoryStack[i-2] = new ItemStack(Material.AIR);
            }
        }
        gui.setContents(InventoryStack);
        player.openInventory(gui);
    }
}
