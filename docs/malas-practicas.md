Malas prácticas
===============

Las malas prácticas escribiendo el código fuente provocan que nuestro código sea difícil de testear.

Incluir en el código fuente factores no determinísticos
-------------------------------------------------------

Imaginemos que estamos programando un microcontrolador para un hogar inteligente.

Uno de los requisitos es que la luz de la entrada se encienda automáticamente si se detecta movimiento durante la tarde o la noche.

Empezamos a programar un método que nos dice la fase del día en la que estamos: “Night”, “Morning”, “Afternoon” or “Evening”:


```java
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
```

Este método lee la hora del sistema y devuelve un string con la fase del día. 

¿Cómo escribiríamos los tests unitarios de este método? ¿Cómo testearíamos manualmente?

Solución 1: Cambiar la hora del sistema en el test

```java
public void GetTimeOfDay_At6AM_ReturnsMorning()
{
    try
    {
        // Arrange: cambiar la hora del sistema a las 6 AM
        ...

        // Act
        string timeOfDay = GetTimeOfDay();

        // Assert
        AssertEqual("Morning", timeOfDay);
    }
    finally
    {
        // Teardown: restablecer la hora del sistema
        ...
    }
}
```

Este test viola varios de los principios de un buen test:

- Es difícil de escribir (por el tema de cambiar la hora del sistema y restablecerla)
- No es fialbe (si por ejemplo, el proceso que ejecuta el test no tiene permisos para cambiar la hora, el test fallará sin tener ningún bug). Por este mismo motivo, además, necesitamos un try-catch
- Lento
- No es un test unitario

Solución 2: Poner un if en el test, que según la hora haga un assert u otro de la respuesta adecuada para esa hora.

- No testea todos los casos
- ¿Lanzar los tests varias veces durante un día antes de dar el código por bueno?


Estos problemas de testeabilidad en realidad están causados por la mala calidad del código de la función GetTimeOfDay(). Este método sufre de varias "malas prácticas": 

- Está acoplado a (depende de) una implementación concreta de la forma de leer la hora del sistema. Es decir, NO cumple el Dependency Inversion principle de los principios SOLID.
- Hace más de una cosa: 1) Leer la hora del sistema, 2) Interpretar la fase del día según la hora leída. Es decir, NO cumple el Single Responsability Principle de los principios SOLID.

**Estas dos malas prácticas son la causa principal de la mayoría de problemas de testeabilidad.**

La mayoría de veces, la solución pasa por aplicar la inversión de dependencias. Vamos a quitar la dependencia interna de la función GetTimeOfDay con la implementación LocalDateTime.

```java
public static String GetTimeOfDay(LocalDateTime time)
{
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
```

Ahora el método requiere ser llamado con un objeto LocalDateTime ya instanciado. Eso es genial, porque en el Test podemos pasarle DateTimes concretos elegidos por nosotros mismos:

```java
public void GetTimeOfDay_For6AM_ReturnsMorning()
{
    // Arrange phase is empty: testing static method, nothing to initialize

    // Act
    string timeOfDay = GetTimeOfDay(new LocalDateTime(2015, 12, 31, 06, 00, 00));

    // Assert
    AssertEqual("Morning", timeOfDay);
}
```

Con esta simple refactorización se resuelven los problemas de testeabilidad debido a las violaciones de los dos principios SOLID antes comentados.

Pero con esta técnica parece que lo que hemos hecho es trasladar el problema del acoplamiento con LocalDateTime a una instancia superior, con lo que ahora será ese método que utilizaba GetDateTime() el que será complejo de testear.

 
Para arreglar la instancia superior, haremos uso de la **Inyección de dpendencias**.

Pongamos que la instancia superior es algo así:


```java
public class SmartHomeController
{
    public LocalDateTime LastMotionTime { get; private set; }

    public void ActuateLights(bool motionDetected)
    {
        LocalDateTime time = LocalDateTime.now(); // ¡¡ MAL !!

        // Update the time of last motion.
        if (motionDetected)
        {
            LastMotionTime = time;
        }
        
        // If motion was detected in the evening or at night, turn the light on.
        string timeOfDay = GetTimeOfDay(time);
        if (motionDetected && (timeOfDay == "Evening" || timeOfDay == "Night"))
        {
            BackyardLightSwitcher.TurnOn();
        }
        // If no motion is detected for one minute, or if it is morning or day, turn the light off.
        else if (time.Subtract(LastMotionTime) > TimeSpan.FromMinutes(1) || (timeOfDay == "Morning" || timeOfDay == "Noon"))
        {
            BackyardLightSwitcher.TurnOff();
        }
    }
}
```

Tenemos el mismo problema de antes con LocalDateTime.now(), solo que ahora el problema está localizado un poquito más "arriba".

Se podría seguir pasando el problema hacía arriba, pero en algún momento no lo podremos hacer más porque no habrá más "arriba". La solución para detener estas dependencias es la **inversión de control**.

La Inversión de control se puede implementar de muchas formas. En este caso lo haremos con la técnica de **Inyección de dependencias utilizando un constructor**.

Vamos a crear una interfaz ILocalDateTimeProvider interface que tenga el siguiente método:

```java
public interface ILocalDateTimeProvider
{
    LocalDateTime GetLocalDateTime();
}
```

Ahora haremos que nuestro SmartHomeController solicite en su constructor una instancia ILocalDateTimeProvider y delegue la responsabilidad de conseguirla.


```java
public class SmartHomeController
{
    private IDateTimeProvider localDateTimeProvider; // Dependency

    public SmartHomeController(ILocalDateTimeProvider localDateTimeProvider)
    {
        // Inject required dependency in the constructor.
        this.localDateTimeProvider = localDateTimeProvider;
    }

    public void ActuateLights(bool motionDetected)
    {
        LocalDateTime time = this.localDateTimeProvider.GetDateTime(); // Delegating the responsibility

        // Resto del código
    }
}
```

Hemos invertido las dependencias. Ahora es el sistema (el FrameWork) el que cuando instancie la clase SmartHomeController nos pasará un objeto que debe cumplir el interfaz ILocalDateTimeProvider. 

Todo FrameWork con Inyección de dependencias tiene algún fichero de configuración o similar para que podamos mapear interfaces con Clases concretas. Al FrameWork habría que indicarle que cuando un controlador pida en su constructor un objeto con la interfaz ILocalDateTimeProvider, le debe proporcionar una instacia de java.time.LocalDateTime. 

ILocalDateTimeProvider => java.time.LocalDateTime.

Al margen de que esta técnica nos ha permitido desacoplar nuestras clases de implementaciones concretas, con las ventajas que eso conlleva, en lo que nos afecta en este curso, es que ahora los tests unitarios son mucho más fáciles de programar, ya que esta técnica nos permite sustituir las implementaciones reales por FAKES controlados por nosotros.

Un Fake de ILocalDateTimeProvider, podría ser así:

```java
public class FakeLocalDateTimeProvider : ILocalDateTimeProvider
{
    private localDateTime; 
    
    public FakeDateTimeProvider(LocalDateTime time) { this.localDateTime = time }
    // public FakeDateTimeProvider() { this.localDateTime = new LocalDateTime(2015, 12, 31, 23, 59, 59) } 
    
    public LocalDateTime getLocalDateTime() { return this.localDateTime; }
}
```

Podemos testear, por ejemplo, si el método ActuateLights se apunta bien el valor en LastMotionTime.

```java
@Test
void ActuateLights_MotionDetected_SavesTimeOfMotion()
{
    // Arrange
    SmartHomeController controller = new SmartHomeController(new FakeDateTimeProvider(new LocalDateTime(2015, 12, 31, 23, 59, 59)));

    // Act
    controller.ActuateLights(true);

    // Assert
    AssertEqual(new DateTime(2015, 12, 31, 23, 59, 59), controller.LastMotionTime);
}
```

Comprobar esto era imposible antes de la refactorización. Hemos eliminado FACTORES NO DETERMINÍSTICOS.


Envenenar el código con efectos colaterales/secundarios
-------------------------------------------------------

Hemos solucionado el problema para testear la hora del día con inyección de dependencias, pero nos queda otro problema con los métodos que encienden y apagan las luces que hacen difícil testear el controlador.

NOTA: Se podría resolver también con inyección de dependencias, pero vamos a utilizar otra técnica.

Centrémonos en la parte del controlador responsable de encender o apagar la luz


```java
// If motion was detected in the evening or at night, turn the light on.
if (motionDetected && (timeOfDay == "Evening" || timeOfDay == "Night"))
{
    BackyardLightSwitcher.TurnOn();
}
// If no motion was detected for one minute, or if it is morning or day, turn the light off.
else if (time.Subtract(LastMotionTime) > TimeSpan.FromMinutes(1) || (timeOfDay == "Morning" || timeOfDay == "Noon"))
{
    BackyardLightSwitcher.TurnOff();
}
```

La responsabilidad de encender y apagar la luz está delegada en la clase concreta BackyardLightSwitcher. ¿Qué hay de malo con este diseño? Otra vez estamos acoplados a una clase concreta. Pero además, el efecto de encender y/o apagar la luz es una interacción con algún otro sistema. Estamos intentando realizar un **test de interacción** en lugar de un **test de estado**. 

Queremos asegurarnos que los métodos TurnOn() o TurnOff() se ejecutan únicamente si se cumplen ciertas condiciones. Para averiguarlo, tal como está escrito el código, no nos quedará más remedio que ejecutar los tests y comprobar de alguna forma en el sistema de luces si están encendidas o apagadas. Y eso puede ser bastante complicado.

En vez de eso la técnica que utilizaremos es comprobar únicamente si hemos llamado a la función TurnOn() o a la función TurnOff(). Esto parece a primera vista que es un testeo insuficiente, pero pensemos en los siguiente:

Si en los tests del controlador testeamos que cuando se cumplen las condiciones de movimiento y hora, las luces se encienden y el test falla debido a algún bug interno de la función TurnOn(). ¿De quién es el bug, de nuestro controlador o del BackyardLightSwitcher? ¿El código de qué clase es el que tenemos que revisar y cambiar para arreglar el bug? 

Efectivamente: el de BackyardLightSwitcher.

Si el bug no está en el SmartHomeController, **entonces no es responsabilidad de los tests de SmartHomeController verificar si la luz se ha encendido**. La responsabilidad de los tests de SmartHomeController es **verificar que el controlador llama al método TurnOn() cuando se cumplen las condiciones**.


Aunque, como ya se ha comentado, este problema se puede resolver con Inyección de dependencias, vamos a utilizar una técnica alternativa: Las **HIGHER-ORDER FUNCTIONS**

Esta técnica solamente funciona en lenguajes orientados a objetos que soporten las llamadas *first-class functions*. Es decir, necesitamos poder pasar funciones como argumentos de entrada de otras funciones.



```java
public void ActuateLights(bool motionDetected, Action turnOn, Action turnOff)
{
    LocalDateTime time = this.localDateTimeProvider.GetDateTime();
    
    // If motion was detected in the evening or at night, turn the light on.
    string timeOfDay = GetTimeOfDay(time);
    if (motionDetected && (timeOfDay == "Evening" || timeOfDay == "Night"))
    {
        turnOn(); // Invocamos la función que nos han pasado por el constructor
    }
    // If no motion is detected for one minute, or if it is morning or day, turn the light off.
    else if (time.Subtract(LastMotionTime) > TimeSpan.FromMinutes(1) || (timeOfDay == "Morning" || timeOfDay == "Noon"))
    {
        turnOff(); // Invocamos la función que nos han pasado por el constructor
    }
}
```

Con esto, realizar un test unitario de interacción es muy fácil con *fakes* de las funciones:


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
    AssertTrue(turnedOnCalled);
}
```

Con inyección de dependencias podría ser algo así:

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


Algunas otras malas prácticas
-----------------------------


Algunas señales de código que hará difícil la programación de los tests.


- Propiedades y campos estáticos, variables globales, etc (que no sean de sólo lectura)

```java
if (!SmartHomeSettings.CostSavingEnabled) { swimmingPoolController.HeatWater(); }
```

Sin embargo, si estas propiedades son de solo lectura, entonces no hay problema

```java
double Circumference(double radius) { return 2 * Math.PI * radius; }
```


- El operador new


Hacer un new de una clase para ejecutar un método de la misma que haga algún trabajo es un anti-patrón. 

Ya hemos visto cómo utilizar invesión de dependencias para quitarnos este problema.


**Si tenemos problemas para escribir un test unitario, el problema está en el código no en los test.**