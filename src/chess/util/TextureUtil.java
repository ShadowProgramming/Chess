package chess.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TextureUtil
{
	public static Texture main_menu;
	public static Texture dropdown_menu_empty, dropdown_menu_credits;
	
	public static Texture button, button_overlay, button_overlay_disabled;
	public static Texture cursor_default;
	
	public static Texture game_background;
	
	public static Texture tile_white, tile_black;
	public static Texture overlay_tile_hover_reject, overlay_tile_hover_accept, overlay_tile_hover_neutral;
	public static Texture move_location;

	public static Texture pawn_black, bishop_black, knight_black, rook_black, queen_black, king_black;
	public static Texture pawn_white, bishop_white, knight_white, rook_white, queen_white, king_white;
	
	public static Texture magic_circle_purple;
	
	public static Texture loadTexture(String key)
	{
		try {
			return TextureLoader.getTexture("png", new FileInputStream(new File("res/" + key + ".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
