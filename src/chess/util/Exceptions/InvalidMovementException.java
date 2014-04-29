package chess.util.Exceptions;

public class InvalidMovementException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;

	public InvalidMovementException()
	{
		super();
		System.err.println("InvalidMovementException: Piece tried to move illegally!");
		System.exit(-1);
	}
}
