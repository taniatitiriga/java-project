public class Main {

    public static void main(String[] args) {
        OutputDevice outputDevice = new OutputDevice();

        if (args.length > 0) {
            if (args[0].equals("testUserGeneration")) {
                Application.test();
            } else {
                outputDevice.writeMessage("Unknown command: " + args[0]);
            }
        } else {
            outputDevice.writeMessage("No arguments provided. Use 'testUserGeneration' to run the test.");
        }
    }
}
