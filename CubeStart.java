import javax.swing.*;
import java.awt.image.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Container;


/*  Author: William Priebe, age 17
	last updated 6/12/19
	Project: Rubik's cube simulator
*/

//Image Paths may need to be updated based on location

//right middle sprites need to be remade
//make a new folder for down middle and fill it with new sprites and build framework
public class CubeStart
{
	private JTextField textField;
	private KeyListener listener;
	public static void main(String args[])
	{
		JFrame window = new JFrame("Rubik's cube");  //name at the top of the window

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		CubeNet gp = new CubeNet(window); //Cube Net constructor code begins
		window.setContentPane(gp);

		window.pack();
 		//these loops build a solved cube(not yet)
		for(int faceNum = 0; faceNum < 6; faceNum++) //every face
		{
			for(int r = 0; r < 3; r++) //every row
			{
				for(int c = 0; c < 3; c++)//every column
				{
					int[]testCoord = {r,c,faceNum}; //coordinates to be passed in so computer knows what image to put there
					CubeFace cubFac = new CubeFace(testCoord, getInitialColor(faceNum), gp.getType(faceNum, r, c)); /*Constructs a cubeface with each coordinate,
					the initial color found there, and what type that coordinate will be*/
					BufferedImage cubFaceIm = cubFac.getImage();
					gp.addImage(cubFaceIm, cubFac);
				}
			}
		}
		MoveMaker mover = new MoveMaker(gp);
		KeyListen keyL = new KeyListen(mover);
		JTextField textField = keyL.getTextField();
		keyL.addKeyListen(textField);
		window.setVisible(true);
	}
	public static String getInitialColor(int faceNum) //Based on the number face it is located on, the method returns the initial color on that face
	{
		String ret = "";
		if(faceNum == 0)
			ret = "Orange";
		else if(faceNum == 1)
			ret = "Blue";
		else if(faceNum == 2)
			ret = "Green";
		else if(faceNum == 3)
			ret = "White";
		else if(faceNum == 4)
			ret = "Red";
		else if(faceNum == 5)
			ret = "Yellow";
		return ret;
	}
}

