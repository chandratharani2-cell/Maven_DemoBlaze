package com.pageobjectmodel;

import com.base.Base_Class;
import com.interfaceelements.PurchaseOrderInterfaceElements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseOrder extends Base_Class implements PurchaseOrderInterfaceElements {

    @FindBy(xpath = name_xpath)
    private static WebElement Name;

    @FindBy(xpath = country_xpath)
    private static WebElement Country;

    @FindBy(xpath = city_xpath)
    private static WebElement City;

    @FindBy(xpath = card_xpath)
    private static WebElement Card;

    @FindBy(xpath = month_xpath)
    private static WebElement Month;

    @FindBy(xpath = year_xpath)
    private static WebElement Year;

    @FindBy(xpath = purchaseOrder_xpath)
    private static WebElement PurchaseOrderButton;

    @FindBy(xpath = logout_xpath)
    private static WebElement Logout;

    @FindBy(xpath = message_xpath)
    private static WebElement message;

    @FindBy(xpath = confirmation_xpath)
    private static WebElement Confirmation;

    @FindBy(xpath = ok_xpath)
    private static WebElement Ok;
    public PurchaseOrder() {
        PageFactory.initElements(driver, this);
    }
    public void purchaseOrder() throws InterruptedException {
        Thread.sleep(2000);
        sendText(Name, "Tharani");
        sendText(Country, "INDIA");
        sendText(City, "Coimbatore");
        sendText(Card, "1234 5678 9012 3456");
        sendText(Month, "07");
        sendText(Year, "2028");
        click(PurchaseOrderButton);
        Thread.sleep(2000);
        webpageText(message);
        webpageText(Confirmation);
        screenShot("PurchaseOrderConfirmation");
        implicitWait(10);
        click(Ok);
        click(Logout);
    }
}