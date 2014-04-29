package chess.entities.board;

import chess.util.BoardUtil;
import chess.util.TextureUtil;


public class Board 
{
	public static enum BoardAction {NEW_BOARD, SET_EMPTY_BOARD, LOAD_SAVED_BOARD}
	
	public static void setupBoard(BoardAction action)
	{
		if(action == BoardAction.NEW_BOARD)
		{
			EntityBoardTile.clearBoardTiles();
			EntityBoardTile tile;
			for(int x = 1; x <= 8; x++) //Note: changing these for-loops will result in the need to change the loops in the drawBoard() method of the EntityBoardTile class
				for(int y = 8; y >= 1; y--)
				{
					if((x % 2 == 1 && y % 2 != 1) || (x % 2 != 1 && y % 2 == 1))
						tile = new EntityBoardTile(x, 9 - y, TextureUtil.tile_black); //Subtract y from 9 to translate y into standard chess-board coordinates
					else
						tile = new EntityBoardTile(x, 9 - y, TextureUtil.tile_white);
					
					EntityBoardTile.addBoardTile(BoardUtil.getCoordinatesToString(tile.getCoordinateX(), tile.getCoordinateY()), tile);
				}
		}
	}
}
