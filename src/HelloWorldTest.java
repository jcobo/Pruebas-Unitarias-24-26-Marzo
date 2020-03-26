import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


class HelloWorldTest {

	@Test
	void testSumPositive() {
		
		HelloWorld myclass = new HelloWorld();
		
		int output = myclass.sum(7, 15);
		
		assertEquals(22, output, "Suma de 7 y de 15 debe dar 22");
	}
	
	@Test
	void testSumNegativos() {
		
		HelloWorld myclass = new HelloWorld();
		
		int output = myclass.sum(-7, -5);
		
		assertEquals(-12, output);
	}

}
