package chess.system;

import org.lwjgl.Sys;

public class Timer 
{
	private long lastFrame;
	
	public Timer()
	{
		lastFrame = getTime();
	}
	
	public void update()
	{
		getDelta();
	}
	
	public long getTime()
	{
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public int getDelta()
	{
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}
	
	public int getFPS()
	{
		return (int) (getTime() - lastFrame);
	}
	
}
