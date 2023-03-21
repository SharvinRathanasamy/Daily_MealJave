import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.sql.*;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;


public class Dinner extends Meal implements ActionListener,MealInterface {
	Dinner(int uid) {
		setID(uid);
		initialize();
		Connect();
		table_load();
		Dinner.setVisible(true);
		}
	
	JFrame Dinner;
	 private JTextField TDate;
	 private JTextField bTDay;
	 private JTextField bTFoodN;
	 private JTextField bTDrinkN;
	 private JTable table;
	 private JPanel brfForm;
	 private JPanel brfDisp;
	 private JPanel brfTitle;
	 private JLabel brfLTitle;
	 private JScrollPane bData; 
	 private JLabel bDate;
	 private JLabel bFoodN;
	 private JLabel bFoodT;
	 private JLabel bDrinkT;
	 private JLabel bDrinkN;
	 private JLabel bFoodI;
	 private JLabel bDay;
	
	 private JButton dHome;
	 private JButton bUpload;
	 private  JButton bEdit;
	 private  JButton bAdd;
	 private  JButton bDelete;
	 private  JButton bClear;
	 private  JComboBox bFoodTc;
	 private  JComboBox bDrinkTc;
	 private String mealid;
	 private JLabel bfImg;
	 private String Userid;
	 private String meal="Dinner";
	 private  String mealdy, mealdt, dfoodname,dfoodgrp,ddrinkname,ddrinkgrp;

	
	 private String path = null;
	  byte [] userimg=null; 
	
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
	
	public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select Meal_ID, Meal_Date, Meal_Day, Food_Name, Food_Grp, Food_Img, Drink_Name, Drink_Type from meal where Meal_Type = 'Dinner' AND User_ID=? ");
		    pst.setString(1,Userid);
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	


	@Override
	public void setID(int id) {
	Userid = Integer.toString(id);
	
}		
	

	
	void initialize() {
		 Dinner = new JFrame();
		 Dinner.setBackground(Color.WHITE);
		 Dinner.setTitle("Dinner");
		 Dinner.setBounds(100, 100, 794, 575);
		 Dinner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 Dinner.getContentPane().setLayout(null);
		

		brfForm = new JPanel();
		brfForm.setBorder(new CompoundBorder());
		Dinner.setBounds(100, 100, 1062, 674);
		Dinner.getContentPane().add(brfForm);
		brfForm.setLayout(null);
		brfForm.setBounds(20, 62, 345, 462);
		
		bDate = new JLabel("Date");
		bDate.setFont(new Font("Verdana", Font.PLAIN, 12));
		bDate.setBounds(20, 38, 68, 13);
		brfForm.add(bDate);
		
		bFoodN = new JLabel("Food Name");
		bFoodN.setFont(new Font("Verdana", Font.PLAIN, 12));
		bFoodN.setBounds(20, 85, 95, 13);
		brfForm.add(bFoodN);
		
		bFoodT = new JLabel("Food Type");
		bFoodT.setFont(new Font("Verdana", Font.PLAIN, 12));
		bFoodT.setBounds(20, 123, 68, 13);
		brfForm.add(bFoodT);
		
		TDate = new JTextField();
		TDate.setText("yyyy-mm-dd");
		TDate.setToolTipText("");
		TDate.setBounds(126, 36, 107, 19);
		brfForm.add(TDate);
		TDate.setColumns(10);
		
		bTDay = new JTextField();
		bTDay.setBounds(126, 59, 107, 19);
		brfForm.add(bTDay);
		bTDay.setColumns(10);
		
		bTFoodN = new JTextField();
		bTFoodN.setBounds(125, 83, 108, 19);
		brfForm.add(bTFoodN);
		bTFoodN.setColumns(10);
		
		
		
		bDrinkN = new JLabel("Drinks Name");
		bDrinkN.setFont(new Font("Verdana", Font.PLAIN, 12));
		bDrinkN.setBounds(20, 169, 83, 13);
		brfForm.add(bDrinkN);
		
		bTDrinkN = new JTextField();
		bTDrinkN.setBounds(126, 167, 107, 19);
		brfForm.add(bTDrinkN);
		bTDrinkN.setColumns(10);
		
		bDrinkT = new JLabel("Drinks Type");
		bDrinkT.setFont(new Font("Verdana", Font.PLAIN, 12));
		bDrinkT.setBounds(20, 212, 83, 13);
		brfForm.add(bDrinkT);

		
		
		bFoodI = new JLabel("Food Img");
		bFoodI.setFont(new Font("Verdana", Font.PLAIN, 12));
		bFoodI.setBounds(20, 259, 83, 13);
		brfForm.add(bFoodI);
		
		
		bfImg = new JLabel("Food Image");
		bfImg.setHorizontalAlignment(SwingConstants.CENTER);
		bfImg.setFont(bfImg.getFont().deriveFont(10f));
		bfImg.setBounds(126, 259, 175, 153);
		brfForm.add(bfImg);
		
		bUpload = new JButton("upload");
		bUpload.addActionListener(this);
		bUpload.setBounds(169, 422, 75, 21);
		brfForm.add(bUpload);
		
		bDay = new JLabel("Day");
		bDay.setFont(new Font("Verdana", Font.PLAIN, 12));
		bDay.setBounds(20, 61, 68, 13);
		brfForm.add(bDay);
		
		bFoodTc = new JComboBox();
		bFoodTc.setModel(new DefaultComboBoxModel(new String[] {"Fruits", "Vegetables", "Grains", "Protein", "Dairy"}));
		bFoodTc.setSelectedIndex(-1);
		bFoodTc.setToolTipText("");
		bFoodTc.setBounds(126, 122, 156, 21);
		brfForm.add(bFoodTc);
		
		bDrinkTc = new JComboBox();
		bDrinkTc.setModel(new DefaultComboBoxModel(new String[] {"Hot", "Cold", "Warm"}));
		bDrinkTc.setSelectedIndex(-1);
		bDrinkTc.setBounds(126, 210, 156, 21);
		brfForm.add(bDrinkTc);
		
		
		
		brfDisp = new JPanel();
		brfDisp.setBorder(new CompoundBorder());
		brfDisp.setForeground(Color.GRAY);
		brfDisp.setBounds(375, 50, 646, 577);
		Dinner.getContentPane().add(brfDisp);
		brfDisp.setLayout(null);
		
		bData = new JScrollPane();
		bData.setBounds(10, 47, 626, 477);
		brfDisp.add(bData);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				  int selectedIndex = table.getSelectedRow();
				  mealid=model.getValueAt(selectedIndex,0 ).toString();
				  TDate.setText(model.getValueAt(selectedIndex,1 ).toString());
				  bTDay.setText(model.getValueAt(selectedIndex, 2).toString());
				  bTFoodN.setText(model.getValueAt(selectedIndex, 3).toString());
				  bFoodTc.setSelectedItem(model.getValueAt(selectedIndex, 4).toString());
				  bTDrinkN.setText(model.getValueAt(selectedIndex, 6).toString());
				  bDrinkTc.setSelectedItem(model.getValueAt(selectedIndex, 7).toString());
				  try {
					  Statement stmt = con.createStatement();
				     
				      String query = "select Food_Img from meal where Meal_ID ="+mealid;
				      
				    rs = stmt.executeQuery(query);
					rs.next();
					  Blob blob1= rs.getBlob("Food_Img");
					  if(blob1==null) {
						  bfImg.setIcon(null);
					  }
					  byte[] imagebyte = blob1.getBytes(1, (int)blob1.length());
					  
					  ImageIcon imageic = new ImageIcon(new ImageIcon(imagebyte).getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT));
					  bfImg.setIcon(imageic);
				  } catch (SQLException e1) {
					e1.printStackTrace();
				}  
				 
				  
				
			}
		});
		bData.setViewportView(table);
		
		brfTitle = new JPanel();
		brfTitle.setBorder(new CompoundBorder());
		brfTitle.setBounds(10, 10, 1028, 42);
		Dinner.getContentPane().add(brfTitle);
		brfTitle.setLayout(null);
		
		brfLTitle = new JLabel("Dinner");
		brfLTitle.setBounds(476, 5, 76, 26);
		brfLTitle.setVerticalAlignment(SwingConstants.TOP);
		brfLTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		brfTitle.add(brfLTitle);
		
		dHome = new JButton("Home");
		dHome.addActionListener(this);
		dHome.setForeground(new Color(240, 248, 255));
		dHome.setBackground(new Color(128, 0, 128));
		dHome.setBounds(909, 12, 90, 30);
		brfTitle.add(dHome);
		
		 bAdd = new JButton("Add");
		 bAdd.setBackground(new Color(50, 205, 50));
		 bAdd.addActionListener(this);
		bAdd.setBounds(20, 534, 80, 21);
		Dinner.getContentPane().add(bAdd);
		
		bEdit = new JButton("Edit");
		bEdit.setBackground(new Color(65, 105, 225));
		bEdit.addActionListener(this);
		bEdit.setBounds(110, 534, 80, 21);
		Dinner.getContentPane().add(bEdit);
		
		
		bDelete = new JButton("Delete");
		bDelete.setBackground(new Color(255, 0, 0));
		bDelete.addActionListener(this);
		bDelete.setBounds(200, 534, 80, 21);
		Dinner.getContentPane().add(bDelete);
		
		bClear = new JButton("Clear");
		bClear.setBackground(new Color(224, 255, 255));
		bClear.addActionListener(this);
		bClear.setBounds(289, 534, 80, 21);
		Dinner.getContentPane().add(bClear);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== bUpload) {uploadimage();}
		if(e.getSource()== bAdd) {addinput();}
		if(e.getSource()== bEdit) {editinput();}
		if(e.getSource()== bDelete) {deleteinput();}
		if(e.getSource()== bClear) {clearinput();}
		if(e.getSource()== dHome) {
			Home wlc= new Home(Integer.valueOf(Userid));
			wlc.frmMealdiary.setVisible(true);
			Dinner.dispose();
		}
	}
	
	@Override
	public void clearinput(){
		TDate.setText("yyyy-mm-dd");
		bTDay.setText("");
		bTFoodN.setText("");
		bTDrinkN.setText("");
		
		bFoodTc.setSelectedIndex(-1);
		bDrinkTc.setSelectedIndex(-1);
		bfImg.setIcon(null);
		
		
	}
	
	@Override
	public void addinput(){
				
				
				mealdt = TDate.getText();
				mealdy = bTDay.getText();
				dfoodname =bTFoodN.getText();
				ddrinkname =bTDrinkN.getText();
				
				
				dfoodgrp = bFoodTc.getSelectedItem().toString();
				ddrinkgrp= bDrinkTc.getSelectedItem().toString();
				
				if (path==null) {
					JOptionPane.showMessageDialog(null, "Pls insert an image or invalid input");
				}
				try 
				{	File image = new File(path);
					FileInputStream fis = new FileInputStream(image);
				} 
				catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Pls insert an image");
				}
				
				
				

				 try {
						pst = con.prepareStatement("insert into meal(User_ID, Meal_Date, Meal_Day, Food_Name, Food_Grp, Drink_Name, Drink_Type, Meal_Type, Food_Img)values(?,?,?,?,?,?,?,?,?)");
						pst.setString(1, Userid);
						pst.setString(2, mealdt);
						pst.setString(3, mealdy);
						pst.setString(4, dfoodname);
						pst.setString(5, dfoodgrp);
						pst.setString(6, ddrinkname);
						pst.setString(7, ddrinkgrp);
						pst.setString(8, meal);
						pst.setBytes(9, userimg);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
						table_load();
						
						TDate.setText("yyyy-mm-dd");
						bTDay.setText("");
						bTFoodN.setText("");
						bTDrinkN.setText("");
						bFoodTc.setSelectedIndex(-1);
						bDrinkTc.setSelectedIndex(-1);
						bfImg.setIcon(null);
						 path = null;
						 userimg=null; 
						
						TDate.requestFocus();	   
				   }
			 
				catch (SQLException e1) 
			        {e1.printStackTrace();
			        }
		
				}
	
	@Override
	public void editinput(){
				DefaultTableModel model = (DefaultTableModel) table.getModel();
			    int selectedIndex = table.getSelectedRow();
			    try {	
			    	mealid=model.getValueAt(selectedIndex,0 ).toString();
				    
					

					mealdt = TDate.getText();
					mealdy = bTDay.getText();
					dfoodname =bTFoodN.getText();
					ddrinkname =bTDrinkN.getText();
					
					
					dfoodgrp = bFoodTc.getSelectedItem().toString();
					ddrinkgrp= bDrinkTc.getSelectedItem().toString();
				
					if(path!=null)
					{
							try 
							{	File image = new File(path);
								FileInputStream fis = new FileInputStream(image);
								pst = con.prepareStatement("update meal set User_ID=?, Meal_Date= ?,Meal_Day= ?, Food_Name= ?, Food_Grp= ?, Drink_Name= ?, Drink_Type= ?, Meal_Type= ?, Food_Img=? where Meal_ID= ?");
								pst.setString(1, Userid);
								pst.setString(2, mealdt);
								pst.setString(3, mealdy);
								pst.setString(4, dfoodname);
								pst.setString(5, dfoodgrp);
								pst.setString(6, ddrinkname);
								pst.setString(7, ddrinkgrp);
								pst.setString(8, meal);
								pst.setBytes(9, userimg);
								pst.setString(10, mealid);
								pst.executeUpdate();
							} 
							catch (FileNotFoundException e1) {
								JOptionPane.showMessageDialog(null, "Pls insert an image");	
							}
					}
						
				    pst = con.prepareStatement("update meal set User_ID=?, Meal_Date= ?,Meal_Day= ?, Food_Name= ?, Food_Grp= ?, Drink_Name= ?, Drink_Type= ?, Meal_Type= ? where Meal_ID= ?");
					pst.setString(1, Userid);
					pst.setString(2, mealdt);
					pst.setString(3, mealdy);
					pst.setString(4, dfoodname);
					pst.setString(5, dfoodgrp);
					pst.setString(6, ddrinkname);
					pst.setString(7, ddrinkgrp);
					pst.setString(8, meal);
					
					pst.setString(9, mealid);
					pst.executeUpdate();
				    
				    JOptionPane.showMessageDialog(null, "Record Updated");
				    table_load();
					
					TDate.setText("yyyy-mm-dd");
					bTDay.setText("");
					bTFoodN.setText("");
					bTDrinkN.setText("");
					bFoodTc.setSelectedIndex(-1);
					bDrinkTc.setSelectedIndex(-1);
					bfImg.setIcon(null);
					
					TDate.requestFocus();
			    
			    	}
				catch (SQLException e1) 
			    {	e1.printStackTrace();
			    }
			
			}
	
	@Override
	public void deleteinput(){
		 DefaultTableModel model = (DefaultTableModel) table.getModel();
	     int selectedIndex = table.getSelectedRow();
	       
	     try {   
	       mealid=model.getValueAt(selectedIndex,0 ).toString();
	       
	       int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to Delete the record","Warning",JOptionPane.YES_NO_OPTION);
	       if(dialogResult == JOptionPane.YES_OPTION)
	       		{
		    	   pst = con.prepareStatement("delete from meal where Meal_ID = ?");
		    	   pst.setString(1,mealid);
		    	   pst.executeUpdate();
		       
		    	   JOptionPane.showMessageDialog(null, "Record Delete");
		    	   table_load();
		       
		    	   TDate.setText("yyyy-mm-dd");
		    	   bTDay.setText("");
		    	   bTFoodN.setText("");
		    	   bTDrinkN.setText("");
				
		    	   bFoodTc.setSelectedIndex(-1);
		    	   bDrinkTc.setSelectedIndex(-1);
		    	   bfImg.setIcon(null);
				
		    	   TDate.requestFocus();
	       		}
	       }

	     catch (SQLException e1) 
	     	{
	    	 e1.printStackTrace();
	     	}	
		}
	
	@Override
	public void uploadimage(){
		JFileChooser picchoose = new JFileChooser();
		picchoose.showOpenDialog(null);
	
		File pic = picchoose.getSelectedFile();
		path = pic.getAbsolutePath();
		BufferedImage img;
		try {
			
			img = ImageIO.read(picchoose.getSelectedFile());
			ImageIcon imageic = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(250, 250,Image.SCALE_DEFAULT));
			bfImg.setIcon(imageic); 
			
			File image = new File(path);
			FileInputStream fis = new FileInputStream(image);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buff =new byte[8192];
			
			
		
			for (int readNum; (readNum = fis.read(buff)) !=-1;) {
				bos.write(buff,0,readNum);
			}
			
			userimg= bos.toByteArray();
			
			
		} catch (IOException e1) {
			
		}
}

	
}


