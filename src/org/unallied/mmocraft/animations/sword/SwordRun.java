package org.unallied.mmocraft.animations.sword;

import org.newdawn.slick.Animation;
import org.unallied.mmocraft.Direction;
import org.unallied.mmocraft.Living;
import org.unallied.mmocraft.animations.AnimationID;
import org.unallied.mmocraft.animations.AnimationState;
import org.unallied.mmocraft.client.SpriteHandler;
import org.unallied.mmocraft.client.SpriteID;
import org.unallied.mmocraft.client.SpriteSheetNode;

/**
 * Animation state when a player is running
 * @author Faythless
 *
 */
public class SwordRun extends AnimationState {

    /**
     * 
     */
    private static final long serialVersionUID = -6030014058203122053L;

    public SwordRun(Living player, AnimationState last) {
        super(player, last);
        animation = new Animation();
        animation.setAutoUpdate(false);
        animation.setLooping(isLooping());
        SpriteSheetNode node = SpriteHandler.getInstance().getNode(SpriteID.SWORD_RUN.toString());
        this.collision = node.getCollision();
        width = node.getWidth();
        height = node.getHeight();
        setAnimation(node.getSpriteSheet());
        animation.start();
        horizontalOffset = 24;
        verticalOffset = 1;
    }

    @Override
    public void idle() {
        living.setState(new SwordIdle(living, this));
    }

    @Override
    public void moveLeft(boolean smash) {
        living.updateDirection(Direction.LEFT);
    }

    @Override
    public void moveRight(boolean smash) {
        living.updateDirection(Direction.RIGHT);
    }

    @Override
    public void moveUp(boolean smash) {
        living.setState(new SwordJump(living, this));
    }

    @Override
    public void moveDown(boolean smash) {
        // TODO Auto-generated method stub

    }

    @Override
    public void attack() {
        living.setState(new SwordHorizontalAttack(living, this));
    }

    @Override
    public void shield() {
        living.setState(new SwordShield(living, this));
    }
    
    @Override
    public boolean canMoveLeft() {
        return living.getDirection() == Direction.LEFT;
    }
    
    @Override
    public boolean canMoveRight() {
        return living.getDirection() == Direction.RIGHT;
    }
    
    @Override
    /**
     * Returns whether or not this character is in a state which can move up
     * @return
     */
    public boolean canMoveUp() {
        return true;
    }

    @Override
    public void land() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void fall() {
        living.setState(new SwordFall(living, this));
    }

    @Override
    public void shieldOff() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public short getId() {
        return AnimationID.SWORD_RUN.getValue();
    }

    @Override
    public void die() {
        living.setState(new SwordDead(living, this));
    }

    @Override
    public boolean isLooping() {
        return true;
    }
}
