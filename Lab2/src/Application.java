public class Application {

    // ----test account generation process----
    public static void test() {
        OutputDevice outputDevice = new OutputDevice();
        InputDevice inputDevice = new InputDevice();

        User mockUser = inputDevice.createMockUser();

        Account[] mockAccounts = inputDevice.createMockAccounts();

        PasswordManager mockPasswordManager = new PasswordManager(mockUser, mockAccounts);

        outputDevice.writeMessage("User: " + mockPasswordManager.getUser().getUsername());

        for (Account acc : mockPasswordManager.getAccounts()) {
            outputDevice.writeMessage("Domain: " + acc.getDomain());
            outputDevice.writeMessage("Username: " + acc.getAccountUsername());
            outputDevice.writeMessage("Password: " + acc.getPassword());
            outputDevice.writeMessage("-------------------");
        }

        Account newAccount = new Account("instagram.com", "mockuser.insta", "mockinstapass");
        mockPasswordManager.addAccount(newAccount);

        Account[] updatedAccounts = mockPasswordManager.getAccounts();
        Account lastAccount = updatedAccounts[updatedAccounts.length - 1];

        outputDevice.writeMessage("Newly added account from PasswordManager:");
        outputDevice.writeMessage("Domain: " + lastAccount.getDomain());
        outputDevice.writeMessage("Username: " + lastAccount.getAccountUsername());
        outputDevice.writeMessage("Password: " + lastAccount.getPassword());
        outputDevice.writeMessage("-------------------");
    }
}
