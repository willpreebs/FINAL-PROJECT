import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.*;
import java.time.LocalTime;

public class CubeNet extends JPanel
{
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;
	public final String CUBE_PATH = "Cube Sprites//Whole Cube (white)5x.png"; //the whole cube
	//this needs to be changed based on location -- currently for github

	private BufferedImage cubeSkeleton; //whole cube
	private ArrayList<BufferedImage> imageList;//individual images of the cube faces
	private ArrayList<CubeFace> faceList;//cooresponding CubeFace instance for each image
	private Graphics2D g;
	private JFrame window;

	private LocalTime startTime;


	public CubeNet(JFrame window)
	{
		super();
		this.window = window;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground (Color.black);
		setFocusable(true);
		requestFocus();

		startTime = LocalTime.now();

		imageList = new ArrayList<BufferedImage>();
		faceList = new ArrayList<CubeFace>();
		try{cubeSkeleton = ImageIO.read(new File(CUBE_PATH));} //cubeSkeleton is now the image in the file
		catch(IOException e){e.printStackTrace();} //I dont know what this does but its necessary
		g = (Graphics2D)cubeSkeleton.getGraphics();
		paintComponent(g);

	}
	public LocalTime getTime()
	{
		return startTime;
	}
	@Override
	 protected void paintComponent(Graphics ge) //paints all images in imageList eventually in the correct spot
	 {
	     super.paintComponent(ge);
         ge.drawImage(cubeSkeleton, 0, 0, null);
         for(int i = 0; i < imageList.size(); i++)
         {
			 ge.drawImage(imageList.get(i), faceList.get(i).getX(), faceList.get(i).getY(), null); //getX and getY return ints of the x and y coordinates of the image
		 }
	 }

	 public void addImage(BufferedImage ad, CubeFace cub)
	 {
		 imageList.add(ad);
		 faceList.add(cub);
	 }
	 public void setImage(BufferedImage set, int index)
	 {
		 imageList.set(index, set);
	 }

	 public static String getType(int faceNum, int row, int col) //based on the face, taking into account middle and cut pieces, it returns the type of that cubeFace
	 {
		String ret = "";
		if(faceNum == 0) // back left
		{
			if(row == 2 && col == 2)
				ret = "blc";
			else
				ret = "bl";
		}
		else if(faceNum == 1) // back right
		{
			if(row == 2 && col == 0)
				ret = "brc";
			else
				ret = "br";
		}
		else if(faceNum == 2) // left
		{
			if(col == 2)
				ret = "lm";
			else
				ret = "l";
		}
		else if(faceNum == 3) // top
		{
			ret = "u";
		}
		else if(faceNum == 4) // right
		{
			if(col == 0)
				ret = "rm";
			else
				ret = "r";
		}
		else if(faceNum == 5) // down
		{
			if(row == 0 && col == 2)
				ret = "dc";
			else if(row + col == 2)
				ret = "dm";
			else
				ret = "d";
		}
		return ret;
	}

	public ArrayList<CubeFace>getCubeFaceArr()
	{
		return faceList;
	}

	public boolean isSolved()
	{
		String[] hold = new String[6];
		boolean ret = true;
		for(int f = 0; f < 6; f++)
		{
			for(CubeFace cub: faceList)
			{
				if(cub.getFaceNum() == f) //if the cubeFace matches the faceNum currently being tested, test if it is the first cube seen on its face,
				{//else test whether it matches the first color seen on that face
					if(hold[f] == null)
						hold[f] = cub.getColor();
					else if(cub.getColor() != hold[f])
						return false;
				}
			}
		}
		return ret;
	}
 }
