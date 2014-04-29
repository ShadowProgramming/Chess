package chess.entities.board.pieces;

import chess.entities.board.AbstractChessPieceEntity;

public class EntityChessPiece extends AbstractChessPieceEntity
{
	public EntityChessPiece(int xCoord, int yCoord, EnumPiece piece) 
	{
		super(xCoord, yCoord, piece);
	}

	@Override
	public void update(int delta)
	{
		super.update(delta);
	}
}
