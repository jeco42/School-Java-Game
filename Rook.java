public class Rook extends Piece
{
	public Rook(String color)
	{
		this(color, null);
	}

	public Rook(String color, Node location)
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
                //determine valid destination nodes
                Node[] dest = new Node[Board.length()];
		//head in in each direction until we hit an occupied node or null
		Node search = at;
		int index = 0;
		while(search.getLeft() != null)
		{
			search = search.getLeft();
			if(search.getPiece() == null)
			{
				dest[index++] = search;
				continue;
			}
			if(search.getPiece().team != team)
			{
				dest[index++] = search;
			}
			break;
		}
		search = at;
		while(search.getRight() != null)
		{
			search = search.getRight();
			if(search.getPiece() == null)
			{
				dest[index++] = search;
				continue;
			}
			if(search.getPiece().team != team)
			{
				dest[index++] = search;
			}
			break;
		}
		search = at;
		while(search.getUp() != null)
		{
			search = search.getUp();
			if(search.getPiece() == null)
			{
				dest[index++] = search;
				continue;
			}
			if(search.getPiece().team != team)
			{
				dest[index++] = search;
			}
			break;
		}
		search = at;
		while(search.getDown() != null)
		{
			search = search.getDown();
			if(search.getPiece() == null)
			{
				dest[index++] = search;
				continue;
			}
			if(search.getPiece().team != team)
			{
				dest[index++] = search;
			}
			break;
		}
		search = at;
		while(search.getIn() != null)
		{
			search = search.getIn();
			if(search.getPiece() == null)
			{
				dest[index++] = search;
				continue;
			}
			if(search.getPiece().team != team)
			{
				dest[index++] = search;
			}
			break;
		}
		search = at;
		while(search.getOut() != null)
		{
			search = search.getOut();
			if(search.getPiece() == null)
			{
				dest[index++] = search;
				continue;
			}
			if(search.getPiece().team != team)
			{
				dest[index++] = search;
			}
			break;
		}

                boolean found = false;
                for(int i = 0; i < index; i++)
                {
                        if(dest[i] == to)
                        {
                                //found the destination
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
                return super.toString() + "Rook";
        }
}
