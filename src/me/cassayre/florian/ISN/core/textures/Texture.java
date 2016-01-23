package me.cassayre.florian.ISN.core.textures;

import me.cassayre.florian.ISN.core.textures.collisions.Collision;
import me.cassayre.florian.ISN.core.textures.collisions.NoCollision;
import me.cassayre.florian.ISN.core.textures.collisions.RectangleCollision;

import java.awt.*;

public enum Texture
{
    AIR(0, 0, 1, NoCollision.class),
    GRASS_LEFT(1, 0, 0, RectangleCollision.class),
    GRASS(2, 1, 0, RectangleCollision.class),
    GRASS_RIGHT(3, 2, 0, RectangleCollision.class),
    DIRT(4, 1, 1, RectangleCollision.class);



    private final int ID;
    private final int X;
    private final int Y;

    private final Collision COLLISION_MODULE;

    private Texture(int id, int x, int y, Class<? extends Collision> module)
    {
        ID = id;
        X = x;
        Y = y;

        COLLISION_MODULE = createCollisionModule(module);
    }

    private static Collision createCollisionModule(Class<? extends Collision> module)
    {
        try
        {
            return module.newInstance();
        } catch(InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public void draw(Graphics g)
    {
        /*g.drawImage()
        drawImage(Image img,
        int dx1, int dy1, int dx2, int dy2,
        int sx1, int sy1, int sx2, int sy2,*/
    }

    public int getId()
    {
        return ID;
    }

    public int getX()
    {
        return X;
    }

    public int getY()
    {
        return Y;
    }

    public Collision getCollisionModule()
    {
        return COLLISION_MODULE;
    }

    public boolean isTransparent()
    {
        return this == AIR;
    }

    public static Texture getById(int id)
    {
        for(Texture texture : values())
            if(texture.getId() == id)
                return texture;
        return Texture.AIR;
    }
}
