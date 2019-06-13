import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class KeyListen{

	private JTextField textField;
	private KeyListener listener;
	private MoveMaker mover;

	 public KeyListen(MoveMaker mover) {
	   JFrame frame = new JFrame("Key Listener");
	   Container contentPane = frame.getContentPane();
	   this.mover = mover;

	   listener = new KeyListener()
		{

			 @Override
			 public void keyPressed(KeyEvent event)
			 {
				 makeAMove(event);
			 }
			 @Override
			 public void keyReleased(KeyEvent event)
			 {

			 }
			 @Override
			 public void keyTyped(KeyEvent event)
			 {
				 //makeAMove(event);

			 }
			public void makeAMove(KeyEvent e)
			{
				mover.moveChecker(e.getKeyChar(), true, -1);
			}
	   };
	   textField = new JTextField();
	   contentPane.add(textField, BorderLayout.NORTH);
	   frame.pack();
	   frame.setVisible(true);
	}
	public JTextField getTextField()
	{
		return textField;
	}
	public void addKeyListen(JTextField field)
	{
		field.addKeyListener(listener);
	}
}
