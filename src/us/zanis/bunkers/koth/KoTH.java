package us.zanis.bunkers.koth;

import org.bukkit.Location;

public class KoTH {
	
	private String name;
	private Location loc1;
	private Location loc2;
	
	public KoTH(final String name){
		this.name = name;
	}
	
	public KoTH(final String name,final Location loc1, final Location loc2 ){
		this.name = name;
		this.loc1 = loc1;
		this.loc2 = loc2;
	}
	
	public String getName(){
		return name;
	}
	
	public Location getLoc1(){
		return loc1;
	}
	
	public Location getLoc2(){
		return loc2;		
	}
	
    public void setLocation1(final Location loc1) {
        this.loc1 = loc1;
    }
    
    public void setLocation2(final Location loc2) {
        this.loc2 = loc2;
    }
	


}
