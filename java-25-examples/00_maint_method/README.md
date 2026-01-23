Java 25 — Entry Point Examples (00_maint_method)

This example shows two ways to write a Java program’s entry point in modern Java:

- New style: unnamed class with an instance `main` method (`void main()`), in `00_maint_method/src/Main.java`
- Classic style: named class with `public static void main(String[] args)`, in `00_maint_method/src/OldStyleMain.java`

Both are valid in Java 25; use whichever best fits your scenario.

1) New entry point: unnamed class + instance main

File: 00_maint_method/src/Main.java

```
// No class declaration needed
void main() {
    System.out.println("Hello from the new entry point (unnamed class + instance main)!");
    for (int i = 1; i <= 5; i++) {
        System.out.println("i = " + i);
    }
}
```

Notes:
- No explicit class or modifiers are required.
- The entry point can be declared as `void main()` or with arguments: `void main(String... args)`.
- Great for quick scripts, learning, demos, small utilities, or single‑file programs.

Run it (single‑source launch):
- From project root (Windows PowerShell):
```
java 00_maint_method\src\Main.java
```
- Or open in IntelliJ IDEA and run the file directly.

2) Classic entry point: public static void main

File: 00_maint_method/src/OldStyleMain.java

```
public class OldStyleMain {
    public static void main(String[] args) {
        System.out.println("Hello from the classic entry point (public static void main)!");
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
        if (args.length > 0) {
            System.out.println("First arg: " + args[0]);
        }
    }
}
```

Notes:
- Ubiquitous and fully supported.
- Works everywhere: IDEs, build tools, packaging, and deployment.
- Preferred for multi‑file applications, libraries with a launcher, or when distributing artifacts (e.g., JARs, containers).

Run it (single‑source launch or compiled):
- Single‑source launch:
```
java 00_maint_method\src\OldStyleMain.java someArg
```
- Or compile and run:
```
javac -d out 00_maint_method\src\OldStyleMain.java
java -cp out OldStyleMain someArg
```

When to use which?

Use the new `void main()` in an unnamed class when:
- You’re writing a quick script, REPL‑like snippet, coding exercise, or tutorial.
- You want minimal boilerplate and plan to keep things in a single file.

Use the classic `public static void main(String[] args)` when:
- You’re building a production application with multiple files/modules.
- You need a stable, named entry point for packaging, distribution, CI/CD, or tool integration.
- You rely on build tools (Maven/Gradle) or want to publish a runnable JAR.

Passing arguments with the new style

If you need command‑line arguments in the unnamed class style, declare:

```
void main(String... args) {
    System.out.println("Arg count: " + args.length);
}
```

Then run:

```
java 00_maint_method\src\Main.java one two three
```

That’s it—use the style that best matches the scope and lifecycle of your program.
