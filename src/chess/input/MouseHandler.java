package chess.input;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class MouseHandler 
{
	
	private int x, y;
	private int dx, dy;
	
	public void update()
	{
		x = Mouse.getX();
		y = Mouse.getY() - Display.getHeight() - 1;
		dx = Mouse.getDX();
		dy = Mouse.getDY();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}
}
