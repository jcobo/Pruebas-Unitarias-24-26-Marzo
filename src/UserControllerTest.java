//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class UserControllerTest {
//
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}
//
//	@BeforeEach
//	void setUp() throws Exception {
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}
//
//	@Test
//	void searchByMail() {
//		
//		UserService fakeService = mock(UserService.class);
//		when(fakeService.searchByMail("carherco@gmail.com")).thenReturn(/*.....*/);
//		
//		Request fakeRequest = mock(Request.class);
//		when(fakeRequest.get("email")).thenReturn("carherco@gmail.com");
//		
//		UserController c = new UserController();
//		c.setUserService(fakeService);
//		
//		Response output = c.searchByMail(fakeRequest);
//		
//      verify(fakeService).searchByMail(ArgumentMatchers.eq("carherco@gmail.com"));
//		assertEquals(output.data.name, "Carlos");
//		assertEquals(output.data.emial, "carherco@gmail.com");
//	}
//
//}
