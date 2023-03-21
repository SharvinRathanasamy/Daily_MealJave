import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Home extends JPanel implements ActionListener {
	Home(int id) {
		getUserID(id);
		Connect();
		getUsername();
		getday();
		getdate();
		initialize();
	}

	
	JFrame frmMealdiary;
	private JLabel your;
	private JLabel wlc;
	private JLabel dt;
	private JPanel tm;
	private JButton breakfast;
	private JButton lunch;
	private JButton dinner;
	private JButton records;
	private JButton profile;
	
	private String date;
	private String day;
	private String username;
	public String userid;
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	



		
	//change the password and username of the localhost
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
	 
	public void getUserID(int uid) {
		 userid = Integer.toString(uid);
		 
		 
		 
	 }
	 
	 private void getUsername(){
		 Statement stmt;
		try {
			stmt = con.createStatement();
			 String query = "select First_Name from user where User_ID ="+userid;
			    rs = stmt.executeQuery(query);
				rs.next();
				username =rs.getString("First_Name");
				
				
				 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	     
	     
	 }
	 
	 private void getday() {
		 Statement stmt;
			try {
				
				stmt = con.createStatement();
				 String query = "SELECT MAX(Meal_Day) as latest_day FROM meal where User_ID ="+userid;
				    rs = stmt.executeQuery(query);
					rs.next();
					day =rs.getString("latest_day");
					 
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		     
		 
	 }
	 
	 private void getdate() {
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		   LocalDateTime now = LocalDateTime.now();  
		   date=dtf.format(now);  
		   
		 
	 }
	 
	

	
	private void initialize() {
		frmMealdiary = new JFrame();
		frmMealdiary.setTitle("Meal_Diary");
		frmMealdiary.setBounds(100, 100, 629, 423);
		frmMealdiary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMealdiary.getContentPane().setLayout(null);
		
		wlc = new JLabel("Welcome" +username);
		wlc.setFont(new Font("Verdana", Font.BOLD, 23));
		wlc.setHorizontalAlignment(SwingConstants.CENTER);
		wlc.setBounds(10, 42, 595, 69);
		frmMealdiary.getContentPane().add(wlc);
		
		dt = new JLabel(date);
		dt.setHorizontalAlignment(SwingConstants.CENTER);
		dt.setBounds(472, 10, 133, 25);
		frmMealdiary.getContentPane().add(dt);
		
		your = new JLabel("It is your "+ day+ " day");
		your.setHorizontalAlignment(SwingConstants.CENTER);
		your.setFont(new Font("Verdana", Font.PLAIN, 17));
		your.setBounds(44, 121, 194, 25);
		frmMealdiary.getContentPane().add(your);
		
		tm = new JPanel();
		tm.setBorder(new TitledBorder(null, "Record Today's Meal", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tm.setToolTipText("");
		tm.setBounds(326, 130, 241, 235);
		frmMealdiary.getContentPane().add(tm);
		tm.setLayout(null);
		
		breakfast = new JButton("Breakfast");
		breakfast.addActionListener(this);
		breakfast.setBackground(new Color(255, 235, 205));
		breakfast.setBounds(62, 49, 123, 42);
		tm.add(breakfast);
		
		lunch = new JButton("Lunch");
		lunch.addActionListener(this);
		lunch.setBackground(new Color(176, 224, 230));
		lunch.setBounds(62, 101, 123, 42);
		tm.add(lunch);
		
		dinner = new JButton("Dinner");
		dinner.addActionListener(this);
		dinner.setBackground(new Color(176, 196, 222));
		dinner.setBounds(62, 153, 123, 42);
		tm.add(dinner);
		
		records = new JButton("Search Record");
		records.setBackground(new Color(123, 104, 238));
		records.setBounds(67, 278, 153, 50);
		records.addActionListener(this);
		frmMealdiary.getContentPane().add(records);
		
		profile = new JButton("Your Profile");
		profile.addActionListener(this);
		profile.setForeground(new Color(245, 255, 250));
		profile.setBackground(new Color(75, 0, 130));
		profile.setBounds(67, 195, 153, 50);
		frmMealdiary.getContentPane().add(profile);
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== profile) {
			ProfileT Work  = new  ProfileT(Integer.valueOf(userid));
       		 Work.Profile.setVisible(true);
			frmMealdiary.dispose();
		}
		if(e.getSource()== records) {
			Record rec  = new  Record(Integer.valueOf(userid));
			rec.record.setVisible(true);
			frmMealdiary.dispose();
		}
		if(e.getSource()== breakfast) {
			Meal meal1 = new Breakfast(Integer.valueOf(userid));
			frmMealdiary.dispose();
		}
		if(e.getSource()== lunch) {
			Meal meal2 = new Lunch(Integer.valueOf(userid));
			frmMealdiary.dispose();
		}
		if(e.getSource()== dinner) {
			Meal meal3 = new Dinner(Integer.valueOf(userid));
			frmMealdiary.dispose();
		}
		
	}
}
