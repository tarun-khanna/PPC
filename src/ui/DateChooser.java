package ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class DateChooser {
	UtilDateModel model;
	 JDatePanelImpl datePanel ;
	 JDatePickerImpl datePicker ; 

	public DateChooser()
	{
		 Properties p=new Properties();
		 p.put("text.today", "Today");
		 p.put("text.month", "Month");
		 p.put("text.year", "Year");

		 
		 model = new UtilDateModel();
		 datePanel = new JDatePanelImpl(model,p);
		 datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter()); 
	}

	public JDatePickerImpl getDatePicker() {
		return datePicker;
	}

	public void setDatePicker(JDatePickerImpl datePicker) {
		this.datePicker = datePicker;
	}
	
	
	
}

class DateLabelFormatter extends AbstractFormatter
{

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String datePattern = "dd/MM/yyyy";
   private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

   @Override
   public Object stringToValue(String text) throws ParseException
   {
       return dateFormatter.parseObject(text);
   }

   @Override
   public String valueToString(Object value) throws ParseException
   {
       if (value != null)
       {
           Calendar cal = (Calendar) value;
           return dateFormatter.format(cal.getTime());
       }

       return "";
   }

}
