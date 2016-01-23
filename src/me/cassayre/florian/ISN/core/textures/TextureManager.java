package me.cassayre.florian.ISN.core.textures;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class TextureManager
{
    private static final int TEXTURE_SIZE = 32;
    private Image BASE_IMAGE;

    public TextureManager() throws IOException
    {
        //E:/Dropbox/Projet_jeu2D_Andy_Florian_Lucas_Benjamin/3.12.2015/jeu isn
        BASE_IMAGE = makeColorTransparent(ImageIO.read(new File("E:/Documents/isn_v1.png")), Color.GREEN);
    }

    public void drawTexture(Graphics g, int textureX, int textureY, int x, int y, int destSize)
    {
        g.drawImage(BASE_IMAGE, x, y, x + destSize, y + destSize, textureX * TEXTURE_SIZE, textureY * TEXTURE_SIZE, textureX * TEXTURE_SIZE + TEXTURE_SIZE, textureY * TEXTURE_SIZE + TEXTURE_SIZE, null);
        //g.setColor(Color.BLACK);
        //g.fillRect(x, y, destSize ,destSize);
    }

    public void drawTexture(Graphics g, Texture texture, int x, int y, int destSize)
    {
        drawTexture(g, texture.getX(), texture.getY(), x, y, destSize);
    }

    public BufferedImage loadImage(String path) throws IOException
    {
        return ImageIO.read(getClass().getResourceAsStream("/" + path));
    }

    private static Image makeColorTransparent(BufferedImage im, final Color color) {
        ImageFilter filter = new RGBImageFilter() {

            public int markerRGB = color.getRGB() | 0xFF000000;

            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == markerRGB) {
                    return 0x00FFFFFF & rgb;
                } else {
                    return rgb;
                }
            }
        };

        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }
}
