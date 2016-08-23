import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class Board
{
	private Node origin;
	private static int numNodes;

	public Board(Scanner input)
	{
		numNodes = input.nextInt();
		Node[] nodes = new Node[numNodes];
		String piece, color;
		Piece p;
		int id, left, right, up, down, in, out;

		for(int i = 0; i < numNodes; i++)
		{
			nodes[i] = new Node();
		}
		for(int i = 0; i < numNodes; i++)
		{
			id = input.nextInt();
			if(i == 0)
			{
				origin = nodes[id];
			}
			Node current = nodes[id];
			left = input.nextInt();
			right = input.nextInt();
			up = input.nextInt();
			down = input.nextInt();
			in = input.nextInt();
			out = input.nextInt();

			if(left != -1)
			{
				current.setLeft(nodes[left]);
			}
			if(right != -1)
			{
				current.setRight(nodes[right]);
			}
			if(up != -1)
			{
				current.setUp(nodes[up]);
			}
			if(down != -1)
			{
				current.setDown(nodes[down]);
			}
			if(in != -1)
			{
				current.setIn(nodes[in]);
			}
			if(out != -1)
			{
				current.setOut(nodes[out]);
			}
			piece = input.next();
			//determine and set the piece
			if(!piece.equals("none"))
			{
				if(piece.charAt(0) == 'B')
				{
					color = "black";
				}
				else
				{
					color = "white";
				}
				piece = piece.substring(1).toLowerCase();;

				switch(piece)
				{
					case("pawn"):
						p = new Pawn(color, current);
						break;
					case("king"):
						p = new King(color, current);
						break;
					case("queen"):
						p = new Queen(color, current);
						break;
					case("bishop"):
						p = new Bishop(color, current);
						break;
					case("knight"):
						p = new Knight(color, current);
						break;
					case("rook"):
						p = new Rook(color, current);
						break;
					default:
						p = null;
				}
				current.setPiece(p);
			}
		}
	}

	private Board()
	{
	}

	public String toString()
	{
		Node[] visited = new Node[numNodes];
		String[] ret = {""};
		toStringBuilder(origin, ret, visited, 0, 0, 0);
		return ret[0];
	}

	private void toStringBuilder(Node at, String[] ret, Node[] visited, int x, int y, int z)
	{
		if(at == null)
		{
			return;
		}

		Node check = visited[0];
		int i = 0;
		while(check != null)
		{
			if(check == at)
			{
				return;
			}
			check = visited[++i];
		}

		visited[i] = at;
		ret[0] += x + "\t" + y + "\t" + z + "\t" + at.toString() + "\n";
		toStringBuilder(at.getLeft(), ret, visited, x-1, y, z);
		toStringBuilder(at.getRight(), ret, visited, x+1, y, z);
		toStringBuilder(at.getUp(), ret, visited, x, y-1, z);
		toStringBuilder(at.getDown(), ret, visited, x, y+1, z);
		toStringBuilder(at.getIn(), ret, visited, x, y, z-1);
		toStringBuilder(at.getOut(), ret, visited, x, y, z+1);
	}

	//Write output string to the game.map, also returns the string.

	public String save() throws FileNotFoundException
	{
		Node[] nodes = new Node[numNodes];
		saveBuilder(origin, nodes);
		String ret = "" + numNodes + "\n";

		for(int i = 0; i < numNodes; i++)
		{
			ret += "" + i;
			ret += "\t" + find(nodes, nodes[i].getLeft());
			ret += "\t" + find(nodes, nodes[i].getRight());
			ret += "\t" + find(nodes, nodes[i].getUp());
			ret += "\t" + find(nodes, nodes[i].getDown());
			ret += "\t" + find(nodes, nodes[i].getIn());
			ret += "\t" + find(nodes, nodes[i].getOut());
			ret += "\t" + nodes[i] + "\n";
		}

		PrintWriter output = new PrintWriter("game.map");
		output.print(ret);
		output.close();
		return ret;
	}
	//helper method for save()
	private int find(Node[] nodes, Node toFind)
	{
		if(toFind == null)
		{
			return -1;
		}
		for(int i = 0; i < nodes.length; i++)
		{
			if(nodes[i] == toFind)
			{
				return i;
			}
		}
		return -1;
	}
	//helper method for save();
	private void saveBuilder(Node at, Node[] visited)
	{
		if(at == null)
		{
			return;
		}

		Node check = visited[0];
		int i = 0;
		while(check != null)
		{
			if(check == at)
			{
				return;
			}
			check = visited[++i];
		}

		visited[i] = at;
		saveBuilder(at.getLeft(), visited);
		saveBuilder(at.getRight(), visited);
		saveBuilder(at.getUp(), visited);
		saveBuilder(at.getDown(), visited);
		saveBuilder(at.getIn(), visited);
		saveBuilder(at.getOut(), visited);
	}

	public boolean load() throws FileNotFoundException
	{
		return load("game.map");
	}

	public boolean load(String str) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File(str));

		numNodes = input.nextInt();
		Node[] nodes = new Node[numNodes];
		String piece, color;
		Piece p;
		int id, left, right, up, down, in, out;

		for(int i = 0; i < numNodes; i++)
		{
			nodes[i] = new Node();
		}
		origin = nodes[0];
		for(int i = 0; i < numNodes; i++)
		{
			//Determine current node
			id = input.nextInt();
			Node current = nodes[id];
			//Determine and set adjacent nodes
			left = input.nextInt();
			right = input.nextInt();
			up = input.nextInt();
			down = input.nextInt();
			in = input.nextInt();
			out = input.nextInt();

			if(left != -1)
			{
				current.setLeft(nodes[left]);
			}
			if(right != -1)
			{
				current.setRight(nodes[right]);
			}
			if(up != -1)
			{
				current.setUp(nodes[up]);
			}
			if(down != -1)
			{
				current.setDown(nodes[down]);
			}
			if(in != -1)
			{
				current.setIn(nodes[in]);
			}
			if(out != -1)
			{
				current.setOut(nodes[out]);
			}
			piece = input.next();
			//determine and set the piece
			if(!piece.equals("none"))
			{
				if(piece.charAt(0) == 'B')
				{
					color = "black";
				}
				else
				{
					color = "white";
				}

				piece = piece.substring(1).toLowerCase();;

				switch(piece)
				{
					case("pawn"):
						p = new Pawn(color, current);
						break;
					case("king"):
						p = new King(color, current);
						break;
					case("queen"):
						p = new Queen(color, current);
						break;
					case("bishop"):
						p = new Bishop(color, current);
						break;
					case("knight"):
						p = new Knight(color, current);
						break;
					case("rook"):
						p = new Rook(color, current);
						break;
					default:
						p = null; //throw invalid input error or something?
				}
				current.setPiece(p);
			}
		}
		return true;
	}

	public Node getAt(int x, int y, int z)
	{
		Node[] visited = new Node[numNodes];
		int[] toFind = {x, y, z};
		Node[] found = new Node[1];
		getAtFinder(origin, found, visited, toFind, 0, 0, 0);
		return found[0];
	}

	private void getAtFinder(Node at, Node[] found, Node[] visited, int[] toFind, int x, int y, int z)
	{
		if((found[0] != null) || (at == null))
		{
			return;
		}
		if((x == toFind[0]) && (y == toFind[1]) && (z == toFind[2]))
		{
			found[0] = at;
		}

		Node check = visited[0];
		int i = 0;
		while(check != null)
		{
			if(check == at)
			{
				return;
			}
			check = visited[++i];
		}

		visited[i] = at;
		getAtFinder(at.getLeft(), found, visited, toFind, x-1, y, z);
		getAtFinder(at.getRight(), found, visited, toFind, x+1, y, z);
		getAtFinder(at.getUp(), found, visited, toFind, x, y-1, z);
		getAtFinder(at.getDown(), found, visited, toFind, x, y+1, z);
		getAtFinder(at.getIn(), found, visited, toFind, x, y, z-1);
		getAtFinder(at.getOut(), found, visited, toFind, x, y, z+1);
	}

	public static int length()
	{
		return numNodes;
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		Board b = new Board(); //initialzing it so java wont complain...
		Scanner input;
		PrintWriter output = new PrintWriter("currentstate"); //saves to currentstate file for the visualizer
		//grab file location if there was an argument.. else load save state if possible
		if(args.length > 0)
		{
			input = new Scanner(new File(args[0]));
			b = new Board(input);
		}
		else if(new File("game.map").isFile())
		{
			input = new Scanner("game.map");
			b.load();
		}
		else
		{
			System.out.printf("No source map provided and no savefile found\n");
			System.exit(1);
		}

		input = new Scanner(System.in);

		//add turn detection for savefile? right now, defaults to whites turn
		char team = 'W';
		Node selection = null, dest = null;
		int x, y, z;
		boolean valid_selection, valid_dest;
		//Game Loop
		while(true)
		{
			//Save game to 'game.map' file
			b.save();
			//Save current board to the visualizer file
			output = new PrintWriter("currentstate");
			output.printf("%s",b);
			output.close();
			//Print current board to screen and indicate whose turn
			System.out.printf("%s", b);
			if(team == 'W')
			{
				System.out.printf("White to move.\n");
			}
			else
			{
				System.out.printf("Black to move.\n");
			}
			//Get piece selection and destination from player
			valid_selection = false;
			valid_dest = false;
			while(!valid_selection)
			{
				System.out.printf("Enter coordinates of piece to move:\t");
				x = input.nextInt();
				y = input.nextInt();
				z = input.nextInt();
				selection = b.getAt(x, y, z);
				if(selection.getPiece() == null)
				{
					System.out.printf("\nNo piece found at that location\n");
					continue;
				}
				if(selection.getPiece().toString().charAt(0) != team)
				{
					System.out.printf("\nEnemy piece found at that location\n");
					continue;
				}

				System.out.printf("Enter destination coordinates:\t");
				x = input.nextInt();
				y = input.nextInt();
				z = input.nextInt();
				dest = b.getAt(x, y, z);
				if(dest == null)
				{
					System.out.printf("\nNo location found with those coordinates\n");
					continue;
				}
				valid_dest = selection.getPiece().canMoveTo(dest);
				if(!valid_dest)
				{
					System.out.printf("\nInvalid destination.\n");
					continue;
				}
				valid_selection = true;
			}

			//Check to see if a King just died
			if(dest.getPiece() != null)
			{
				if(dest.getPiece().toString().substring(1).equals("King"))
				{
					selection.getPiece().moveTo(dest);
					break;
				}
			}

			//Make the move
			selection.getPiece().moveTo(dest);

			//toggle turns
			if(team == 'W')
			{
				team = 'B';
			}
			else
			{
				team = 'W';
			}
		}
		//Game over!
		System.out.printf("Checkmate! ");
		if(team == 'W')
		{
			System.out.printf("White wins!\n");
		}
		else
		{
			System.out.printf("Black wins!\n");
		}

		output = new PrintWriter("currentstate");
		output.printf("%s", b);
		output.close();
		output = new PrintWriter("endgame");
		output.printf("%s", b); //or b.save() depending on desired format
		output.close();
	}
}
