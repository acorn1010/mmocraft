package org.unallied.mmocraft.blocks;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;
import org.unallied.mmocraft.BlockType;
import org.unallied.mmocraft.client.ImageHandler;
import org.unallied.mmocraft.client.ImageID;
import org.unallied.mmocraft.items.ItemData;

public class AirGreenBlock extends Block {
    private static final BlockType type = BlockType.AIR;

    public AirGreenBlock() {
    }

    @Override
    public Image getImage() {
        return ImageHandler.getInstance().getImage(ImageID.BLOCK_AIR.toString());
    }

    @Override
    public BlockType getType() {
        return type;
    }
    
    @Override
    public boolean isCollidable() {
        return false; // You can't collide with air!
    }
    
    @Override
    public long getMaximumHealth() {
    	return 0;
    }

    @Override
    public Block getCopy() {
        return new AirGreenBlock();
    }
    
    @Override
    /**
     * 
     * @param g
     * @param x the x position (in pixels) to use when drawing this block.
     * Far left is 0 x.  (Screen coordinates)
     * @param y the y position (in pixels) to use when drawing this block.
     * Top is 0 y.  (Screen coordinates)
     */
    public void render(Graphics g, int x, int y) {
        g.translate(x, y);
        g.fill(new Rectangle(0, 0, 16, 16), new GradientFill(0, 0, new Color(0, 255, 0), 8, 8, new Color(0, 150, 0)) );
        g.translate(-x, -y);
    }

    @Override
    public ItemData getItem() {
        return null;
    }
}
