package me.cassayre.florian.ISN.core.map;

import me.cassayre.florian.ISN.core.textures.Texture;

public class Tile
{
    protected Texture texture;

    public Tile(Texture texture)
    {
        this.texture = texture;
    }

    public Texture getTexture()
    {
        return texture;
    }

}
