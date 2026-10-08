package org.example;

public class PersonalInfo {
    private String fullName;
    private String title;
    private String email;
    private String phone;
    private String location;
    private String photoPath;

    public PersonalInfo(String fullName, String title, String email, String phone, String location, String photoPath) {
        this.fullName = fullName;
        this.title = title;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.photoPath = photoPath;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public String getPhotoPath() {
        return photoPath;
    }
}