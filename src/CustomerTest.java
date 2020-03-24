import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class CustomerTest {

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

	@Test()
    public void testInvalidSSN() {
        // Check if it raises exception for invalid Social Security Number
		Customer customer = new Customer();
		
		assertThrows(IllegalArgumentException.class, () -> {
	        customer.setSSN("123");  
		});   
    }
	
	@Test()
	public void testInvalidAge() {
		Customer customer = new Customer();
		
        assertThrows(IllegalArgumentException.class, () -> {
        	customer.setAge(-5);
        }, "Age can't be a negative number.");
  
    }

}
