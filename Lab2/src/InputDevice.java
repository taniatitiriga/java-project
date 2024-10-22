public class InputDevice {

    // ----for testing account generation----
    public User createMockUser() {
        return new User("mockuser@example.com");
    }

    public Account[] createMockAccounts() {
        Account account1 = new Account("google.com", "mockuser", "mockpassword123");
        Account account2 = new Account("facebook.com", "mockuser.fb", "mockpass456");

        return new Account[] {account1, account2};
    }
}
