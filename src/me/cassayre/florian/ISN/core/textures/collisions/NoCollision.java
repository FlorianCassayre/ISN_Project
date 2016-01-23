package me.cassayre.florian.ISN.core.textures.collisions;

import me.cassayre.florian.ISN.core.map.BoundingBox;

public class NoCollision extends Collision
{
    @Override
    public boolean collide(BoundingBox box1, BoundingBox box2)
    {
        return false;
    }
}
