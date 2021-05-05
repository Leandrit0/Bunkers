package us.zanis.bunkers.eco;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

public class CoinsData implements Listener
{
public static File config = new File("plugins/Bunkers", "money.yml");
public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(config);

public static void saveFile()
{
  try
  {
    cfg.save(config);
  }
  catch (IOException e)
  {
    e.printStackTrace();
  }
}
}
