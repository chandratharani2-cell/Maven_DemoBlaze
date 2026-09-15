package com.pageobjectmodel;

import com.base.Base_Class;
import com.interfaceelements.AddToCartProductInterfaceElements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCartProduct extends Base_Class implements AddToCartProductInterfaceElements {

    @FindBy(xpath = cart_xpath)
    private static WebElement Cart;

    @FindBy(xpath = placeOrder_xpath)
    private static WebElement PlaceOrder;

    public AddToCartProduct() {
        PageFactory.initElements(driver, this);
    }

    public void clickCart() throws InterruptedException {
        click(Cart);
        Thread.sleep(2000); // Wait for 2 seconds to ensure the cart page is loaded
        screenShot("CartPage");
        click(PlaceOrder);
    }
}

