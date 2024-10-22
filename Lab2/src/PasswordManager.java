public class PasswordManager {
    private User user;
    private Account[] accounts;

    public PasswordManager(User user, Account[] accounts) {
        this.user = user;
        this.accounts = accounts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account newAccount) {
        Account[] updatedAccounts = new Account[this.accounts.length + 1];
        System.arraycopy(this.accounts, 0, updatedAccounts, 0, this.accounts.length);
        updatedAccounts[this.accounts.length] = newAccount;
        this.accounts = updatedAccounts;
    }
}
