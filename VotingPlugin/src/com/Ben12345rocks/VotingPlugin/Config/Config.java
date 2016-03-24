package com.Ben12345rocks.VotingPlugin.Config;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import com.Ben12345rocks.VotingPlugin.Main;

public class Config {

	static Config instance = new Config();

	static Main plugin = Main.plugin;

	public static Config getInstance() {
		return instance;
	}

	FileConfiguration data;

	File dFile;

	private Config() {
	}

	public Config(Main plugin) {
		Config.plugin = plugin;
	}

	public boolean allowUnJoined() {
		return getData().getBoolean("allowunjoined");
	}

	public int bungeePort() {
		return getData().getInt("bungeeport");
	}

	public boolean getBonusRewardEnabled() {
		return getData().getBoolean("allvotesbonus");
	}

	public boolean getBroadCastVotesEnabled() {
		return getData().getBoolean("broadcastvote");
	}

	public FileConfiguration getData() {
		return data;
	}

	public boolean getDebugEnabled() {
		return getData().getBoolean("debug");
	}

	public boolean getRemindVotesEnabled() {
		return getData().getBoolean("remindvotes");
	}

	public boolean recieveBungeeVotes() {
		return getData().getBoolean("recievebungeevotes");
	}

	public void reloadData() {
		data = YamlConfiguration.loadConfiguration(dFile);
	}

	public void saveData() {
		try {
			data.save(dFile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger()
			.severe(ChatColor.RED + "Could not save Config.yml!");
		}
	}

	public boolean sendBungeeVotes() {
		return getData().getBoolean("sendbungeevotes");
	}

	public void setBonusRewardEnabled(boolean value) {
		getData().set("allvotesbonus", value);
		saveData();
	}

	public void setBroadcastVoteEnabled(boolean value) {
		getData().set("broadcastvote", value);
		saveData();
	}

	public void setDebugEnabled(boolean value) {
		getData().set("debug", value);
		saveData();
	}

	public void setRemindVotesEnabled(boolean value) {
		getData().set("remindvotes", value);
		saveData();
	}

	public void setup(Plugin p) {
		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}

		dFile = new File(p.getDataFolder(), "Config.yml");

		if (!dFile.exists()) {
			try {
				dFile.createNewFile();
				plugin.saveResource("Config.yml", true);
			} catch (IOException e) {
				Bukkit.getServer().getLogger()
				.severe(ChatColor.RED + "Could not create Config.yml!");
			}
		}

		data = YamlConfiguration.loadConfiguration(dFile);
	}

	public boolean updateReminder() {
		return getData().getBoolean("updatereminder");
	}
}
