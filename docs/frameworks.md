Frameworks de Mocks en Java
---------------------------

Existen varios frameworks que nos facilitan la creación de mocks y testeo con ellos. Mockito es la más utilizada actualmente.

- Mockito
- EasyMock
- PowerMock

Ejemplos
--------

```java
import static org.mockito.Mockito.*;

//... 

@Test
void test() {
	//  SetUp: create mock
	LocalDateTime fakeTime = mock(LocalDateTime.class);

    // SetUp: define return value for method getUniqueId()
    when(fakeTime.getHour()).thenReturn(23);

    String output = TimeUtils.GetTimeOfDay(fakeTime);
    assertEquals("Evening", output);
}
```

Los métodos no especificados devolverán valores "empty":

- null para objetos
- 0 para números numbers
- false para booleanos boolean
- ...


Otros ejemplos:

Mockear respuestas distintas en llamadas consecutivas al mismo método:

```java
when(fakeTime.getHour()).thenReturn(5).thenReturn(13).thenReturn(20);
```

Mockear respuestas dependiendo de los argumentos de entrada

```java
when(mimock.mimetodo("Mockito")).thenReturn(1);
when(mimock.mimetodo("Eclipse")).thenReturn(2);
```

Mockear respuestas dependiendo del tipo de dato de entrada

```java
when(mimock.mimetodo(anyInt())).thenReturn(-1);
```

Mockear respuestas en base al tipo de objeto de entrada

```java
when(mimock.mimetodo(isA(Todo.class))).thenReturn(0);
```

Mockear el lanzamiento de una excepción

```java
when(mimock.mimetodo(-12)).thenThrow(new IllegalArgumentException(...));
```

Verificar si se ha hecho la llamada a un método concreto con argumentos concretos

```java
verify(mimock).mimetodo(ArgumentMatchers.eq(12));
```

Verificar que un método del mock ha sido llamado 2 veces

```java
verify(mimock, times(2)).mimetodo();
```


Otras variantes

```java
verify(mimock, never()).mimetodo(...); // Verifica que NO se ha llamado a ese método
verify(mimock, atLeastOnce()).mimetodo("called at least once"); // Verifica que se ha llamado a ese método al menos una vez
verify(mimock, atLeast(2)).mimetodo("called at least twice"); // Verifica que se ha llamado a ese método al menos dos veces
verify(mimock, times(5)).mimetodo("called exactly five times"); // Verifica que se ha llamado a ese método exactamente 5 veces
verify(mimock, atMost(3)).mimetodo("called at most 3 times"); // Verifica que se ha llamado a ese método como mucho 3 veces
```

Behavior testing vs. state testing
----------------------------------

Aunque los Frameworks de mockeo ponen el foco en el testeo de interacción y no en el testeo de resultados, en algunos testeos de resultados también necesitamos mockear las dependencias.






