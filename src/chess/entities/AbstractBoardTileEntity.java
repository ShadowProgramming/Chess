package chess.entities;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import chess.entities.board.AbstractChessPieceEntity;
import chess.entities.board.pieces.EntityChessPiece;
import chess.entities.board.pieces.EnumPiece.Team;
import chess.main.Chess;
import chess.util.BoardUtil;
import chess.util.TextureUtil;
import chess.util.Exceptions.InvalidMovementException;

public abstract class AbstractBoardTileEntity extends AbstractTileEntity implements TileEntity
{	
	protected int coordinateX;
	protected int coordinateY;
	
	protected final int inverseFactor = 9; //Inverts Y-Coordinates to match a chess board
	
	protected static double xShift = 0.4, yShift = 1, boardShift = 0;
	
	private double rotate = 0.0;
	private double xx = (coordinateX + xShift + getBoardShift()) * getTileSize();
	private double yy = (coordinateY + yShift + getBoardShift()) * getTileSize();

	protected double[] animationFactor = {0, 0};
	
	private static boolean wasLastHoverChessPiece = false;
	protected boolean isSelected = false;
	
	
	public AbstractBoardTileEntity(int xCoord, int yCoord, Texture texture) {
		super(xCoord * tileSize, yCoord * tileSize, texture);
		this.coordinateX = xCoord;
		this.coordinateY = inverseFactor - yCoord;
	}
	
	@Override
	public void update(int delta)
	{
		if(inBounds(Mouse.getX(), Mouse.getY())) System.out.println("true");
		 System.out.println("false");
	}
	
	@Override
	public void draw() 
	{
		xx = (coordinateX + xShift + getBoardShift()) * getTileSize();
		yy = (coordinateY + yShift + getBoardShift()) * getTileSize();

		GL11.glPushMatrix();
		
		GL11.glBindTexture(GL_TEXTURE_2D, this.getTexture().getTextureID());
		
		if(this.getRotate() != 0)
		{
			BoardUtil.rotate(getRotate(), this);
		}
		
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 0);
			glVertex2d(xx + animationFactor[0], yy + animationFactor[1]);
					
			glTexCoord2f(1, 0);
			glVertex2d(xx + animationFactor[0] + getTileSize(), yy + animationFactor[1]);
					
			glTexCoord2f(1, 1);
			glVertex2d(xx + animationFactor[0] + getTileSize(), yy + animationFactor[1] + getTileSize());
					
			glTexCoord2f(0, 1);
			glVertex2d(xx + animationFactor[0], yy + animationFactor[1] + getTileSize());

		}
		glEnd();
		
		if(inBounds(Chess.instance.input.getMouseHandler().getX(), Display.getHeight() - Chess.instance.input.getMouseHandler().getY() - 1))
		{
			if(this instanceof AbstractChessPieceEntity && ((AbstractChessPieceEntity)this).getEnumPiece().getTeam() == Team.BLACK) 
			{
				GL11.glBindTexture(GL_TEXTURE_2D, TextureUtil.overlay_tile_hover_accept.getTextureID());
			}
			else if(this instanceof AbstractChessPieceEntity && ((AbstractChessPieceEntity)this).getEnumPiece().getTeam() == Team.WHITE)
			{
				GL11.glBindTexture(GL_TEXTURE_2D, TextureUtil.overlay_tile_hover_reject.getTextureID());
			}
			else if(!wasLastHoverChessPiece)
			{
				GL11.glBindTexture(GL_TEXTURE_2D, TextureUtil.overlay_tile_hover_neutral.getTextureID());
			}
			
			glBegin(GL_QUADS);
			{
				glTexCoord2f(0, 0);
				glVertex2d(xx, yy);
						
				glTexCoord2f(1, 0);
				glVertex2d(xx + getTileSize(), yy);
						
				glTexCoord2f(1, 1);
				glVertex2d(xx + getTileSize(), yy + getTileSize());
						
				glTexCoord2f(0, 1);
				glVertex2d(xx, yy + getTileSize());
			}
			glEnd();
			
			if(this instanceof AbstractChessPieceEntity) 
			{
				wasLastHoverChessPiece = true;
			}
			else
			{
				wasLastHoverChessPiece = false;
			}
		}
		GL11.glPopMatrix();
	}
	
	@Override
	public double getX()
	{
		return xx;
	}

	@Override
	public double getY()
	{
		return yy;
	}
	
	
	public void setLocation(String coordinates)
	{
		this.coordinateX = BoardUtil.getXToInteger(coordinates.charAt(0));
		this.coordinateY = inverseFactor - Integer.parseInt(coordinates.substring(1, 2));
	}
	
	public void setLocation(int x, int y)
	{
		this.coordinateX = x;
		this.coordinateY = inverseFactor - y;
	}
	
	public String getLocation()
	{
		return BoardUtil.getCoordinatesToString(getCoordinateX(), getCoordinateY());
	}

	public int getCoordinateY()
	{
		return inverseFactor - coordinateY;
	}
	
	public int getCoordinateX()
	{
		return coordinateX;
	}
	
	public char getCoordinateXToChar()
	{
		return BoardUtil.getXToChar(coordinateX);
	}
	
	public boolean inBounds(int mousex, int mousey)
	{
		mousey = mousey - Display.getHeight() - 1;
		if((mousex > xx && mousex < xx + getTileSize()) && ((mousey > yy && mousey < yy + getTileSize())))
				return true;
		return false;
	}
	
	public void setSelected(boolean selected)
	{
		this.isSelected = selected;
	}
	
	public boolean isSelected()
	{
		return this.isSelected;
	}
	
	public static void shiftBoard(double shift)
	{
		boardShift += shift;
	}
	
	public static void shiftY(double shift)
	{
		yShift += shift;
	}
	
	public static void shiftX(double shift)
	{
		xShift += shift;
	}
	
	
	public static void setYShift(double shift)
	{
		yShift = shift;
	}
	
	public static void setXShift(double shift)
	{
		xShift = shift;
	}
	
	public static void setBoardShift(double shift)
	{
		boardShift = shift;
	}
	
	public static double getBoardShift()
	{
		return boardShift;
	}
	
	public static double getYShift()
	{
		return yShift;
	}
	
	public static double getXShift()
	{
		return xShift;
	}
	
	public void rotate(double rotation)
	{
		rotate += rotation;
	}
	
	public double getRotate()
	{
		return this.rotate;
	}
	
	public void setRotation(double rotation)
	{
		rotate = rotation;
	}
}
