package com.herbprofittracker;

import com.google.inject.Injector;
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.ItemID;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.PluginPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

@Slf4j
@PluginDescriptor(
	name = "Herb run profit tracker"
)
public class HerbProfitTrackerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private HerbProfitTrackerConfig config;

	@Inject
	private ItemManager itemManager;

	@Inject
	private ClientToolbar clientToolbar;


	private NavigationButton navigationButton;
	private HerbProfitTrackerPanel herbTrackerProfitPanel;


	@Override
	protected void startUp() throws Exception
	{
		herbTrackerProfitPanel = injector.getInstance(HerbProfitTrackerPanel.class);
		herbTrackerProfitPanel.init();
		setNavigationButton(herbTrackerProfitPanel);
		clientToolbar.addNavigation(navigationButton);
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		clientToolbar.removeNavigation(navigationButton);
		log.info("Example stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}

	@Provides
	HerbProfitTrackerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(HerbProfitTrackerConfig.class);
	}


	private void setNavigationButton(PluginPanel panel){
		NavigationButton navButton = NavigationButton.builder()
				.icon(getItemImage(ItemID.GRIMY_RANARR_WEED))
				.panel(panel)
				.tooltip("Herb run profit tracker")
				.build();
		log.info("Returning nav button!");
		log.info("Returning nav button!");
		log.info("Returning nav button!");
		log.info("Returning nav button!");

		navigationButton = navButton;
	}

	private BufferedImage getItemImage(int id){
		BufferedImage bufferedImage;
		bufferedImage = itemManager.getImage(id);

		return bufferedImage;
	}
}
