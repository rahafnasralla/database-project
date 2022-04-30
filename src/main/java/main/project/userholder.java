package main.project;


import model.member;

public final class userholder {

    private member user;
    private final static userholder INSTANCE = new userholder();

    private userholder() {}

    public static userholder getInstance() {
        return INSTANCE;
    }

    public void setUser(member u) {
        this.user = u;
    }

    public member getUser() {
        return this.user;
    }
}