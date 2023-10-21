package org.pythonchik.pridealchemsit;


import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class listeners implements Listener {
    PrideAlchemsit plugin;
    FileConfiguration config;


    public listeners(PrideAlchemsit plugin, FileConfiguration config) {
        this.plugin = plugin;
        this.config = config;
    }

    private final Message message = PrideAlchemsit.getMessage();
    @EventHandler
    public void clickEvent(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&6Алхимия"))) {
            return;
        }
        if (event.getCurrentItem() == null) {
            return;
        }
        if (event.getCursor() == null){
            return;
        }

        if ((event.getCurrentItem().getType().equals(Material.POTION) || event.getCurrentItem().getType().equals(Material.SPLASH_POTION) || event.getCurrentItem().getType().equals(Material.LINGERING_POTION)) || event.getCurrentItem().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "I-tem"), PersistentDataType.STRING)) {
            Player player = (Player) event.getWhoClicked();
            XP xpclass = new XP();
            Inventory inv = event.getInventory();
            ItemStack item = event.getCurrentItem();
            ItemMeta meta = item.getItemMeta();
            if (meta.getPersistentDataContainer().has(new NamespacedKey(plugin, "I-tem"), PersistentDataType.STRING)) {
                switch (meta.getPersistentDataContainer().get(new NamespacedKey(plugin, "I-tem"), PersistentDataType.STRING)) {
                    case "combine" -> {
                        if (xpclass.getTotalExperience(player) < config.getInt("merge-price")) {
                            message.send(player, "&7У вас недостаточно опыта для алхимии");
                            event.setCancelled(true);
                            return;
                        }
                        if (inv.getItem(3) == null || inv.getItem(5) == null) {
                            message.send(player, "&7Положите зелья в свободные слоты в алхимии");
                            event.setCancelled(true);
                            return;
                        }
                        if (inv.getItem(3).getType() != inv.getItem(5).getType()){
                            message.send(player, "&7Зелья должны быть одинакого типа");
                            event.setCancelled(true);
                            return;
                        }
                        if (!(inv.getItem(3).getType().equals(Material.POTION) || inv.getItem(3).getType().equals(Material.SPLASH_POTION) || inv.getItem(3).getType().equals(Material.LINGERING_POTION))) {
                            message.send(player, "&7Вы можете положить сюда только зелья");
                            event.setCancelled(true);
                            return;
                        }
                        //test for apptoving

                        switch (((PotionMeta) inv.getItem(3).getItemMeta()).getBasePotionData().getType().toString()) {
                            case "SPEED":
                            case "JUMP":
                            case "STRENGTH":
                                PotionData dat31 = ((PotionMeta) inv.getItem(3).getItemMeta()).getBasePotionData();
                                PotionMeta met31 = ((PotionMeta) inv.getItem(3).getItemMeta());

                                met31.addCustomEffect(dat31.getType().getEffectType().createEffect(dat31.isExtended() ? 9600 : 3600, dat31.isUpgraded() ? 2 : 1), true);
                                inv.getItem(3).setItemMeta(met31);
                                break;
                            case "INSTANT_HEAL":
                            case "INSTANT_DAMAGE":
                                PotionData dat32 = ((PotionMeta) inv.getItem(3).getItemMeta()).getBasePotionData();
                                PotionMeta met32 = ((PotionMeta) inv.getItem(3).getItemMeta());

                                met32.addCustomEffect(dat32.getType().getEffectType().createEffect(10, dat32.isUpgraded() ? 2 : 1), true);
                                inv.getItem(3).setItemMeta(met32);
                                break;
                            case "LUCK":
                                PotionData dat33 = ((PotionMeta) inv.getItem(3).getItemMeta()).getBasePotionData();
                                PotionMeta met33 = ((PotionMeta) inv.getItem(3).getItemMeta());

                                met33.addCustomEffect(dat33.getType().getEffectType().createEffect(6000, 1), true);
                                inv.getItem(3).setItemMeta(met33);
                                break;
                            case "WATER_BREATHING":
                            case "INVISIBILITY":
                            case "NIGHT_VISION":
                            case "FIRE_RESISTANCE":
                                PotionData dat34 = ((PotionMeta) inv.getItem(3).getItemMeta()).getBasePotionData();
                                PotionMeta met34 = ((PotionMeta) inv.getItem(3).getItemMeta());

                                met34.addCustomEffect(dat34.getType().getEffectType().createEffect(dat34.isExtended() ? 9600 : 3600, 1), true);
                                inv.getItem(3).setItemMeta(met34);
                                break;
                            case "WEAKNESS":
                            case "SLOW_FALLING":
                                PotionData dat35 = ((PotionMeta) inv.getItem(3).getItemMeta()).getBasePotionData();
                                PotionMeta met35 = ((PotionMeta) inv.getItem(3).getItemMeta());

                                met35.addCustomEffect(dat35.getType().getEffectType().createEffect(dat35.isExtended() ? 4800 : 1800, 1), true);
                                inv.getItem(3).setItemMeta(met35);
                                break;
                            case "REGEN":
                            case "POISON":
                                PotionData dat36 = ((PotionMeta) inv.getItem(3).getItemMeta()).getBasePotionData();
                                PotionMeta met36 = ((PotionMeta) inv.getItem(3).getItemMeta());

                                met36.addCustomEffect(dat36.getType().getEffectType().createEffect(dat36.isExtended() ? 1800 : dat36.isUpgraded() ? 420 : 900, dat36.isUpgraded() ? 2 : 1), true);
                                inv.getItem(3).setItemMeta(met36);
                                break;
                            case "SLOWNESS":
                                PotionData dat37 = ((PotionMeta) inv.getItem(3).getItemMeta()).getBasePotionData();
                                PotionMeta met37 = ((PotionMeta) inv.getItem(3).getItemMeta());

                                met37.addCustomEffect(dat37.getType().getEffectType().createEffect(dat37.isExtended() ? 4800 : dat37.isUpgraded() ? 400 : 900, dat37.isUpgraded() ? 4 : 1), true);
                                inv.getItem(3).setItemMeta(met37);
                                break;
                            case "TURTLE_MASTER":
                                PotionData dat38 = ((PotionMeta) inv.getItem(3).getItemMeta()).getBasePotionData();
                                PotionMeta met38 = ((PotionMeta) inv.getItem(3).getItemMeta());

                                met38.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, dat38.isExtended() ? 800 : 400, dat38.isUpgraded() ? 6 : 4), true);
                                met38.addCustomEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, dat38.isExtended() ? 800 : 400, dat38.isUpgraded() ? 4 : 3), true);
                                inv.getItem(3).setItemMeta(met38);
                                break;
                        } //potion in 3 is good, meens we need to add custom effects from basic potion data
                        switch (((PotionMeta) inv.getItem(5).getItemMeta()).getBasePotionData().getType().toString()) {
                            case "SPEED":
                            case "JUMP":
                            case "STRENGTH":
                                PotionData dat31 = ((PotionMeta) inv.getItem(5).getItemMeta()).getBasePotionData();
                                PotionMeta met31 = ((PotionMeta) inv.getItem(5).getItemMeta());

                                met31.addCustomEffect(dat31.getType().getEffectType().createEffect(dat31.isExtended() ? 9600 : 3600, dat31.isUpgraded() ? 2 : 1), true);
                                inv.getItem(5).setItemMeta(met31);
                                break;
                            case "INSTANT_HEAL":
                            case "INSTANT_DAMAGE":
                                PotionData dat32 = ((PotionMeta) inv.getItem(5).getItemMeta()).getBasePotionData();
                                PotionMeta met32 = ((PotionMeta) inv.getItem(5).getItemMeta());

                                met32.addCustomEffect(dat32.getType().getEffectType().createEffect(10, dat32.isUpgraded() ? 2 : 1), true);
                                inv.getItem(5).setItemMeta(met32);
                                break;
                            case "LUCK":
                                PotionData dat33 = ((PotionMeta) inv.getItem(5).getItemMeta()).getBasePotionData();
                                PotionMeta met33 = ((PotionMeta) inv.getItem(5).getItemMeta());

                                met33.addCustomEffect(dat33.getType().getEffectType().createEffect(6000, 1), true);
                                inv.getItem(5).setItemMeta(met33);
                                break;
                            case "WATER_BREATHING":
                            case "INVISIBILITY":
                            case "NIGHT_VISION":
                            case "FIRE_RESISTANCE":
                                PotionData dat34 = ((PotionMeta) inv.getItem(5).getItemMeta()).getBasePotionData();
                                PotionMeta met34 = ((PotionMeta) inv.getItem(5).getItemMeta());

                                met34.addCustomEffect(dat34.getType().getEffectType().createEffect(dat34.isExtended() ? 9600 : 3600, 1), true);
                                inv.getItem(5).setItemMeta(met34);
                                break;
                            case "WEAKNESS":
                            case "SLOW_FALLING":
                                PotionData dat35 = ((PotionMeta) inv.getItem(5).getItemMeta()).getBasePotionData();
                                PotionMeta met35 = ((PotionMeta) inv.getItem(5).getItemMeta());

                                met35.addCustomEffect(dat35.getType().getEffectType().createEffect(dat35.isExtended() ? 4800 : 1800, 1), true);
                                inv.getItem(5).setItemMeta(met35);
                                break;
                            case "REGEN":
                            case "POISON":
                                PotionData dat36 = ((PotionMeta) inv.getItem(5).getItemMeta()).getBasePotionData();
                                PotionMeta met36 = ((PotionMeta) inv.getItem(5).getItemMeta());

                                met36.addCustomEffect(dat36.getType().getEffectType().createEffect(dat36.isExtended() ? 1800 : dat36.isUpgraded() ? 420 : 900, dat36.isUpgraded() ? 2 : 1), true);
                                inv.getItem(5).setItemMeta(met36);
                                break;
                            case "SLOWNESS":
                                PotionData dat37 = ((PotionMeta) inv.getItem(5).getItemMeta()).getBasePotionData();
                                PotionMeta met37 = ((PotionMeta) inv.getItem(5).getItemMeta());

                                met37.addCustomEffect(dat37.getType().getEffectType().createEffect(dat37.isExtended() ? 4800 : dat37.isUpgraded() ? 400 : 900, dat37.isUpgraded() ? 4 : 1), true);
                                inv.getItem(5).setItemMeta(met37);
                                break;
                            case "TURTLE_MASTER":
                                PotionData dat38 = ((PotionMeta) inv.getItem(5).getItemMeta()).getBasePotionData();
                                PotionMeta met38 = ((PotionMeta) inv.getItem(5).getItemMeta());

                                met38.addCustomEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, dat38.isExtended() ? 800 : 400, dat38.isUpgraded() ? 4 : 3), true);
                                met38.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, dat38.isExtended() ? 800 : 400, dat38.isUpgraded() ? 6 : 4), true);
                                inv.getItem(5).setItemMeta(met38);
                                break;
                        } //potion in 5 is good, meens we need to add custom effects from basic potion data

                        //player have more that enough xp, potions have custom effects
                        ItemStack potion = event.getInventory().getItem(3).getType().equals(Material.POTION) ? new ItemStack(Material.POTION) : event.getInventory().getItem(3).getType().equals(Material.SPLASH_POTION) ? new ItemStack(Material.SPLASH_POTION) : new ItemStack(Material.LINGERING_POTION);
                        PotionMeta pometa = (PotionMeta) potion.getItemMeta();
                        int i3 = 0;
                        for (PotionEffect met : ((PotionMeta) inv.getItem(3).getItemMeta()).getCustomEffects()) {
                            i3 = 0;
                            for (PotionEffect ksdmfsalkdm : pometa.getCustomEffects()) {
                                i3 = i3 + 1;
                            }
                            if (i3 >5) continue;
                            if (((PotionMeta) inv.getItem(5).getItemMeta()).hasCustomEffect(met.getType())) {
                                for (PotionEffect met2 : ((PotionMeta) inv.getItem(5).getItemMeta()).getCustomEffects()) {
                                    if (met2.getType() == met.getType()) {
                                        pometa.addCustomEffect(new PotionEffect(met.getType(), Math.min(met.getDuration() + met2.getDuration(),12000), Math.min((met.getAmplifier() + met2.getAmplifier()), 5)), true);
                                        break;
                                    }
                                }
                            } else {
                                pometa.addCustomEffect(new PotionEffect(met.getType(), met.getDuration(), met.getAmplifier()), true);
                            }
                        }
                        int i5 = 0;
                        for (PotionEffect met : ((PotionMeta) inv.getItem(5).getItemMeta()).getCustomEffects()) {
                            i5 = 0;
                            for (PotionEffect ksdmfsalkdm : pometa.getCustomEffects()) {
                                i5 = i5 + 1;
                            }
                            if (i5 > 5) continue;
                            if (((PotionMeta) inv.getItem(3).getItemMeta()).hasCustomEffect(met.getType())) {
                                for (PotionEffect met2 : ((PotionMeta) inv.getItem(3).getItemMeta()).getCustomEffects()) {
                                    if (met2.getType() == met.getType()) {
                                        pometa.addCustomEffect(new PotionEffect(met.getType(), Math.min(met.getDuration() + met2.getDuration(),12000), Math.min((met.getAmplifier() + met2.getAmplifier()), 5)), true);
                                        break;
                                    }
                                }
                            } else {
                                pometa.addCustomEffect(new PotionEffect(met.getType(), met.getDuration(), met.getAmplifier()), true);
                            }
                        }

                        Random random = new Random();
                        random.setSeed(System.nanoTime());

                        pometa.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&dАлхимическая настоечка"));
                        pometa.setColor(Color.fromRGB(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));

                        {
                            if (random.nextInt(999999999) == 777777777) {
                                pometa.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eРедкая алхимическая настоечка!!"));
                                List<String> rarelore = new ArrayList<>();
                                rarelore.add("&6В этой настойке нет ничего необычного кроме названия и этого описания");
                                rarelore.add("&6Впрочем, шанс появления такой настойки &l&5один &6в &l&5999.999.999");
                                rarelore.add("&6Если быть точным то только выпало число &67&b7&c7&7.&d7&e7&f7&7.&17&27&37");
                                rarelore.add("&6Надеюсь эта зелька не будет просто потеряна !)");
                                rarelore.add("&1P.s. Если использовать её в алхимии то она потеряет свою уникальность, а не передаст дальше");
                                pometa.setLore(rarelore);
                                pometa.setColor(Color.YELLOW);
                            }
                        }
                        //pometa.getPersistentDataContainer().set(new NamespacedKey(plugin,"I-tem"),PersistentDataType.STRING,"preview");
                        potion.setItemMeta(pometa);

                        inv.setItem(3, new ItemStack(Material.AIR));
                        inv.setItem(5, new ItemStack(Material.AIR));

                        if (player.getPersistentDataContainer().get(new NamespacedKey(plugin, "chance"), PersistentDataType.INTEGER) >= random.nextInt(100)) {
                            if (!player.getInventory().addItem(potion).equals(new HashMap<>())) {
                                ItemStack item9 = player.getInventory().getItemInMainHand();
                                player.getInventory().setItemInMainHand(potion);
                                player.dropItem(false);
                                player.getInventory().setItemInMainHand(item9);
                                player.closeInventory();
                            }
                            message.send(player, "&7Алхимия прошла успешно!");
                            event.setCancelled(true);
                        } else {
                            message.send(player, "&7Алхимия не удалась(");
                            event.setCancelled(true);
                        }
                        xpclass.setTotalExperience(player, (xpclass.getTotalExperience(player) - config.getInt("merge-price")));
                        player.getPersistentDataContainer().set(new NamespacedKey(plugin, "chance"), PersistentDataType.INTEGER, config.getInt("basic-chance"));
                        {
                            ItemStack itemStack1 = new ItemStack(Material.getMaterial(config.getString("merge-material")) == null ? Material.BARRIER : Material.getMaterial(config.getString("merge-material").toString()));
                            ItemMeta meta1 = itemStack1.getItemMeta();
                            List<String> lore1 = new ArrayList<>();

                            meta1.getPersistentDataContainer().set(new NamespacedKey(plugin, "I-tem"), PersistentDataType.STRING, "combine");

                            meta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&r" + "&dАлхимичить!"));
                            lore1.add(ChatColor.translateAlternateColorCodes('&', String.format("&7Нажмите сюда что-бы с шансом в &6%d%%&7 совместить зелья вместе", player.getPersistentDataContainer().get(new NamespacedKey(plugin, "chance"), PersistentDataType.INTEGER))));
                            lore1.add(ChatColor.translateAlternateColorCodes('&', String.format("&7Сейчас у вас &6%d&7 единиц опыта", xpclass.getTotalExperience(player))));
                            lore1.add(ChatColor.translateAlternateColorCodes('&', String.format("&7Попытка создания стоит ~&6%d&7 единиц опыта", config.getInt("merge-price"))));
                            meta1.setLore(lore1);

                            itemStack1.setItemMeta(meta1);
                            event.getInventory().setItem(4, itemStack1);
                            //cauldron setup
                            ItemStack itemStack2 = new ItemStack(Material.getMaterial(config.getString("upgrade-material")) == null ? Material.BARRIER : Material.getMaterial(config.getString("upgrade-material").toString()));
                            ItemMeta meta2 = itemStack2.getItemMeta();
                            meta2.getPersistentDataContainer().set(new NamespacedKey(plugin, "I-tem"), PersistentDataType.STRING, "upgrade");
                            List<String> lore2 = new ArrayList<>();

                            meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&dУвеличить шанс"));
                            lore2.add(ChatColor.translateAlternateColorCodes('&', String.format("&7Нажмите сюда что-бы на &6%d%%&7 увеличить свой шанс на успех", config.getInt("buy-chance"))));
                            lore2.add(ChatColor.translateAlternateColorCodes('&', String.format("&7Сейчас у вас шанс на успех &6%d%%&7 и &6%d&7 ед. опыта", player.getPersistentDataContainer().get(new NamespacedKey(plugin, "chance"), PersistentDataType.INTEGER), xpclass.getTotalExperience(player))));
                            if (!config.getBoolean("up-static")) lore2.add(ChatColor.translateAlternateColorCodes('&', String.format("&7Следующие &6%d%%&7 стоят ~&6%d&7 единиц опыта", config.getInt("buy-chance"), (config.getInt("up-per-lvl") * ((player.getPersistentDataContainer().get(new NamespacedKey(plugin,"chance"),PersistentDataType.INTEGER) -config.getInt("basic-chance")) /config.getInt("buy-chance")) + config.getInt("up-price")))));
                            else lore2.add(ChatColor.translateAlternateColorCodes('&', String.format("&7каждые &6%d%%&7 стоят ~&6%d&7 единиц опыта", config.getInt("buy-chance"),config.getInt("up-price"))));
                            meta2.setLore(lore2);

                            itemStack2.setItemMeta(meta2);
                            event.getInventory().setItem(8, itemStack2);
                            //upgrade setup
                        } //setting up new items
                    }
                    case "upgrade" -> {
                        if (config.getBoolean("up-static")) {
                            if (xpclass.getTotalExperience(player) < config.getInt("up-price")) {
                                message.send(player, "&7У вас недостаточно опыта для увеличения шансов");
                                event.setCancelled(true);
                                return;
                            }
                        } else {
                            if (xpclass.getTotalExperience(player) < (config.getInt("up-per-lvl") * ((player.getPersistentDataContainer().get(new NamespacedKey(plugin,"chance"),PersistentDataType.INTEGER) -config.getInt("basic-chance")) /config.getInt("buy-chance"))+config.getInt("up-price"))) {
                                message.send(player, "&7У вас недостаточно опыта для увеличения шансов");
                                event.setCancelled(true);
                                return;
                            }
                        }
                        if (player.getPersistentDataContainer().get(new NamespacedKey(plugin, "chance"), PersistentDataType.INTEGER) >= 100) {
                            message.send(player, "&7У вас и так максимальные шансы на успех");
                            event.setCancelled(true);
                            return;
                        }
                        //can upgrade
                        if (config.getBoolean("up-static")) xpclass.setTotalExperience(player, (xpclass.getTotalExperience(player) - config.getInt("up-price")));
                        else xpclass.setTotalExperience(player, (xpclass.getTotalExperience(player) - (config.getInt("up-per-lvl") * ((player.getPersistentDataContainer().get(new NamespacedKey(plugin,"chance"),PersistentDataType.INTEGER) -config.getInt("basic-chance")) /config.getInt("buy-chance"))+config.getInt("up-price"))));
                        player.getPersistentDataContainer().set(new NamespacedKey(plugin, "chance"), PersistentDataType.INTEGER, (player.getPersistentDataContainer().get(new NamespacedKey(plugin, "chance"), PersistentDataType.INTEGER) + config.getInt("buy-chance")));
                        message.send(player, String.format("&7Вы успешно увеличили шансы до &6%d%%", player.getPersistentDataContainer().get(new NamespacedKey(plugin, "chance"), PersistentDataType.INTEGER)));
                        {
                            ItemStack itemStack1 = new ItemStack(Material.getMaterial(config.getString("merge-material")) == null ? Material.BARRIER : Material.getMaterial(config.getString("merge-material").toString()));
                            ItemMeta meta1 = itemStack1.getItemMeta();
                            List<String> lore1 = new ArrayList<>();

                            meta1.getPersistentDataContainer().set(new NamespacedKey(plugin, "I-tem"), PersistentDataType.STRING, "combine");

                            meta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&r" + "&dАлхимичить!"));
                            lore1.add(ChatColor.translateAlternateColorCodes('&', String.format("&7Нажмите сюда что-бы с шансом в &6%d%%&7 совместить зелья вместе", player.getPersistentDataContainer().get(new NamespacedKey(plugin, "chance"), PersistentDataType.INTEGER))));
                            lore1.add(ChatColor.translateAlternateColorCodes('&', String.format("&7Сейчас у вас &6%d&7 единиц опыта", xpclass.getTotalExperience(player))));
                            lore1.add(ChatColor.translateAlternateColorCodes('&', String.format("&7Попытка создания стоит ~&6%d&7 единиц опыта", config.getInt("merge-price"))));
                            meta1.setLore(lore1);

                            itemStack1.setItemMeta(meta1);
                            event.getInventory().setItem(4, itemStack1);
                            //cauldron setup
                            ItemStack itemStack2 = new ItemStack(Material.getMaterial(config.getString("upgrade-material")) == null ? Material.BARRIER : Material.getMaterial(config.getString("upgrade-material").toString()));
                            ItemMeta meta2 = itemStack2.getItemMeta();
                            meta2.getPersistentDataContainer().set(new NamespacedKey(plugin, "I-tem"), PersistentDataType.STRING, "upgrade");
                            List<String> lore2 = new ArrayList<>();

                            meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&dУвеличить шанс"));
                            lore2.add(ChatColor.translateAlternateColorCodes('&', String.format("&7Нажмите сюда что-бы на &6%d%%&7 увеличить свой шанс на успех", config.getInt("buy-chance"))));
                            lore2.add(ChatColor.translateAlternateColorCodes('&', String.format("&7Сейчас у вас шанс на успех &6%d%%&7 и &6%d&7 ед. опыта", player.getPersistentDataContainer().get(new NamespacedKey(plugin, "chance"), PersistentDataType.INTEGER), xpclass.getTotalExperience(player))));
                            if (!config.getBoolean("up-static")) lore2.add(ChatColor.translateAlternateColorCodes('&', String.format("&7Следующие &6%d%%&7 стоят ~&6%d&7 единиц опыта", config.getInt("buy-chance"), (config.getInt("up-per-lvl") * ((player.getPersistentDataContainer().get(new NamespacedKey(plugin,"chance"),PersistentDataType.INTEGER) -config.getInt("basic-chance")) /config.getInt("buy-chance")) + config.getInt("up-price")))));
                            else lore2.add(ChatColor.translateAlternateColorCodes('&', String.format("&7каждые &6%d%%&7 стоят ~&6%d&7 единиц опыта", config.getInt("buy-chance"),config.getInt("up-price"))));
                            meta2.setLore(lore2);

                            itemStack2.setItemMeta(meta2);
                            event.getInventory().setItem(8, itemStack2);
                            //upgrade setup
                        } //setting up new items
                        event.setCancelled(true);
                    }
                    default -> event.setCancelled(true);

                } //switch end
            } //container item
            else {

            } //potion
        } else {

        } //item is not alchem usable
    }



    @EventHandler
    public void onplayerJOIN(PlayerJoinEvent event){
        if (!event.getPlayer().getPersistentDataContainer().has(new NamespacedKey(plugin,"chance"),PersistentDataType.INTEGER)){
            event.getPlayer().getPersistentDataContainer().set(new NamespacedKey(plugin, "chance"),PersistentDataType.INTEGER,config.getInt("basic-chance"));
        }
    }


    @EventHandler
    public void onInvClosing(InventoryCloseEvent event){
        if (!event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&6Алхимия"))) {
            return;
        }
        if (event.getInventory().getItem(3) == null && event.getInventory().getItem(5) == null){
            return;
        }
        if (event.getInventory().getItem(3) != null) {
            if (!event.getPlayer().getInventory().addItem(event.getInventory().getItem(3)).equals(new HashMap<>())){
                ItemStack item9 = event.getPlayer().getInventory().getItemInMainHand();
                event.getPlayer().getInventory().setItemInMainHand(event.getInventory().getItem(3));
                event.getPlayer().dropItem(false);
                event.getPlayer().getInventory().setItemInMainHand(item9);
            }
        }
        if (event.getInventory().getItem(5) != null) {
            if (!event.getPlayer().getInventory().addItem(event.getInventory().getItem(5)).equals(new HashMap<>())){
                ItemStack item9 = event.getPlayer().getInventory().getItemInMainHand();
                event.getPlayer().getInventory().setItemInMainHand(event.getInventory().getItem(5));
                event.getPlayer().dropItem(false);
                event.getPlayer().getInventory().setItemInMainHand(item9);
            }
        }
    }
}
