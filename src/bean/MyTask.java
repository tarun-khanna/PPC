package bean;
import java.util.Calendar;
import java.util.Date;

import org.jfree.data.gantt.Task;

public class MyTask {

	Task task;
	String taskName;
	
	
	Date startDate;
	Date endDate;
	
	String machineNo;
	String orderId;
	
	Calendar startCalendarDate;
	Calendar endCalendarDate;
		
	public MyTask( String machineNo,String orderId, Date s, Date e)
	{	
		taskName = machineNo + " : " + orderId;
		
//		
		this.machineNo = machineNo;
		this.orderId = orderId;
		startDate =s;
		endDate = e;
		
		startCalendarDate = Calendar.getInstance();
		endCalendarDate = Calendar.getInstance();
		
		startCalendarDate.setTime(startDate);
		endCalendarDate.setTime(endDate);
		this.task = new Task(taskName,s,e);
		
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMachineNo() {
		return machineNo;
	}

	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Calendar getStartCalendarDate() {
		return startCalendarDate;
	}

	public void setStartCalendarDate(Calendar startCalendarDate) {
		this.startCalendarDate = startCalendarDate;
	}

	public Calendar getEndCalendarDate() {
		return endCalendarDate;
	}

	public void setEndCalendarDate(Calendar endCalendarDate) {
		this.endCalendarDate = endCalendarDate;
	}
	
}
