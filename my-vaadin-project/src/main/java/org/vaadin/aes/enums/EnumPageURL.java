package org.vaadin.aes.enums;

public enum EnumPageURL {
    SIGNUP_PAGE("Signup Page", "signup",""),
    LOGIN_PAGE("Login Page", "login",""),
    FOOD_PAGE("Food Page", "food-page","images/food.jpg"),
    PROFILE("Profile", "profile","images/profile.png"),
    PAYMENT("Payment", "payment","images/dollar.png");

    private final String name;
    private final String url;
    private final String imagePath;

    EnumPageURL(String name, String url, String imagePath) {
        this.name = name;
        this.url = url;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getImagePath() {
        return imagePath;
    }
}
