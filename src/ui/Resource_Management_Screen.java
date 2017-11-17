package ui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXCollapsiblePane;

import bean.Machine;
import bean.RawMaterials;
import dao.DatabaseCon;
import dao.MachineDaoImpl;
import dao.RawMaterialsDaoImpl;


public class Resource_Management_Screen implements ActionListener,ItemListener {
	 JFrame resourceFrame;
	 JPanel resourcePanel;
	JLabel header;
	JPanel resourcePanel1;
	static Color hd_color = Color.decode("#119796");
	static Color bg_color=Color.DARK_GRAY;
	
	// static int countRaw=1,countMachine=1;
	  //Raw_Materials

	 JPanel rawPanel,rawFinalPanel,combinePanel;
	 JButton raw_toggle,add_material;
	 JTextField rawName,rawQuantity;
	int bothAllocated=0;
	 
	 JLabel raw_label,Name,Quantity,rawNo,orderLabel;
	 JTable table;
	 JXCollapsiblePane raw_cp;
	 GridBagConstraints gbc_raw;
	 DefaultTableModel table_model;
	 JScrollPane pane;
	 JSlider range;
	 JButton confirm,addname;
	 JComboBox<String> jbc_raw=new JComboBox<String>();
	 boolean repeat;
	public boolean bool;
	 JDialog jd;
	 JPopupMenu popup;
	 JLabel label_raw;
	 

	 //Machinery
	 JPanel machine_panel,machine_final_panel,machine_combine_panel;
	 JButton machine_toggle,add_machine,confirm_machine;
	 JXCollapsiblePane machine_cp;
	 GridBagConstraints gbc_machine;
	 DefaultTableModel machine_model;
	 JScrollPane machine_pane;
	 JLabel machine_label,machine_Name,machine_Quantity,machineno;
	 JTable machine_table;
	 JTextField machine_name,machine_quantity;
	 JComboBox<String> jbc_machine=new JComboBox<String>();
	 static JComboBox<String> selectOrder;
     ArrayList<String> orderAl;
     static String selectedOrderId;
     
     
	 //Manpower
	 JPanel manpower_panel,man_final_panel,man_combine_panel;
	 JButton manpower_toggle;
	 JTable manTable;
	 JScrollPane manPane;
	 DefaultTableModel manModel;
	 JLabel manpowerLabel;
	 JButton manAdd,manRemove;
	 DateChooser dc,dc1;
	 Date d;
	 JXCollapsiblePane man_cp;
	 JButton man_toggle;
	    
	
	 //-------
	 DatabaseCon dB;
	
	
	public ArrayList<RawMaterials> rawAl; 
	ArrayList<Machine> machineAl; 
	  RawMaterials rm;
	  RawMaterialsDaoImpl rmdi;
	  MachineDaoImpl rmdiM;
	  Machine m;
	  String managerId;
	public Resource_Management_Screen(DatabaseCon dB, String managerId )
	{
		 this.managerId=managerId;
		 this.dB=dB;
		
		try {
			init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void init() throws SQLException
      {
		rawAl=new ArrayList<RawMaterials>();
		
		resourceFrame=new JFrame();
        resourcePanel=new JPanel(); 
       resourcePanel.setLayout(new BoxLayout( resourcePanel, BoxLayout.Y_AXIS));
       
        header=new JLabel("Resource Management");
     // --------------------------------Raw Materials Start--------------------------------------------
        final String NOT_SELECTABLE_OPTION = "   SELECT  ";
        jbc_raw.addItem(NOT_SELECTABLE_OPTION);
        jbc_raw.addItem("Add New Item");
        rm=new RawMaterials();
        rmdi=new RawMaterialsDaoImpl(dB);
        jd=new JDialog();
        rawNo=new JLabel("Raw No");
        popup= new JPopupMenu();
        gbc_raw=new GridBagConstraints();
        confirm=new JButton();
        orderLabel=new JLabel("Select your ORDER");
       // addname=new JButton("Add Item");
        //addname.addActionListener(this);
        table_model=new DefaultTableModel();
        rawPanel=new JPanel(new GridBagLayout());
        //rawFinalPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,50,30));
        rawFinalPanel=new JPanel(new GridBagLayout());
        rawFinalPanel.setBackground(bg_color);
        combinePanel=new JPanel(new GridBagLayout());
        combinePanel.setBackground(Color.GRAY);
        Name=new JLabel("Item Name");
        Quantity=new JLabel("Item Quantity");
    	rawName=new JTextField(10);
    
      	rawQuantity=new JTextField(10);
        raw_cp = new JXCollapsiblePane();
        label_raw=new JLabel("Select your material.");
        label_raw.setFont(new Font("sans-serif",Font.ITALIC,20));
        add_material=new JButton("Add Material");
      	add_material.addActionListener(this);
      	confirm.addActionListener(this);
      	jbc_raw.addItemListener(this);
       // range =new JSlider(1,1000,50);
      	table=new JTable(table_model);
      	table.setEnabled(false);
      	create_columns(table,table_model);
      	table.setPreferredScrollableViewportSize(new Dimension(540, 200));
      	table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
      	table.setDefaultRenderer(Object.class, new MyRenderer());
    	table.setModel(table_model);
      	pane = new JScrollPane(table);
	
    	rawPanel.add(rawNo,setGBConstraints(0,0,1,GridBagConstraints.NONE,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
      	rawPanel.add(Name,setGBConstraints(1,0,1,GridBagConstraints.NONE,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
      	rawPanel.add(Quantity,setGBConstraints(2,0,1,GridBagConstraints.NONE,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
      	
      	rawPanel.add(jbc_raw,setGBConstraints(0,2,1,GridBagConstraints.NONE,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(10,10,20,10)));
      	rawPanel.add(rawName,setGBConstraints(1,2,1,GridBagConstraints.NONE,GridBagConstraints.BASELINE,1.0,new Insets(10,10,20,10)));
      	
      	rawPanel.add(rawQuantity,setGBConstraints(2,2,1,GridBagConstraints.NONE,GridBagConstraints.BASELINE,1.0,new Insets(10,10,20,10)));
      	rawPanel.add(add_material,setGBConstraints(3,2,1,GridBagConstraints.NONE,GridBagConstraints.BASELINE,1.0,new Insets(10,10,20,10)));
      	rawPanel.add(pane,setGBConstraints(0,5,4,GridBagConstraints.NONE,GridBagConstraints.WEST,1.0,new Insets(10,0,0,0)));

      	
      	
      	
      	//rawPanel.setBounds(0, 0, 400, 400);
      	rawPanel.setBackground(Color.pink);
      rawPanel.setBorder(new LineBorder(Color.black, 2));
       // resourceFrame.add("NORTH",raw_toggle);
      	// raw_toggle.setText("Add Raw Materials");  
     	//rawFinalPanel.add(raw_toggle,setGBConstraints(0,0,0,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
     	//combinePanel.add(label_raw,setGBConstraints(0,0,0,GridBagConstraints.NONE,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
      raw_toggle=new JButton(raw_cp.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
      design(rawPanel,raw_toggle,raw_cp);	
      	 rawFinalPanel.add(createSeparator("Add Raw Materials ",raw_toggle),setGBConstraints(0,0,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(2,2,2,2)));
      	designButton("save.png",confirm);        	    	
      	combinePanel.add(rawPanel,setGBConstraints(1,2,0,GridBagConstraints.NONE,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
    	combinePanel.add(confirm,setGBConstraints(1,3,0,GridBagConstraints.NONE,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
      	//resourceFrame.add("SOUTH",rawPanel);
    	raw_cp.add(combinePanel);
    	//jbc_raw.setSelectedIndex(0);
   	 
    	rawFinalPanel.add(raw_cp,setGBConstraints(0,1,0,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
    	
   // ---------------------------------Raw Materials ENd--------------------------------------------
  // ---------------------------------MAchinery START--------------------------------------------
    
    	orderAl=new ArrayList<String>();
    	 jbc_machine.addItem(" SELECT ");
         jbc_machine.addItem("Add New Machine");
         machineAl=new ArrayList<Machine>();
         m=new Machine();
 		rmdiM=new  MachineDaoImpl(dB); 
       machineno=new JLabel("Machine No");
         gbc_machine=new GridBagConstraints();
         confirm_machine=new JButton();
       
         machine_model=new DefaultTableModel();
         machine_panel=new JPanel(new GridBagLayout());
        // machine_final_panel=new JPanel(new FlowLayout(FlowLayout.CENTER,50,30));
         machine_final_panel=new JPanel(new GridBagLayout());
         machine_final_panel.setBackground(bg_color);
         machine_combine_panel=new JPanel(new GridBagLayout());
         machine_combine_panel.setBackground(Color.GRAY);
         machine_Name=new JLabel("Machine Name");
         machine_Quantity=new JLabel("Machine Quantity");
     	 machine_name=new JTextField(10);
     
         machine_quantity=new JTextField(10);
         machine_cp = new JXCollapsiblePane();
         machine_label=new JLabel("Select your Machine.");
         machine_label.setFont(new Font("sans-serif",Font.ITALIC,20));
      
         orderAl=rmdiM.getOrders(managerId);
         selectOrder=new JComboBox<String>();
        // selectOrder.setFont(new Font("sans-serif",Font.ITALIC,20));
         String NOT_SELECTABLE_OPTIONN = "        Orders        ";
         selectOrder.addItem(NOT_SELECTABLE_OPTIONN);
    if(!orderAl.isEmpty()){
         for(String s : orderAl)
         { 
      	   selectOrder.addItem(s);
         System.out.println("s="+s);
         }
    }
        selectOrder.setBackground(hd_color);
        selectOrder.setForeground(Color.WHITE);
         
         add_machine=new JButton("Add Machine");
         
         add_machine.addActionListener(this);
       	confirm_machine.addActionListener(this);
       	jbc_machine.addItemListener(this);
      
    
       selectOrder.addItemListener(this);
          	machine_table=new JTable(machine_model);
       	machine_table.setEnabled(false);
     
       	create_columns(machine_table,machine_model);
       	machine_table.setPreferredScrollableViewportSize(new Dimension(400, 170));
       	machine_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
       	machine_table.setDefaultRenderer(Object.class, new MyRenderer());
     	machine_table.setModel(machine_model);
       	machine_pane = new JScrollPane(machine_table);
       	
       	machine_panel.add(machineno,setGBConstraints(0,0,0,GridBagConstraints.NONE,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
       	machine_panel.add(machine_Name,setGBConstraints(1,0,0,GridBagConstraints.NONE,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
       	machine_panel.add(jbc_machine,setGBConstraints(0,2,0,GridBagConstraints.NONE,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(10,10,20,10)));
       	
       	machine_panel.add(machine_name,setGBConstraints(1,2,0,GridBagConstraints.NONE,GridBagConstraints.BASELINE,1.0,new Insets(10,20,10,10)));
       	machine_panel.add(add_machine,setGBConstraints(2,2,0,GridBagConstraints.NONE,GridBagConstraints.EAST,1.0,new Insets(5,5,5,5)));
       	machine_panel.add(machine_pane,setGBConstraints(0,5,0,GridBagConstraints.NONE,GridBagConstraints.WEST,1.0,new Insets(10,0,0,0)));

          
       	machine_panel.setBackground(Color.pink);
       machine_panel.setBorder(new LineBorder(Color.black, 2));
     
       	 machine_toggle=new JButton(machine_cp.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
       	 design(machine_panel,machine_toggle,machine_cp);
      //	 machine_toggle.setText("Add Machines");  
      	machine_final_panel.add(createSeparator("Add Machines ",machine_toggle),setGBConstraints(0,0,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(2,2,2,2)));
     // 	machine_combine_panel.add(machine_label,setGBConstraints(0,0,0,GridBagConstraints.NONE,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
   designButton("save.png",confirm_machine);
     	machine_combine_panel.add(machine_panel,setGBConstraints(1,2,0,GridBagConstraints.NONE,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
     	machine_combine_panel.add(confirm_machine,setGBConstraints(1,3,0,GridBagConstraints.NONE,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
      	machine_cp.add(machine_combine_panel);
     
     	machine_final_panel.add(machine_cp,setGBConstraints(0,1,0,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(5,5,5,5)));
  
    	
    	//-------------Machine_end--------------
     	
     	
    	//resourcePanel.add(orderLabel);
     	//resourcePanel.add(selectOrder);
     
     	resourcePanel.add(createSeparator("Select Your Order ",selectOrder),setGBConstraints(0,0,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(2,2,2,2)));
     	resourcePanel.add(rawFinalPanel);
     	//resourcePanel.add(rawFinalPanel,createSeparator("Add new Materials ",raw_toggle),setGBConstraints(0,0,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE_LEADING,1.0,new Insets(2,2,2,2)));
     	resourcePanel.add(machine_final_panel);
    	
     	resourcePanel.add(Box.createVerticalGlue());
     	resourcePanel.setBorder(new LineBorder(Color.DARK_GRAY, 3) );
     	resourcePanel.setBackground(bg_color);
     	resourceFrame.add(resourcePanel);
     
     	resourceFrame.setSize(1000,800);
    resourceFrame.setExtendedState( resourceFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
      //	resourceFrame.setVisible(true);	
      	 
      }
	
	public JPanel getResource_panel() {
		return resourcePanel;
	}
	private void create_columns(JTable table,DefaultTableModel dtm)
	{ //get table model
		dtm=(DefaultTableModel) table.getModel();
		
		if(table==manTable){
			 dtm.addColumn("Availability");
		     dtm.addColumn("From");
		     dtm.addColumn("To");
		}
		else{
		if(dtm==table_model)
	     	dtm.addColumn("Material No");
	     	else if(dtm==machine_model)
	         	dtm.addColumn("Machine No");
		dtm.addColumn("Name");
     	dtm.addColumn("Quantity");
     	
		}
	}
	void clearRows(DefaultTableModel dm)
	{
		int rowCount = dm.getRowCount();
		
		for (int i = rowCount - 1; i >= 0; i--) {
		    dm.removeRow(i);
		}
	}
	private void addMaterials(String rawNo,String namee,String quan,DefaultTableModel table_model,JTextField rawName,JTextField rawQuantity) throws SQLException{
		
		
		
		String rowdata[]={rawNo,namee,quan};
	
         	 rm=new RawMaterials();
	rm.setRawNo(rawNo);
         	 rm.setRawName(namee);
			rm.setRawQuantity(Integer.parseInt(quan));
			
			rawAl.add(rm);
		
	
		table_model.addRow(rowdata);
		JOptionPane.showMessageDialog(rawPanel, "Item Added successfully");
		rawName.setText(null);
		rawQuantity.setText(null);
		jbc_raw.setSelectedIndex(0);
	 
	}
	private void addMachine(String machineno,String namee,String quan,DefaultTableModel table_model,JTextField machineName,JTextField machineQuantity) throws SQLException{
		//jbc_raw.
		
		
		String machinedata[]={machineno,namee,quan};
	
	 
	        m=new Machine();
	        m.setMachineNo(machineno);
	        m.setNameOFMachine(namee);
			m.setQuantityOfMachine(Integer.parseInt(quan));
            
			
            machineAl.add(m);
		
		
			
	
		machine_model.addRow(machinedata);
		JOptionPane.showMessageDialog(machine_panel, "Machine Added succesfully");
		machine_name.setText(null);
	    jbc_machine.setSelectedIndex(0);
	 
	}
public void designButton(String text,Component comp)
{
	   
    Icon icon = new ImageIcon(text);
	Image Img= ((ImageIcon)icon).getImage();
icon = new ImageIcon(Img);
	((AbstractButton) comp).setIcon(icon);
	((AbstractButton) comp).setContentAreaFilled(false);
    
}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		 

		
		
		
		if (e.getStateChange() == ItemEvent.SELECTED) {
			
			 System.out.println(ItemEvent.SELECTED);
			if((String) jbc_raw.getSelectedItem()=="SELECT")
		    {
				rawQuantity.setEnabled(false);
			 
			}
			
			
			if((String) jbc_machine.getSelectedItem()=="SELECT")
		    {
			// JOptionPane.showMessageDialog(resourcePanel," Invalid,Please Enter Correct Input");
				machine_quantity.setEnabled(false);
			 
			}	 
			
		    if((String) jbc_raw.getSelectedItem()=="Add New Item")
			 {
				// JOptionPane.showMessageDialog(resourcePanel,"Enter your Item rawName in the Item Name field");
			String str= (String)JOptionPane.showInputDialog(rawName,"Enter the material No.");
		    if(!str.isEmpty()){
				//rawName.setText(str);
				jbc_raw.addItem(str);
           jbc_raw.setSelectedItem(str);
           System.out.println("ITEM ="+selectOrder.getSelectedItem());
			rmdi.currentOrderId((String) selectOrder.getSelectedItem());
				
		
			}
			else jbc_raw.setSelectedIndex(0);
		  //  selectOrder.setSelectedIndex(0);
			
			 }
			  
		    if((String) jbc_machine.getSelectedItem()=="Add New Machine")
			 {
				
			String str1= (String)JOptionPane.showInputDialog(machine_name,"Enter the machine No");
			if(!str1.isEmpty())
			{
			//machine_name.setText(str1);
			jbc_machine.addItem(str1);
            jbc_machine.setSelectedItem(str1);

            System.out.println("MACHINE ="+selectOrder.getSelectedItem());
			rmdiM.currentOrderId((String) selectOrder.getSelectedItem());
			//if(bothAllocated==1)
			rmdiM.updateOrderId((String) selectOrder.getSelectedItem());
				
			}
			else {
				jbc_machine.setSelectedIndex(0);
		//		selectOrder.setSelectedIndex(0);
			    
			}
				
		    
			 }	
		 if((String) selectOrder.getSelectedItem()=="Orders")
		 {
			 
		 }
		 	
	}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==add_material)
		{  
			try {
				addMaterials((String)jbc_raw.getSelectedItem(),rawName.getText(),rawQuantity.getText(),table_model,rawName,rawQuantity);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

		}
		if(e.getSource()==add_machine){
		try {
					addMachine((String)jbc_machine.getSelectedItem(),machine_name.getText(),"1",machine_model,machine_name,machine_quantity);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
		}

		if(e.getSource()==confirm_machine)
		{
			
			if(!machineAl.isEmpty())
			{ rmdiM.setMachineData(machineAl);	
				JOptionPane.showMessageDialog(resourcePanel, "!Successfully Added!");
				clearRows(machine_model);
			}
			else 
				JOptionPane.showMessageDialog(resourcePanel, "Fill the Resources First!");
				
			}
			
				
		
				
		
		if(e.getSource()==confirm)
		{if(!rawAl.isEmpty()){
			rmdi.setRawData(rawAl);	
			JOptionPane.showMessageDialog(resourcePanel, "!Successfully Added!");
		   bothAllocated=1;
			clearRows(table_model);
		}
		else JOptionPane.showMessageDialog(resourcePanel, "Fill the Resources First!");
			}
		
		if(e.getSource()==manAdd)
		{
			new DateChooser();
		}
		
	}
	
	public void design(JPanel panel,JButton button,JXCollapsiblePane cp){
		Icon downKeyIcon = new ImageIcon("downArrow.png");
		Image downKeyImg= ((ImageIcon)downKeyIcon).getImage();
		downKeyIcon = new ImageIcon(downKeyImg);
		
		Icon upKeyIcon = new ImageIcon("upArrow.png");
		Image upKeyImg= ((ImageIcon)upKeyIcon).getImage();
		upKeyIcon = new ImageIcon(upKeyImg);
	    button.setText("");
		button.setContentAreaFilled(false);
	    Action logintoggleAction = cp.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION);
	    logintoggleAction.putValue(JXCollapsiblePane.COLLAPSE_ICON,upKeyIcon);
	    logintoggleAction.putValue(JXCollapsiblePane.EXPAND_ICON,downKeyIcon);
	    panel.setBackground(hd_color);

	}
	static	JPanel createSeparator(String title ,Component toggle)
	{
		JPanel jPanel1 = new javax.swing.JPanel();
		JLabel  loginLabel = new javax.swing.JLabel();
		JSeparator  loginSeparator = new javax.swing.JSeparator();

		 jPanel1.setLayout(new java.awt.GridBagLayout());

	     
	     loginLabel.setText(title);
	     loginLabel.setForeground(Color.WHITE);
	     jPanel1.setBackground(hd_color);     
	     jPanel1.add(loginLabel, setGBConstraints(0,0,GridBagConstraints.LINE_START,new java.awt.Insets(5, 5, 5, 5)));
	        
	  if(toggle==selectOrder)
	  {   loginLabel.setFont(new java.awt.Font("SANS_SERIF", 1, 20));
	  	
	      toggle.setFont(new java.awt.Font("ARIAL",Font.BOLD, 15));
	      jPanel1.setBackground(bg_color);
		  jPanel1.add(toggle,setGBConstraints(1,0,GridBagConstraints.BASELINE,new Insets(5,0,5,0)));
		  
	  }
	  else{
		  loginLabel.setFont(new java.awt.Font("SANS_SERIF", 1, 14));
		  GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
	        gridBagConstraints.gridx = 1;
	        gridBagConstraints.gridy = 0;
	        gridBagConstraints.gridwidth = 1;
	        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
	        gridBagConstraints.weightx = 1.0;
	        jPanel1.add(loginSeparator, gridBagConstraints);
	        jPanel1.add(toggle,setGBConstraints(3,0,GridBagConstraints.LINE_END,new Insets(5,0,5,0)));
	        
	  } 
	        
	        
	        return jPanel1;
	
	}
	private static GridBagConstraints setGBConstraints(int gridx,int gridy,int anchor,Insets in)
	{
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.gridx = gridx;
      gbc.gridy = gridy;
      gbc.anchor = anchor;
      gbc.insets = in;
		
		return gbc;
	}
	

	private GridBagConstraints setGBConstraints(int gridx,int gridy,int gridwidth,int fill,int anchor,double weightx,Insets in)
	{
		GridBagConstraints gbc=setGBConstraints(gridx,gridy,anchor,in);
		
		gbc.gridwidth=gridwidth;
		gbc.fill=fill;
		gbc.weightx=weightx;
		
		return gbc;
	}
  

	
		public static void main(String[] args)throws SQLException {
//			
			//lc=new LaunchClass();
            //lc.dB.closeAll();	
			//Resource_Management_Screen rms=new Resource_Management_Screen(new DatabaseCon());

		}
	

}
