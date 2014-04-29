package chess.entities.board;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import chess.entities.AbstractBoardTileEntity;
import chess.entities.TileEntity;
import chess.entities.board.pieces.EntityChessPiece;
import chess.entities.board.pieces.EnumPiece;
import chess.entities.board.pieces.EnumPiece.Team;
import chess.main.Chess;
import chess.util.BoardUtil;
import chess.util.Exceptions.InvalidMovementException;

public abstract class AbstractChessPieceEntity extends AbstractBoardTileEntity implements TileEntity
{	
	protected static Map<String, AbstractChessPieceEntity> black_pieces = new HashMap<String, AbstractChessPieceEntity>();
	protected static Map<String, AbstractChessPieceEntity> white_pieces = new HashMap<String, AbstractChessPieceEntity>();
	
	private int animationLoop = 1 * getTileSize();
	private double animationSpeed = ((-0.5 / getTileSize()) * animationLoop) + 1.5;
	
	protected EnumPiece piece;
	
	public AbstractChessPieceEntity(int xCoord, int yCoord, EnumPiece piece) 
	{
		super(xCoord, yCoord, piece.getTexture());
		this.piece = piece;
	}
	
	@Override
	public void update(int delta)
	{
		super.update(delta);
		drawPieces();
	}
	
	@Override
	public void setLocation(String coordinates)
	{
		removePiece(this);
		coordinateX = BoardUtil.getXToInteger(coordinates.charAt(0));
		coordinateY = inverseFactor - Integer.parseInt(coordinates.substring(1, 2));
		addPiece(coordinates, this);
	}
	
	public void movePiece(String coordinates)
	{
		boolean[] moveDirection = {false, false, false, false, false}; //Diagonal, Vertical, Horizontal, WideL, LongL

		int x = this.getCoordinateX();
		int y = this.getCoordinateY();
		int toX = BoardUtil.getXToInteger(coordinates.charAt(0));
		int toY = Integer.parseInt(coordinates.substring(1, 2));
		
		boolean diagnalFlag = false;
		
		for(int i = 1; i <= 8; i++)
		{	
			if(x + i == toX && y + i == toY)
				diagnalFlag = true;
			if(x - i == toX && y - i == toY)
				diagnalFlag = true;
			if(x + i == toX && y - i == toY)
				diagnalFlag = true;
			if(x - i == toX && y + i == toY)
				diagnalFlag = true;
		}
		
		if(diagnalFlag) moveDirection[0] = true;
		else 
			if(x == toX) moveDirection[1] = true;
		else
			if(y == toY) moveDirection[2] = true;
		else
		{
			if(((y + 1) == toY || (y - 1) == toY) && 
					((x + 2) == toX || (x - 2) == toX)) moveDirection[3] = true;
			else
				if(((x + 1) == toX || (x - 1) == toX) && 
						((y + 2) == toY || (y - 2) == toY)) moveDirection[4] = true;
		}
		
		if(moveDirection[0])
		{
			double horizontalDirection = 0, verticalDirection = 0;

			if(x < toX) horizontalDirection = animationSpeed;
			if(x > toX) horizontalDirection = -animationSpeed;
			
			if(y < toY) verticalDirection = animationSpeed;
			if(y > toY) verticalDirection = -animationSpeed;
			
			if(toX - x > 0)
				for(int i = 0; i < (toX - x) * animationLoop; i++)
				{
					animateTile(horizontalDirection, verticalDirection);
				}
			else
				for(int i = 0; i > (toX - x) * animationLoop; i--)
				{
					animateTile(horizontalDirection, verticalDirection);
				}
		}
		else if(moveDirection[1])
		{
			double direction = 0;
			
			if(y < toY) direction = animationSpeed;
			else direction = -animationSpeed;
			
			if(direction > 0)
				for(int i = 0; i < (toY - y) * animationLoop; i++)
				{
					animateTile(0, direction);
				}
			else
				for(int i = 0; i < (toY - y); i--)
				{
					animateTile(0, direction);
				}	
		}
		else if(moveDirection[2])
		{
			double direction = 0;
			
			if(x < toX) direction = animationSpeed;
			else direction = -animationSpeed;
			
			if(direction > 0)
				for(int i = 0; i < (toX - x) * animationLoop; i++)
				{
					animateTile(direction, 0);
				}
			else
				for(int i = 0; i < (toX - x); i--)
				{
					animateTile(direction, 0);
				}	
		}
		else if(moveDirection[3])
		{
			double horizontalDirection = 0, verticalDirection = 0;
			
			if(x < toX) horizontalDirection = animationSpeed;
			if(x > toX) horizontalDirection = -animationSpeed;
			
			if(y < toY) verticalDirection = animationSpeed;
			if(y > toY) verticalDirection = -animationSpeed;
			
			if(verticalDirection > 0)
			{
					for(int i = 0; i < 2 * animationLoop; i++)
						animateTile(horizontalDirection, 0);
					for(int i = 0; i < animationLoop; i++)
						animateTile(0, verticalDirection);
			}
			else
			{
				for(int i = 0; i < animationLoop; i++)
					animateTile(0, verticalDirection);
				for(int i = 0; i < 2 * animationLoop; i++)
					animateTile(horizontalDirection, 0);
			}
		}
		else if(moveDirection[4])
		{
			double horizontalDirection = 0, verticalDirection = 0;
			
			if(x < toX) horizontalDirection = animationSpeed;
			if(x > toX) horizontalDirection = -animationSpeed;
			
			if(y < toY) verticalDirection = animationSpeed;
			if(y > toY) verticalDirection = -animationSpeed;
			
			if(verticalDirection > 0)
			{
					for(int i = 0; i < 2 * animationLoop; i++)
						animateTile(0, verticalDirection);
					for(int i = 0; i < animationLoop; i++)
						animateTile(horizontalDirection, 0);
			}
			else
			{
				for(int i = 0; i < animationLoop; i++)
					animateTile(horizontalDirection, 0);
				for(int i = 0; i < 2 * animationLoop; i++)
					animateTile(0, verticalDirection);
			}
		}
		else
			throw new InvalidMovementException();
		
		resetAnimationFactor();
		setLocation(coordinates);
	}
	
	private void resetAnimationFactor()
	{
		animationFactor[0] = 0;
		animationFactor[1] = 0;
	}
	
	private void animateTile(double x, double y)
	{
		animationFactor[0] += x;
		animationFactor[1] -= y;
		Chess.instance.game.render();
		Chess.instance.update(Chess.instance.timer.getDelta());
	}
	
	public void setAnimationSpeed(int speed)
	{
		if(speed < 1 || speed > 2)
		{
			System.err.println("Speed may not fall below 1 or exceed 2");
			return;
		}
		animationLoop = speed * getTileSize();
	}
	
	public int getPieceValue()
	{
		return piece.getValue();
	}
	
	public EnumPiece getEnumPiece()
	{
		return piece;
	}
	
	public void editPieceLocation(int x, int y)
	{	
		AbstractChessPieceEntity oldPiece = this;
		EntityChessPiece newPiece = new EntityChessPiece(x, y, this.getEnumPiece());
		
		addPiece(BoardUtil.getCoordinatesToString(x, y), newPiece);
		
		removePiece(oldPiece);
	}
	
	public static AbstractChessPieceEntity getPieceByLocation(String coordinate)
	{
		coordinate = coordinate.toUpperCase();
		if(black_pieces.get(coordinate) != null) return black_pieces.get(coordinate);
		if(white_pieces.get(coordinate) != null) return white_pieces.get(coordinate);
		return null;
	}
	
	public static void movePiece(AbstractChessPieceEntity piece, String coordinate)
	{
		piece.editPieceLocation(piece.getCoordinateX(), piece.getCoordinateY() + 1);
	}

	public static void addPiece(String coordinates, AbstractChessPieceEntity piece)
	{
		if(piece.getEnumPiece().getTeam() == Team.BLACK)
			black_pieces.put(coordinates, piece);
		if(piece.getEnumPiece().getTeam() == Team.WHITE)
			white_pieces.put(coordinates, piece);
	}
	
	public static void removePiece(AbstractChessPieceEntity piece)
	{

		if(piece.getEnumPiece().getTeam() == Team.BLACK)
			black_pieces.remove(BoardUtil.getCoordinatesToString(piece.getCoordinateX(), piece.getCoordinateY()));
		if(piece.getEnumPiece().getTeam() == Team.WHITE)
			white_pieces.remove(BoardUtil.getCoordinatesToString(piece.getCoordinateX(), piece.getCoordinateY()));
	}
	
	public static void removePiece(String coordinates)
	{
		if(getPieceByLocation(coordinates).getEnumPiece().getTeam() == Team.BLACK)
			black_pieces.remove(coordinates);
		if(getPieceByLocation(coordinates).getEnumPiece().getTeam() == Team.BLACK)
			white_pieces.remove(coordinates);
	}
	
	public static void clearBlackPieces()
	{
		black_pieces.clear();
	}
	
	public static void clearWhitePieces()
	{
		white_pieces.clear();
	}

	public static void clearAllPieces()
	{
		black_pieces.clear();
		white_pieces.clear();
	}
	
	/**
	 * Iterates through all active chess entities and draws them
	 */
	public static void drawPieces()
	{
		Iterator<Entry<String, AbstractChessPieceEntity>> black_iterator = black_pieces.entrySet().iterator();
		Iterator<Entry<String, AbstractChessPieceEntity>> white_iterator = white_pieces.entrySet().iterator();

		while(white_iterator.hasNext()) 
		{
			Map.Entry<String, AbstractChessPieceEntity> entry = (Map.Entry<String, AbstractChessPieceEntity>)white_iterator.next();
		    entry.getValue().draw();
		    entry.getValue().update(Chess.instance.timer.getDelta());
		}
		while(black_iterator.hasNext()) 
		{
			Map.Entry<String, AbstractChessPieceEntity> entry = (Map.Entry<String, AbstractChessPieceEntity>)black_iterator.next();
		    entry.getValue().draw();
		    entry.getValue().update(Chess.instance.timer.getDelta());
		}
	}
}
