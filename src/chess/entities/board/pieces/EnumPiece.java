package chess.entities.board.pieces;

import org.newdawn.slick.opengl.Texture;

import chess.util.TextureUtil;

public enum EnumPiece
{
	PAWN_BLACK(1, Team.BLACK, TextureUtil.pawn_black), 
	KNIGHT_BLACK(3, Team.BLACK, TextureUtil.knight_black), 
	BISHOP_BLACK(3, Team.BLACK, TextureUtil.bishop_black), 
	ROOK_BLACK(5, Team.BLACK, TextureUtil.rook_black), 
	QUEEN_BLACK(9, Team.BLACK, TextureUtil.queen_black), 
	KING_BLACK(10, Team.BLACK, TextureUtil.king_black),
	
	PAWN_WHITE(1, Team.WHITE, TextureUtil.pawn_white), 
	KNIGHT_WHITE(3, Team.WHITE, TextureUtil.knight_white), 
	BISHOP_WHITE(3, Team.WHITE, TextureUtil.bishop_white), 
	ROOK_WHITE(5, Team.WHITE, TextureUtil.rook_white), 
	QUEEN_WHITE(9, Team.WHITE, TextureUtil.queen_white), 
	KING_WHITE(10, Team.WHITE, TextureUtil.king_white);
	
	private int value;
	private Team team;
	private Texture texture;
	
	EnumPiece(int value, Team team, Texture texture)
	{
		this.value = value;
		this.team = team;
		this.texture = texture;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public Team getTeam()
	{
		return team;
	}
	
	public Texture getTexture()
	{
		return texture;
	}
	
	public static enum Team
	{
		BLACK, WHITE;
	}
}