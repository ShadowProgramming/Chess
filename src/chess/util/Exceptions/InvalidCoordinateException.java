package chess.util.Exceptions;

public class InvalidCoordinateException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public InvalidCoordinateException() 
	{
		super();
		System.err.println("InvalidCoordinateException: Invalid Coordinate");
		System.exit(-1);
	}
}
