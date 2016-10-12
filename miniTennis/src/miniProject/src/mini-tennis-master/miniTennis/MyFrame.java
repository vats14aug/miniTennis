package miniTennis;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends Frame implements KeyListener {
TextField t1;
Button b1;
MyFrame()
{
	this.setLayout(new FlowLayout());
	Font f=new Font("Arial",Font.BOLD,25);
	t1=new TextField(20);
	b1=new Button("Press Enter here");
	t1.setFont(f);
	b1.setFont(f);
	this.add(t1);
	this.add(b1);
	b1.addKeyListener(this);
	
}	public void keyTyped(KeyEvent ke){}
	
	

public static void main(String[] args)
{
	MyFrame m=new MyFrame();
	m.setSize(400,400);;
	m.setTitle("My Frame");
	
	m.setLocation(250,250);
	//m.setVisible(true);
	
}
@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	  int a=e.getKeyCode();
	  if(a==e.VK_ENTER);
	  System.out.println(t1.getText());
	
}
@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	int a=e.getKeyCode();
	System.out.println(e.getKeyText(a));

}
}

	
	
	

