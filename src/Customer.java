public class Customer {
    private String SSN;
    private int age = 0;

    public void setSSN(String ssn) {
        if (ssn.length() < 10) {
            throw new IllegalArgumentException();
        }
        this.SSN = ssn;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age can't be a negative number.");
        }
        this.age = age;
    }
}