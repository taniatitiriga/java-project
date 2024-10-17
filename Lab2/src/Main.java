public class Main {
    public static void main(String[] args) {
        InputDevice inputDevice = new InputDevice();
        OutputDevice outputDevice = new OutputDevice();

        Application app = new Application(inputDevice, outputDevice);

        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                outputDevice.writeMessage(args[i]);

                if (args[i].equals("words")) {
                    app.exampleHistogram();

                } else if (args[i].equals("numbers")) {
                    app.randomArraySort();

                } else if (args[i].equals("play")) {

                    if (i + 1 < args.length) {
                        String nextArg = args[i + 1];

                        try {
                            int playCount = Integer.parseInt(nextArg);
                            app.run(playCount);
                        } catch (NumberFormatException e) {
                            outputDevice.writeMessage("Error: Next argument must be an integer for 'play'.");
                        }
                    } else {
                        outputDevice.writeMessage("Error: No integer provided after 'play'.");
                    }
                }
                else{
                    outputDevice.writeMessage("Error: Invalid command line argument.");
                }
            }
        } else {
            outputDevice.writeMessage("Error: No arguments provided.");
        }

    }
}
