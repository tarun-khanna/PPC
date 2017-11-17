package bean;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Orders {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private String managerId;
	private String orderId;
	private String nameOfProject;
	private Date startDate;
	private Date endDate;
	
	private String company;
	private int quantity;
	private String unit;
	private String supervisor;
	private int allocated;
	private int gotResources;
	private String manpower;
	private Date dueDate;
	
	
	private Calendar startCalDate = Calendar.getInstance();
	private Calendar endCalDate =Calendar.getInstance();
	private Calendar dueCalDate = Calendar.getInstance();
	
	
	public Orders()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.startDate = formatter.parse("31/12/2050");
			this.endDate =  formatter.parse("01/01/1970");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

	public void setOrders(String mId,String oId,String nop,String ed,String c,int q,String u,String s,String mp) throws ParseException
	{
		this.managerId=mId;
		this.orderId=oId;
		this.nameOfProject=nop;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		if(ed!=null)
		this.dueDate=formatter.parse(ed);
		else this.dueDate=null;
		
		this.company=c;
		this.quantity=q;
		this.unit=u;
		this.supervisor=s;
		this.gotResources=0;
		this.allocated=0;
		this.manpower=mp;
		}
	public String getManagerId()
	{
		return managerId;
	}
	public String getOrderId()
	{
		return orderId;
	}
	public String getNameOfProject()
	{
		return nameOfProject;
	}
	public Date getStartDate()
	{
		return startDate;
	}
	public Date getEndDate()
	{
		return endDate;
	}
	public String getCompany()
	{
		return company;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setNameOfProject(String nameOfProject) {
		this.nameOfProject = nameOfProject;
	}
	public void setStartDate(Date startDate) throws ParseException {
		
			this.startDate=startDate;
		}
	public void setEndDate(Date endDate) throws ParseException {
		
			this.endDate = endDate;
		}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public void setAllocated(int allocated) {
		this.allocated = allocated;
	}
	public void setGotResources(int gotResources) {
		this.gotResources = gotResources;
	}
	public void setManpower(String manpower) {
		this.manpower = manpower;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
		this.dueCalDate.setTime(dueDate);
	}
	public String getUnit()
	{
		return unit;
	}
	public String getSupervisor()
	{
		return supervisor;
	}
	public String getManpower()
	{
		return manpower;
	}
	public Date getDueDate()
	{
		return dueDate;
	}

/////////////

	public Calendar getStartCalDate() {
		return startCalDate;
	}
	public void setStartCalDate(Calendar startCalDate) {
		this.startCalDate = startCalDate;
	}
	public Calendar getEndCalDate() {
		return endCalDate;
	}
	public void setEndCalDate(Calendar endCalDate) {
		this.endCalDate = endCalDate;
	}
	public int getAllocated() {
		return allocated;
	}
	public int getGotResources() {
		return gotResources;
	}
	
	public void setCalDates()
	{
		
		
		startCalDate.setTime(startDate);
		endCalDate.setTime(endDate);
		//dueCalDate.setTime(dueDate);
		
		}
	public Calendar getDueCalDate() {
		return dueCalDate;
	}
	public void setDueCalDate(Calendar dueCalDate) {
		this.dueCalDate = dueCalDate;
	}
	
}
