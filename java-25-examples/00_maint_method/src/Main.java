// Example: New entry point in Java with an unnamed class and instance main method
// This uses the modern form `void main()` without declaring a class.
// You can run this file directly as a single source file.

void main() {
    System.out.println("Hello from the new entry point (unnamed class + instance main)!");

    for (int i = 1; i <= 5; i++) {
        System.out.println("i = " + i);
    }

    // If you want command-line arguments with the new style, use:
    // void main(String... args) { /*...*/ }
    // See README.md for details.
}
