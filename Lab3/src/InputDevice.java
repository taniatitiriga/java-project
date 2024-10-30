public class InputDevice {
    public static int getIntInput(String prompt) {
        OutputDevice.print(prompt);
        return (int) (Math.random() * 100); // Mock random value for testing
    }

    public static String getStringInput(String prompt) {
        OutputDevice.print(prompt);
        String[] options = {"Patient", "Visitor", "Nurse", "Doctor"};
        return options[(int) (Math.random() * options.length)]; // Random type for testing
    }
}
