package kr.edcan.sunrinton.models;

/**
 * Created by fluor on 2017-05-10.
 */

public class User {
    private String _id;
    private int userType = 0;
    private String name, pw, email, token;
    private int money;

    public User(int userType, String name, String pw, String email, String token, int money) {
        this.userType = userType;
        this.name = name;
        this.pw = pw;
        this.email = email;
        this.token = token;
        this.money = money;
    }

    public String get_id() {
        return "No. " + _id;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}