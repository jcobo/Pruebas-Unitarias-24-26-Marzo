import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

class MocksTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

//	@Test
//	void test() {
//		  //  SetUp: create mock
//		  LocalDateTime fakeTime = mock(LocalDateTime.class);
//
//        // SetUp: define return value for method getUniqueId()
//        when(fakeTime.getHour()).thenReturn(20);
//
//        String output = TimeUtils.GetTimeOfDay(fakeTime);
//        assertEquals("Evening", output);
//	}

}