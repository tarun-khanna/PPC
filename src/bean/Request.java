package bean;

import java.util.Date;

public class Request {
	
	String OrderId;
	String sender;
	String receiver;
	String message;
	Date onDate;
	int addressed;

	
	
	
	
	
	public Request(String orderId, String sender, String receiver, String message, Date onDate, int addressed) {
		
		this.OrderId = orderId;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.onDate = onDate;
		this.addressed = addressed;
	}
	
	
	public String getOrderId() {
		return OrderId;
	}
	public void setOrderId(String orderId) {
		OrderId = orderId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getOnDate() {
		return onDate;
	}
	public void setOnDate(Date onDate) {
		this.onDate = onDate;
	}
	public int getAdressed() {
		return addressed;
	}
	public void setAdressed(int addressed) {
		this.addressed = addressed;
	}
	

}
