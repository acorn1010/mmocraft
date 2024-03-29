package org.unallied.mmocraft.states;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.unallied.mmocraft.client.Game;
import org.unallied.mmocraft.client.GameState;
import org.unallied.mmocraft.client.ImageHandler;
import org.unallied.mmocraft.client.ImageID;
import org.unallied.mmocraft.client.SpriteHandler;
import org.unallied.mmocraft.constants.ClientConstants;
import org.unallied.mmocraft.gui.GUIElement.Event;
import org.unallied.mmocraft.gui.GUIElement.EventIntf;
import org.unallied.mmocraft.gui.frame.LoginFrame;
import org.unallied.mmocraft.monsters.ClientMonsterManager;
import org.unallied.mmocraft.net.Heartbeat;
import org.unallied.mmocraft.net.PacketCreator;
import org.unallied.mmocraft.net.PacketSender;
import org.unallied.mmocraft.sessions.LoginSession;

public class LoginState extends AbstractState {

	private int stateID = GameState.LOGIN;

	private LoginFrame loginFrame = null;
	
	public LoginState() {
		super(null, null, null, 0, 0, Game.getInstance().getWidth(), Game.getInstance().getHeight());
	}

	@Override
	public void keyPressed(int key, char c) {
	    GameContainer container = Game.getInstance().getContainer();
	    Input input = container.getInput();
	    
	    // Check for fullscreen.
        if (key == Input.KEY_ENTER && (input.isKeyDown(Input.KEY_LALT) || input.isKeyDown(Input.KEY_RALT))) {
            toggleFullscreen(container);
            resetGUI(container);
            return;
        }
        
        super.keyPressed(key, c);
	}
	
    /**
     * Resets the GUI elements of this state by destroying and re-initializing them.
     * @param container The container used for resizing the GUI and creating the GUI elements.
     */
	public void resetGUI(GameContainer container) {
	    resize(container);
	    // Set GUI elements
        if( orderedFrames.getFrameCount() > 0 ) {
            orderedFrames.destroy();
            orderedFrames.removeAllFrames();
        }
        loginFrame = new LoginFrame(orderedFrames, new EventIntf(){

            @Override
            public void callback(Event event) {
                switch( event.getId() ) {
                case LOGIN_CLICKED:
                    // Set user / pass for the loginSession
                    LoginSession loginSession = Game.getInstance().getClient().loginSession;
                    loginSession.setUsername(loginFrame.getUsername());
                    loginSession.setPassword(loginFrame.getPassword());

                    // Initiate communication to the server for the login
                    if (loginFrame.getUsername().length() > 0 && loginFrame.getPassword().length() > 0) {
                        Game.getInstance().getClient().announce(
                                PacketCreator.getLogon(loginSession.getUsername()));
                    }
                    break;
                case REGISTER_CLICKED:
                    Game.getInstance().enterState(GameState.REGISTER);
                    break;
                }
            }

        }, container, 0, 0, -1, -1);
        loginFrame.setX( (Game.getInstance().getWidth() - loginFrame.getWidth()) / 2);
        loginFrame.setY( (Game.getInstance().getHeight() - loginFrame.getHeight()) / 2);

        // Controls
        orderedFrames.addFrame(loginFrame);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		// Remove all cached chunks.
		Game.getInstance().getClient().getTerrainSession().clear();
		// Enable anti-aliasing
		container.getGraphics().setAntiAlias(true);
		resetGUI(container);
		container.getInput().enableKeyRepeat();
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	    ClientMonsterManager.getInstance().load(ClientConstants.MONSTER_PACK_LOCATION);
		// Initialize the packet sending thread
		(new Thread(new PacketSender())).start();
		(new Thread(Heartbeat.getInstance())).start();
		SpriteHandler.getInstance(); // init
		if (container != null) {
			container.setVerbose(false);
			container.setShowFPS(false);
			container.setAlwaysRender(true);
			container.setUpdateOnlyWhenVisible(false);
			container.setTargetFrameRate(Game.MAX_FPS);
			container.setClearEachFrame(false);
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		if(orderedFrames.getFrameCount() > 0 ) {
			if (loginFrame != null) {
				loginFrame.destroy();
			}
			
			// Controls
			orderedFrames.removeAllFrames();
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
        if (container.getWidth() != Display.getWidth() || 
                container.getHeight() != Display.getHeight() ||
                container.getWidth() != container.getGraphics().getWidth() ||
                container.getHeight() != container.getGraphics().getHeight()) {
            resetGUI(container);
        }
		orderedFrames.update(container);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		Image image = ImageHandler.getInstance().getImage(ImageID.LOGIN_SCREEN.toString());
		if( image != null) {
		    long curTime = System.currentTimeMillis();
            int backgroundOffsetX = (int) ((curTime % (image.getWidth() * ClientConstants.BACKGROUND_SCROLL_RATE_X)) / ClientConstants.BACKGROUND_SCROLL_RATE_X);
            int backgroundOffsetY = (int) ((curTime % (image.getHeight() * ClientConstants.BACKGROUND_SCROLL_RATE_Y)) / ClientConstants.BACKGROUND_SCROLL_RATE_Y);
            
			// Tile the login state across the game
            final int imageWidth = image.getWidth();
            final int imageHeight = image.getHeight();
            image.startUse();
			for (int i = orderedFrames.getAbsoluteX() - backgroundOffsetX; i < container.getWidth(); i += imageWidth) {
				for (int j = orderedFrames.getAbsoluteY() - backgroundOffsetY; j < container.getHeight(); j += imageHeight) {
					image.drawEmbedded(i, j, imageWidth, imageHeight);
				}
			}
			image.endUse();
		}
		
		Image title = ImageHandler.getInstance().getImage(ImageID.LOGIN_TITLE.toString());
		if (title != null) {
		    title.draw(container.getWidth() / 2 - title.getWidth() / 2, container.getHeight() / 2 - title.getHeight() - 100);
		}

		orderedFrames.render(container, game, g);
	}
}
