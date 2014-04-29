package chess.util;

public class SystemUtil 
{
	public static void wait(final int time)
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(time);
				} catch (InterruptedException e) {}
			}
		}).run();
	}
}
