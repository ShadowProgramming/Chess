package chess.gui;

import org.lwjgl.input.Mouse;

import chess.system.GameLogic;
import chess.util.RenderUtil;
import chess.util.SystemUtil;
import chess.util.TextureUtil;

public class GuiMainMenu implements GameLogic
{
	
	private boolean[] button = new boolean[6];
	
	private boolean startRequest;
	private boolean creditsFlag;
	
	public GuiMainMenu()
	{
		startRequest = false;
		creditsFlag = false;
		button[0] = false;
		button[1] = false;
		button[2] = false;
		button[3] = false;
		button[4] = false;
		button[5] = false;
	}
	
	public void update(int delta)
	{
		render();
		updateButtons();
	}
	
	public void render()
	{
		RenderUtil.drawFullScreenImage(TextureUtil.main_menu);
		
		if(creditsFlag)
			RenderUtil.renderWithDropdownAnimation(TextureUtil.dropdown_menu_credits);
	}
	
	private void handleButtonLogic()
	{	
		if(Mouse.isButtonDown(0))
		{
			if(noFlagsTriggered())
			{
				if(button[0])
				{
					requestStart();
				}
				if(button[1])
				{
					return;
				}
				if(button[2])
				{
					return;
				}
				if(button[3])
				{
					return;
				}
				if(button[4])
				{
						creditsFlag = true;		
						SystemUtil.wait(200);
						button[4] = false;
				}
				if(button[5] == true)
				{
					System.exit(0);
				}
			}		
			else if(isCreditsFlagged())
			{
				creditsFlag = false;
				RenderUtil.resetAnimation();
				SystemUtil.wait(200);
			}
		}
	}
	
	private void updateButtons()
	{
		/*RenderUtil.drawImageToScale(200, 700 - 346, TextureUtil.button_overlay_disabled);
		RenderUtil.drawImageToScale(200, 700 - 476, TextureUtil.button_overlay_disabled);*/
		if(noFlagsTriggered())
			resetButtons();
		
		if(Mouse.getX() > 200 && Mouse.getX() < 550)
		{
			if(Mouse.getY() > 497 && Mouse.getY() < 542)
			{
				button[0] = true;
				if(noFlagsTriggered())
					RenderUtil.drawImageToScale(200, 700 - 541, TextureUtil.button_overlay);
			}
			else if(Mouse.getY() > 432 && Mouse.getY() < 477)
			{
				button[1] = true;
				if(noFlagsTriggered())
					RenderUtil.drawImageToScale(200, 700 - 476, TextureUtil.button_overlay);
			}
			else if(Mouse.getY() > 366 && Mouse.getY() < 412)
			{
				button[2] = true;
				if(noFlagsTriggered())
					RenderUtil.drawImageToScale(200, 700 - 411, TextureUtil.button_overlay);
			}
			else if(Mouse.getY() > 301 && Mouse.getY() < 347)
			{
				button[3] = true;
				if(noFlagsTriggered())
					RenderUtil.drawImageToScale(200, 700 - 346, TextureUtil.button_overlay);
			}
			else if(Mouse.getY() > 236 && Mouse.getY() < 282)
			{
				button[4] = true;
				if(noFlagsTriggered())
					RenderUtil.drawImageToScale(200, 700 - 282, TextureUtil.button_overlay);
			}
			else if(Mouse.getY() > 171 && Mouse.getY() < 217)
			{
				button[5] = true;
				if(noFlagsTriggered())
					RenderUtil.drawImageToScale(200, 700 - 217, TextureUtil.button_overlay);
			}
		}
		handleButtonLogic();
	}
	
	public boolean noButtonsHovered()
	{
		if(button[0] || button[1] || 
			button[2] || button[3] || 
			 button[4] || button[5])
			return false;
		return true;
	}
	
	public boolean noFlagsTriggered()
	{
		if(!creditsFlag)
			return true;
		return false;
	}
	
	public void flagCredits(boolean flag)
	{
		creditsFlag = flag;
	}
	
	public boolean isCreditsFlagged()
	{
		return creditsFlag;
	}
	
	public void resetRequests()
	{
		startRequest = false;
	}
	
	private void resetButtons()
	{
		button[0] = false;
		button[1] = false;
		button[2] = false;
		button[3] = false;
		button[4] = false;
		button[5] = false;
	}
	
	public void requestStart()
	{
		startRequest = true;
	}
	
	public boolean isStartRequested()
	{
		return startRequest;
	}
}
