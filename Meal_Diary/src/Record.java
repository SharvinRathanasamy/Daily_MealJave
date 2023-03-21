import java.awt.EventQueue;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class Record implements ActionListener, recordinterface {
Record(int uid) {
        setID(uid);
        initialize();
        Connect();
        table_load();
        record.setVisible(true);
        }
	
     JFrame record;
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
	 private JComboBox rFilIBy;
	 private JComboBox rFilter;
	 private JLabel lblFilterBy;
	 private JButton bClear;
	 private JButton Search;
	 private JButton dHome;
	 private String mealid,Userid;
	 private JLabel bfImg;
	 String foodn,foodg,drinkt,mealt,filterg,filterb="0";
	
     @Override
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
	public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select Meal_ID, Meal_Date, Meal_Day, Food_Name, Food_Grp, Food_Img, Drink_Name, Drink_Type, Meal_Type from meal where User_ID=? ORDER BY Meal_Day ASC; ");
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
	
	private JTextField bTFoodT;
	private JTextField bTDrinkT;
	private JPanel panel;
	private JLabel rfname;
	private JLabel rftype;
	private JLabel rmtype;
	private JComboBox rftypeT;
	private JComboBox rmtyprT;
	private JTextField rfnameT;
	private JButton Filter;
	private JButton rrefresh;
	

	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
					Record window = new Record(3);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

    @Override
    public void setID(int id) {
	Userid = Integer.toString(id);
	
}		
	


	 void initialize() {
		 
		 record = new JFrame();
		 record.setBackground(Color.WHITE);
		 record.setTitle("Dinner");
		 record.setBounds(100, 100, 794, 575);
		 record.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 record.getContentPane().setLayout(null);
		

		brfForm = new JPanel();
		brfForm.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Selected Record", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		record.setBounds(100, 100, 1062, 674);
		record.getContentPane().add(brfForm);
		brfForm.setLayout(null);
		brfForm.setBounds(20, 243, 345, 384);
		
		bDate = new JLabel("Date");
		bDate.setFont(new Font("Verdana", Font.PLAIN, 12));
		bDate.setBounds(20, 38, 68, 13);
		brfForm.add(bDate);
		
		bFoodN = new JLabel("Food Name");
		bFoodN.setFont(new Font("Verdana", Font.PLAIN, 12));
		bFoodN.setBounds(20, 85, 95, 13);
		brfForm.add(bFoodN);
		
		bFoodT = new JLabel("Food Group");
		bFoodT.setFont(new Font("Verdana", Font.PLAIN, 12));
		bFoodT.setBounds(20, 108, 95, 13);
		brfForm.add(bFoodT);
		
		TDate = new JTextField();
		TDate.setEditable(false);
		TDate.setToolTipText("");
		TDate.setBounds(126, 36, 107, 19);
		brfForm.add(TDate);
		TDate.setColumns(10);
		
		bTDay = new JTextField();
		bTDay.setEditable(false);
		bTDay.setBounds(126, 59, 107, 19);
		brfForm.add(bTDay);
		bTDay.setColumns(10);
		
		bTFoodN = new JTextField();
		bTFoodN.setEditable(false);
		bTFoodN.setBounds(125, 83, 108, 19);
		brfForm.add(bTFoodN);
		bTFoodN.setColumns(10);
		
		
		
		bDrinkN = new JLabel("Drinks Name");
		bDrinkN.setFont(new Font("Verdana", Font.PLAIN, 12));
		bDrinkN.setBounds(20, 131, 83, 13);
		brfForm.add(bDrinkN);
		
		bTDrinkN = new JTextField();
		bTDrinkN.setEditable(false);
		bTDrinkN.setBounds(126, 129, 107, 19);
		brfForm.add(bTDrinkN);
		bTDrinkN.setColumns(10);
		
		bDrinkT = new JLabel("Drinks Type");
		bDrinkT.setFont(new Font("Verdana", Font.PLAIN, 12));
		bDrinkT.setBounds(20, 154, 83, 13);
		brfForm.add(bDrinkT);

		
		
		bFoodI = new JLabel("Food Img");
		bFoodI.setFont(new Font("Verdana", Font.PLAIN, 12));
		bFoodI.setBounds(20, 228, 83, 13);
		brfForm.add(bFoodI);
		
		
		bfImg = new JLabel("Food Image");
		bfImg.setHorizontalAlignment(SwingConstants.CENTER);
		bfImg.setFont(bfImg.getFont().deriveFont(10f));
		bfImg.setBounds(104, 187, 197, 168);
		brfForm.add(bfImg);
		
		bDay = new JLabel("Day");
		bDay.setFont(new Font("Verdana", Font.PLAIN, 12));
		bDay.setBounds(20, 61, 68, 13);
		brfForm.add(bDay);
		
		bTFoodT = new JTextField();
		bTFoodT.setEditable(false);
		bTFoodT.setColumns(10);
		bTFoodT.setBounds(126, 106, 108, 20);
		brfForm.add(bTFoodT);
		
		bTDrinkT = new JTextField();
		bTDrinkT.setEditable(false);
		bTDrinkT.setColumns(10);
		bTDrinkT.setBounds(126, 152, 108, 19);
		brfForm.add(bTDrinkT);
		
		
		
		brfDisp = new JPanel();
		brfDisp.setBorder(new CompoundBorder());
		brfDisp.setForeground(Color.GRAY);
		brfDisp.setBounds(375, 50, 646, 577);
		record.getContentPane().add(brfDisp);
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
				  bTFoodT.setText(model.getValueAt(selectedIndex, 4).toString());
				  bTDrinkN.setText(model.getValueAt(selectedIndex, 6).toString());
				  bTDrinkT.setText(model.getValueAt(selectedIndex, 7).toString());
				  
				
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
		
		rFilIBy = new JComboBox();
		rFilIBy.setSelectedIndex(-1);
		rFilIBy.setBounds(412, 15, 138, 21);
		brfDisp.add(rFilIBy);
		
		rFilter = new JComboBox();
		rFilter.setModel(new DefaultComboBoxModel(new String[] {"Food_Grp", "Meal_Type", "Drink_Type"}));
		rFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rFilter.getSelectedIndex()== 0) 
				{
					rFilIBy.setModel(new DefaultComboBoxModel(new String[] {"Fruits", "Vegetables", "Grains", "Protein", "Dairy"}));
				}
				
				if(rFilter.getSelectedIndex()== 1) 
				{
					rFilIBy.setModel(new DefaultComboBoxModel(new String[] {"Breakfast", "Lunch", "Dinner"}));
				}
				
				if(rFilter.getSelectedIndex()== 2) 
				{
					rFilIBy.setModel(new DefaultComboBoxModel(new String[] {"Hot", "Warm", "Cold"}));
				}
			}
		});
		rFilter.setToolTipText("");
		rFilter.setSelectedIndex(-1);
		rFilter.setBounds(265, 15, 138, 21);
		brfDisp.add(rFilter);
		
		
		
		lblFilterBy = new JLabel("Filter By");
		lblFilterBy.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblFilterBy.setBounds(203, 19, 62, 13);
		brfDisp.add(lblFilterBy);
		
		Filter = new JButton("Filter");
		Filter.addActionListener(this);
		
		
		Filter.setBackground(new Color(224, 255, 255));
		Filter.setBounds(557, 13, 80, 21);
		brfDisp.add(Filter);
		
		rrefresh = new JButton("Refresh");
		rrefresh.addActionListener(this);
		rrefresh.setBackground(new Color(255, 153, 0));
		rrefresh.setBounds(556, 546, 80, 21);
		brfDisp.add(rrefresh);
		
		brfTitle = new JPanel();
		brfTitle.setBorder(new CompoundBorder());
		brfTitle.setBounds(10, 10, 1028, 42);
		record.getContentPane().add(brfTitle);
		brfTitle.setLayout(null);
		
		brfLTitle = new JLabel("Your Meals");
		brfLTitle.setHorizontalAlignment(SwingConstants.CENTER);
		brfLTitle.setBounds(421, 5, 130, 26);
		brfLTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		brfTitle.add(brfLTitle);
		
		dHome = new JButton("Home");
		dHome.addActionListener(this);
		dHome.setForeground(new Color(240, 248, 255));
		dHome.setBackground(new Color(128, 0, 128));
		dHome.setBounds(909, 7, 90, 30);
		brfTitle.add(dHome);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "General Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(20, 62, 345, 171);
		record.getContentPane().add(panel);
		panel.setLayout(null);
		
		rfname = new JLabel("Food Name");
		rfname.setBounds(26, 34, 92, 16);
		rfname.setFont(new Font("Verdana", Font.PLAIN, 12));
		panel.add(rfname);
		
		rftype = new JLabel("Food Group");
		rftype.setFont(new Font("Verdana", Font.PLAIN, 12));
		rftype.setBounds(26, 58, 92, 16);
		panel.add(rftype);
		
		rmtype = new JLabel("Meal Type");
		rmtype.setFont(new Font("Verdana", Font.PLAIN, 12));
		rmtype.setBounds(26, 84, 75, 16);
		panel.add(rmtype);
		
		rftypeT = new JComboBox();
		rftypeT.setToolTipText("");
		rftypeT.setModel(new DefaultComboBoxModel(new String[] {"Fruits", "Vegetables", "Grains", "Protein", "Dairy"}));
		rftypeT.setSelectedIndex(-1);
		rftypeT.setBounds(128, 57, 138, 21);
		panel.add(rftypeT);
		
		rmtyprT = new JComboBox();
		rmtyprT.setToolTipText("");
		rmtyprT.setModel(new DefaultComboBoxModel(new String[] {"Breakfast", "Lunch", "Dinner"}));
		rmtyprT.setSelectedIndex(-1);
		rmtyprT.setBounds(128, 83, 138, 21);
		panel.add(rmtyprT);
		
		rfnameT = new JTextField();
		rfnameT.setColumns(10);
		rfnameT.setBounds(128, 34, 138, 19);
		panel.add(rfnameT);
		
		bClear = new JButton("Clear");
		bClear.addActionListener(this);
		bClear.setBackground(new Color(224, 255, 255));
		bClear.setBounds(255, 132, 80, 21);
		panel.add(bClear);
		
		Search = new JButton("Search");
		Search.setForeground(new Color(255, 255, 255));
		Search.addActionListener(this);
		Search.setBackground(new Color(0, 153, 153));
		Search.setBounds(165, 132, 80, 21);
		panel.add(Search);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== Search) { GeneralSearch();}
		if(e.getSource()== Filter) { filteritem();}
		if(e.getSource()== bClear) { clearinput();}
		if(e.getSource()== rrefresh) { refreshall();}
		
		if(e.getSource()== dHome) {
			Home home = new Home(Integer.valueOf(Userid));
			home.frmMealdiary.setVisible(true);
			record.dispose();
		}
	}
	
	
	@Override
	public void refreshall() {
		table_load();
		
	}

    @Override
	public void clearinput() {
		TDate.setText("");
		bTDay.setText("");
		bTFoodN.setText("");
		bTDrinkN.setText("");
		bTFoodT.setText("");
		bTDrinkT.setText("");
		bfImg.setIcon(null);
		
		rfnameT.setText("");
		rmtyprT.setSelectedIndex(-1);
		rftypeT.setSelectedIndex(-1);
		
	}

    @Override
	public void filteritem() {
		if((rFilter.getSelectedIndex()!= -1)) {
			filterg = rFilter.getSelectedItem().toString();
			filterb = rFilIBy.getSelectedItem().toString();
		
	    try {
	    	Statement stmt = con.createStatement();
	    	String query = "select Meal_ID, Meal_Date, Meal_Day, Food_Name, Food_Grp, Food_Img, Drink_Name, Drink_Type, Meal_Type from meal where User_ID="+Userid+" AND "+ filterg + "='" + filterb +"' ORDER BY Meal_Day ASC";
	    	rs = stmt.executeQuery(query);
	    	table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.next();
			
	    } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   
	    
		}
		else
		{JOptionPane.showMessageDialog(null, "Pls choose the filter group and filter by");}
		
	}

    @Override
	public void GeneralSearch() {
		
			
		if( rftypeT.getSelectedIndex()== -1 && rmtyprT.getSelectedIndex()== -1){
			foodn ="%"+ rfnameT.getText() +"%"; 
			foodg = "%";
			mealt = "%";
		}
		
		else if (rftypeT.getSelectedIndex()== -1 && rfnameT.getText()== "") {
			foodn = "%"; 
			foodg = "%";
			mealt = rmtyprT.getSelectedItem().toString();
			
		}
		
		else if (rmtyprT.getSelectedIndex()== -1 && rfnameT.getText()== "") {
			foodn = "%"; 
			foodg = rftypeT.getSelectedItem().toString();
			mealt = "%";	
			
		}
		
		else if(rfnameT.getText()== "") {
			foodn ="%"; 
			foodg = rftypeT.getSelectedItem().toString();
			mealt = rmtyprT.getSelectedItem().toString();
			
		}
		else if(rftypeT.getSelectedIndex()== -1) {
			foodn ="%"+ rfnameT.getText() +"%"; 
			foodg = "%";
			mealt = rmtyprT.getSelectedItem().toString();
		}
		else if(rmtyprT.getSelectedIndex()== -1) {
			foodn ="%"+ rfnameT.getText() +"%"; 
			foodg = rftypeT.getSelectedItem().toString();
			mealt = "%";	
		}
		

		else
		{
			foodn ="%"+ rfnameT.getText() +"%"; 
			foodg = rftypeT.getSelectedItem().toString();
			mealt = rmtyprT.getSelectedItem().toString();
		}
		
		
		Statement stmt;
		try {
			stmt = con.createStatement();
			String query = "select Meal_ID, Meal_Date, Meal_Day, Food_Name, Food_Grp, Food_Img, Drink_Name, Drink_Type, Meal_Type from meal where User_ID="+Userid+" AND  Food_Name LIKE '" + foodn+"' AND  Food_Grp LIKE '"+ foodg+ "'AND  Meal_Type LIKE '"+ mealt +"' ORDER BY Meal_Day ASC"; 

	    	rs = stmt.executeQuery(query);
	    	table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.next();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		
	}
}


