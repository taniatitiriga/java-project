public class OutputDevice {

    //----basic printing----
    public void writeMessage(String mess) {
        System.out.println(mess);
    }

    //----pretty printer for single account----
    public void printAccount(Account account) {
        writeMessage("Domain: " + account.getDomain());
        writeMessage("Username: " + account.getAccountUsername());
        writeMessage("Password: " + account.getPassword());
        writeMessage("-------------------");
    }

    //----pretty printer for all accounts----
    public void printAllAccounts(Account[] accounts) {
        for (Account account : accounts) {
            printAccount(account);
        }
    }

}
