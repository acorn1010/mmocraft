package org.unallied.mmocraft.animations.sword;

import org.newdawn.slick.Animation;
import org.unallied.mmocraft.Living;
import org.unallied.mmocraft.animations.AnimationID;
import org.unallied.mmocraft.animations.AnimationState;
import org.unallied.mmocraft.client.SpriteHandler;
import org.unallied.mmocraft.client.SpriteID;
import org.unallied.mmocraft.client.SpriteSheetNode;


/**
 * Animation state when a sword user is dead (0 HP).
 * @author Faythless
 *
 */
public class SwordDead extends AnimationState {

    /**
     * 
     */
    private static final long serialVersionUID = -6110866984051635635L;

    public SwordDead(Living player, AnimationState last) {
        super(player, last);
        animation = new Animation();
        animation.setAutoUpdate(false);
        animation.setLooping(true);
        SpriteSheetNode node = SpriteHandler.getInstance().getNode(SpriteID.SWORD_IDLE.toString());
        width = node.getWidth();
        height = node.getHeight();
        setAnimation(node.getSpriteSheet());
        animation.start();
        horizontalOffset = 26;
        verticalOffset = 6;
        canDoubleJump = true; // reset double jump.
    }

    @Override
    public void idle() {
    }

    @Override
    public void moveLeft(boolean smash) {
    }

    @Override
    public void moveRight(boolean smash) {
    }

    @Override
    public void moveUp(boolean smash) {
    }

    @Override
    public void moveDown(boolean smash) {
    }

    @Override
    /**
     * Returns whether or not this character is in a state which can move left
     * @return
     */
    public boolean canMoveLeft() {
        return false;
    }
    
    @Override
    /**
     * Returns whether or not this character is in a state which can move right
     * @return
     */
    public boolean canMoveRight() {
        return false;
    }
    
    @Override
    /**
     * Returns whether or not this character is in a state which can move up
     * @return
     */
    public boolean canMoveUp() {
        return false;
    }
    
    @Override
    /**
     * Returns a multiplier for the movement speed downwards.  Default is 1.0.
     * This should be multiplied by the player's movement speed to determine
     * how fast they're moving.  A result of 0 signifies that the player is
     * unable to move down.
     * @return downMultiplier
     */
    public float moveDownMultiplier() {
        return 0;
    }
    
    @Override
    public void attack() {
    }

    @Override
    public void shield() {
    }

    @Override
    public void land() {
    }

    @Override
    public void fall() {
    }

    @Override
    public void shieldOff() {
    }

    @Override
    public short getId() {
        return AnimationID.SWORD_DEAD.getValue();
    }

    @Override
    public void die() {
    }

}