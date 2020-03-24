# Ejercicios

## Calcular letra DNI

Crear una clase DNI con un método calcularLetra. Este DNI recibe como argumento un número (el número de dni) y devuelve un string (la letra correspondiente a dicho número).

public char calcularLetra(int dni)

El algoritmo para calcular la letra es el siguiente:

1) Se divide el número entre 23
2) El resto de la división sirve para identificar la letra según la siguiente tabla:

Resto -> Letra
0 -> T
1 -> R
2 -> W
3 -> A
4 -> G
5 -> M
6 -> Y
7 -> F
8 -> P
9 -> D
10 -> X
11 -> B
12 -> N
13 -> J
14 -> Z
15 -> S
16 -> Q
17 -> V
18 -> H
19 -> L
20 -> C
21 -> K
22 -> E


A) Programar la clase (no copiar de internet). Comprobar ("manualmente") que funciona bien.

- ¿La subiríais a producción?
- ¿Cuantos dnis comprobaríais antes de subirlo a producción?

B) Testear la clase

- ¿Cuántos tests haríais?
- ¿Se tarda más en programar tests o en testear manualmente?
- ¿De qué tipo de tests os fiaríais más: del manual o del automatizado?
- ¿Volvéis a testear de forma manual de forma concienzuda tras un tiempo desarrollando o cambiando funcionalidades? ¿Alguna vez se os ha "roto algo en otro sitio" al cambiar cosas en un sitio que aparentemente nada tiene que ver?

 
## ISBN Checksum calculator

/******************************************************************************
 * 
 *  Determines the check digit of an ISBN-10 number given the first 9 digits.
 *
 *  An ISBN-10 number is valid if it consists of 10 digits and
 *  d_1 + 2*d_2 + 3*d_3 + ... + 10*d_10 is a multiple of 11.
 *  For example, 0-201-31452-5 is valid since
 *  1*5 + 2*2 + 3*5 + 4*4 + 5*1 + 6*3 + 7*1 + 8*0 + 9*2 + 10*0 = 88
 *  and 88 is a multiple of 11.
 *
 *  % java ISBN 013407642
 *  The full ISBN number is 0134076427
 *
 *  % java ISBN 067233784
 *  The full ISBN number is 0672337843
 *
 *  % java ISBN 032157351
 *  The full ISBN number is 032157351X
 *
 ******************************************************************************/

Si la función ya estuviera programada... ¿cuántos tests serían necesarios?

## Shopping Cart

Testear de forma unitaria la clase ShoppingCart

Las especificaciones de esta clase son las siguientes:

- Cuando se crea un carrito, el carrito tiene 0 productos.
- Cuando se vacía el carrito, el carrito tiene 0 productos.
- Cuadno se añade un producto nuevo, el número de items se debe incrementar en 1.
- Cuando se añade un producto nuevo, el *balance* debe ser la suma del coste de este nuevo producto más el balance anterior.
- Cuando se elimina un producto, se debe decrementar el número de productos en 1.
- Cuando se elimina un producto, el balance debe actualizarse correctamente.
- Si se elimina un producto que NO está en el carrito, se debe lanzar una excepción ProductNotFoundException

## StringToInt Converter

Write a class with a static method that converts a string
into an integer value
 Specifications
 The method must accept a string and convert it into an integer
 Well formed strings does not contain characters different from
numbers, trailing spaces and minus
 The represented number must be in the range [-32768, 32767]
 No real number are allowed
 OK: “ -3”, “500”, “-10”, “32767”
 NO: “2 3”, “32768”, “A3”, “2.3


## Fridge

/**
 * Practical Unit Testing with JUnit and Mockito - source code for exercises.
 * Visit http://practicalunittesting.com for more information.
 *
 * @author Tomek Kaczanowski
 */
public class Fridge {

	private Collection<String> food = new HashSet<String>();

	public boolean put(String item) {
		return food.add(item);
	}

	public boolean contains(String item) {
		return food.contains(item);
	}

	public void take(String item) throws NoSuchItemException {
		boolean result = food.remove(item);
		if (!result) {
			throw new NoSuchItemException(item + " not found in the fridge");
		}
	}
}