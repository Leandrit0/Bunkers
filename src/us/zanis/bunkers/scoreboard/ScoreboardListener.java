package us.zanis.bunkers.scoreboard;

import java.util.WeakHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import us.zanis.bunkers.Bunkers;
import us.zanis.bunkers.GameTime;
import us.zanis.bunkers.StartCountdown;
import us.zanis.bunkers.eco.Coins;
import us.zanis.bunkers.teams.Blue;
import us.zanis.bunkers.utils.MessageUtils;

public class ScoreboardListener
  implements Listener
{
  private final Bunkers instance = Bunkers.getInstance();
  private final WeakHashMap<Player, ScoreboardHelper> helper = new WeakHashMap();
 
  public ScoreboardHelper getScoreboardFor(Player player)
  {
    return (ScoreboardHelper)this.helper.get(player);
  }
 
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event)
  {
    Player player = event.getPlayer();
    this.helper.remove(player);
  }
 
  @EventHandler
  public void onPlayerKick(PlayerKickEvent event)
  {
    Player player = event.getPlayer();
    this.helper.remove(player);
  }
 
  public void unregister(Scoreboard board, String name)
  {
    Team team = board.getTeam(name);
    if (team != null) {
      team.unregister();
    }
  }
 
  public Team getTeam(Scoreboard board, String name, String prefix)
  {
    Team team = board.getTeam(name);
    if (team == null) {
      team = board.registerNewTeam(name);
    }
    team.setPrefix(prefix);
    return team;
  }
 
  public void registerScoreboards(Player player)
  {
    Scoreboard bukkitScoreBoard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
    player.setScoreboard(bukkitScoreBoard);
    ScoreboardHelper scoreboardHelper = new ScoreboardHelper(bukkitScoreBoard, MessageUtils.translate("&b&lZanis &8[&7Bunkers&8]"));
    this.helper.put(player, scoreboardHelper);
    resendTab(player);
    for (Player other : Bukkit.getServer().getOnlinePlayers()) {
      if (other != player) {
        if (getScoreboardFor(other) != null)
        {
          Scoreboard scoreboard = getScoreboardFor(other).getScoreBoard();
          Team otherTeam = getTeam(scoreboard, "other", MessageUtils.translate("&e"));
          otherTeam.addEntry(player.getName());
        }
      }
    }
  }
 
  public void resendTab(Player player)
  {
    if (getScoreboardFor(player) == null) {
      return;
    }
    Scoreboard scoreboard = getScoreboardFor(player).getScoreBoard();
    unregister(scoreboard, "player");
    unregister(scoreboard, "other");
   
    Team playerTeam = getTeam(scoreboard, "player", MessageUtils.translate("&a"));
    Team otherTeam = getTeam(scoreboard, "other", MessageUtils.translate("&e"));
    for (Player other : Bukkit.getOnlinePlayers()) {
      if (other == player) {
        playerTeam.addEntry(other.getName());
      } else {
        otherTeam.addEntry(other.getName());
      }
    }
  }
 
  public void setupScoreboard()
  {
    new BukkitRunnable()
    {
      public void run()
      {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
          if (ScoreboardListener.this.helper.containsKey(player))
          {
            ScoreboardHelper scoreboardHelper = ScoreboardListener.this.getScoreboardFor(player);
            scoreboardHelper.clear();
            scoreboardHelper.add(MessageUtils.translate("&7&m-------------------------"));
            if(Blue.blueteam.contains(player.getName())) {
            	scoreboardHelper.add(MessageUtils.translate("&6&lTeam&r: &1Blue"));
                scoreboardHelper.add(MessageUtils.translate("&e&lDTR&r: &r" + Blue.getBlueDtr()));
                scoreboardHelper.add(MessageUtils.translate("&6&lBalance&r: &a$" + Coins.getCoins(player.getUniqueId())));
            }
            else {
            	scoreboardHelper.add(MessageUtils.translate("&6&lTeam&r: &7/team join!"));
            }
            if(StartCountdown.canstart) {
            	scoreboardHelper.add(MessageUtils.translate("&cStarting in: " + StartCountdown.waitingTimer));
            }
            if(GameTime.gametime) {
            	scoreboardHelper.add(MessageUtils.translate("&cGameTime: " + getFormatedDruation()));
            }
            scoreboardHelper.add(MessageUtils.translate("&7&m-------------------------"));
            scoreboardHelper.update(player);
          }
        }
      }
    }.runTaskTimer(this.instance, 0L, 3L);
  }
 
  @EventHandler
  public void onJoin(PlayerJoinEvent e)
  {
    Player p = e.getPlayer();
   
    registerScoreboards(p);
  }
  
  public static String getFormatedDruation()
  {
    int time = GameTime.gametimer;
    int minutes = (int)(time / 60.0D);
    int seconds = time % 60;
    return String.format("%d:%02d", new Object[] { Integer.valueOf(minutes), Integer.valueOf(seconds) });
  }

}