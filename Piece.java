public abstract class Piece
{
	protected Node at;
	protected String team;

	//ask if can use constructor in the abstract class?
	public Piece(String color, Node location)
	{
                at = location;
                color = color.toUpperCase();
                if((color.equals("WHITE")) || (color.equals("BLACK")))
                {
                        team = color;
                        return;
                }
                team = null;
	}

	public String toString()
	{
		return "" + team.charAt(0);
	}

	//should these be public/protected?
	abstract boolean canMoveTo(Node to);
	abstract boolean moveTo(Node to);
}
