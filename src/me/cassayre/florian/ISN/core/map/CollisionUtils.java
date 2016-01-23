package me.cassayre.florian.ISN.core.map;

public class CollisionUtils
{
    private CollisionUtils()
    {

    }

    public static boolean tileCollision(BoundingBox box, TileMap map)
    {
        for(int x = (int) Math.floor(box.getX()); x <= Math.ceil(box.getX() + 1); x++)
        {
            for(int y = (int) Math.floor(box.getY()); y <= Math.ceil(box.getY() + 1); y++)
            {
                if(map.getTile(x, y).getTexture().isTransparent())
                    continue;

                if(map.getTile(x, y).getTexture().getCollisionModule().collide(box, new BoundingBox(x, y, 1, 1)))
                {
                    return true;
                }
            }
        }

        return false;
    }
}
