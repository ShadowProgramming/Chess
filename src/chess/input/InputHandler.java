package chess.input;

public class InputHandler 
{
	KeyHandler keyhandler;
	MouseHandler mousehandler;
	
	public InputHandler()
	{
		keyhandler = new KeyHandler();
		mousehandler = new MouseHandler();
	}
	
	public void update()
	{
		keyhandler.update();
		mousehandler.update();
	}
	
	public KeyHandler getKeyHandler()
	{
		return this.keyhandler;
	}
	
	public MouseHandler getMouseHandler()
	{
		return this.mousehandler;
	}
}
