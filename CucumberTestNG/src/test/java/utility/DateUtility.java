package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

	public static String generateTimeStamp() {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		return formatter.format(new Date());
	}
	
	public static String generateTimeStamp(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(new Date());
	}
}
