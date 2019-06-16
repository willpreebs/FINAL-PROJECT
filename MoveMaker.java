import java.util.*;
import java.time.LocalTime;

public class MoveMaker
{
	private CubeNet cubeN;
	private ArrayList<CubeFace>cubeList;
	private ArrayList<Character>moveList; //keeps track of moves done for undoing purposes
	private LocalTime startTime;

	public MoveMaker(CubeNet cubby)
	{
		cubeN = cubby;
		cubeList = cubeN.getCubeFaceArr();
		moveList = new ArrayList<Character>();
		startTime = LocalTime.now();
	}
	public void moveChecker(Character a, boolean add, int i) //a controls what moves happen, "add" tells if that move
	{//is being added to moveList, and i is to make the random number generation in scramble() easier
		if(a == '-')
		{
			undoLastMove();
			add = false;
		}
		else if(a == '`')
			scramble(30);//change number to have longer or shorter scrambles
/*Permutation algorithms: completely optional for the cube's functionality, but make it easier to solve by making 15 or so moves
with one button press -- not yet done*/
		else if(a == '0')
			uBPerm();
		else if(a == '.')
			uAPerm();
		else if(a == '1')
			aAPerm();
		else if(a == '2')
			aBPerm();
		else if(a == '3')
			ePerm();
		/*else if(a == '4')
			jAPerm();
		else if(a == '5')
			jBPerm();
		else if(a == '6')
			yPerm();
		else if(a == '7')
			tPerm();
		else if(a == '8')
			hPerm();
		else if(a == '9')
			zPerm();
		else if(a == '/')
			rAPerm();
		else if(a == '*')
			rBPerm();
		else if(a == '+')
			fPerm();  (not yet written)*/
/*Basic face turns of the cube, these are absolutely necessary
for all of the non-orientating moves, there is also an option to control what moves happen with an int*/
		else if(a == 'u' || i == 0)
			uMove();
		else if(a == 'U' || i == 1)
			uMovePrime();
		else if(a == 'r' || i == 2)
			rMove();
		else if(a == 'R' || i == 3)
			rMovePrime();
		else if(a == 'f' || i == 4)
			fMove();
		else if(a == 'F' || i == 5)
			fMovePrime();
		else if(a == 'l' || i == 6)
			lMove();
		else if(a == 'L' || i == 7)
			lMovePrime();
		else if(a == 'd' || i == 8)
			dMove();
		else if(a == 'D' || i == 9)
			dMovePrime();
		else if(a == 'b' || i == 10)
			bMove();
		else if(a == 'B' || i == 11)
			bMovePrime();
		else if(a == 'm' || i == 12)  //M, E, and S move the middle layers of the cube
			mMove();
		else if(a == 'M' || i == 13)
			mMovePrime();
		else if(a == 'e' || i == 14)
			eMove();
		else if(a == 'E' || i == 15)
			eMovePrime();
		else if(a == 's' || i == 16)
			sMove();
		else if(a == 'S' || i == 17)
			sMovePrime();
		else if(a == 'x')    //X, Y, and Z change the orientation of the cube on screen
			xOrientation();
		else if(a == 'X')
			xOrientationPrime();
		else if(a == 'y')
			yOrientation();
		else if(a == 'Y')
			yOrientationPrime();
		else if(a == 'z')
			zOrientation();
		else if(a == 'Z')
			zOrientationPrime();

		if(add)
			moveList.add(a); //to keep track for undoing
		
	public void undoLastMove()//passes in the opposite move to the moveChecker, needs fixing
	{
		char a = '!'; //just a placeholder
		if(moveList.size() > 0)
			a = moveList.get(moveList.size() - 1);
		else
			System.out.println("No moves to undo");
	    if(a == 'u')
	    	moveChecker('U', false, -1);
	    else if(a == 'U')
	    	moveChecker('u', false, -1);
	    else if(a == 'r')
	     	moveChecker('R', false, -1);
	    else if(a == 'r')
	    	moveChecker('R', false, -1);
	    else if(a == 'R')
	    	moveChecker('r', false, -1);
	    else if(a == 'l')
	    	moveChecker('L', false, -1);
	    else if(a == 'L')
	    	moveChecker('l', false, -1);
	    else if(a == 'd')
		moveChecker('D', false, -1);
	    else if(a == 'D')
	    	moveChecker('d', false, -1);
	    else if(a == 'b')
		moveChecker('B', false, -1);
	    else if(a == 'B')
	    	moveChecker('b', false, -1);
	    else if(a == 'm')
	        moveChecker('M', false, -1);
	    else if(a == 'M')
	    	moveChecker('m', false, -1);
	    else if(a == 'e')
		moveChecker('E', false, -1);
	    else if(a == 'E')
		moveChecker('e', false, -1);
	    else if(a == 's')
		moveChecker('S', false, -1);
	    else if(a == 'S')
		moveChecker('s', false, -1);
	    else if(a == 'x')
	 	moveChecker('X', false, -1);
	    else if(a == 'X')
		moveChecker('x', false, -1);
	    else if(a == 'y')
		moveChecker('Y', false, -1);
	    else if(a == 'Y')
	  	moveChecker('y', false, -1);
	    else if(a == 'z')
		moveChecker('Z', false, -1);
	    else if(a == 'Z')
		moveChecker('z', false, -1);
	//for each of these conditions, it tests what the last move is in the arrayList and effectively undos it
	}
	public String setColor(int row, int col, int faceNum, String color)
	{//Finds the exact cubeFace needed and both returns and sets the color of that cubeFace
		String ret = "";
		for(int i = 0; i < cubeList.size(); i++)
		{
			if(row == cubeList.get(i).getCoords(0))
				{
					if(col == cubeList.get(i).getCoords(1))
					{
						if(faceNum == cubeList.get(i).getCoords(2)) 
						{
							ret = cubeList.get(i).setColor(color);
							cubeList.get(i).resetImage();
							cubeN.setImage(cubeList.get(i).getImage(), i);
						}

					}
				}
		}
		return ret;
	}
	public String getColor(int row, int col, int faceNum) //same as setColor() but just returns the color of the cubeFace
	{
		String ret = "";
		for(int i = 0; i < cubeList.size(); i++)
		{
			if(row == cubeList.get(i).getCoords(0))
			{
				if(col == cubeList.get(i).getCoords(1))
				{
					if(faceNum == cubeList.get(i).getCoords(2))
						ret = cubeList.get(i).getColor();
				}
			}

		}
		return ret;
	}
	public void scramble(int moves) //num moves gets passed in, and it generates that many moves
	{//int moves is currently set at 30
		System.out.println("Scramble:");
		for(int i = 0; i < moves; i++)
		{
			int rand = (int)(Math.random() * 17); // change based on how many moves there are
			moveChecker('~', true, rand);
		}
		System.out.println("");
		startTime = LocalTime.now(); //starts timer
	}
	public void cubeSolved() //if the cube is solved... (already determined)
	{
		LocalTime endTime = LocalTime.now(); //gets the time it was solved
		int secs = endTime.getSecond() - startTime.getSecond(); //calculates seconds
		int mins = endTime.getMinute() - startTime.getMinute(); //calculates minutes
		int hours = endTime.getHour() - startTime.getHour(); //calculates hours
		if(secs < 0)
		{
			mins--;
			secs+=60;
		}
		if(mins < 0)
		{
			hours--;
			mins+=60;
		}
		if(hours < 0)
		{
			hours+=12;
		}
		//^makes the time calculation work
		System.out.println("Congratulations, you have solved the cube!");
		System.out.println("Your time is:" + "\t" + hours + ":" + mins + ":" + secs); //needs formatting ¯\_(ツ)_/¯
		startTime = LocalTime.now();
	}
//Ease of use methods:   (totally optional)
	public void uBPerm() // F2 U L R' F2 L' R U
	{
		moveChecker('f', false, -1);
		moveChecker('f', false, -1);
		moveChecker('u', false, -1);
		moveChecker('l', false, -1);
		moveChecker('R', false, -1);
		moveChecker('f', false, -1);
		moveChecker('f', false, -1);
		moveChecker('L', false, -1);
		moveChecker('r', false, -1);
		moveChecker('u', false, -1);
		moveChecker('f', false, -1);
		moveChecker('f', false, -1);
	}
	public void uAPerm() // F2 U L R' F2 L' R U
	{
		moveChecker('f', false, -1);
		moveChecker('f', false, -1);
		moveChecker('U', false, -1);
		moveChecker('l', false, -1);
		moveChecker('R', false, -1);
		moveChecker('f', false, -1);
		moveChecker('f', false, -1);
		moveChecker('L', false, -1);
		moveChecker('r', false, -1);
		moveChecker('U', false, -1);
		moveChecker('f', false, -1);
		moveChecker('f', false, -1);
	}
	public void aAPerm() // R' F R' B2 R F' R' B2 R2
	{
		moveChecker('R', false, -1);
		moveChecker('f', false, -1);
		moveChecker('R', false, -1);
		moveChecker('b', false, -1);
		moveChecker('b', false, -1);
		moveChecker('r', false, -1);
		moveChecker('F', false, -1);
		moveChecker('R', false, -1);
		moveChecker('b', false, -1);
		moveChecker('b', false, -1);
		moveChecker('r', false, -1);
		moveChecker('r', false, -1);
	}
	public void aBPerm() // x' R2 U2 R D R' U2 R D' R
	{
		moveChecker('X', false, -1);
		moveChecker('r', false, -1);
		moveChecker('r', false, -1);
		moveChecker('u', false, -1);
		moveChecker('u', false, -1);
		moveChecker('r', false, -1);
		moveChecker('d', false, -1);
		moveChecker('R', false, -1);
		moveChecker('u', false, -1);
		moveChecker('u', false, -1);
		moveChecker('r', false, -1);
		moveChecker('D', false, -1);
		moveChecker('r', false, -1);
		moveChecker('x', false, -1);
	}
	public void ePerm() // x' R U' R' D R U R' D' R U R' D R U' R' D'
	{
		moveChecker('X', false, -1);
		moveChecker('r', false, -1);
		moveChecker('U', false, -1);
		moveChecker('R', false, -1);
		moveChecker('d', false, -1);
		moveChecker('r', false, -1);
		moveChecker('u', false, -1);
		moveChecker('R', false, -1);
		moveChecker('D', false, -1);
		moveChecker('r', false, -1);
		moveChecker('u', false, -1);
		moveChecker('R', false, -1);
		moveChecker('d', false, -1);
		moveChecker('r', false, -1);
		moveChecker('U', false, -1);
		moveChecker('R', false, -1);
		moveChecker('D', false, -1);
		moveChecker('x', false, -1);
	}

	
//Beginning of necessary methods for solving it	
		
	//orientation moves:
	public void xOrientation()
	{
		System.out.print("(");
		moveChecker('r', false, -1);
		moveChecker('m', false, -1);
		moveChecker('L', false, -1);
		System.out.print(")X ");
	}
	public void xOrientationPrime()
	{
		System.out.print("(");
		moveChecker('R', false, -1);
		moveChecker('M', false, -1);
		moveChecker('l', false, -1);
		System.out.print(")X' ");
	}
	public void yOrientation()
	{
		System.out.print("(");
		moveChecker('u', false, -1);
		moveChecker('E', false, -1);
		moveChecker('D', false, -1);
		System.out.print(")Y ");
	}
	public void yOrientationPrime()
	{
		System.out.print("(");
		moveChecker('U', false, -1);
		moveChecker('e', false, -1);
		moveChecker('d', false, -1);
		System.out.print(")Y' ");
	}
	public void zOrientation()
	{
		System.out.print("(");
		moveChecker('f', false, -1);
		moveChecker('s', false, -1);
		moveChecker('B', false, -1);
		System.out.print(")Z ");
	}
	public void zOrientationPrime()
	{
		System.out.print("(");
		moveChecker('F', false, -1);
		moveChecker('S', false, -1);
		moveChecker('b', false, -1);
		System.out.print(")Z' ");
	}
		
/*Each of the following single layer moves must get and set the colors of all necessary cubeFaces to simulate a move. It also tests
whether the cube is solved after making the move, and calls repaint() which is overridden by paintcomponent() or something*/
	
	public void sMove() // Clockwise
	{
		String store = getColor(0, 1, 3);
		store = setColor(0, 1, 4, store);
		store = setColor(2, 1, 5, store);
		store = setColor(2, 1, 0, store);
		setColor(0, 1, 3, store);
		//Shifts edges on the middle layer

		store = getColor(1, 1, 3);
		store = setColor(1, 1, 4, store);
		store = setColor(1, 1, 5, store);
		store = setColor(1, 1, 0, store);
		setColor(1, 1, 3, store);
		//Shifts centers

		store = getColor(2, 1, 3);
		store = setColor(2, 1, 4, store);
		store = setColor(0, 1, 5, store);
		store = setColor(0, 1, 0, store);
		setColor(2, 1, 3, store);
		//Shifts other edges on the middle layer

		System.out.print("S  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
	}
	public void sMovePrime() // Counter-Clockwise
	{
		String store = getColor(0, 1, 3);
		store = setColor(2, 1, 0, store);
		store = setColor(2, 1, 5, store);
		store = setColor(0, 1, 4, store);
		setColor(0, 1, 3, store);
		//Shifts edges on the middle layer

		store = getColor(1, 1, 3);
		store = setColor(1, 1, 0, store);
		store = setColor(1, 1, 5, store);
		store = setColor(1, 1, 4, store);
		setColor(1, 1, 3, store);
		//Shifts centers

		store = getColor(2, 1, 3);
		store = setColor(0, 1, 0, store);
		store = setColor(0, 1, 5, store);
		store = setColor(2, 1, 4, store);
		setColor(2, 1, 3, store);
		//Shifts other edges on the middle layer

		System.out.print("S'  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
	}
	public void eMove() // Clockwise
	{
		String store = getColor(1, 0, 2);
		store = setColor(1, 0, 4, store);
		store = setColor(1, 2, 1, store);
		store = setColor(1, 2, 0, store);
		setColor(1, 0, 2, store);
		//Shifts edges on the middle layer(across)

		store = getColor(1, 1, 2);
		store = setColor(1, 1, 4, store);
		store = setColor(1, 1, 1, store);
		store = setColor(1, 1, 0, store);
		setColor(1, 1, 2, store);
		//Shifts centers

		store = getColor(1, 2, 2);
		store = setColor(1, 2, 4, store);
		store = setColor(1, 0, 1, store);
		store = setColor(1, 0, 0, store);
		setColor(1, 2, 2, store);
		//Shifts other edges on the middle layer (across

		System.out.print("E  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
	}
	public void eMovePrime() // Counter-Clockwise
	{
		String store = getColor(1, 0, 2);
		store = setColor(1, 2, 0, store);
		store = setColor(1, 2, 1, store);
		store = setColor(1, 0, 4, store);
		setColor(1, 0, 2, store);
		//Shifts edges on the middle layer(across)

		store = getColor(1, 1, 2);
		store = setColor(1, 1, 0, store);
		store = setColor(1, 1, 1, store);
		store = setColor(1, 1, 4, store);
		setColor(1, 1, 2, store);
		//Shifts centers

		store = getColor(1, 2, 2);
		store = setColor(1, 0, 0, store);
		store = setColor(1, 0, 1, store);
		store = setColor(1, 2, 4, store);
		setColor(1, 2, 2, store);
		//Shifts other edges on the middle layer (across

		System.out.print("E'  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
	}
	public void mMove() // Clockwise
	{
		String store = getColor(2, 1, 2);
		store = setColor(1, 0, 3, store);
		store = setColor(0, 1, 1, store);
		store = setColor(1, 2, 5, store);
		setColor(2, 1, 2, store);
		//Shifts edges on the middle layer

		store = getColor(1, 1, 2);
		store = setColor(1, 1, 3, store);
		store = setColor(1, 1, 1, store);
		store = setColor(1, 1, 5, store);
		setColor(1, 1, 2, store);
		//Shifts centers

		store = getColor(0, 1, 2);
		store = setColor(1, 2, 3, store);
		store = setColor(2, 1, 1, store);
		store = setColor(1, 0, 5, store);
		setColor(0, 1, 2, store);
		//Shifts other edges on the middle layer

		System.out.print("M  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
	}
	public void mMovePrime() // Counter-Clockwise
	{
		String store = getColor(2, 1, 2);
		store = setColor(1, 2, 5, store);
		store = setColor(0, 1, 1, store);
		store = setColor(1, 0, 3, store);
		setColor(2, 1, 2, store);
		//Shifts edges on the middle layer

		store = getColor(1, 1, 2);
		store = setColor(1, 1, 5, store);
		store = setColor(1, 1, 1, store);
		store = setColor(1, 1, 3, store);
		setColor(1, 1, 2, store);
		//Shifts centers

		store = getColor(0, 1, 2);
		store = setColor(1, 0, 5, store);
		store = setColor(2, 1, 1, store);
		store = setColor(1, 2, 3, store);
		setColor(0, 1, 2, store);
		//Shifts other edges on the middle layer

		System.out.print("M'  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
		}
	public void bMove() // Clockwise
	{
		String store = getColor(0, 0, 1);
		store = setColor(2, 0, 1, store);
		store = setColor(2, 2, 1, store);
		store = setColor(0, 2, 1, store);
		setColor(0, 0, 1, store);
		//Shifts corners on the back side

		store = getColor(1, 0, 1);
		store = setColor(2, 1, 1, store);
		store = setColor(1, 2, 1, store);
		store = setColor(0, 1, 1, store);
		setColor(1, 0, 1, store);
		//Shifts edges on the back side

		store = getColor(0, 2, 4);
		store = setColor(0, 2, 3, store);
		store = setColor(2, 2, 0, store);
		store = setColor(2, 2, 5, store);
		setColor(0, 2, 4, store);
		//Shifts one set of corners on the top layer

		store = getColor(1, 2, 4);
		store = setColor(1, 2, 3, store);
		store = setColor(1, 2, 0, store);
		store = setColor(1, 2, 5, store);
		setColor(1, 2, 4, store);
		//Shifts edges on the back layer

		store = getColor(2, 2, 3);
		store = setColor(0, 2, 0, store);
		store = setColor(0, 2, 5, store);
		store = setColor(2, 2, 4, store);
		setColor(2, 2, 3, store);
		//Shifts other set of corners on the top layer

		System.out.print("B  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
	}
	public void bMovePrime() // Counter-Clockwise
	{
		String store = getColor(0, 0, 1);
		store = setColor(0, 2, 1, store);
		store = setColor(2, 2, 1, store);
		store = setColor(2, 0, 1, store);
		setColor(0, 0, 1, store);
		//Shifts corners on the back side

		store = getColor(1, 0, 1);
		store = setColor(0, 1, 1, store);
		store = setColor(1, 2, 1, store);
		store = setColor(2, 1, 1, store);
		setColor(1, 0, 1, store);
		//Shifts edges on the back side

		store = getColor(0, 2, 4);
		store = setColor(2, 2, 5, store);
		store = setColor(2, 2, 0, store);
		store = setColor(0, 2, 3, store);
		setColor(0, 2, 4, store);
		//Shifts one set of corners on the top layer

		store = getColor(1, 2, 4);
		store = setColor(1, 2, 5, store);
		store = setColor(1, 2, 0, store);
		store = setColor(1, 2, 3, store);
		setColor(1, 2, 4, store);
		//Shifts edges on the back layer

		store = getColor(2, 2, 3);
		store = setColor(2, 2, 4, store);
		store = setColor(0, 2, 5, store);
		store = setColor(0, 2, 0, store);
		setColor(2, 2, 3, store);
		//Shifts other set of corners on the top layer

		System.out.print("B'  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
	}
	public void uMove() // Clockwise
	{
		String store = getColor(0, 0, 3);
		store = setColor(0, 2, 3, store);
		store = setColor(2, 2, 3, store);
		store = setColor(2, 0, 3, store);
		setColor(0, 0, 3, store);
		//Shifts corners on the top side

		store = getColor(1, 0, 3);
		store = setColor(0, 1, 3, store);
		store = setColor(1, 2, 3, store);
		store = setColor(2, 1, 3, store);
		setColor(1, 0, 3, store);
		//Shifts edges on the top side

		store = getColor(0, 0, 2);
		store = setColor(0, 2, 0, store);
		store = setColor(0, 2, 1, store);
		store = setColor(0, 0, 4, store);
		setColor(0, 0, 2, store);
		//Shifts one set of corners on the top layer

		store = getColor(0, 1, 2);
		store = setColor(0, 1, 0, store);
		store = setColor(0, 1, 1, store);
		store = setColor(0, 1, 4, store);
		setColor(0, 1, 2, store);
		//Shifts edges on the top layer

		store = getColor(0, 2, 2);
		store = setColor(0, 0, 0, store);
		store = setColor(0, 0, 1, store);
		store = setColor(0, 2, 4, store);
		setColor(0, 2, 2, store);
		//Shifts other set of corners on the top layer

		System.out.print("U  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
	}
	public void uMovePrime() // Counter-Clockwise
	{
		String store = getColor(0, 0, 3);
		store = setColor(2, 0, 3, store);
		store = setColor(2, 2, 3, store);
		store = setColor(0, 2, 3, store);
		setColor(0, 0, 3, store);
		//Shifts corners on the top side

		store = getColor(1, 0, 3);
		store = setColor(2, 1, 3, store);
		store = setColor(1, 2, 3, store);
		store = setColor(0, 1, 3, store);
		setColor(1, 0, 3, store);
		//Shifts edges on the top side

		store = getColor(0, 0, 2);
		store = setColor(0, 0, 4, store);
		store = setColor(0, 2, 1, store);
		store = setColor(0, 2, 0, store);
		setColor(0, 0, 2, store);
		//Shifts one set of corners on the top layer

		store = getColor(0, 1, 2);
		store = setColor(0, 1, 4, store);
		store = setColor(0, 1, 1, store);
		store = setColor(0, 1, 0, store);
		setColor(0, 1, 2, store);
		//Shifts edges on the top layer

		store = getColor(0, 2, 2);
		store = setColor(0, 2, 4, store);
		store = setColor(0, 0, 1, store);
		store = setColor(0, 0, 0, store);
		setColor(0, 2, 2, store);
		//Shifts other set of corners on the top layer

		System.out.print("U'  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
	}

	public void rMove() // Clockwise
	{
		String store = getColor(0, 0, 4);
		store = setColor(0, 2, 4, store);
		store = setColor(2, 2, 4, store);
		store = setColor(2, 0, 4, store);
		setColor(0, 0, 4, store);
		//Shifts corners on the right side

		store = getColor(1, 0, 4);
		store = setColor(0, 1, 4, store);
		store = setColor(1, 2, 4, store);
		store = setColor(2, 1, 4, store);
		setColor(1, 0, 4, store);
		//Shifts edges on the right side

		store = getColor(2, 0, 3);
		store = setColor(0, 2, 1, store);
		store = setColor(2, 2, 5, store);
		store = setColor(2, 2, 2, store);
		setColor(2, 0, 3, store);
		//Shifts one set of corners on the right layer

		store = getColor(2, 1, 3);
		store = setColor(1, 2, 1, store);
		store = setColor(2, 1, 5, store);
		store = setColor(1, 2, 2, store);
		setColor(2, 1, 3, store);
		//Shifts edges on the right layer

		store = getColor(2, 2, 3);
		store = setColor(2, 2, 1, store);
		store = setColor(2, 0, 5, store);
		store = setColor(0, 2, 2, store);
		setColor(2, 2, 3, store);
		//Shifts other set of corners on the right layer

		System.out.print("R  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
	}
	public void rMovePrime() // Counter-Clockwise
	{
		String store = getColor(0, 0, 4);
		store = setColor(2, 0, 4, store);
		store = setColor(2, 2, 4, store);
		store = setColor(0, 2, 4, store);
		setColor(0, 0, 4, store);
		//Shifts corners on the right side

		store = getColor(1, 0, 4);
		store = setColor(2, 1, 4, store);
		store = setColor(1, 2, 4, store);
		store = setColor(0, 1, 4, store);
		setColor(1, 0, 4, store);
		//Shifts edges on the right side

		store = getColor(2, 0, 3);
		store = setColor(2, 2, 2, store);
		store = setColor(2, 2, 5, store);
		store = setColor(0, 2, 1, store);
		setColor(2, 0, 3, store);
		//Shifts one set of corners on the right layer

		store = getColor(2, 1, 3);
		store = setColor(1, 2, 2, store);
		store = setColor(2, 1, 5, store);
		store = setColor(1, 2, 1, store);
		setColor(2, 1, 3, store);
		//Shifts edges on the right layer

		store = getColor(2, 2, 3);
		store = setColor(0, 2, 2, store);
		store = setColor(2, 0, 5, store);
		store = setColor(2, 2, 1, store);
		setColor(2, 2, 3, store);
		//Shifts other set of corners on the right layer

		System.out.print("R'  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
	}
	public void fMove() // Clockwise
	{
		String store = getColor(0, 0, 2);
		store = setColor(0, 2, 2, store);
		store = setColor(2, 2, 2, store);
		store = setColor(2, 0, 2, store);
		setColor(0, 0, 2, store);
		//Shifts corners on the front side done

		store = getColor(0, 1, 2);
		store = setColor(1, 2, 2, store);
		store = setColor(2, 1, 2, store);
		store = setColor(1, 0, 2, store);
		setColor(0, 1, 2, store);
		//Shifts edges on the front side

		store = getColor(0, 0, 3);
		store = setColor(0, 0, 4, store);
		store = setColor(2, 0, 5, store);
		store = setColor(2, 0, 0, store);
		setColor(0, 0, 3, store);
		//Shifts one set of corners on the front layer done

		store = getColor(1, 0, 3);
		store = setColor(1, 0, 4, store);
		store = setColor(1, 0, 5, store);
		store = setColor(1, 0, 0, store);
		setColor(1, 0, 3, store);
		//Shifts edges on the right layer

		store = getColor(2, 0, 3);
		store = setColor(2, 0, 4, store);
		store = setColor(0, 0, 5, store);
		store = setColor(0, 0, 0, store);
		setColor(2, 0, 3, store);
		//Shifts other set of corners on the right layer

		System.out.print("F  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
	}
	public void fMovePrime() // Counter-Clockwise
	{
		String store = getColor(0, 0, 2);
		store = setColor(2, 0, 2, store);
		store = setColor(2, 2, 2, store);
		store = setColor(0, 2, 2, store);
		setColor(0, 0, 2, store);
		//Shifts corners on the front side done

		store = getColor(0, 1, 2);
		store = setColor(1, 0, 2, store);
		store = setColor(2, 1, 2, store);
		store = setColor(1, 2, 2, store);
		setColor(0, 1, 2, store);
		//Shifts edges on the front side

		store = getColor(0, 0, 3);
		store = setColor(2, 0, 0, store);
		store = setColor(2, 0, 5, store);
		store = setColor(0, 0, 4, store);
		setColor(0, 0, 3, store);
		//Shifts one set of corners on the front layer done

		store = getColor(1, 0, 3);
		store = setColor(1, 0, 0, store);
		store = setColor(1, 0, 5, store);
		store = setColor(1, 0, 4, store);
		setColor(1, 0, 3, store);
		//Shifts edges on the right layer

		store = getColor(2, 0, 3);
		store = setColor(0, 0, 0, store);
		store = setColor(0, 0, 5, store);
		store = setColor(2, 0, 4, store);
		setColor(2, 0, 3, store);
		//Shifts other set of corners on the right layer

		System.out.print("F'  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
	}
	public void lMove() // Clockwise
	{
		String store = getColor(0, 0, 0);
		store = setColor(2, 0, 0, store);
		store = setColor(2, 2, 0, store);
		store = setColor(0, 2, 0, store);
		setColor(0, 0, 0, store);
		//Shifts corners on the left side done

		store = getColor(0, 1, 0);
		store = setColor(1, 0, 0, store);
		store = setColor(2, 1, 0, store);
		store = setColor(1, 2, 0, store);
		setColor(0, 1, 0, store);
		//Shifts edges on the left side

		store = getColor(0, 0, 3);
		store = setColor(2, 0, 2, store);
		store = setColor(0, 2, 5, store);
		store = setColor(0, 0, 1, store);
		setColor(0, 0, 3, store);
		//Shifts one set of corners on the left layer

		store = getColor(0, 1, 3);
		store = setColor(1, 0, 2, store);
		store = setColor(0, 1, 5, store);
		store = setColor(1, 0, 1, store);
		setColor(0, 1, 3, store);
		//Shifts edges on the left layer

		store = getColor(0, 2, 3);
		store = setColor(0, 0, 2, store);
		store = setColor(0, 0, 5, store);
		store = setColor(2, 0, 1, store);
		setColor(0, 2, 3, store);
		//Shifts other set of corners on the left layer

		System.out.print("L  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
	}
	public void lMovePrime() // Clockwise
	{
		String store = getColor(0, 0, 0);
		store = setColor(0, 2, 0, store);
		store = setColor(2, 2, 0, store);
		store = setColor(2, 0, 0, store);
		setColor(0, 0, 0, store);
		//Shifts corners on the left side done

		store = getColor(0, 1, 0);
		store = setColor(1, 2, 0, store);
		store = setColor(2, 1, 0, store);
		store = setColor(1, 0, 0, store);
		setColor(0, 1, 0, store);
		//Shifts edges on the left side

		store = getColor(0, 0, 3);
		store = setColor(0, 0, 1, store);
		store = setColor(0, 2, 5, store);
		store = setColor(2, 0, 2, store);
		setColor(0, 0, 3, store);
		//Shifts one set of corners on the left layer

		store = getColor(0, 1, 3);
		store = setColor(1, 0, 1, store);
		store = setColor(0, 1, 5, store);
		store = setColor(1, 0, 2, store);
		setColor(0, 1, 3, store);
		//Shifts edges on the left layer

		store = getColor(0, 2, 3);
		store = setColor(2, 0, 1, store);
		store = setColor(0, 0, 5, store);
		store = setColor(0, 0, 2, store);
		setColor(0, 2, 3, store);
		//Shifts other set of corners on the left layer

		System.out.print("L'  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
	}
	public void dMove() // Clockwise
	{
		String store = getColor(0, 0, 5);
		store = setColor(2, 0, 5, store);
		store = setColor(2, 2, 5, store);
		store = setColor(0, 2, 5, store);
		setColor(0, 0, 5, store);
		//Shifts corners on the bottom side

		store = getColor(1, 0, 5);
		store = setColor(2, 1, 5, store);
		store = setColor(1, 2, 5, store);
		store = setColor(0, 1, 5, store);
		setColor(1, 0, 5, store);
		//Shifts edges on the bottom side

		store = getColor(2, 0, 2);
		store = setColor(2, 0, 4, store);
		store = setColor(2, 2, 1, store);
		store = setColor(2, 2, 0, store);
		setColor(2, 0, 2, store);
		//Shifts one set of corners on the bottom layer

		store = getColor(2, 1, 2);
		store = setColor(2, 1, 4, store);
		store = setColor(2, 1, 1, store);
		store = setColor(2, 1, 0, store);
		setColor(2, 1, 2, store);
		//Shifts edges on the bottom layer

		store = getColor(2, 2, 2);
		store = setColor(2, 2, 4, store);
		store = setColor(2, 0, 1, store);
		store = setColor(2, 0, 0, store);
		setColor(2, 2, 2, store);
		//Shifts other set of corners on the bottom layer

		System.out.print("D  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
		}
	public void dMovePrime() // Counter-Clockwise
	{
		String store = getColor(0, 0, 5);
		store = setColor(0, 2, 5, store);
		store = setColor(2, 2, 5, store);
		store = setColor(2, 0, 5, store);
		setColor(0, 0, 5, store);
		//Shifts corners on the bottom side

		store = getColor(1, 0, 5);
		store = setColor(0, 1, 5, store);
		store = setColor(1, 2, 5, store);
		store = setColor(2, 1, 5, store);
		setColor(1, 0, 5, store);
		//Shifts edges on the bottom side

		store = getColor(2, 0, 2);
		store = setColor(2, 2, 0, store);
		store = setColor(2, 2, 1, store);
		store = setColor(2, 0, 4, store);
		setColor(2, 0, 2, store);
		//Shifts one set of corners on the bottom layer

		store = getColor(2, 1, 2);
		store = setColor(2, 1, 0, store);
		store = setColor(2, 1, 1, store);
		store = setColor(2, 1, 4, store);
		setColor(2, 1, 2, store);
		//Shifts edges on the bottom layer

		store = getColor(2, 2, 2);
		store = setColor(2, 0, 0, store);
		store = setColor(2, 0, 1, store);
		store = setColor(2, 2, 4, store);
		setColor(2, 2, 2, store);
		//Shifts other set of corners on the bottom layer

		System.out.print("D'  ");
		cubeN.repaint();
		if(cubeN.isSolved())
			cubeSolved();
	}
}
