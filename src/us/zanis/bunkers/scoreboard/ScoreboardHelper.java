package us.zanis.bunkers.scoreboard;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.google.common.base.Preconditions;

public class ScoreboardHelper
{
  private List<ScoreboardText> list = new ArrayList<ScoreboardText>();
  private Scoreboard scoreBoard;
  private Objective objective;
  private String tag = "PlaceHolder";
  private int lastSentCount = -1;

  public ScoreboardHelper(Scoreboard scoreBoard, String title) 
  {
    Preconditions.checkState(title.length() <= 32, "title can not be more than 32");
    this.tag = ChatColor.translateAlternateColorCodes('&', title);
    this.scoreBoard = scoreBoard;
    this.objective = this.getOrCreateObjective(this.tag);
    this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
  }

  public Scoreboard getScoreBoard()
  {
    return this.scoreBoard;
  }
  
  public void add(String input) 
  {
    input = ChatColor.translateAlternateColorCodes('&', input);
    ScoreboardText text = null;
    if(input.length() <= 16)
    {
      text = new ScoreboardText(input, "");
    }
    else
    {
      String first = input.substring(0 , 16);
      String second = input.substring(16 , input.length());
      if(first.endsWith(String.valueOf(ChatColor.COLOR_CHAR)))
      {
        first = first.substring(0, first.length() -1);
        second = ChatColor.COLOR_CHAR + second;
      }
      String lastColors = ChatColor.getLastColors(first);
      second = lastColors + second;
      text = new ScoreboardText(first, StringUtils.left(second , 16));
    }
    this.list.add(text);
  }
  
  public void clear() 
  {
    this.list.clear();
  }

  public void remove(int index) 
  {
    String name = this.getNameForIndex(index);
    this.scoreBoard.resetScores(name);
    Team team = this.getOrCreateTeam(ChatColor.stripColor(StringUtils.left(this.tag , 14)) + index, index);
    team.unregister();
  }

  public void update(Player player)
  {
    player.setScoreboard(scoreBoard);
    int sentCount;
    for (sentCount = 0; sentCount < this.list.size(); ++sentCount) 
    {
      Team i = this.getOrCreateTeam(ChatColor.stripColor(StringUtils.left(this.tag , 14)) + sentCount, sentCount);
      ScoreboardHelper.ScoreboardText str = this.list.get(this.list.size() - sentCount - 1);
      i.setPrefix(str.getLeft());
      i.setSuffix(str.getRight());
      this.objective.getScore(this.getNameForIndex(sentCount)).setScore(sentCount + 1);
    }
    
    if (this.lastSentCount != -1) 
    {
      sentCount = this.list.size();
      for (int var4 = 0; var4 < this.lastSentCount - sentCount; ++var4) 
      {
	this.remove(sentCount + var4);
      }
    }	
    this.lastSentCount = this.list.size();
  }

  public Team getOrCreateTeam(String team, int i)
  {
    Team value = this.scoreBoard.getTeam(team);
    if (value == null) 
    {
      value = this.scoreBoard.registerNewTeam(team);
      value.addEntry(this.getNameForIndex(i));
    }
    return value;
  }

  public Objective getOrCreateObjective(String objective)
  {
    Objective value = this.scoreBoard.getObjective("dummyhubobj");
    if (value == null)
    {
      value = this.scoreBoard.registerNewObjective("dummyhubobj", "dummy");
    }
    value.setDisplayName(objective);
    return value;
  }

  public String getNameForIndex(int index)
  {
    return ChatColor.values()[index].toString() + ChatColor.RESET;
  }

  private static class ScoreboardText
  {
    private String left;
    private String right;

    public ScoreboardText(String left, String right)
    {
      this.left = left;
      this.right = right;
    }

    public String getLeft() 
    {
      return this.left;
    }
    
    public String getRight() 
    {
      return this.right;
    }
  }
}