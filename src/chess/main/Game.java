package chess.main;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import chess.entities.board.AbstractChessPieceEntity;
import chess.entities.board.Board;
import chess.entities.board.EntityBoardTile;
import chess.entities.board.Board.BoardAction;
import chess.entities.board.pieces.EntityChessPiece;
import chess.entities.board.pieces.Pieces;
import chess.entities.board.pieces.Pieces.PiecesAction;
import chess.system.GameLogic;
import chess.util.RenderUtil;
import chess.util.TextureUtil;

public class Game implements GameLogic
{
	private boolean flagPause, flagSideMenu;
	public static AbstractChessPieceEntity piece, piece2;
	public Game()
	{
		Board.setupBoard(BoardAction.NEW_BOARD);
		Pieces.setupPieces(PiecesAction.SET_NEW_GAME);
		
		flagPause = false;
		flagSideMenu = false;
		piece = EntityChessPiece.getPieceByLocation("A2");
	}

	@Override
	public void update(int delta) 
	{
		piece.movePiece("B4");
		
		render();
		updateMenus();
	}
	
	private void updateMenus()
	{
		handleGeneralMenu();
		handlePauseMenu();
	}
	
	private void handleGeneralMenu()
	{
		if(Mouse.getX() <= 2 && -(Mouse.getY() - Display.getHeight() + 1) <= 2)
		{
			RenderUtil.renderWithDropdownAnimation(TextureUtil.dropdown_menu_credits);
		}
		else
		{
			RenderUtil.resetAnimation();
		}
	}
	
	private void handlePauseMenu()
	{
		
	}

	@Override
	public void render() 
	{
		RenderUtil.drawFullScreenImage(TextureUtil.game_background);
		EntityBoardTile.drawBoard();
		EntityChessPiece.drawPieces();
	}

	@Override
	public boolean noFlagsTriggered() 
	{
		return (!flagPause && !flagSideMenu) ? true : false;
	}

	@Override
	public void resetRequests() 
	{
		
	}
}
