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
    DIRT(4, 1, 1, RectangleCollision.class),
    STONE_DESCENT_LEFT(5, 3, 0, RectangleCollision.class), // TODO descent collision.
    STONE_DESCENT_RIGHT(6, 5, 0, RectangleCollision.class), // TODO
    STONE_PLATFORM(7, 4, 0, RectangleCollision.class),
    STONE(8, 4, 1, RectangleCollision.class),
    STONE_SLOPE_LEFT(9, 6, 0, RectangleCollision.class), // TODO
    STONE_SLOPE_RIGHT(10, 8, 0, RectangleCollision.class), // TODO
    STONE_CEILING(11, 7, 0, RectangleCollision.class),

    TOILETS_0_0(12, 0, 2, NoCollision.class),
    TOILETS_1_0(13, 1, 2, NoCollision.class),
    TOILETS_2_0(14, 2, 2, NoCollision.class),
    TOILETS_0_1(15, 0, 3, NoCollision.class),
    TOILETS_1_1(16, 1, 3, NoCollision.class),
    TOILETS_2_1(17, 2, 3, NoCollision.class),
    TOILETS_0_2(18, 0, 4, NoCollision.class),
    TOILETS_1_2(19, 1, 4, NoCollision.class),
    TOILETS_2_2(20, 2, 4, NoCollision.class);


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
