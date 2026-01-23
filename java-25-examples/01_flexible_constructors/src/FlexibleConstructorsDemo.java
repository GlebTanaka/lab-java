// Java 25: Flexible constructors — statements before super(...)
// Run from project root (Windows PowerShell):
//   java 01_flexible_constructors\src\FlexibleConstructorsDemo.java

class Person {
    final String name;

    Person(String name) {
        this.name = name;
        System.out.println("Person(super) called with name=" + this.name);
    }
}

class User extends Person {
    final int id;

    User(String name, int id) {
        // Statements BEFORE the super(...) call — normalize and log
        name = (name == null || name.isBlank()) ? "Unknown" : name.trim();
        System.out.println("Before super, normalized name=" + name);

        // Delegate to superclass after doing useful work
        super(name);

        // Continue with subclass initialization
        this.id = id;
        System.out.println("After super, id=" + this.id);
    }
}

public class FlexibleConstructorsDemo {
    public static void main(String[] args) {
        var u = new User("  Alice  ", 42);
        System.out.println("Result: " + u.name + ", id=" + u.id);

        // Try different inputs
        var v = new User("   ", 7);
        System.out.println("Result: " + v.name + ", id=" + v.id);
    }
}
