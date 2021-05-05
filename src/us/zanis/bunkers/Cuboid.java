package us.zanis.bunkers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

public class Cuboid implements Iterable<Block>, Cloneable, ConfigurationSerializable
{
    World w;
    int xmax;
    int xmin;
    int ymax;
    int ymin;
    int zmax;
    int zmin;
    
    public Cuboid(final Cuboid cuboid) {
        this.w = cuboid.getWorld();
        this.xmax = cuboid.getXmax();
        this.xmin = cuboid.getXmin();
        this.ymax = cuboid.getYmax();
        this.ymin = cuboid.getYmin();
        this.zmax = cuboid.getZmax();
        this.zmin = cuboid.getZmin();
    }
    
    public Cuboid(final Location l1, final Location l2) {
        if (l1.getWorld().getName().equals(l2.getWorld().getName())) {
            this.w = l1.getWorld();
            this.xmax = Math.max(l1.getBlockX(), l2.getBlockX());
            this.xmin = Math.min(l1.getBlockX(), l2.getBlockX());
            this.ymax = Math.max(l1.getBlockY(), l2.getBlockY());
            this.ymin = Math.min(l1.getBlockY(), l2.getBlockY());
            this.zmax = Math.max(l1.getBlockZ(), l2.getBlockZ());
            this.zmin = Math.min(l1.getBlockZ(), l2.getBlockZ());
        }
    }
    
    public Cuboid(final int xmax, final int xmin, final int ymax, final int ymin, final int zmax, final int zmin, final World world) {
        this.w = world;
        this.xmax = xmax;
        this.xmin = xmin;
        this.ymax = ymax;
        this.ymin = ymin;
        this.zmax = zmax;
        this.zmin = zmin;
    }
    
    public Cuboid(final Map<String, Object> map) {
        this.xmax = (int) map.get("xmax");
        this.xmin = (int) map.get("xmin");
        this.ymax = (int) map.get("ymax");
        this.ymin = (int) map.get("ymin");
        this.zmax = (int) map.get("zmax");
        this.zmin = (int) map.get("zmin");
        this.w = Bukkit.getServer().getWorld(map.get("world").toString());
    }
    
    public int getXmax() {
        return this.xmax;
    }
    
    public int getXmin() {
        return this.xmin;
    }
    
    public int getYmax() {
        return this.ymax;
    }
    
    public int getYmin() {
        return this.ymin;
    }
    
    public int getZmax() {
        return this.zmax;
    }
    
    public int getZmin() {
        return this.zmin;
    }
    
    public World getWorld() {
        return this.w;
    }
    
    public void setXmax(final int xmax) {
        this.xmax = xmax;
    }
    
    public void setXmin(final int xmin) {
        this.xmin = xmin;
    }
    
    public void setYmax(final int ymax) {
        this.ymax = ymax;
    }
    
    public void setYmin(final int ymin) {
        this.ymin = ymin;
    }
    
    public void setZmax(final int zmax) {
        this.zmax = zmax;
    }
    
    public void setZmin(final int zmin) {
        this.zmin = zmin;
    }
    
    public void setWorld(final World world) {
        this.w = world;
    }
    
    @Override
    public Iterator<Block> iterator() {
        return new CuboidIterator(new Cuboid(this.xmax, this.xmin, this.ymax, this.ymin, this.zmax, this.zmin, this.w));
    }
    
    public boolean hasPlayerInside(final Player player) {
        final Location loc = player.getLocation();
        return this.xmin <= loc.getX() && this.xmax + 1 >= loc.getX() && this.ymin <= loc.getY() && this.ymax >= loc.getY() && this.zmin <= loc.getZ() && this.zmax + 1 >= loc.getZ() && this.w.getName().equals(loc.getWorld().getName());
    }
    
    public boolean hasBlockInside(final Block block) {
        final Location loc = block.getLocation();
        return this.xmin <= loc.getX() && this.xmax + 1 >= loc.getX() && this.ymin <= loc.getY() && this.ymax >= loc.getY() && this.zmin <= loc.getZ() && this.zmax + 1 >= loc.getZ() && this.w.getName().equals(loc.getWorld().getName());
    }
    
    public boolean hasLocationInside(final Location loc) {
        return this.xmin <= loc.getX() && this.xmax + 1 >= loc.getX() && this.ymin <= loc.getY() && this.ymax >= loc.getY() && this.zmin <= loc.getZ() && this.zmax + 1 >= loc.getZ() && this.w.getName().equals(loc.getWorld().getName());
    }
    
    public Map<String, Object> serialize() {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("world", this.getWorld().toString());
        map.put("xmax", this.getXmax());
        map.put("xmin", this.getXmin());
        map.put("ymax", this.getYmax());
        map.put("ymin", this.getYmin());
        map.put("zmax", this.getZmax());
        map.put("zmin", this.getZmin());
        return map;
    }
    
    public Cuboid clone() throws CloneNotSupportedException {
        return new Cuboid(this);
    }
    
    public class CuboidIterator implements Iterator<Block>
    {
        Cuboid cci;
        World wci;
        int baseX;
        int baseY;
        int baseZ;
        int sizeX;
        int sizeY;
        int sizeZ;
        private int x;
        private int y;
        private int z;
        ArrayList<Block> blocks;
        Map<Location, Material> blocks2;
        ArrayList<Location> blocks3;
        
        public CuboidIterator(final Cuboid c) {
            this.cci = c;
            this.wci = c.getWorld();
            this.baseX = Cuboid.this.getXmin();
            this.baseY = Cuboid.this.getYmin();
            this.baseZ = Cuboid.this.getZmin();
            this.sizeX = Math.abs(Cuboid.this.getXmax() - Cuboid.this.getXmin()) + 1;
            this.sizeY = Math.abs(Cuboid.this.getYmax() - Cuboid.this.getYmin()) + 1;
            this.sizeZ = Math.abs(Cuboid.this.getZmax() - Cuboid.this.getZmin()) + 1;
            final boolean x = false;
            this.z = (x ? 1 : 0);
            this.y = (x ? 1 : 0);
            this.x = (x ? 1 : 0);
        }
        
        public Cuboid getCuboid() {
            return this.cci;
        }
        
        @Override
        public boolean hasNext() {
            return this.x < this.sizeX && this.y < this.sizeY && this.z < this.sizeZ;
        }
        
        @Override
        public Block next() {
            final Block b = Cuboid.this.w.getBlockAt(this.baseX + this.x, this.baseY + this.y, this.baseZ + this.z);
            if (++this.x >= this.sizeX) {
                this.x = 0;
                if (++this.y >= this.sizeY) {
                    this.y = 0;
                    ++this.z;
                }
            }
            return b;
        }
        
        @Override
        public void remove() {
        }
        
        public Map<Location, Material> getBlockAtLocations() {
            this.blocks2 = new HashMap<Location, Material>();
            for (int x = this.cci.getXmin(); x <= this.cci.getXmax(); ++x) {
                for (int y = this.cci.getYmin(); y <= this.cci.getYmax(); ++y) {
                    for (int z = this.cci.getZmin(); z <= this.cci.getZmax(); ++z) {
                        this.blocks2.put(new Location(this.cci.getWorld(), (double)x, (double)y, (double)z), Cuboid.this.getWorld().getBlockAt(x, y, z).getType());
                    }
                }
            }
            return this.blocks2;
        }
        
        public Collection<Location> getLocations() {
            this.blocks3 = new ArrayList<Location>();
            for (int x = this.cci.getXmin(); x <= this.cci.getXmax(); ++x) {
                for (int y = this.cci.getYmin(); y <= this.cci.getYmax(); ++y) {
                    for (int z = this.cci.getZmin(); z <= this.cci.getZmax(); ++z) {
                        this.blocks3.add(new Location(this.wci, (double)x, (double)y, (double)z));
                    }
                }
            }
            return this.blocks3;
        }
        
        public Collection<Block> iterateBlocks() {
            this.blocks = new ArrayList<Block>();
            for (int x = this.cci.getXmin(); x <= this.cci.getXmax(); ++x) {
                for (int y = this.cci.getYmin(); y <= this.cci.getYmax(); ++y) {
                    for (int z = this.cci.getZmin(); z <= this.cci.getZmax(); ++z) {
                        this.blocks.add(this.cci.getWorld().getBlockAt(x, y, z));
                    }
                }
            }
            return this.blocks;
        }
    }
}
