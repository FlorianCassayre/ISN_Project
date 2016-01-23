package me.cassayre.florian.ISN.core.map;

import me.cassayre.florian.ISN.core.map.entities.Entity;

public class MapLoop implements Runnable
{
    private final TileMap MAP;

    public MapLoop(TileMap map)
    {
        MAP = map;
    }

    @Override
    public void run()
    {
        for(Entity entity : MAP.getEntities())
        {
            for(int i = 0; i < 10; i++)
            {
                entity.setVecY(entity.getVecY() - 0.005);

                double lastX = entity.getX();
                double lastY = entity.getY();

                entity.setX(entity.getX() + entity.getVecX());

                if(CollisionUtils.tileCollision(entity.getBox(), MAP))
                {
                    entity.setX(lastX);
                    entity.setVecX(0);
                }

                entity.setY(entity.getY() + entity.getVecY());

                if(CollisionUtils.tileCollision(entity.getBox(), MAP))
                {
                    entity.setY(lastY);
                    entity.setVecY(0);
                }

                if(entity.isOnGround() && entity.getVecX() != 0)
                    entity.setVecX(entity.getVecX() + (entity.getVecX() > 0 ? -0.005 : 0.005));
            }
        }
    }
}
