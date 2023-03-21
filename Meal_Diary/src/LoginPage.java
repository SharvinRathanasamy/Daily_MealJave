import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.JOptionPane;


public class LoginPage implements ActionListener {
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	String userID;
	private String password;
	String sql;
	
	JFrame frmLogin = new JFrame();
	private JButton loginButton = new JButton("Login");
	private JButton resetButton = new JButton("Reset");
	private JTextField userIDField = new JTextField();
	private JPasswordField userPasswordField = new JPasswordField();
	private JLabel userIDLabel = new JLabel("userID:");
	private JLabel userPasswordLabel = new JLabel("password:");
	private final JLabel Title = new JLabel("Meal Diary System");
	

 public void Connect()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/meal_dairy","root","04042000");
		}
		catch (ClassNotFoundException ex) 
		{
		  ex.printStackTrace();
		}
		catch (SQLException ex) 
		{
			   ex.printStackTrace();
		}

	}


	

	
LoginPage(){
		
		Connect();
		
		userIDLabel.setBounds(50,100,75,25);
		userPasswordLabel.setBounds(50,150,75,25);
		
		userIDField.setBounds(125,100,200,25);
		userPasswordField.setBounds(125,150,200,25);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(new Color(51, 153, 102));
		
		loginButton.setBounds(125,200,100,25);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		resetButton.setBackground(new Color(51, 51, 102));
		resetButton.setForeground(Color.WHITE);
		
		resetButton.setBounds(225,200,100,25);
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		
		frmLogin.getContentPane().add(userIDLabel);
		frmLogin.getContentPane().add(userPasswordLabel);
		frmLogin.getContentPane().add(userIDField);
		frmLogin.getContentPane().add(userPasswordField);
		frmLogin.getContentPane().add(loginButton);
		frmLogin.getContentPane().add(resetButton);
		frmLogin.setTitle("Login");
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.setSize(479,312);
		frmLogin.getContentPane().setLayout(null);
		Title.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 24));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(10, 10, 445, 69);
		
		frmLogin.getContentPane().add(Title);
		frmLogin.setVisible(true);
	}	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==resetButton) {resetentry();}
		
		if(e.getSource()==loginButton) {login();}
	}
	private void resetentry() {
		userIDField.setText("");
		userPasswordField.setText("");
		
	}
	
	private void login() {
		userID = userIDField.getText();
		password = String.valueOf(userPasswordField.getPassword());
		try{
				Statement atm = con.createStatement();
				sql = "select * from user where User_ID='"+ userID +"'and Password='"+password+"' ";
				ResultSet rs = atm.executeQuery(sql);
	
				if(rs.next()){
					Home wlc= new Home(Integer.valueOf(userID));
					wlc.frmMealdiary.setVisible(true);
					frmLogin.dispose();
	
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid Username or Password");
				}
			}

		catch(Exception e2){
			System.out.println(e2.getMessage());
			}
		}
		
	

}

