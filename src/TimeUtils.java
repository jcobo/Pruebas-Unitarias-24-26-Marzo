//import java.time.LocalDateTime;
//
//public class SmartHomeController
//{	
//    private LocalDateTime lastMotionTime;
//    
//    @Autowired()
//    private UserService userService
//    
//    
//    @Injected
//    private LocalDateTimeInterfaces localDateTimeInstance;
//    
//    
//    public Get(Request request) {
//    	
//    	
//    	// Recogida de datos
//    	
//    	// salidaDelServicio = userService.buscar('Carlos')
//    	
//    	
//    	// Elaboración de la salida
//    	
//    	return salidaJson;
// 
//    }
//    
//    
//    
//    @Test
//    void simular7Usuarios() {
//    	SmartHomeController c = new SmartHomeController();
//    	
//    	UserService fakeRequest = mock(Request.class);
//    	when(fakeRequest.get('name')).thenReturn("Carlos");
//    	
//    	UserService fakeservice = mock(UserService);
//    	when(fakeservice.buscar()).thenReturn(//...................);
//    	
//    	c.setUserService(fakeservice);
//    	
//    	XXXXX salida = c.Get(fakeRequest);
//    	
//    	verify(fakeservice).buscar('Carlos');
//    	assertEqual(salida.lenght, 7)
//    }
//    
//    @Test
//    void simular0Usuarios() {
//    	SmartHomeController c = new SmartHomeController();
//    	
//    	UserService fakeRequest = mock(Request.class);
//    	when(fakeRequest.get('name')).thenReturn("Carlos");
//    	
//    	UserService fakeservice = mock(UserService);
//    	when(fakeservice.buscar()).thenReturn(null);
//    	
//    	c.setUserService(fakeservice);
//    	
//    	XXXXX salida = c.Get(fakeRequest);
//    	
//    	
//    	verify(fakeservice).buscar('Carlos');
//    	assertEqual(salida.lenght, 7)
//    }
//    
//
//    public void ActuateLights(bool motionDetected)
//    {
//        LocalDateTime time = this.localDateTimeInstance.now(); 
//
//        // Update the time of last motion.
//        if (motionDetected)
//        {
//            LastMotionTime = time;
//        }
//        
//        // If motion was detected in the evening or at night, turn the light on.
//        String timeOfDay = GetTimeOfDay(time);
//        if (motionDetected && (timeOfDay == "Evening" || timeOfDay == "Night"))
//        {
//            BackyardLightSwitcher.TurnOn();
//        }
//        // If no motion is detected for one minute, or if it is morning or day, turn the light off.
//        else if (time.Subtract(lastMotionTime) > TimeSpan.FromMinutes(1) || (timeOfDay == "Morning" || timeOfDay == "Afternoon"))
//        {
//            BackyardLightSwitcher.TurnOff();
//        }
//    }
//}
//
//public class TimeUtils {
//	
//	
//	public static String GetTimeOfDay(LocalDateTime time)
//	{
//		// LocalDateTime time = LocalDateTime.now();
//	    if (time.getHour() >= 0 && time.getHour() < 5)
//	    {
//	        return "Night";
//	    }
//	    if (time.getHour() >= 6 && time.getHour() < 12)
//	    {
//	        return "Morning";
//	    }
//	    if (time.getHour() >= 12 && time.getHour() < 18)
//	    {
//	        return "Afternoon";
//	    }
//	    return "Evening";
//	}
//}
//

