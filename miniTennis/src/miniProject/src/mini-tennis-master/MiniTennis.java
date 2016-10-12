import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

//Database Dependencies
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MiniTennis extends JPanel{
	Racquet racquet = new Racquet(this);
	Ball ball = new Ball(this);
	int speed = 1;
	int saveScore = 0;
	static int highScore = 0;
	
	private int getScore() {
		return speed -1 ;

		
	}
	
	//String high=String(high1);
	public MiniTennis() {
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				
			}
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
	
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}
		});
		setFocusable(true);
	}

	public static void main(String[] args) throws InterruptedException {
		


		
		// TODO Auto-generated method stub
		MiniTennis game = new MiniTennis();
		JFrame frame = new JFrame();
		frame.add(game);
		frame.setSize(600, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
				
	}
	
	public void paint(Graphics g) {
		
		/*try{
			String high1=Integer.toString(getScore());
			int high2=Integer.parseInt(high1);
			if(high2>saveScore)
			{
				high1=Integer.toString(high2);
			}
			}
			catch(Exception e)
			{
				System.err.println("001 Exception is"+e);
			}
			*/
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		racquet.paint(g2d);
		//String high1=Integer.toString(getScore());
		//high1 = "High Score : " + high1;
		
		g2d.setColor(Color.RED);
		g2d.setFont(new Font("Verdana", Font.BOLD, 25));
		g2d.drawString("Score : ", 10, 30);
		
		g2d.drawString(String.valueOf(getScore()), 120, 30);
		//g2d.drawString(high1, 360, 30);
		saveScore = getScore();
		highScore = greaterOf(getScore(), highScore);
		g2d.drawString("High Score: " + String.valueOf(highScore), 360, 30);
		
		
			
			
			}
	
	
	public int greaterOf(int a, int b)
	{
		if(a > b)
			return a;
		else
			return b;
	}
		
	private void move() {
		ball.move();
		racquet.move();
	}
	
	public void gameOver() {
		//Saving data to DB
				// load the sqlite-JDBC driver using the current class loader
			      //Class.forName("org.sqlite.JDBC");

			      Connection connection = null;
			      try
			      {
			         // create a database connection
			         connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

			         Statement statement = connection.createStatement();
			         statement.setQueryTimeout(30);  // set timeout to 30 sec.


			         statement.executeUpdate("DROP TABLE IF EXISTS score");
			         statement.executeUpdate("CREATE TABLE score (score INTEGER)");

			         //int ids [] = {1,2,3,4,5};
			         
			         //String names [] = {"Peter","Pallar","William","Paul","James Bond"};
			         /*
			         for(int i=0;i<ids.length;i++){
			              statement.executeUpdate("INSERT INTO person values(' "+ids[i]+"', '"+names[i]+"')");   
			         }
			         */
			         statement.executeUpdate("INSERT INTO score values(' "+ saveScore+" ')");
			         //statement.executeUpdate("UPDATE person SET name='Peter' WHERE id='1'");
			         //statement.executeUpdate("DELETE FROM person WHERE id='1'");

			           ResultSet resultSet = statement.executeQuery("SELECT * from score");
			           while(resultSet.next())
			           {
			              // iterate & read the result set
			              //System.out.println("name = " + resultSet.getString("name"));
			              System.out.println("score = " + resultSet.getInt("score"));
			           }
			          }

			     catch(SQLException e){  System.err.println(e.getMessage()); }       
			      finally {         
			            try {
			                  if(connection != null)
			                     connection.close();
			                  }
			            catch(SQLException e) {  // Use SQLException class instead.          
			               System.err.println(e); 
			             }
			      }
			      
			      //DB connection ends	

		JOptionPane.showMessageDialog(this, "Points : " + saveScore);
		int n = JOptionPane.showConfirmDialog(
			    this,
			    "Would you like to play again ?",
			    "Game Over",
			    JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			ball.yspd = -1;
		//	game.move();
		
		
		} else {
			System.exit(ABORT);
		}
	}
	
}
