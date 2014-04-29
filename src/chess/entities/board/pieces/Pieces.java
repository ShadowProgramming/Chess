package chess.entities.board.pieces;

import chess.util.BoardUtil;
import chess.util.TextureUtil;

public class Pieces 
{
	public static enum PiecesAction {SET_NEW_GAME, CLEAR_PIECES, LOAD_SAVED_PIECES}
	
	public static void setupPieces(PiecesAction action)
	{
		if(action == PiecesAction.SET_NEW_GAME) //TODO Find a better way to initiate all these entities...
		{
			EntityChessPiece.clearAllPieces();
			EntityChessPiece wpiece, bpiece;
			
			for(int i = 1; i <= 8; i++)
			{
				bpiece = new EntityChessPiece(i, 2, EnumPiece.PAWN_BLACK);
				wpiece = new EntityChessPiece(i, 7, EnumPiece.PAWN_WHITE);
				EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(bpiece.getCoordinateX(), bpiece.getCoordinateY()), bpiece);
				EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(wpiece.getCoordinateX(), wpiece.getCoordinateY()), wpiece);
			}
			
			bpiece = new EntityChessPiece(1, 1, EnumPiece.ROOK_BLACK);
			EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(bpiece.getCoordinateX(), bpiece.getCoordinateY()), bpiece);
			bpiece = new EntityChessPiece(8, 1, EnumPiece.ROOK_BLACK);
			EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(bpiece.getCoordinateX(), bpiece.getCoordinateY()), bpiece);
			
			bpiece = new EntityChessPiece(2, 1, EnumPiece.KNIGHT_BLACK);
			EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(bpiece.getCoordinateX(), bpiece.getCoordinateY()), bpiece);
			bpiece = new EntityChessPiece(7, 1, EnumPiece.KNIGHT_BLACK);
			EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(bpiece.getCoordinateX(), bpiece.getCoordinateY()), bpiece);

			bpiece = new EntityChessPiece(3, 1, EnumPiece.BISHOP_BLACK);
			EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(bpiece.getCoordinateX(), bpiece.getCoordinateY()), bpiece);
			bpiece = new EntityChessPiece(6, 1, EnumPiece.BISHOP_BLACK);
			EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(bpiece.getCoordinateX(), bpiece.getCoordinateY()), bpiece);

			bpiece = new EntityChessPiece(4, 1, EnumPiece.QUEEN_BLACK);
			EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(bpiece.getCoordinateX(), bpiece.getCoordinateY()), bpiece);
			bpiece = new EntityChessPiece(5, 1, EnumPiece.KING_BLACK);
			EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(bpiece.getCoordinateX(), bpiece.getCoordinateY()), bpiece);
			
			
			//------------------------------------------------- WHITE PIECES -------------------------------------------------
			
			
			wpiece = new EntityChessPiece(1, 8, EnumPiece.ROOK_WHITE);
			EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(wpiece.getCoordinateX(), wpiece.getCoordinateY()), wpiece);
			wpiece = new EntityChessPiece(8, 8, EnumPiece.ROOK_WHITE);
			EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(wpiece.getCoordinateX(), wpiece.getCoordinateY()), wpiece);
			
			wpiece = new EntityChessPiece(2, 8, EnumPiece.KNIGHT_WHITE);
			EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(wpiece.getCoordinateX(), wpiece.getCoordinateY()), wpiece);
			wpiece = new EntityChessPiece(7, 8, EnumPiece.KNIGHT_WHITE);
			EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(wpiece.getCoordinateX(), wpiece.getCoordinateY()), wpiece);

			wpiece = new EntityChessPiece(3, 8, EnumPiece.BISHOP_WHITE);
			EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(wpiece.getCoordinateX(), wpiece.getCoordinateY()), wpiece);
			wpiece = new EntityChessPiece(6, 8, EnumPiece.BISHOP_WHITE);
			EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(wpiece.getCoordinateX(), wpiece.getCoordinateY()), wpiece);

			wpiece = new EntityChessPiece(4, 8, EnumPiece.QUEEN_WHITE);
			EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(wpiece.getCoordinateX(), wpiece.getCoordinateY()), wpiece);
			wpiece = new EntityChessPiece(5, 8, EnumPiece.KING_WHITE);
			EntityChessPiece.addPiece(BoardUtil.getCoordinatesToString(wpiece.getCoordinateX(), wpiece.getCoordinateY()), wpiece);
		}
	}
}
