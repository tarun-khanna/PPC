package bean;

import java.util.Random;

public class Employee {
	
	private String name ;
	private String gender;
	private String dob;
	private String addr;
	private String email;
	private long phoneNo;
	public String role;
	protected String userId;
	protected int userPass;
	
	public Employee()
	{
		 
		 this.name ="" ;
		 this.gender="";
		 this.dob="";
		 this.addr="";
		 this.email="";
		 this.phoneNo=0;
	     this.role="";
		 	
	     
			
	}
	
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public int getUserPass() {
		return userPass;
	}


	public void setUserPass(int userPass) {
		this.userPass = userPass;
	}


	public String generateID()
	{
	
		int no=Math.abs(email.hashCode()%1000);
		return "C4_"+no;
	}
	
	public int  generatePass()
	{
	
		do{
		userPass=new Random().nextInt(9999);
		}while(userPass<1000);
		
		return userPass;
		
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public long getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(long l) {
		this.phoneNo = l;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Employee(String n,String gen,String dob,String addr,String email,long phone,String role)
	{
		 this();
		 this.name =n ;
		 this.gender=gen;
		 this.dob=dob;
		 this.addr=addr;
		 this.email=email;
		 this.phoneNo=phone;
	     this.role=role;
			
	}
	
	@Override
	public String toString()
	{
		
		return this.getName()+" "+this.getAddr()+" "+this.getDob()+" "+this.getEmail()+" "+this.getGender()+" "+this.getRole()+" "+this.getPhoneNo()+" "+this.getUserId()+" "+this.getUserPass();
		
	}
	
}