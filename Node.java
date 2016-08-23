public class Node
{
	private Piece piece;
	private Node left;
	private Node right;
	private Node up;
	private Node down;
	private Node in;
	private Node out;

	public Node()
	{
		piece = null;
		left = right = up = down = in = out = null;
	}

	public Node(Piece p)
	{
		piece = p;
		left = right = up = down = in = out = null;
	}

	public String toString()
	{
		if(piece != null)
		{
			return piece.toString();
		}
		return "null";
	}

	public Piece setPiece(Piece p)
	{
		return piece = p;
	}

	public Piece getPiece()
	{
		return piece;
	}

	public Node getLeft()
	{
		return left;
	}

	public Node getRight()
	{
		return right;
	}

	public Node getUp()
	{
		return up;
	}

	public Node getDown()
	{
		return down;
	}

	public Node getIn()
	{
		return in;
	}

	public Node getOut()
	{
		return out;
	}

	public Node setLeft(Node n)
	{
		return left = n;
	}

	public Node setRight(Node n)
	{
		return right = n;
	}

	public Node setUp(Node n)
	{
		return up = n;
	}

	public Node setDown(Node n)
	{
		return down = n;
	}

	public Node setIn(Node n)
	{
		return in = n;
	}

	public Node setOut(Node n)
	{
		return out = n;
	}
 }
