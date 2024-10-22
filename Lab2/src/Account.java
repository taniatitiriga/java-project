public class Account {
    private String domain;
    private String accountUsername;
    private String password;

    public Account(String domain, String accountUsername, String password) {
        this.domain = domain;
        this.accountUsername = accountUsername;
        this.password = password;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
