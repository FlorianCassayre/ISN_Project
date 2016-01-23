package me.cassayre.florian.ISN.core.textures.collisions;

import me.cassayre.florian.ISN.core.map.BoundingBox;

public class RectangleCollision extends Collision
{
    @Override
    public boolean collide(BoundingBox box1, BoundingBox box2)
    {
        if((box2.getX() >= box1.getX() + box1.getWidth())      // trop à droite
                || (box2.getX() + box2.getWidth() <= box1.getX()) // trop à gauche
                || (box2.getY() >= box1.getY() + box1.getHeight()) // trop en bas
                || (box2.getY() + box2.getHeight() <= box1.getY()))  // trop en haut
            return false;
        else
            return true;
    }
}
