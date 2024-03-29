package org.unallied.mmocraft.constants;


/**
 * Contains all world-related constants, such as number of blocks in a chunk, etc.
 * @author Alexandria
 *
 */
public abstract class WorldConstants {
    /**
     *  The width (in blocks) of a block "chunk"
     */
    public static final int WORLD_CHUNK_WIDTH = 64;
    
    /**
     *  The height (in blocks) of a block "chunk"
     */
    public static final int WORLD_CHUNK_HEIGHT = 64;
    
    /**
     *  The width (in blocks) of a block "region."  Regions contain names,
     *  weather effects, terrain style, unique enemies, and so on.
     */
    public static final int WORLD_REGION_WIDTH = 20 * WORLD_CHUNK_WIDTH;
    
    /** 
     * Height (in blocks) of a block "region"
     */
    public static final int WORLD_REGION_HEIGHT = 100 * WORLD_CHUNK_HEIGHT;
    
    /**
     *  Width of the game world in blocks.  Use 65 for production for a
     *  world size of about 2 GB.
     */
    public static final int WORLD_WIDTH = 1 * WORLD_REGION_WIDTH; //30 * WORLD_REGION_WIDTH;
    
    /**
     *  Height of the game world in blocks.  Leave this at 1 for now.
     */
    public static final int WORLD_HEIGHT = 1 * WORLD_REGION_HEIGHT;
    
    /**
     *  The width (in pixels) of a block.
     */
    public static final int WORLD_BLOCK_WIDTH = 16;
    
    /**
     *  The height (in pixels) of a block.
     */
    public static final int WORLD_BLOCK_HEIGHT = 16;
    
    /**
     *  The number of chunks, lined from end to end, that it takes to span 
     *  the width of the world
     */
    public static final int WORLD_CHUNKS_WIDE = WORLD_WIDTH / WORLD_CHUNK_WIDTH;
    
    /**
     *  The number of chunks, lined end to end, that it takes to span the 
     *  height of the world
     */
    public static final int WORLD_CHUNKS_TALL = WORLD_HEIGHT / WORLD_CHUNK_HEIGHT;

    /**
     * The number of regions, lined from end to end, that it takes to span
     * the width of the world
     */
    public static int WORLD_REGIONS_WIDE = WORLD_WIDTH / WORLD_REGION_WIDTH;
    
    /**
     * The number of regions, linked end to end, that it takes to span the
     * height of the world
     */
    public static int WORLD_REGIONS_TALL = WORLD_HEIGHT / WORLD_REGION_HEIGHT;
    
    /**
     * The stepping to use for the world.  Lower values make the world appear larger
     * (player is smaller), and higher values make the world appear smaller.
     */
    public static final double WORLD_STEP = 100;
    
    /**
     * Weighting to use to prevent "floating" blocks and strangeness.  Used
     * during the world creation.
     */
    public static final double WORLD_WEIGHT = 100.0;

    /**
     *  The number of chunks (radius) to draw.  Right now we're just using squares.
     */
//    public static final int WORLD_DRAW_DISTANCE = 1;
    
    /**
     * The number of worms to generate throughout the world.  A worm is land
     * that has been "tunneled" through as if by a worm.
     */
    public static final long WORM_COUNT = (long) (WORLD_WIDTH) * (long) (WORLD_HEIGHT) / 1000000L;
    
    /**
     * A value between 1 and 99999 should be used.  Higher values will cause 
     * worms to be longer on average.
     */
    public static final int WORM_LENGTH = 99990;
    
    /**
     * The rate at which the worm's radius changes.  Should be between 1 and 99.
     * Low values cause the worm to slowly change its radius, and higher values
     * cause it to rapidly change its radius.
     */
    public static final int WORM_RADIUS_CHANGE_RATE = 20;
    
    /**
     * The rate at which the worm's direction changes.  Should be between 1 and 99.
     * Low values cause the worm to slowly change direction, and higher values
     * cause it to rapidly change direction.
     */
    public static final int WORM_DIRECTION_CHANGE_RATE = 19;

    /**
     * The distance that the server uses to determine which players are "nearby."
     * The width / height in chunks is determined by:
     * (<code>WORLD_DRAW_DISTANCE</code>*2+1)
     * You can think of this value as the "radius" in chunks between the player and whatever else.
     */
	public static final int WORLD_DRAW_DISTANCE = 2;
	
	/** The maximum visible depth before all blocks are rendered with full shadows. */
	public static final int WORLD_BLOCK_SHADOW_DEPTH = 9;
	
	/** 
	 * The maximum amount of shadow to apply to a block.  Values range between 0 and 1 inclusive,
	 * with 1 being pure black and 0 being no shadow at all. 
	 */
	public static final float WORLD_BLOCK_MAX_SHADOW = 0.976f;

	/** 
	 * True if we are displaying shadows of the world terrain, else false. 
	 * Later on we should allow the player to decide through a configuration menu.
	 */
    public static final boolean WORLD_SHADOWS_ENABLED = true;
    
    /** 
     * The distance (in blocks) between the player's location and the farthest
     * location that they're allowed to place a block.
     */
    public static final double MINIMUM_BLOCK_PLACEMENT_RADIUS = 5.0;
}
