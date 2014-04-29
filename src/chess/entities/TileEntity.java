package chess.entities;

import chess.entities.board.EntityBoardTile;

public interface TileEntity extends Entity 
{
	public int getCoordinateY();
	public int getCoordinateX();
	public char getCoordinateXToChar();
}
