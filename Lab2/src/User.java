public class User {
    private String username;
    private Password password;

    public User(String username, Password password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password.getPassword();
    }

    public void setPassword(String newPassword) {
        this.password.setPassword(newPassword);
    }
}
//game should first be simple, then build on top of it.
//it is played from the terminal, I am not interested in graphics right now.
//I got the idea from a fun description of the reentrancy attack: imagine you are in a virtual city, that has a virtual bank. You deposit cash at the bank and are able to withdraw from that bank. 