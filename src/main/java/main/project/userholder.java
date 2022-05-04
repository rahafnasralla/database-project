package main.project;


public final class userholder {

    private user user;
    private final static userholder INSTANCE = new userholder();

    private userholder() {}

    public static userholder getInstance() {
        return INSTANCE;
    }

    public void setUser(user u) {
        this.user = u;
    }

    public user getUser() {
        return this.user;
    }
}