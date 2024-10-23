public class Main {

    public static void main(String[] args) {
        OutputDevice outputDevice = new OutputDevice();

        if (args.length > 0) {
            for (String arg : args) {
                if (arg.equals("testInitializeMockUser")) {
                    Application.testInitializeMockUser();
                }
                else if (arg.equals("testAppendMockAccount")) {
                    Application.testAppendMockAccount();
                }
                else {
                    outputDevice.writeMessage("Unknown command: " + arg);
                }
            }
        } else {
            outputDevice.writeMessage("No arguments provided. Use 'testUserGeneration' to run the test.");
        }
    }
}
