package chess.entities;

import org.newdawn.slick.opengl.Texture;

import chess.util.BoardUtil;

public abstract class AbstractTileEntity extends AbstractEntity implements TileEntity
{
	protected static int tileSize = 70;
	
	public AbstractTileEntity(int xCoord, int yCoord, Texture texture) {
		super(xCoord * tileSize, yCoord * tileSize, tileSize, tileSize, texture);
	}

	public static void setTileSize(int size)
	{
		tileSize = size;
	}
	
	public static int getTileSize()
	{
		return tileSize;
	}
}
