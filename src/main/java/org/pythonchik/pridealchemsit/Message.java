package org.pythonchik.pridealchemsit;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Message {

    private final PrideAlchemsit bad;
    public Message(PrideAlchemsit bad) {this.bad = bad;}
    public void send(CommandSender sender,String message) {
        sender.sendMessage(recreator(message));
    }
    public String recreator(String message) {
        return ChatColor.translateAlternateColorCodes('&',"&7[&6Алхимия&7]&r " + message);
    }
}
