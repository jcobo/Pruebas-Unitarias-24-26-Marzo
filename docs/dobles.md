Dobles
======

Un test unitario frecuentemente necesita interaccionar con otros objetos para realizar su trabajo. Testear con los objetos reales tiene muchos inconvenientes (lentitud, dependencia de otros sistemas como una base de datos, resultados no determinísticos...).

Y en este contexto es donde los **dobles de testeo** entran en escena. El objeto de los dobles es responder como si fueran el objeto real.

Nos podemos encontrar distintos tipos de dobles:

Dummies
-------

Los *Dummies* son normalmente valores que no nos importan, pero se necesitan, por ejemplo, para cumplir las especificaciones de llamada a una función. La respuesta del Dummy es irrelevante, pero necesaria.

En este ejemplo que vimos, la función turnOff es un Dummy.

```java
@Test
public void ActuateLights_MotionDetectedAtNight_TurnsOnTheLight()
{
    // Arrange: create a pair of actions that change boolean variable instead of really turning the light on or off.
    bool turnedOnCalled  = false;
    Action turnOn  = () => turnedOnCalled = true;
    Action turnOff = () => {};
    var controller = new SmartHomeController(new FakeDateTimeProvider(new DateTime(2015, 12, 31, 23, 59, 59)));

    // Act
    controller.ActuateLights(true, turnOn, turnOff);

    // Assert
    Assert.IsTrue(turnedOnCalled);
}
```

Stubs
-----

Los Stubs son métodos hardcodeados que devuelven siempre lo mismo sin importar los datos que reciba de entrada ni las condiciones del entorno...

```java
public bool FileExists(String path)
{
  return true;
}
```


Fakes
-----

Los Fake son implementaciones de un interfaz completo que suelen simular el comportamiento completo de un objeto real pero sin depender de ninguna infraestructura. Todo el trabajo lo hacen simulado en memoria.


```java
public class FakeLocalDateTimeProvider : ILocalDateTimeProvider
{
    private localDateTime; 
    
    public FakeDateTimeProvider(LocalDateTime time) { this.localDateTime = time; }
    
    public LocalDateTime getLocalDateTime() { return this.localDateTime; }
}
```


Mocks
-----

Los Mocks se utilizan para testear el comportamiento o la implementación en vez de testear el estado.

Normalmente se usan para testear si se ha llamado a cierto método, o si se ha llamado a cierto método con ciertos argumentos de entrada en concreto...


```java

class FakeBackyardLightSwitcher {
	public static bool turnedOnCalled  = false;
	public static turnOn() {
		this.turnedOnCalled = true;
	}
	public static turnOn() {}
}

@Test
public void ActuateLights_MotionDetectedAtNight_TurnsOnTheLight()
{
    FakeDateTimeProvider fakeDateTimeProvider = new FakeDateTimeProvider(new DateTime(2015, 12, 31, 23, 59, 59));
    SmartHomeController controller = new SmartHomeController(fakeDateTimeProvider, FakeBackyardLightSwitcher.class);

    // Act
    controller.ActuateLights(true);

    // Assert
    AssertTrue(FakeBackyardLightSwitcher.turnedOnCalled);
}
```

Programarlos es algo más complejo, por lo que se suele recurrir a FrameWorks de testeo.
