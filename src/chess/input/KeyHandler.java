package chess.input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import chess.gui.GuiMainMenu;
import chess.main.Chess;
import chess.main.Game;
import chess.system.GameState;
import chess.util.RenderUtil;

public class KeyHandler 
{
	public void update()
	{
		while(Keyboard.next())
		{
			if(Keyboard.getEventKeyState())
			{
				if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) & Keyboard.isKeyDown(Keyboard.KEY_TAB))
				{
					Mouse.setGrabbed(!Mouse.isGrabbed());
				}
				
				if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
				{
					if(Chess.instance.state == GameState.MAIN_MENU)
					{
						if(Chess.instance.mainmenu.isCreditsFlagged())
						{
							Chess.instance.mainmenu.flagCredits(false);
							RenderUtil.resetAnimation();
						}
						else Chess.instance.dispose();
					}
					else if(Chess.instance.state == GameState.GAME)
					{
						Chess.instance.state = GameState.MAIN_MENU;
					}
					else
						Chess.instance.dispose();
				}
					
				if(Keyboard.getEventKey() == Keyboard.KEY_RETURN)
				{
					if(Chess.instance.state == GameState.MAIN_MENU)
					{
						if(!Chess.instance.mainmenu.isCreditsFlagged())
							Chess.instance.mainmenu.requestStart();
					}
				}
			}
		}
	}
}
