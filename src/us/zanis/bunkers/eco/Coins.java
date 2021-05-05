package us.zanis.bunkers.eco;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.event.Listener;

public class Coins implements Listener {
	
	  public static Integer getCoins(UUID uuid)
	  {
	    return Integer.valueOf(CoinsData.cfg.getInt(uuid + ".Coins"));
	  }
	  
	  public static void addCoins(UUID uuid, int amount)
	  {
	    CoinsData.cfg.set(uuid + ".Coins", Integer.valueOf(getCoins(uuid).intValue() + amount));
	    try
	    {
	      CoinsData.cfg.save(CoinsData.config);
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	  }
	  
	  public static void removeCoins(UUID UUID, int amount)
	  {
	    CoinsData.cfg.set(UUID + ".Coins", Integer.valueOf(getCoins(UUID).intValue() - amount));
	    try
	    {
	      CoinsData.cfg.save(CoinsData.config);
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	  }
}