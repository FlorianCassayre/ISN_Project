package me.cassayre.florian.ISN.core.map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.cassayre.florian.ISN.core.map.entities.Entity;
import me.cassayre.florian.ISN.core.map.entities.Player;
import me.cassayre.florian.ISN.core.textures.Texture;

import java.util.ArrayList;
import java.util.List;

public class TileMap
{
    private Tile[][] tiles;
    private List<Entity> entities = new ArrayList<>();
    private double spawnX, spawnY;
    private int width, height;

    private String name = "", description = "";

    public TileMap(int width, int height)
    {
        tiles = new Tile[width][height];

        this.width = width;
        this.height = height;

        for(int x = 0; x < width; x++)
            for(int y = 0; y < height; y++)
                tiles[x][y] = new Tile(Texture.AIR);

        Player player = new Player(this);
        player.setX(spawnX);
        player.setY(spawnY);

        addEntity(player);
    }

    @Deprecated
    public TileMap(JsonObject object)
    {
        int x = 0;
        int y = 0;

        for(JsonElement element : object.get("map").getAsJsonArray())
        {
            for(JsonElement tile : element.getAsJsonArray())
            {
                JsonObject obj = tile.getAsJsonObject();
                int id = obj.get("tile").getAsInt();

                tiles[x][y] = new Tile(Texture.getById(id));

                x++;
            }
            y++;
        }

    }

    public void setTile(int x, int y, Tile tile)
    {
        tiles[x][y] = tile;
    }

    public Tile getTile(int x, int y)
    {
        if(x < 0 || x >= getWidth() || y < 0 || y >= getHeight())
            return new Tile(Texture.GRASS_LEFT);

        return tiles[x][y];
    }

    public void setSpawn(int x, int y)
    {
        this.spawnX = x;
        this.spawnY = y;

        getPlayer().setX(x);
        getPlayer().setY(y);
    }

    public Tile getTile(double x, double y)
    {
        return getTile((int) Math.floor(x), (int) Math.floor(y));
    }

    public void addEntity(Entity entity)
    {
        entities.add(entity);
    }

    public void removeEntity(Entity entity)
    {
        entities.remove(entity);
    }

    public List<Entity> getEntities()
    {
        return new ArrayList<>(entities);
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public Player getPlayer()
    {
        for(Entity entity : getEntities())
            if(entity instanceof Player)
                return (Player) entity;
        return null;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getSpawnX()
    {
        return spawnX;
    }

    public double getSpawnY()
    {
        return spawnY;
    }
}
