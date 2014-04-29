package chess.entities.board;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import chess.entities.AbstractBoardTileEntity;
import chess.entities.board.pieces.EntityChessPiece;
import chess.main.Chess;
import chess.util.BoardUtil;

public class EntityBoardTile extends AbstractBoardTileEntity
{

	private static Map<String, EntityBoardTile> board_tiles = new HashMap<String, EntityBoardTile>();
	
	public EntityBoardTile(int xCoord, int yCoord, Texture texture) 
	{
		super(xCoord, yCoord, texture);
	}

	@Override
	public void update(int delta) 
	{
	}
	
	public static EntityBoardTile getBoardTile(String coordinates) 
	{
		return board_tiles.get(coordinates);
	}

	public static void addBoardTile(String coordinates, EntityBoardTile tile) 
	{
		board_tiles.put(coordinates, tile);
	}
	
	public static void removeBoardTile(String coordinates)
	{
		board_tiles.remove(coordinates);
	}
	
	public static void clearBoardTiles()
	{
		board_tiles.clear();
	}
	
	public static void drawBoard()
	{
		
		for(int x = 1; x <= 8; x++) //Note: changing these for-loops will result in the need to change the loops in the setupBoard() method of the Board class
		{
			for(int y = 8; y >= 1; y--)
			{
				EntityBoardTile tile = EntityBoardTile.getBoardTile(BoardUtil.getCoordinatesToString(x, y));
				tile.draw();
			}
		}
	}
}
