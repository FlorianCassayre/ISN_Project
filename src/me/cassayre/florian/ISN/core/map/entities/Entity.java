package me.cassayre.florian.ISN.core.map.entities;

import me.cassayre.florian.ISN.core.map.BoundingBox;
import me.cassayre.florian.ISN.core.map.TileMap;

public abstract class Entity
{
    protected double x;
    protected double y;

    protected final double width;
    protected final double height;

    protected double vecX;
    protected double vecY;

    protected TileMap map;

    public Entity(TileMap map, double width, double height)
    {
        this.map = map;

        this.width = width;
        this.height = height;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getVecX()
    {
        return vecX;
    }

    public void setVecX(double vecX)
    {
        this.vecX = vecX;
    }

    public double getVecY()
    {
        return vecY;
    }

    public void setVecY(double vecY)
    {
        this.vecY = vecY;
    }

    public double getWidth()
    {
        return width;
    }

    public double getHeight()
    {
        return height;
    }

    public BoundingBox getBox()
    {
        return new BoundingBox(x, y, width, height);
    }

    public TileMap getMap()
    {
        return map;
    }

    public boolean isOnGround()
    {
        return !map.getTile((int) Math.floor(x), (int) Math.floor(y - 0.05)).getTexture().isTransparent();
    }

    public void a()
    {
        System.out.println(map.getTile((int) Math.floor(x), (int) Math.floor(y - 0.05)).getTexture());
    }
}
