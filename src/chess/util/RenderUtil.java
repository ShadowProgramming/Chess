package chess.util;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class RenderUtil 
{
	public static double widthConversionFactor = 1.448;
	public static double heightConversionFactor = 1.4;
	
	private static int animation = 0;
	
	public static void renderWithDropdownAnimation(Texture texture)
	{
		if(animation < 50) animation++;
		glPushMatrix();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D,texture.getTextureID());
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 0);
			glVertex2d(0, 0 - 50 + animation);
			
			glTexCoord2f(1, 0);
			glVertex2d(Display.getWidth() + 273, 0 - 50 + animation);
			
			glTexCoord2f(1, 1);
			glVertex2d(Display.getWidth() + 273, Display.getHeight() + 328 - 50 + animation);
			
			glTexCoord2f(0, 1);
			glVertex2d(0, Display.getHeight() + 328 - 50 + animation);
		}
		glEnd();
		glPopMatrix();
	}
	
	public static void resetAnimation()
	{
		animation = 0;
	}
	
	public static void renderMouse(Texture texture)
	{
		double width = texture.getImageWidth() / 1.4 * widthConversionFactor; 
		double height = texture.getImageHeight() / 1.4 * heightConversionFactor;
		glPushMatrix();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D,texture.getTextureID());
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 0);
			glVertex2d(Mouse.getX(), -Mouse.getY() + 700);
			
			glTexCoord2f(1, 0);
			glVertex2d(Mouse.getX() + width, -Mouse.getY() + 700);
			
			glTexCoord2f(1, 1);
			glVertex2d(Mouse.getX() + width, -Mouse.getY() + 700 + height);
			
			glTexCoord2f(0, 1);
			glVertex2d(Mouse.getX(), -Mouse.getY() + 700 + height);
		}
		glEnd();
		glPopMatrix();
	}
	
	public static void drawImageToScale(double x, double y, Texture texture)
	{
		double width = texture.getImageWidth() * widthConversionFactor; 
		double height = texture.getImageHeight() * heightConversionFactor;
		glPushMatrix();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D,texture.getTextureID());
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 0);
			glVertex2d(x, y);
			
			glTexCoord2f(1, 0);
			glVertex2d(x + width, y);
			
			glTexCoord2f(1, 1);
			glVertex2d(x + width, y + height);
			
			glTexCoord2f(0, 1);
			glVertex2d(x, y + height);
		}
		glEnd();
		glPopMatrix();
	}
	
	public static void drawFullScreenImage(Texture texture)
	{
		glPushMatrix();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D,texture.getTextureID());
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 0);
			glVertex2d(0, 0);
			
			glTexCoord2f(1, 0);
			glVertex2d(Display.getWidth() + 273, 0);
			
			glTexCoord2f(1, 1);
			glVertex2d(Display.getWidth() + 273, Display.getHeight() + 328);
			
			glTexCoord2f(0, 1);
			glVertex2d(0, Display.getHeight() + 328);
		}
		glEnd();
		glPopMatrix();
	}
}
