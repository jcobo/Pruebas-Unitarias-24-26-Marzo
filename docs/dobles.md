Dobles
======

A unit test is only concerned with testing a single unit, not whether multiple units work correctly together. Some people see a unit as a class, others as a method. Either way, a single unit often requires other objects in order to function correctly. To get the tests to compile, we could build real objects and give them to the unit under test in the same way we would when using the unit in production. By doing this however, we start leaning away from really just testing a single unit, and couple the test code to more units than it’s concerned with. It can even be impractical to use real objects if they connect to external technologies such as third-party web services, queuing systems or data stores.

This is where test-doubles come in. There are various test-doubles that we can use to stand in for actual objects. The purpose of these is solely to fulfil the requirements of using the unit under test. The following are the test doubles I know of and have used.

Dummies
-------

Dummies are typically values that don’t matter, but are needed in order to call a method that we are testing. The value may be irrelevant as it does not affect the test, but needs to exist in order to call the method. Out parameters are a common example of this:

[Test]
public void GetOccurrences_NewDateTimePattern_HasZeroOccurrences()
{
  var pattern = new DateTimePattern();
  var dummy;

  var count = pattern.GetOccurrences(out dummy);

  Assert.AreEqual(0, count);
}

Stubs
-----

Stubs are hard coded methods that return an expected answer and don’t care about method arguments or the state of any objects, so don’t function normally. They might be anonymous functions passed directly to a method on the unit we are testing, in which case we are testing that the unit behaves correctly when the function returns that hard coded result. A stub could be a method implemented as a requirement of an interface that the unit under test needs to call. A stub that checks the existence of a file for example could always return true if we’re not actually using the file system while testing the unit:

public bool FileExists(string path)
{
  return true;
}

Fakes
-----

Fakes are entirely functional objects that usually implement an interface or at least extend an abstract class that the unit under test needs. Fakes are quick and dirty implementations that wouldn’t be used outside of being a unit test double. There are many good examples of fakes, I’ve recently used a fake to implement an IRedisClient interface (data structure store). Rather than actually running Redis, data gets stored in C# data structures in a very simplistic manner. Units being tested that require an IRedisClient to operate can be given an instance of that fake rather than relying on Redis to be running:

public class FakeRedisClient : IRedisClient
{
  private Dictionary<string, object> _redis = new Dictionary<string,object>();

  // and so on

  public void AddItemToSet(string setId, string item)
  {
    object obj;
    _redis.TryGetValue(setId, out obj);
    HashSet set = (HashSet)obj;
    if (set == null)
    {
      set = new HashSet();
      _redis[setId] = set;
    }
    set.Add(item);
  }

  // and so forth
}

Mocks
-----

Mocks are used to test internal behavior or implementation, rather than state. You use them to verify, for example, certain functions were or were not invoked as a result of calling the method you are testing. Mocking is generally achieved with the help of mocking frameworks such as Moq for .NET. You mock an object from an interface which gives you a concrete object to work with. As part of the setup, you can attach bits of logic or hard coded values in place of methods or properties. This is done so that the mocked object behaves correctly when used by the unit under test. A mocked object can be functionally similar to a fake, and is capable of being used for state testing. I strictly use mocks only for the few occasions I write implementation tests. Fakes are better suited for state testing as they cleanly encapsulate private members such as variables and functions:

[SetUp]
public void SetUp()
{
  _customer = new Mock();
  _customer.Setup(c => c.PaymentID).Returns(1);
}

[Test]
public void CreateSubscription_NewCustomer_ExistingSubscriptionsAreChecked()
{
  var service = CreatePaymentSubscriptionService();

  var subscription = service.CreateSubscription(_customer);

  _customer.Verify(c => c.GetSubscriptions());
}
So where do I 

