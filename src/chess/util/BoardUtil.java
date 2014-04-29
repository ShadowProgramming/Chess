package chess.util;

import org.lwjgl.opengl.GL11;

import chess.entities.AbstractBoardTileEntity;
import chess.entities.AbstractTileEntity;
import chess.util.Exceptions.InvalidCoordinateException;

public class BoardUtil 
{	
	public static String getCoordinatesToString(int x, int y)
	{
		if(x < 1 || x > 8 || y < 1 || y > 8) throw new InvalidCoordinateException();
		return "" + getXToChar(x) + y;
	}
	
	public static char getXToChar(int x)
	{	
		if(x > 8 || x < 1)  throw new InvalidCoordinateException();
		return (char) (Character.getNumericValue(Integer.toString(x - 1).charAt(0)) + 49 + 16);
	}
	
	public static int getXToInteger(char x)
	{
		if(x > 'H' || x < 'A')  throw new InvalidCoordinateException();
		return Character.getNumericValue(x) - 9;
	}
	
	public static void rotate(double rotation, AbstractBoardTileEntity entity)
	{
		GL11.glTranslated(-1*(-entity.getX()-AbstractTileEntity.getTileSize()/2), -1*(-entity.getY()-AbstractTileEntity.getTileSize()/2), 0);
		GL11.glRotated(rotation, 0, 0, 1); 
		GL11.glTranslated(1*(-entity.getX()-AbstractTileEntity.getTileSize()/2), 1*(-entity.getY()-AbstractTileEntity.getTileSize()/2), 0);
	}
}
