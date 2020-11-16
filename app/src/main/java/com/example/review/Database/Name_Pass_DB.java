package com.example.review.Database;

public class Name_Pass_DB {
    private String Name;
    private  String Password;

    // 3amalt create le constructor w setters and getters mn el generate code 3l4an han7taghom
    public Name_Pass_DB(String name, String password) {
        Name = name;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
