public class OutputDevice {
    public void writeMessage(String mess) {
        System.out.println(mess);
    }

    // Method to pretty print a single account
    public void printAccount(Account account) {
        writeMessage("Domain: " + account.getDomain());
        writeMessage("Username: " + account.getAccountUsername());
        writeMessage("Password: " + account.getPassword());
        writeMessage("-------------------");
    }

    // Method to pretty print all accounts in an array
    public void printAllAccounts(Account[] accounts) {
        for (Account account : accounts) {
            printAccount(account);
        }
    }
}
