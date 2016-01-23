package me.cassayre.florian.ISN.core.textures.collisions;

import me.cassayre.florian.ISN.core.map.BoundingBox;

public abstract class Collision
{
    public abstract boolean collide(BoundingBox box1, BoundingBox box2);
}
