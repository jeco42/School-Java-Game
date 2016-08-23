public class Knight extends Piece
{
	public Knight(String color)
	{
		this(color, null);
	}

	public Knight(String color, Node location)
	{
		super(color, location);
	}

	//should have void return? or what
	public void setLocation(Node location)
	{
		at = location;
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
		//Determine valid destinations
		Node[] dest = new Node[48];
		//Manual insertion since there are only 24 possible destinations
		//Each dest can be reached by 2 routes.
		//dest array may contain duplicates if a node can be reached by both paths
		int index = 0;
		if(at.getLeft() != null)
		{
			//2 left, 1-in other directions
			if(at.getLeft().getLeft() != null)
			{
				dest[index++] = at.getLeft().getLeft().getUp();
				dest[index++] = at.getLeft().getLeft().getDown();
				dest[index++] = at.getLeft().getLeft().getIn();
				dest[index++] = at.getLeft().getLeft().getOut();
			}
			//1 left, 2 in other directions
			if(at.getLeft().getUp() != null)
			{
				dest[index++] = at.getLeft().getUp().getUp();
			}
			if(at.getLeft().getDown() != null)
			{
				dest[index++] = at.getLeft().getDown().getDown();
			}
			if(at.getLeft().getIn() != null)
			{
				dest[index++] = at.getLeft().getIn().getIn();
			}
			if(at.getLeft().getOut() != null)
			{
				dest[index++] = at.getLeft().getOut().getOut();
			}
		}
		if(at.getRight() != null)
		{
			//2 right, 1 in other directions
			if(at.getRight().getRight() != null)
			{
				dest[index++] = at.getRight().getRight().getUp();
				dest[index++] = at.getRight().getRight().getDown();
				dest[index++] = at.getRight().getRight().getIn();
				dest[index++] = at.getRight().getRight().getOut();
			}
			//1 right, 2 in other directions
			if(at.getRight().getUp() != null)
			{
				dest[index++] = at.getRight().getUp().getUp();
			}
			if(at.getRight().getDown() != null)
			{
				dest[index++] = at.getRight().getDown().getDown();
			}
			if(at.getRight().getIn() != null)
			{
				dest[index++] = at.getRight().getIn().getIn();
			}
			if(at.getRight().getOut() != null)
			{
				dest[index++] = at.getRight().getOut().getOut();
			}
		}
		if(at.getUp() != null)
		{
			//2 up, 1 in other directions
			if(at.getUp().getUp() != null)
			{
				dest[index++] = at.getUp().getUp().getLeft();
				dest[index++] = at.getUp().getUp().getRight();
				dest[index++] = at.getUp().getUp().getIn();
				dest[index++] = at.getUp().getUp().getOut();
			}
			//1 up, 2 in other directions
			if(at.getUp().getLeft() != null)
			{
				dest[index++] = at.getUp().getLeft().getLeft();
			}
			if(at.getUp().getRight() != null)
			{
				dest[index++] = at.getUp().getRight().getRight();
			}
			if(at.getUp().getIn() != null)
			{
				dest[index++] = at.getUp().getIn().getIn();
			}
			if(at.getUp().getOut() != null)
			{
				dest[index++] = at.getUp().getOut().getOut();
			}
		}
		if(at.getDown() != null)
		{
			//2 down, 1 in other directions
			if(at.getDown().getDown() != null)
			{
				dest[index++] = at.getDown().getDown().getLeft();
				dest[index++] = at.getDown().getDown().getRight();
				dest[index++] = at.getDown().getDown().getIn();
				dest[index++] = at.getDown().getDown().getOut();
			}
			//1 down, 2 in other directions
			if(at.getDown().getLeft() != null)
			{
				dest[index++] = at.getDown().getLeft().getLeft();
			}
			if(at.getDown().getRight() != null)
			{
				dest[index++] = at.getDown().getRight().getRight();
			}
			if(at.getDown().getIn() != null)
			{
				dest[index++] = at.getDown().getIn().getIn();
			}
			if(at.getDown().getOut() != null)
			{
				dest[index++] = at.getDown().getOut().getOut();
			}
		}
		if(at.getIn() != null)
		{
			//2 in, 1 in other directions
			if(at.getIn().getIn() != null)
			{
				dest[index++] = at.getIn().getIn().getLeft();
				dest[index++] = at.getIn().getIn().getRight();
				dest[index++] = at.getIn().getIn().getUp();
				dest[index++] = at.getIn().getIn().getDown();
			}
			//1 in, 2 in other directions
			if(at.getIn().getUp() != null)
			{
				dest[index++] = at.getIn().getUp().getUp();
			}
			if(at.getIn().getDown() != null)
			{
				dest[index++] = at.getIn().getDown().getDown();
			}
			if(at.getIn().getLeft() != null)
			{
				dest[index++] = at.getIn().getLeft().getLeft();
			}
			if(at.getIn().getRight() != null)
			{
				dest[index++] = at.getIn().getRight().getRight();
			}
		}
		if(at.getOut() != null)
		{
			//2 out, 1 in other directions
			if(at.getOut().getOut() != null)
			{
				dest[index++] = at.getOut().getOut().getLeft();
				dest[index++] = at.getOut().getOut().getRight();
				dest[index++] = at.getOut().getOut().getUp();
				dest[index++] = at.getOut().getOut().getDown();
			}
			//1 out, 2 in other directions
			if(at.getOut().getUp() != null)
			{
				dest[index++] = at.getOut().getUp().getUp();
			}
			if(at.getOut().getDown() != null)
			{
				dest[index++] = at.getOut().getDown().getDown();
			}
			if(at.getOut().getLeft() != null)
			{
				dest[index++] = at.getOut().getLeft().getLeft();
			}
			if(at.getOut().getRight() != null)
			{
				dest[index++] = at.getOut().getRight().getRight();
			}
		}
		//Search for 'to' in the dest array.
		boolean found = false;
		for(int i = 0; i < index; i++)
		{
			if(dest[i] == to)
			{
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
                return super.toString() + "Knight";
        }
}
