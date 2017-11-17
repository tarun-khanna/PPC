package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

import bean.Employee;
import ppc.LaunchClass;

public class AddForm {
	
	Color hd_color = Color.decode("#119796");
	Color bg_color = Color.DARK_GRAY;
	JFrame addWin;
	JPanel addPanel;
	JTextField firstnameField,addr1Field,cityField,stateField,countryField,pinField,emailIdField,phoneField;
	JLabel firstnameLabel,addr1Label,cityLabel,stateLabel,countryLabel,pinLabel,emailIdLabel,phoneLabel,genderLabel,dobLabel,roleLabel;
	JLabel validateName,validateEmail,validatePhone;
	JComboBox<String> gen;
	private String[] sex={"MALE","FEMALE","OTHER"} ;
    private static final String NOT_SELECTABLE_OPTION = " You are... ";
    static DateChooser dc;
    JDatePickerImpl datePicker;
    private JPanel headingPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel personalLabel;
    private javax.swing.JSeparator personalSeparator;
    
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel contactLabel;
    private javax.swing.JSeparator contactSeparator;
    
    JButton performFunc;
	boolean addEnable=false;
    JPanel buttonPanel;
    
	JRadioButton supervisor;
	JRadioButton productionM;
	ButtonGroup role;
	
	String title;
	Employee newEmp;
	
	LaunchClass launchClass;
    
	boolean enableSaveToDB=false;
	
    public boolean isEnableSaveToDB() {
		return enableSaveToDB;
	}

	public void setEnableSaveToDB(boolean enableSaveToDB) {
		this.enableSaveToDB = enableSaveToDB;
	}
	
	public AddForm()
	{
		
	}
	
	public AddForm(LaunchClass lc)
	{
		this();
		launchClass=lc;
	}
	public void displayAddForm()
	{
		addPanel = new JPanel(new GridBagLayout());
		title="ADD NEW USER";
		Icon addIcon = new ImageIcon("add.png");
		Image addImg= ((ImageIcon)addIcon).getImage();
		//addImg = addImg.getScaledInstance(80,50, Image.SCALE_SMOOTH);
		addIcon = new ImageIcon(addImg);
		performFunc=new JButton(addIcon);
		performFunc.setContentAreaFilled(false);
	 	
		init(addPanel,performFunc);
		performFunc.addActionListener(new AdminActionListener(this,"ADD"));
		
//		addWin=new JFrame("Add");
//		addWin.pack();
//		addWin.setSize(500,500);
//		addWin.getContentPane().add(addPanel);
//		addWin.setVisible(true);
	
	}
	
	void formattingLabels(Component c )
	{
		c.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,12));
	 	c.setForeground(Color.WHITE);
	}
	void formattingFields(Component c )
	{
		c.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,12));
	 	c.setForeground(Color.BLACK);
	}
	
	void init(JPanel addPanel,JButton performFunc)
	{
		addPanel.setBackground(bg_color);//new Color(240,249,254,255));
		
		firstnameField=new JTextField(10);
		firstnameLabel=new JLabel("NAME ");
	 	validateName=new JLabel("*Mandatory Field"); 
	 	validateName.setFont(new Font("Serif",Font.PLAIN,12));
	 	validateName.setForeground(Color.GRAY);
	    addr1Field=new JTextField(10);
        addr1Label=new JLabel("ADDRESS ");
        cityField=new JTextField(10);
        cityLabel=new JLabel("CITY ");
        stateField=new JTextField(10);
        stateLabel=new JLabel("STATE ");
        countryField=new JTextField(10);
        countryLabel=new JLabel("COUNTRY ");
        pinField=new JTextField(10);
        pinLabel=new JLabel("PIN ");
        emailIdField=new JTextField(10);
        emailIdLabel=new JLabel("EMAIL ID ");
        validateEmail=new JLabel("*Mandatory Field");
        validateEmail.setFont(new Font("Serif",Font.PLAIN,12));
        validateEmail.setForeground(Color.GRAY);
        phoneField=new JTextField(10);
		phoneLabel=new JLabel("PHONE NO ");		
		validatePhone=new JLabel("*Mandatory Field");
		validatePhone.setFont(new Font("Serif",Font.PLAIN,12));
		validatePhone.setForeground(Color.GRAY);
		genderLabel=new JLabel("GENDER ");
		roleLabel=new JLabel("ROLE ");
		dobLabel=new JLabel("DATE OF BIRTH ");
	    dc=new DateChooser();
	    datePicker=dc.getDatePicker();
		
		
		 gen=new JComboBox<String>(sex);
	     gen.setModel(new DefaultComboBoxModel<String>() {
	    	 	private static final long serialVersionUID = 1L;
	            boolean selectionAllowed = true;

	            @Override
	            public void setSelectedItem(Object anObject) {
	              if (!NOT_SELECTABLE_OPTION.equals(anObject)) {
	                super.setSelectedItem(anObject);
	              } else if (selectionAllowed) {
	                // Allow this just once
	                selectionAllowed = false;
	                super.setSelectedItem(anObject);
	              }	
	            }
	          });

	     gen.addItem(NOT_SELECTABLE_OPTION);
	     for(String s:sex)
         gen.addItem(s);
		
	    
	    productionM=new JRadioButton("PRODUCTION MANAGER");
	    productionM.setOpaque(false);
	    supervisor=new JRadioButton("SUPERVISOR");
	    supervisor.setOpaque(false);
	    role=new ButtonGroup();
	    role.add(productionM);
	    role.add(supervisor);
	    productionM.setSelected(true);
	     
	    JLabel addLabel=new JLabel(title);
        addLabel.setFont(new Font("Calibri (Body)", Font.BOLD,30 ));
		addLabel.setForeground(Color.BLACK);
	    headingPanel=new JPanel();
	    headingPanel.setBackground(Color.GRAY);
	    headingPanel.setForeground(Color.WHITE);
	    headingPanel.setLayout(new GridBagLayout());
	    headingPanel.add(addLabel);
	    
	    //formattingLabels components 
	    formattingLabels(firstnameLabel);
	    formattingLabels(addr1Label);
	    formattingLabels(cityLabel);
	    formattingLabels(stateLabel);
	    formattingLabels(countryLabel);
	    formattingLabels(pinLabel);
	    formattingLabels(emailIdLabel);
	    formattingLabels(phoneLabel);
	    formattingLabels(genderLabel);
	    formattingLabels(dobLabel);
	    formattingLabels(roleLabel);
	    formattingLabels( productionM);
	    formattingLabels(supervisor);
	    
	    //formattingFields components
	    formattingFields(firstnameField);
	    formattingFields(addr1Field);
	    formattingFields(cityField);
	    formattingFields(stateField);
	    formattingFields(countryField);
	    formattingFields(pinField);
	    formattingFields(emailIdField);
	    formattingFields(phoneField);
	    formattingFields(gen);
	    formattingFields(dc.datePicker.getJFormattedTextField());
	    
	    
	    int x=2;

	    addPanel.add(headingPanel, setGBConstraints(0,x-2,4,GridBagConstraints.HORIZONTAL,GridBagConstraints.LINE_START,0.0,new java.awt.Insets(10, 0,15, 0)));

	    
		addPanel.add(roleLabel,setGBConstraints(0,x-1,GridBagConstraints.BASELINE_LEADING,new Insets(5,5,5,5) ));
		addPanel.add(productionM,setGBConstraints(1,x-1,1,GridBagConstraints.NONE,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		addPanel.add(supervisor,setGBConstraints(2,x-1,1,GridBagConstraints.NONE,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		
	    
		addPanel.add(firstnameLabel,setGBConstraints(0,x+1,GridBagConstraints.BASELINE_LEADING,new Insets(5,5,5,5) ));
		addPanel.add(firstnameField,setGBConstraints(1,x+1,3,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		addPanel.add(validateName,setGBConstraints(1,x+2,3,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(0,5,5,5)));
		
		addPanel.add(genderLabel,setGBConstraints(0,x+3,GridBagConstraints.BASELINE_LEADING,new Insets(5,5,5,5) ));
		addPanel.add(gen,setGBConstraints(1,x+3,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		
		addPanel.add(dobLabel,setGBConstraints(2,x+3,GridBagConstraints.BASELINE_LEADING,new Insets(5,5,5,5) ));
		addPanel.add(datePicker,setGBConstraints(3,x+3,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		
		
		addPanel.add(addr1Label,setGBConstraints(0,x+5,GridBagConstraints.BASELINE_LEADING,new Insets(5,5,5,5) ));
		addPanel.add(addr1Field,setGBConstraints(1,x+5,3,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		
		addPanel.add(cityLabel,setGBConstraints(0,x+6,GridBagConstraints.BASELINE_LEADING,new Insets(5,5,5,5) ));
		addPanel.add(cityField,setGBConstraints(1,x+6,3,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		
		addPanel.add(stateLabel,setGBConstraints(0,x+7,GridBagConstraints.BASELINE_LEADING,new Insets(5,5,5,5) ));
		addPanel.add(stateField,setGBConstraints(1,x+7,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		
		addPanel.add(pinLabel,setGBConstraints(2,x+7,GridBagConstraints.BASELINE_LEADING,new Insets(5,5,5,5) ));
		addPanel.add(pinField,setGBConstraints(3,x+7,1,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		
		
		addPanel.add(countryLabel,setGBConstraints(0,x+8,GridBagConstraints.BASELINE_LEADING,new Insets(5,5,5,5) ));
		addPanel.add(countryField,setGBConstraints(1,x+8,3,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		
		addPanel.add(emailIdLabel,setGBConstraints(0,x+9,GridBagConstraints.BASELINE_LEADING,new Insets(5,5,5,5) ));
		addPanel.add(emailIdField,setGBConstraints(1,x+9,3,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		addPanel.add(validateEmail,setGBConstraints(1,x+10,3,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(0,5,5,5)));
		
		addPanel.add(phoneLabel,setGBConstraints(0,x+11,GridBagConstraints.BASELINE_LEADING,new Insets(5,5,5,5) ));
		addPanel.add(phoneField,setGBConstraints(1,x+11,3,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(5,5,5,5)));
		addPanel.add(validatePhone,setGBConstraints(1,x+12,3,GridBagConstraints.BOTH,GridBagConstraints.BASELINE,1.0,new Insets(0,5,5,5)));
	

		
		 jPanel1 = new javax.swing.JPanel();
	     personalLabel = new javax.swing.JLabel();
	     personalSeparator = new javax.swing.JSeparator();

		 jPanel1.setLayout(new java.awt.GridBagLayout());

	     personalLabel.setFont(new java.awt.Font("SANS_SERIF", 1, 14));
	     personalLabel.setText("PERSONAL DETIALS ");
	     personalLabel.setForeground(Color.WHITE);
	     jPanel1.setBackground(hd_color);     
	       
	  
	        jPanel1.add(personalLabel, setGBConstraints(0,0,GridBagConstraints.LINE_START,new java.awt.Insets(5, 5, 5, 5)));
	        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
	        gridBagConstraints.gridx = 1;
	        gridBagConstraints.gridy = 0;
	        gridBagConstraints.gridwidth = 3;
	        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
	        gridBagConstraints.weightx = 1.0;
	        jPanel1.add(personalSeparator, gridBagConstraints);
	        addPanel.add(jPanel1, setGBConstraints(0,x+0,4,GridBagConstraints.HORIZONTAL,GridBagConstraints.BASELINE,0.0,new java.awt.Insets(0, 0, 0, 0)));

	      jPanel2 = new javax.swing.JPanel();
	      contactLabel = new javax.swing.JLabel();
	      contactSeparator = new javax.swing.JSeparator();
	      jPanel2.setLayout(new java.awt.GridBagLayout());
	      contactLabel.setFont(new java.awt.Font("SANS_SERIF", 1, 14));
	      contactLabel.setText("CONTACT DETIALS ");
	      contactLabel.setForeground(Color.WHITE);
	      jPanel2.setBackground(hd_color);     
		  
		        jPanel2.add(contactLabel, setGBConstraints(0,0,GridBagConstraints.LINE_START,new java.awt.Insets(5, 5, 5, 5)));
		        GridBagConstraints gridBagConstraints2 = new java.awt.GridBagConstraints();
		        gridBagConstraints2.gridx = 1;
		        gridBagConstraints2.gridy = 0;
		        gridBagConstraints2.gridwidth = 3;
		        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
		        gridBagConstraints2.weightx = 1.0;
		        jPanel2.add(contactSeparator, gridBagConstraints2);
		        addPanel.add(jPanel2, setGBConstraints(0,x+4,4,GridBagConstraints.HORIZONTAL,GridBagConstraints.BASELINE,0.0,new java.awt.Insets(0, 0, 0, 0)));
		     
		buttonPanel=new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBackground(Color.GRAY);
		performFunction(performFunc);
		buttonPanel.add(performFunc,setGBConstraints(0,0,GridBagConstraints.BASELINE,new Insets(2,5,2,5)));
		addPanel.add(buttonPanel,setGBConstraints(0,x+13,4,GridBagConstraints.HORIZONTAL,GridBagConstraints.LINE_END,1.0,new Insets(5,0,5,0)));
		
		//Validators
			firstnameField.addKeyListener(new KeyAdapter(){
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(firstnameLabel.getText().length()>25 || !isAlpha(firstnameField.getText()))
				{
					validateName.setForeground(Color.RED);
					validateName.setText("INVALID !!!");// Character length should be less than 25");
					addEnable=false;
				}
				else if(firstnameField.getText().length()==0 )
				{	validateName.setForeground(Color.GRAY);
					validateName.setText("*Mandatory field");
					addEnable=false;
				}
				else if(isAlpha( firstnameField.getText()))
				{
					validateName.setForeground(hd_color);
					validateName.setText("VALID");
				}
				if(validateName.getText().equals("VALID") && validateEmail.getText().equals("VALID") && validatePhone.getText().equals("VALID"))
					addEnable=true;
				performFunc.setEnabled(addEnable);
			}
			public boolean isAlpha(String name) {
			    char[] chars = name.toCharArray();

			    for (char c : chars) {
			        if(!(c>=65 && c<=90) && !(c>=97 && c<=122) && !Character.isWhitespace(c))
  	 				{
  	 					return false;
  	 				}
			    }

			    return true;
			}

				
  	 	});
	 	
			emailIdField.addKeyListener(new KeyAdapter(){
	  	 		
	  	 		@Override
	  	 		public void keyReleased(KeyEvent kv)
	  	 		{
	  	 		    if(emailIdField.getText().equals(""))
	  	 		    {
	  	 		    	validateEmail.setForeground(Color.GRAY);
	  	 				validateEmail.setText("*Mandatory field");
	  	 		    	addEnable=false;
	  	 		    }
	  	 			else if(emailIdField.getText().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"))
	  	 			{
	  	 				
	  	 				validateEmail.setForeground(hd_color);
	  	 				validateEmail.setText("VALID");
	  	 				if(validateName.getText().equals("VALID") && validateEmail.getText().equals("VALID") && validatePhone.getText().equals("VALID"))
	  						addEnable=true;
	  	 			}
	  	 			else
	  	 			{
	  	 				validateEmail.setForeground(Color.RED);
	  	 				validateEmail.setText("INVALID !!!");
	  	 				addEnable=false;
	  	 			}
	  	 		    performFunc.setEnabled(addEnable);
	  	 		}
	  	 	});
		
			phoneField.addKeyListener(new KeyAdapter(){
		 		
		 		@Override
		 		public void keyReleased(KeyEvent kv)
		 		{
		 		    if(phoneField.getText().equals(""))
		 		    {
		 		    	validatePhone.setForeground(Color.GRAY);
		 		    	validatePhone.setText("*Mandatory field");
		 		    	addEnable=false;
		 		    }
		 			else if(phoneField.getText().matches("^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$"))
		 			{
		 				
		 				validatePhone.setForeground(hd_color);
		 				validatePhone.setText("VALID");
		 				if(validateName.getText().equals("VALID") && validateEmail.getText().equals("VALID") && validatePhone.getText().equals("VALID"))
							addEnable=true;
		 			}
		 			else
		 			{
		 				validatePhone.setForeground(Color.RED);
		 				validatePhone.setText("INVALID !!!");
		 				addEnable=false;
		 			}
		 		   performFunc.setEnabled(addEnable);
		 		}
		 	});
		 	      
		
	}
	
	void performFunction(JButton performFunc)
	{
		performFunc.setEnabled(addEnable);
		
	}
	
	static GridBagConstraints setGBConstraints(int gridx,int gridy,int anchor,Insets in)
	{
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.insets = in;
		
		return gbc;
	}
	
	static GridBagConstraints setGBConstraints(int gridx,int gridy,int gridwidth,int fill,int anchor,double weightx,Insets in)
	{
		GridBagConstraints gbc=setGBConstraints(gridx,gridy,anchor,in);
		
		gbc.gridwidth=gridwidth;
		gbc.fill=fill;
		gbc.weightx=weightx;
		
		return gbc;
	}
	public static void main(String[] args) {
		
	//new AddForm().displayAddForm();
	}

	public JPanel getAddPanel() {
		return addPanel;
	}

	void  clear()
	{
		firstnameField.setText("");
		validateName.setText("*Mandatory Field"); 
	 	validateName.setForeground(Color.GRAY);
	    addr1Field.setText("");
        cityField.setText("");
        stateField.setText("");
        countryField.setText("");
        pinField.setText("");
        emailIdField.setText("");
        validateEmail.setText("*Mandatory Field");
        validateEmail.setForeground(Color.GRAY);
        phoneField.setText("");
		validatePhone.setText("*Mandatory Field");
		validatePhone.setForeground(Color.GRAY);
		addEnable=false;
	    performFunc.setEnabled(addEnable);
	    dc.datePicker.getJFormattedTextField().setText("");
	    productionM.setSelected(true);
	    gen.getModel().setSelectedItem(NOT_SELECTABLE_OPTION);
	    
		
	}
	
}
