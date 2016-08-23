public class King extends Piece
{
	public King(String color)
	{
		this(color, null);
	}

	public King(String color, Node location)
	{
		super(color, location);
	}

	public boolean canMoveTo(Node to)
	{
		//check if destination contains a piece that is on our team
		if(to.getPiece() != null)
                {
			if(to.getPiece().team.equals(team))
			{
				return false;
			}
		}
		//determine neighboring nodes
		Node[] neighbors = new Node[6];
		//manual insertion since king case is trivial
		neighbors[0] = at.getLeft();
		neighbors[1] = at.getRight();
		neighbors[2] = at.getUp();
		neighbors[3] = at.getDown();
		neighbors[4] = at.getIn();
		neighbors[5] = at.getOut();
		boolean found = false;
		for(int i = 0; i < neighbors.length; i++)
		{
			if(neighbors[i] == to)
			{
				//found the destination in the neighbors
				found = true;
				break;
			}
		}
		return found;
	}

	public boolean moveTo(Node to)
	{
		if(canMoveTo(to))
		{
			at.setPiece(null);
			to.setPiece(this);
			at = to;
			return true;
		}
		return false;
	}
	public String toString()
	{
		return super.toString() + "King";
	}
}
