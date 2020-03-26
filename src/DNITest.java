import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DNITest {

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

	@Test
	void testCalcularLetraDNI() {
		DNI dni = new DNI();
		
		char letra = dni.calcularLetra(24391541);
		assertEquals('H', letra, "El DNI 24391541 le corresponde la letra H");
		
		char letra2 = dni.calcularLetra(43253425);
		assertEquals('Q', letra2);
		
	}
	
	@ParameterizedTest
	@CsvSource({
		"15454423, X",
		"15454424, B",
		"24391541, H",
		"43253425, Q",
		"11111116, T",
		"11111117, R",
		"11111118, W",
		"11111119, A",
		"11111120, G",
		"11111121, M",
		"11111122, Y",
		"11111123, F",
		"11111124, P",
		"11111125, D",
		"11111126, X",
		"11111127, B",
		"11111128, N",
		"11111129, J",
		"11111130, Z",
		"11111131, S",
		"11111132, Q",
		"11111133, V",
		"11111134, H",
		"11111135, L",
		"11111136, C",
		"11111137, K",
		"11111138, E"
	})
	void testCalcularLetraDNIParametrizado(int numero, char letraEsperada) {
		DNI dni = new DNI();
		
		char letraCalculada = dni.calcularLetra(numero);
		assertEquals(letraEsperada, letraCalculada, () -> "El DNI " + numero + " le corresponde la letra " + letraEsperada);
		
	}

}
