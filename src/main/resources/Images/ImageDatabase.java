package Images;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class ImageDatabase 
{
	 private static final Logger LOG =
	            Logger.getLogger("com.nullprogram.chess.pieces.ImageServer");

    /** The image cache. */
    private static Map<String, BufferedImage> cache =
        new HashMap<String, BufferedImage>();
	public static BufferedImage getTile(final String name) {
        BufferedImage cached = cache.get(name);
        if (cached != null) {
            return cached;
        }

        String file = name + ".png";
        try {
            BufferedImage i = ImageIO.read(ImageDatabase.class.getResource(file));
            cache.put(name, i);
            return i;
        } catch (java.io.IOException e) {
            String message = "Failed to read image: " + file + ": " + e;
            LOG.severe(message);
            System.exit(1);
        } catch (IllegalArgumentException e) {
            String message = "Failed to find image: " + file + ": " + e;
            LOG.severe(message);
            System.exit(1);
        }
        return null;
    }

}
