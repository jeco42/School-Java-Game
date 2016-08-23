public class Queen extends Piece
{
	public Queen(String color)
	{
		this(color, null);
	}

	public Queen(String color, Node location)
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
		//Try to move like a rook!
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
                if(found)
		{
			return true;
		}
		// Couldn't move like a rook, try to move like a bishop!
                //Find all nodes and record their relative coordinates
                Node[] nodes = new Node[Board.length()];
                int[][] coords = new int[Board.length()][3];
                //helper method to find nodes and coords
                search(at, nodes, coords, 0, 0, 0);
                index = -1;
                int x = -1, y = -1, z = -1;
                //extract relative coordinates of 'to'
                for(int i = 0; i < nodes.length; i++)
                {
                        if(nodes[i] == to)
                        {
                                index = i;
                                x = Math.abs(coords[i][0]);
                                y = Math.abs(coords[i][1]);
                                z = Math.abs(coords[i][2]);
                                break;
                        }
                }
                //determine if 'to' is valid based on relative coordinates
                boolean valid_coords = false;
                if((x == 0) && (y == z))
                {
                        valid_coords = true;
                }
                else if((y == 0) && (x == z))
                {
                        valid_coords = true;
                }
                else if((z == 0) && (x == y))
                {
                        valid_coords = true;
                }
                else if((x == y) && (y == z))
                {
                        valid_coords = true;
                }
                else
                {
                        return false; // invalid relative coordinates
                }
                //coordinates are ok, verify valid path to destination exit
                return verifyPath(nodes, coords, 0, 0, 0, coords[index][0], coords[index][1], coords[index][2]);
        }

        private boolean verifyPath(Node[] nodes, int[][] coords, int cur_x, int cur_y, int cur_z, int to_x, int to_y, int to_z)
        {
                //Check if you made it to the destination
                if((cur_x == to_x) && (cur_y == to_y) && (cur_z == to_z))
                {
                        return true;
                }

                //Determine if x,y,z will be increasing or decreasing to get to the destination...
                int delta_x = 0;
                int delta_y = 0;
                int delta_z = 0;
                if((to_x - cur_x) != 0)
                {
                        delta_x = (to_x - cur_x) / Math.abs(to_x - cur_x);
                }
                if((to_y - cur_y) != 0)
                {
                        delta_y = (to_y - cur_y) / Math.abs(to_y - cur_y);
                }
                if((to_z - cur_z) != 0)
                {
                        delta_z = (to_z - cur_z) / Math.abs(to_z - cur_z);
                }

                //2D move case
                if((delta_x == 0) || (delta_y == 0) || (delta_z == 0))
                {
                        while((cur_x != to_x) || (cur_y != to_y) || (cur_z != to_z))
                        {
				boolean piece_check = true;
				//if next diag loaction isn't the final loaction, it has to be empty
				if((cur_x + delta_x != to_x) && (cur_y + delta_y != to_y) && (cur_z + delta_z != to_z))
				{
					piece_check = checkPiece(nodes, coords, cur_x + delta_x, cur_y + delta_y, cur_z + delta_z);
				}

                                boolean diag_check = checkCoord(coords, cur_x + delta_x, cur_y + delta_y, cur_z + delta_z) ;
                                boolean path_check = false;
                                //two possible paths to get to the next diagonal node.. check if at least 1 path exists
                                if(delta_x == 0)
                                {
                                        path_check = (checkCoord(coords, cur_x, cur_y + delta_y, cur_z) || checkCoord(coords, cur_x, cur_y, cur_z + delta_z));
                                }
                                if(delta_y == 0)
                                {
                                        path_check = (checkCoord(coords, cur_x + delta_x, cur_y, cur_z) || checkCoord(coords, cur_x, cur_y, cur_z + delta_z));
                                }
                                if(delta_z == 0)
                                {
                                        path_check = (checkCoord(coords, cur_x, cur_y + delta_y, cur_z) || checkCoord(coords, cur_x + delta_x, cur_y, cur_z));
                                }
                                if(!(piece_check && diag_check && path_check))
                                {
                                        //clear path not found
                                        return false;
                                }
                                cur_x += delta_x;
                                cur_y += delta_y;
                                cur_z += delta_z;
                        }
                        //clear path found successfully
                        return true;
                }
                //3D move case
                while((cur_x != to_x) || (cur_y != to_y) || (cur_z != to_z))
                {
			boolean piece_check = true;
			//if next diag loaction isn't the final loaction, it has to be empty
			if((cur_x + delta_x != to_x) && (cur_y + delta_y != to_y) && (cur_z + delta_z != to_z))
			{
				piece_check = checkPiece(nodes, coords, cur_x + delta_x, cur_y + delta_y, cur_z + delta_z);
			}
                        boolean diag_check = checkCoord(coords, cur_x + delta_x, cur_y + delta_y, cur_z + delta_z) ;
                        //6 possible paths to get to the next diagonal node.. check if at least 1 path exists
                        boolean path1 = checkCoord(coords, cur_x + delta_x, cur_y, cur_z) && checkCoord(coords, cur_x + delta_x, cur_y + delta_y, cur_z);
                        boolean path2 = checkCoord(coords, cur_x + delta_x, cur_y, cur_z) && checkCoord(coords, cur_x + delta_x, cur_y, cur_z + delta_z);
                        boolean path3 = checkCoord(coords, cur_x, cur_y + delta_y, cur_z) && checkCoord(coords, cur_x + delta_x, cur_y + delta_y, cur_z);
                        boolean path4 = checkCoord(coords, cur_x, cur_y + delta_y, cur_z) && checkCoord(coords, cur_x, cur_y + delta_y, cur_z + delta_y);
                        boolean path5 = checkCoord(coords, cur_x, cur_y, cur_z + delta_z) && checkCoord(coords, cur_x + delta_x, cur_y, cur_z + delta_z);
                        boolean path6 = checkCoord(coords, cur_x, cur_y, cur_z + delta_z) && checkCoord(coords, cur_x, cur_y + delta_y, cur_z + delta_z);
                        boolean path_check = path1 || path2 || path3 || path4 || path5 || path6;
                        if(!(piece_check && diag_check && path_check))
                        {
                                //clear path not found
                                return false;
                        }
                        cur_x += delta_x;
                        cur_y += delta_y;
                        cur_z += delta_z;
                }
                //clear path found successfully
                return true;
        }
	//helper method for verifyPath.  Determine if a set of coords is empty
        private boolean checkPiece(Node[] nodes, int[][] coords, int x, int y, int z)
        {
                for(int i = 0; i < coords.length; i++)
                {
                        if((coords[i][0] == x) && (coords[i][1] == y) && (coords[i][2] == z))
                        {
                                if(nodes[i].getPiece() == null)
                                {
                                        return true;
                                }
                                else
                                {
                                        return false;
                                }
                        }
                }
                return false;
        }

        //helper method for verifyPath.  Determines if a set of coords is in the coord array
        private boolean checkCoord(int[][] coords, int x, int y, int z)
        {
                for(int i = 0; i < coords.length; i++)
                {
                        if((coords[i][0] == x) && (coords[i][1] == y) && (coords[i][2] == z))
                        {
                                return true;
                        }
                }
                return false;
        }
        //helper method for canMoveTo
        private void search(Node current, Node[] dest, int[][] coords, int x, int y, int z)
        {
                if(current == null)
                {
                        return;
                }

                Node check = dest[0];
                int i = 0;
                while(check != null)
                {
                        if(check == current)
                        {
                                return;
                        }
                        check = dest[++i];
                }

                dest[i] = current;
                coords[i][0] = x;
                coords[i][1] = y;
                coords[i][2] = z;
                search(current.getLeft(), dest, coords, x-1, y, z);
                search(current.getRight(), dest, coords, x+1, y, z);
                search(current.getUp(), dest, coords, x, y-1, z);
                search(current.getDown(), dest, coords, x, y+1, z);
                search(current.getIn(), dest, coords, x, y, z-1);
                search(current.getOut(), dest, coords, x, y, z+1);
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
                return super.toString() + "Queen";
        }
}
