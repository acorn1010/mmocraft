package org.unallied.mmocraft.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.TrueTypeFont;

/**
 * Stores all fonts in a map for easy access.  If the font requested is not
 * in the map, it will attempt to query the server to obtain the image.
 * This implements the Singleton pattern.
 * @author Faythless
 *
 */
public class FontHandler {
    private Map<String, Font> fonts = new ConcurrentHashMap<String, Font>(8, 0.9f, 1);
    
    /**
     * Initializes the FontHandler with the default fonts.
     */
    private FontHandler() {
        init();
    }
    
    private static class FontHandlerHolder {
        public static final FontHandler instance = new FontHandler();
    }
    
	private void init() {
        try {
            TrueTypeFont uFont;
            
            try {
                uFont = new TrueTypeFont(new java.awt.Font("Tahoma", 0, 10), false);
                fonts.put( FontID.TOOLTIP_DEFAULT.toString(), uFont );
                fonts.put( FontID.STATIC_TEXT_SMALL.toString(), uFont);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            try {
                uFont = new TrueTypeFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 10), false);
                fonts.put( FontID.TOOLTIP_DEFAULT.toString(), uFont );
                fonts.put( FontID.STATIC_TEXT_SMALL_BOLD.toString(), uFont);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            
            try {
                uFont = new TrueTypeFont(new java.awt.Font("Tahoma", 0, 11), false);
                fonts.put( FontID.TOOLTIP_DEFAULT.toString(), uFont );
                fonts.put( FontID.STATIC_TEXT_MEDIUM_SMALL.toString(), uFont);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            try {
                uFont = new TrueTypeFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 11), false);
                fonts.put( FontID.TOOLTIP_DEFAULT.toString(), uFont );
                fonts.put( FontID.STATIC_TEXT_MEDIUM_SMALL_BOLD.toString(), uFont);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            
            try {
                uFont = new TrueTypeFont(new java.awt.Font("Tahoma", 0, 12), false);
                fonts.put( FontID.TOOLTIP_DEFAULT.toString(), uFont );
                fonts.put( FontID.STATIC_TEXT_MEDIUM.toString(), uFont);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            try {
                uFont = new TrueTypeFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12), false);
                fonts.put( FontID.TOOLTIP_DEFAULT.toString(), uFont );
                fonts.put( FontID.STATIC_TEXT_MEDIUM_BOLD.toString(), uFont);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            
            try {
              uFont = new TrueTypeFont(new java.awt.Font("Tahoma", 0, 13), false);
              fonts.put( FontID.STATIC_TEXT_MEDIUM_LARGE.toString(), uFont );
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            
            try {
                uFont = new TrueTypeFont(new java.awt.Font("Tahoma", 0, 15), false);
                fonts.put( FontID.STATIC_TEXT_LARGE.toString(), uFont );
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            
            try {
                uFont = new TrueTypeFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 15), false);
                fonts.put( FontID.STATIC_TEXT_LARGE_BOLD.toString(), uFont );
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Draws a message to the screen.  If the font is not in the map,
     * it will be queried from the server.
     * @param key The string identifier of the font to draw.
     * @param message The message to write to the screen
     * @param x The x position for drawing to the screen
     * @param y The y position for drawing to the screen
     * @param col The color of the string
     * @param width The maximum width of a line
     * @param height The maximum height of a line
     * @param wordWrap Whether to wrap the text or not
     * @return y + the offset from the text drawn.  This can be used to determine
     *         where to render the next line.
     */
    public int draw(String key, String message, float x, float y, Color col,
    		int width, int height, boolean wordWrap) {
    	int newY = (int)y;
        if( fonts.containsKey(key) && fonts.get(key) != null ) {
            fonts.get(key).drawString(x, y, message, col);
            newY += fonts.get(key).getLineHeight();
        } else {
            // FIXME:  Query from the server
        }
        
        return newY;
    }
    
    public static FontHandler getInstance() {
        return FontHandlerHolder.instance;
    }

    /**
     * Returns the maximum width needed to draw this <code>message</code>.<br />
     * If the key isn't found, 0 is returned.
     * @param key The string identifier of the font to draw.
     * @param message The message to get the width of.
     * @return width
     */
    public int getMaxWidth(String key, String message) {
        if (fonts.containsKey(key) && fonts.get(key) != null) {
            String[] lines = message.split("\n");
            int maxWidth = 0;
            for (String line : lines) {
                int lineWidth = fonts.get(key).getWidth(line);
                if (lineWidth > maxWidth) {
                    maxWidth = lineWidth;
                }
            }
                
            return maxWidth;
        }
        return 0;
    }
    
    /**
     * Returns the maximum height needed to draw this <code>message</code>.
     * @param key The string identifier of the font to draw.
     * @param message The message to get the height of.
     * @return height
     */
    public int getMaxHeight(String key, String message) {
        if (fonts.containsKey(key) && fonts.get(key) != null) {
            return fonts.get(key).getHeight(message);
        }
        return 0;
    }

    /**
     * Retrieves the font of the given key.  If no font is found, returns null.
     * @param key The font's key
     * @return font
     */
    public Font getFont(String key) {
        return fonts.get(key);
    }

    /**
     * Retrieve the maximum height of any line drawn by this font.  If the font
     * doesn't exist, 0 is returned.
     * @param key The string identifier of the font to get the line height of.
     * @return lineHeight
     */
    public int getLineHeight(String key) {
        if (fonts.containsKey(key) && fonts.get(key) != null) {
            return fonts.get(key).getLineHeight();
        }
        return 0;
    }
}
