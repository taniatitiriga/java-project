public class Account {
    private String domain;
    private String accountUsername;
    private Password password;

    public Account(String domain, String accountUsername, Password password) {
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

    //class for pass 1FA or 2FA, generate
    public String getPassword() {
        return password.getPassword();
    }

    public void setPassword(String newPassword) {
        this.password.setPassword(newPassword);
    }
}
