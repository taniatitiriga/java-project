public class Application {

    // ----test user generation process----
    public static void testInitializeMockUser() {
        OutputDevice outputDevice = new OutputDevice();
        InputDevice inputDevice = new InputDevice();

        User mockUser = inputDevice.createMockUser();

        Account[] mockAccounts = inputDevice.createMockAccounts();
        PasswordManager mockPasswordManager = new PasswordManager(mockUser, mockAccounts);

        outputDevice.writeMessage("User: " + mockPasswordManager.getUser().getUsername());
        outputDevice.printAllAccounts(mockPasswordManager.getAccounts());
    }

    // ----test account appending process----
    public static void testAppendMockAccount() {
        OutputDevice outputDevice = new OutputDevice();
        InputDevice inputDevice = new InputDevice();

        User mockUser = inputDevice.createMockUser();
        Account[] mockAccounts = inputDevice.createMockAccounts();
        PasswordManager mockPasswordManager = new PasswordManager(mockUser, mockAccounts);

        Account newAccount = new Account("instagram.com", "mockuser.insta", "mockinstapass");
        mockPasswordManager.addAccount(newAccount);

        Account[] updatedAccounts = mockPasswordManager.getAccounts();
        outputDevice.writeMessage("Newly added account from PasswordManager:");
        outputDevice.printAccount(updatedAccounts[updatedAccounts.length - 1]);
    }
}
