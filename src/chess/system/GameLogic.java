package chess.system;


public interface GameLogic 
{
	public void update(int delta);
	public void render();
	public boolean noFlagsTriggered();
	public void resetRequests();
	
}
