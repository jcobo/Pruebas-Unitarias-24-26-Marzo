import java.time.LocalDateTime;

public class TimeUtils {
	public static String GetTimeOfDay()
	{
		LocalDateTime time = LocalDateTime.now();
	    if (time.getHour() >= 0 && time.getHour() < 6)
	    {
	        return "Night";
	    }
	    if (time.getHour() >= 6 && time.getHour() < 12)
	    {
	        return "Morning";
	    }
	    if (time.getHour() >= 12 && time.getHour() < 18)
	    {
	        return "Afternoon";
	    }
	    return "Evening";
	}
}
