package chess.main;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.ImageIOImageData;

import chess.entities.board.Board;
import chess.entities.board.Board.BoardAction;
import chess.entities.board.EntityBoardTile;
import chess.entities.board.pieces.EntityChessPiece;
import chess.gui.GuiMainMenu;
import chess.input.InputHandler;
import chess.system.GameState;
import chess.system.Timer;
import chess.util.RenderUtil;
import chess.util.TextureUtil;

public class Chess 
{
	
	public static Chess instance;
	
	public Game game;
	public Timer timer;
	public InputHandler input;
	public GameState state;
	public GuiMainMenu mainmenu;
	
	private void start() 
	{
		initDisplay();
		initTextures();
		initObjects();
		
		gameLoop();
		dispose();
	}
	
	private void gameLoop()
	{

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND); 
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		timer.getDelta();

		while(!Display.isCloseRequested()) 
		{
			glClear(GL_COLOR_BUFFER_BIT);
			input.update();
			
			if(state == GameState.INTRO)
			{
				//TODO Intro video
				state = GameState.MAIN_MENU;
			}
			
			if(state == GameState.MAIN_MENU)
			{
				mainmenu.update(timer.getDelta());

				if(mainmenu.isStartRequested())
				{
					mainmenu.resetRequests();
					state = GameState.GAME;
				}
			}
			
			if(state == GameState.GAME)
			{
				game.update(timer.getDelta());
				//EntityBoardTile.clearBoardTiles();
				//Board.setupBoard(BoardAction.NEW_BOARD);
			}

			update(timer.getDelta());
		}
	}
	
	public void update(int delta)
	{
		input.update();
		render();
		Display.update();
		Display.sync(120);
	}
	
	public void render()
	{
		if(Mouse.isGrabbed())
			RenderUtil.renderMouse(TextureUtil.cursor_default);
	}
	
	private void initObjects()
	{
		instance = this;
		timer = new Timer();
		input = new InputHandler();
		state = GameState.INTRO;
		mainmenu = new GuiMainMenu();
		game = new Game();
	}
	
	private void initTextures()
	{
		//------------------------ Interfaces ------------------------
		TextureUtil.main_menu = TextureUtil.loadTexture("menu/main_menu");
		TextureUtil.button = TextureUtil.loadTexture("menu/button");
		TextureUtil.button_overlay = TextureUtil.loadTexture("menu/button_overlay");
		TextureUtil.button_overlay_disabled = TextureUtil.loadTexture("menu/button_overlay_disabled");
		TextureUtil.dropdown_menu_empty = TextureUtil.loadTexture("menu/dropdown_menu_empty");
		TextureUtil.dropdown_menu_credits = TextureUtil.loadTexture("menu/dropdown_menu_credits");

		TextureUtil.cursor_default = TextureUtil.loadTexture("icons/cursor");
		
		//------------------------ Tiles ------------------------
		TextureUtil.tile_white = TextureUtil.loadTexture("tiles/tile_white");
		TextureUtil.tile_black = TextureUtil.loadTexture("tiles/tile_black");
		
		TextureUtil.overlay_tile_hover_reject = TextureUtil.loadTexture("tiles/tile_hover_reject");
		TextureUtil.overlay_tile_hover_accept = TextureUtil.loadTexture("tiles/tile_hover_accept");
		TextureUtil.overlay_tile_hover_neutral = TextureUtil.loadTexture("tiles/tile_hover_neutral");
	
		TextureUtil.move_location = TextureUtil.loadTexture("tiles/move_location");
	
		//------------------------ Pieces ------------------------
		TextureUtil.pawn_black = TextureUtil.loadTexture("pieces/pawn_black");
		TextureUtil.bishop_black = TextureUtil.loadTexture("pieces/bishop_black");
		TextureUtil.knight_black = TextureUtil.loadTexture("pieces/knight_black");
		TextureUtil.rook_black = TextureUtil.loadTexture("pieces/rook_black");
		TextureUtil.queen_black = TextureUtil.loadTexture("pieces/queen_black");
		TextureUtil.king_black = TextureUtil.loadTexture("pieces/king_black");
		
		TextureUtil.pawn_white = TextureUtil.loadTexture("pieces/pawn_white");
		TextureUtil.bishop_white = TextureUtil.loadTexture("pieces/bishop_white");
		TextureUtil.knight_white = TextureUtil.loadTexture("pieces/knight_white");
		TextureUtil.rook_white = TextureUtil.loadTexture("pieces/rook_white");
		TextureUtil.queen_white = TextureUtil.loadTexture("pieces/queen_white");
		TextureUtil.king_white = TextureUtil.loadTexture("pieces/king_white");
		
		//------------------------ Backgrounds / Senery ------------------------
		TextureUtil.game_background = TextureUtil.loadTexture("game_background");
		
		//------------------------ Effects ------------------------
		TextureUtil.magic_circle_purple= TextureUtil.loadTexture("tiles/mgs");
	}

	private static void initDisplay() 
	{
		System.err.println("Initiating Display...");
		try {
			Display.setDisplayMode(new DisplayMode(750, 700));
			Display.setTitle("Chess");
			Display.setIcon(new ByteBuffer[] {new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("res/icons/icon.png")), false, false, null)}); //DISPLACON
			Display.create();
			//Mouse.setGrabbed(true);
		} catch (LWJGLException e) {
			Logger.getLogger(Chess.class.getName()).log(Level.SEVERE, null, e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void dispose() 
	{
		Display.destroy();
		System.exit(0);
	}
	
	public static void main(String args[]) 
	{
		Chess game = new Chess();
		game.start();
	}
}
