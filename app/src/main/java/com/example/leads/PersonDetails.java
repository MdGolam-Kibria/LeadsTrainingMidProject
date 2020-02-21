package com.example.leads;

public class PersonDetails {

    private String name;
    private String email;
    private String adress;
    private String password;
    private byte[] image;

    public PersonDetails() {
    }

    public PersonDetails(String name, String email, String adress, String password, byte[] image) {
        this.name = name;
        this.email = email;
        this.adress = adress;
        this.password = password;
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
