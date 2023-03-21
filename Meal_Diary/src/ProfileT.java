import java.awt.Color;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.text.DecimalFormat;
import java.awt.Font;

public class ProfileT extends JFrame implements ActionListener, profile{
ProfileT(int uid) {
		setAlwaysOnTop(true);
		setID(uid);
		initialize();
		Connect();
		getdata();
	}
	
JFrame Profile;
 private JLabel JL_fname,JL_lname,JL_pass,JL_id;
 private JTextField JT_fname,JT_lname,JT_pass,JT_id;
 private JButton btn_update,btn_reset,btn_home;
 private JLabel JL_age;
 private JLabel JL_hg;
 private JLabel JL_wg;
 private JLabel JL_bmi;
 private JTextField JT_age;
 private JTextField JT_hg;
 private JTextField JT_wg;
 private JTextField JT_bmi;
 private JLabel JL_dob;
 private JTextField JT_dob;
 
 private String  fname,lname, password, userid,dob;
 private int age;
 private double height,weight,bmi=0;
 
 Connection con;
 PreparedStatement pst;
 ResultSet rs;

    @Override //change the password and username of the localhost 
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
 
     @Override
    public void setID(int id) {
		userid = Integer.toString(id);
		
	}	
    
    @Override
    public void getdata() {
	 try 
 	{
	    pst = con.prepareStatement("select First_Name, Last_Name, Password, Age, Height, Weight, BMI, Date_of_Birth from user where  User_ID=? ");
	    pst.setString(1,userid);
	    rs = pst.executeQuery();
	    rs.next();
	    fname =rs.getString("First_Name");
	    lname =rs.getString("Last_Name");
	    password =rs.getString("Password");
	    age =rs.getInt("Age");
	    height= Double.valueOf(rs.getString("Height"));
	    weight= Double.valueOf(rs.getString("Weight"));
	    bmi = Double.valueOf(rs.getString("BMI")) ;
	    dob =rs.getString("Date_of_Birth");
	    
	    
	    JT_fname.setText(fname);
	    JT_lname.setText(lname);
	    JT_pass.setText(password);
	    JT_age.setText(Integer.toString(age));
	    JT_dob.setText(dob);
	    JT_hg.setText(Double.toString(height));
	    JT_wg.setText(Double.toString(weight));
	    JT_bmi.setText(Double.toString(bmi));
	    JT_id.setText(userid);
	    
	} 
 	catch (SQLException e) 
 	 {
 		e.printStackTrace();
	  } 
 }
	 
    @Override
    public void setBMI(double h, double w) {
	 DecimalFormat fmt = new DecimalFormat ("0.##");
	 double m= h/100;
	 bmi = (w/(m*m));	 
	 JT_bmi.setText(fmt.format(bmi));
 }
 
 
 
    void initialize(){
	 Profile = new JFrame();
	 Profile.setBackground(Color.WHITE);
	 Profile.setTitle("Profile");
	 Profile.setBounds(100, 100, 478, 392);
	 Profile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 Profile.getContentPane().setLayout(null);
	
     setTitle("Profile");
     JL_id = new JLabel("Id:");
     JL_id.setBounds(32, 70, 100, 20);
     JL_fname = new JLabel("Fname:");
     JL_fname.setBounds(32, 100, 100, 20);
     JL_lname = new JLabel("Lname:");
     JL_lname.setBounds(32, 130, 100, 20);
     JL_pass = new JLabel("Password:");
     JL_pass.setBounds(32, 160, 100, 20);
     
     JT_id = new JTextField(20);
     JT_id.setEditable(false);
     JT_id.setBounds(142, 70, 150, 20);
     
     JT_fname = new JTextField(20);
     JT_fname.setBounds(142, 101, 150, 20);
     
     JT_lname = new JTextField(20);
     JT_lname.setBounds(142, 130, 150, 20);
     
     JT_pass = new JTextField(20);
     JT_pass.setBounds(142, 160, 150, 20);
     
     btn_update = new JButton("Update");
     btn_update.setForeground(Color.WHITE);
     btn_update.setBackground(new Color(0, 204, 102));
     btn_update.setBounds(328, 273, 80, 20);
     btn_update.addActionListener(this);
     
     
     btn_reset = new JButton("Reset");
     btn_reset.setForeground(Color.WHITE);
     btn_reset.setBackground(new Color(51, 102, 255));
     btn_reset.addActionListener(this);
     btn_reset.setBounds(328, 305, 80, 20);
     
     Profile.getContentPane().setLayout(null);
     Profile.getContentPane().add(JL_id);
     Profile. getContentPane().add(JL_fname);
     Profile.getContentPane().add(JL_lname);
     Profile.getContentPane().add(JL_pass);
     Profile.getContentPane().add(JT_id);
     Profile.getContentPane().add(JT_fname);
     Profile.getContentPane().add(JT_lname);
     Profile.getContentPane().add(JT_pass);
     Profile.getContentPane().add(btn_update);
     Profile.getContentPane().add(btn_reset);
     
     JL_age = new JLabel("Age:");
     JL_age.setBounds(32, 224, 45, 13);
     Profile.getContentPane().add(JL_age);
     
     JL_hg = new JLabel("Height:");
     JL_hg.setBounds(32, 254, 45, 13);
     Profile.getContentPane().add(JL_hg);
     
     JL_wg = new JLabel("Weight:");
     JL_wg.setBounds(32, 277, 45, 13);
     Profile.getContentPane().add(JL_wg);
     
     JL_bmi = new JLabel("BMI:");
     JL_bmi.setBounds(32, 309, 45, 13);
     Profile.getContentPane().add(JL_bmi);
     
     JT_age = new JTextField(20);
     JT_age.setBounds(142, 221, 150, 20);
     Profile.getContentPane().add(JT_age);
     
     JT_hg = new JTextField(20);
     JT_hg.addKeyListener(new KeyAdapter() {
     	@Override
     	public void keyReleased(KeyEvent e) {
     		setBMI(Double.valueOf(JT_hg.getText()), weight);
     	}
     });
     JT_hg.setBounds(142, 247, 150, 20);
     Profile.getContentPane().add(JT_hg);
     
     JT_wg = new JTextField(20);
     JT_wg.addKeyListener(new KeyAdapter() {
     	@Override
     	public void keyReleased(KeyEvent e) {
     		setBMI(height,Double.valueOf(JT_wg.getText()));
     	}
     });
     JT_wg.setBounds(142, 274, 150, 20);
     Profile.getContentPane().add(JT_wg);
     
     JT_bmi = new JTextField(20);
     JT_bmi.setBounds(142, 306, 150, 20);
     Profile.getContentPane().add(JT_bmi);
     
     JL_dob = new JLabel("Date of Birth:");
     JL_dob.setBounds(32, 190, 100, 13);
     Profile.getContentPane().add(JL_dob);
     
     JT_dob = new JTextField(20);
     JT_dob.setBounds(142, 187, 150, 20);
     Profile.getContentPane().add(JT_dob);
     
     btn_home = new JButton("Home");
     btn_home.addActionListener(this);
     btn_home.setForeground(Color.WHITE);
     btn_home.setBackground(new Color(128, 0, 128));
     btn_home.setBounds(380, 10, 74, 20);
     Profile.getContentPane().add(btn_home);
     
     JLabel lblNewLabel = new JLabel("Your Profile");
     lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 22));
     lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
     lblNewLabel.setBounds(0, 10, 454, 50);
     Profile.getContentPane().add(lblNewLabel);
    
       
  
        
    
 }
 

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==  btn_reset) {reset();}
		if(e.getSource()==  btn_update) {update();}
		if(e.getSource()== btn_home){
            Home wlc= new Home(Integer.valueOf(userid));
			wlc.frmMealdiary.setVisible(true);
			Profile.dispose();
        }
		
	}

    @Override
	public void update() {
		
	  
		try {  pst = con.prepareStatement("update user set First_Name=?, Last_Name= ?,Password= ?, Age= ?, Height= ?, Weight= ?, BMI= ?, Date_of_Birth= ? where User_ID= ?");
			pst.setString(1, JT_fname.getText());
			pst.setString(2, JT_lname.getText());
			pst.setString(3, JT_pass.getText());
			pst.setString(4, JT_age.getText());
			pst.setString(5, JT_hg.getText());
			pst.setString(6, JT_wg.getText());
			pst.setString(7, JT_bmi.getText());
			pst.setString(8, JT_dob.getText());
			pst.setString(9, userid);
			pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Record Updated");
		getdata();
		
	}

    @Override
	public void reset() {
		getdata();
		
	}
}