public class OldStyleMain {
    /**
     * Classic entry point: public static void main(String[] args)
     * This remains fully supported and is preferred for packaged/distributed apps.
     */
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
