package com.interfaceelements;

public interface PurchaseOrderInterfaceElements {
    String name_xpath = "//input[@id='name']";
    String country_xpath = "//input[@id='country']";
    String city_xpath = "//input[@id='city']";
    String card_xpath = "//input[@id='card']";
    String month_xpath = "//input[@id='month']";
    String year_xpath = "//input[@id='year']";
    String purchaseOrder_xpath = "//button[@onclick='purchaseOrder()']";

    String message_xpath = "//h2[contains(text(),'Thank')]";
    String confirmation_xpath = "//p[contains(@class,'lead ')]";
    String ok_xpath = "//button[text()='OK']";
    String logout_xpath = "//a[text()='Log out']";
}
