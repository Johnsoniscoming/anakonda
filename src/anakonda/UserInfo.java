package anakonda;
import java.awt.*;
import javax.swing.*;

public class UserInfo {
	public static void main(String[] args) {
		
	}
	public UserInfo(){
		JFrame jfr = new JFrame("UserInfo");
		Container c = jfr.getContentPane();
		c.setLayout(new GridLayout(150, 150, 5, 5));
		
		jfr.setSize(350, 300);
		jfr.setLocation(500, 300);
		jfr.setVisible(true);
		jfr.setResizable(false);
		jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

}

