import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;


public class CubeFace extends JPanel
{
	private final String CUBE_SPRITES_PATH = "E:\\FINAL PROJECT\\Cube Sprites\\5x Cubies";

	private int [] coord; //coord should be an array of three ints denoting the simulated 3D coordinates (X, Y, Face#);
	//Face#s:    Back Left 0, Back Right 1, Left 2, Up 3, Right 4, Down 5

	private String color; //White, Yellow, Blue, Green, Orange, Red
	private String type; /*types are: bl(back left), blc (back left cutoff), br (back right), brc (back right cutoff)
	u (up), l (left), lm (left middle), r (right), rm (right middle), d (down), dm(down middle), dc (down cutoff)*/

	private BufferedImage img;
	private Graphics2D g;

//precondition: coo must be an array of three ints, x, y, and face#
	public CubeFace(int[]coo, String c, String t)
	{
		super(); //JPanel constructor

		coord = coo;
		color = c;
		type = t;

		//System.out.println(c);

        String imagePath = findImage(); //Image path retrieved

		//System.out.println(imagePath);

		try{img = ImageIO.read(new File(imagePath));}  //img = the image retreived using findImage()
		catch(IOException e){e.printStackTrace();} //I dont know what this does but its necessary

		g = (Graphics2D)img.getGraphics(); //I dont know if this does anything
		paintComponent(g);
	}
	public BufferedImage getImage()
	{
		return img;
	}
	public int getX()
	{
		int ret = 300;
		if(coord[2] == 0) //back left face
		{
			ret = (coord[1] * 59) + 6;
		}
		else if(coord[2] == 1)//back right face
		{
			ret = (coord[1] * 62) + 495;
		}
		else if(coord[2] == 2)//left face
		{
			ret = (coord[1] * 62) + 162;
		}
		else if(coord[2] == 3)//up face
		{
			ret = (coord[0] + coord[1]) * 60 + 165;
		}
		else if(coord[2] == 4)//right face
		{
			ret = (coord[1] * 59) + 345;
		}
		else if(coord[2] == 5)//down face
		{
			if(coord[0] + coord[1] < 3)
			{
				ret = (coord[0] + coord[1]) * 60 + 160;
			}
			else
				ret = (coord[0] + coord[1]) * 60 + 170;
		}
		return ret;
	}
	public int getY()
	{
		int ret = 300;
		if(coord[2] == 0) //back left face
		{
			ret = coord[0]*71 - (coord[1] * 30) + 68;
		}
		else if(coord[2] == 1)//back right face
		{
			ret = coord[0]*71 + (coord[1] * 30) + 5;

		}
		else if(coord[2] == 2)//left face
		{
			ret = coord[0]*71 + (coord[1] * 30) + 200;
		}
		else if(coord[2] == 3)//up face
		{
			ret = coord[0] * 30 - coord[1] * 30 + 165;
		}
		else if(coord[2] == 4)//right face
		{
			ret = coord[0]*71 - (coord[1] * 30) + 257;

		}
		else if(coord[2] == 5)//down face
		{
			if(coord[0] == 0 && coord[1] == 2)
				ret = coord[0] * 30 - coord[1] * 30 + 540;
			else
				ret = coord[0] * 30 - coord[1] * 30 + 525;
		}
		return ret;
	}
	public int getFaceNum()
	{
		return coord[2];
	}
	public String getType()
	{
		return type;
	}
	public String setColor(String nColor) //returns the current color and sets the color of this CubeFace
	{
		String ret = color;
		color = nColor;
		return ret;
	}
	public int getCoords(int index)
	{
		return coord[index];
	}
	public String getColor()
	{
		return color;
	}
	public void resetImage()
	{
		String imagePath = findImage();
		try{img = ImageIO.read(new File(imagePath));}
		catch(IOException e){e.printStackTrace();}
	}
	public String findImage() //returns the path where the image returned is located
	{
		String typPath = "";
		String colPath = "";
		if(type.equals("br") || type.equals("l"))
		{
			typPath = "\\Left\\";
			if(color.equals("White"))
			{
				colPath = "Side White Cubie 2 5x.png";
			}
			else if(color.equals("Yellow"))
			{
				colPath = "Side Yellow Cubie 2 5x.png";
			}
			else if(color.equals("Green"))
			{
				colPath = "Side Green Cubie 2 5x.png";
			}
			else if(color.equals("Blue"))
			{
				colPath = "Side Blue Cubie 2 5x.png";
			}
			else if(color.equals("Orange"))
			{
				colPath = "Side Orange Cubie 2 5x.png";
			}
			else if(color.equals("Red"))
			{
				colPath = "Side Red Cubie 2 5x.png";
			}

		}
		else if(type.equals("blc"))
		{
			typPath = "\\Cut Side Left\\";
			if(color.equals("White"))
			{
				colPath = "Cut Side White 2 5x.png";
			}
			else if(color.equals("Yellow"))
			{
				colPath = "Cut Side Yellow2 5x.png";
			}
			else if(color.equals("Green"))
			{
				colPath = "Cut Side Green 2 5x.png";
			}
			else if(color.equals("Blue"))
			{
				colPath = "Cut Side Blue 2 5x.png";
			}
			else if(color.equals("Orange"))
			{
				colPath = "Cut Side Orange 2 5x.png";
			}
			else if(color.equals("Red"))
			{
				colPath = "Cut Side Red 2 5x.png";
			}

		}
		else if(type.equals("bl") || type.equals("r"))
		{
			typPath = "\\Right\\";
			if(color.equals("White"))
			{
				colPath = "Side White Cubie5x.png";
			}
			else if(color.equals("Yellow"))
			{
				colPath = "Side Yellow Cubie5x.png";
			}
			else if(color.equals("Green"))
			{
				colPath = "Side Green Cubie5x.png";
			}
			else if(color.equals("Blue"))
			{
				colPath = "Side Blue Cubie5x.png";
			}
			else if(color.equals("Orange"))
			{
				colPath = "Side Orange Cubie5x.png";
			}
			else if(color.equals("Red"))
			{
				colPath = "Side Red Cubie5x.png";
			}
		}
		else if(type.equals("brc"))
		{
			typPath = "\\Cut Side Right\\";
			if(color.equals("White"))
			{
				colPath = "Cut Side White5x.png";
			}
			else if(color.equals("Yellow"))
			{
				colPath = "Cut Side Yellow5x.png";
			}
			else if(color.equals("Green"))
			{
				colPath = "Cut Side Green5x.png";
			}
			else if(color.equals("Blue"))
			{
				colPath = "Cut Side Blue5x.png";
			}
			else if(color.equals("Orange"))
			{
				colPath = "Cut Side Orange5x.png";
			}
			else if(color.equals("Red"))
			{
				colPath = "Cut Side Red5x.png";
			}
		}
		else if(type.equals("u") || type.equals("d"))
		{
			typPath = "\\Top\\";
			if(color.equals("White"))
			{
				colPath = "Top White Cubie5x.png";
			}
			else if(color.equals("Yellow"))
			{
				colPath = "Top Yellow Cubie5x.png";
			}
			else if(color.equals("Green"))
			{
				colPath = "Top Green Cubie5x.png";
			}
			else if(color.equals("Blue"))
			{
				colPath = "Top Blue Cubie5x.png";
			}
			else if(color.equals("Orange"))
			{
				colPath = "Top Orange Cubie5x.png";
			}
			else if(color.equals("Red"))
			{
				colPath = "Top Red Cubie5x.png";
			}
		}
		else if(type.equals("lm"))
		{
			typPath = "\\Middle Left\\";
			if(color.equals("White"))
			{
				colPath = "Middle White Cubie 2 5x.png";
			}
			else if(color.equals("Yellow"))
			{
				colPath = "Middle Yellow Cubie 2 5x.png";
			}
			else if(color.equals("Green"))
			{
				colPath = "Middle Green Cubie 2 5x.png";
			}
			else if(color.equals("Blue"))
			{
				colPath = "Middle Blue Cubie 2 5x.png";
			}
			else if(color.equals("Orange"))
			{
				colPath = "Middle Orange Cubie 2 5x.png";
			}
			else if(color.equals("Red"))
			{
				colPath = "Middle Red Cubie 2 5x.png";
			}
		}
		else if(type.equals("rm"))
		{
			typPath = "\\Middle Right\\";
			if(color.equals("White"))
			{
				colPath = "Middle White Cubie5x.png";
			}
			else if(color.equals("Yellow"))
			{
				colPath = "Middle Yellow Cubie5x.png";
			}
			else if(color.equals("Green"))
			{
				colPath = "Middle Green Cubie5x.png";
			}
			else if(color.equals("Blue"))
			{
				colPath = "Middle Blue Cubie5x.png";
			}
			else if(color.equals("Orange"))
			{
				colPath = "Middle Orange Cubie5x.png";
			}
			else if(color.equals("Red"))
			{
				colPath = "Middle Red Cubie5x.png";
			}
		}
		else if(type.equals("dc"))
		{
			typPath = "\\Cut Bottom\\";
			if(color.equals("White"))
			{
				colPath = "Cut Bottom White Cubie5x.png";
			}
			else if(color.equals("Yellow"))
			{
				colPath = "Cut Bottom Yellow Cubie5x.png";
			}
			else if(color.equals("Green"))
			{
				colPath = "Cut Bottom Green Cubie5x.png";
			}
			else if(color.equals("Blue"))
			{
				colPath = "Cut Bottom Blue Cubie5x.png";
			}
			else if(color.equals("Orange"))
			{
				colPath = "Cut Bottom Orange Cubie5x.png";
			}
			else if(color.equals("Red"))
			{
				colPath = "Cut Bottom Red Cubie5x.png";
			}
		}
		else if(type.equals("dm"))
		{
			typPath = "\\Middle Down\\";
			if(color.equals("White"))
			{
				colPath = "Middle Down White5x.png";
			}
			else if(color.equals("Yellow"))
			{
				colPath = "Middle Down Yellow5x.png";
			}
			else if(color.equals("Green"))
			{
				colPath = "Middle Down Green5x.png";
			}
			else if(color.equals("Blue"))
			{
				colPath = "Middle Down Blue5x.png";
			}
			else if(color.equals("Orange"))
			{
				colPath = "Middle Down Orange5x.png";
			}
			else if(color.equals("Red"))
			{
				colPath = "Middle Down Red5x.png";
			}
		}
		return CUBE_SPRITES_PATH + typPath + colPath;
	}
}