package com.herbprofittracker;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class HerbProfitTrackerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HerbProfitTrackerPlugin.class);
		RuneLite.main(args);
	}
}