package bean;

import java.util.ArrayList;
import bean.MyTask;

public class Machine {
	String machineNo;
	String nameOFMachine;
	int quantityOfMachine;
	
	ArrayList<MyTask> allottedTask = new ArrayList<MyTask>();
	
	
	
	public String getNameOFMachine() {
		return nameOFMachine;
	}
	
	public int getQuantityOfMachine() {
		return quantityOfMachine;
	}
	public void setNameOFMachine(String nameOFMachine) {
		this.nameOFMachine = nameOFMachine;
	}
	public void setQuantityOfMachine(int i) {
		this.quantityOfMachine = i;
	}
	public String getMachineNo() {
		return machineNo;
	}
	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}
	
	
	public void assignTask(MyTask mt)
	{
		allottedTask.add(mt);
		
	}

	public ArrayList<MyTask> getAllottedTask() {
		return allottedTask;
	}
	public void setAllottedTask(ArrayList<MyTask> allottedTask) {
		this.allottedTask = allottedTask;
	}
	
	

}
