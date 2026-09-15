package com.pageobjectmodel;

import com.base.Base_Class;
import com.interfaceelements.SearchProductInterfaceElements;
import com.pageobjectmanager.PageObjectManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends Base_Class implements SearchProductInterfaceElements {
    @FindBy(linkText = linkText_laptop )
    private static WebElement Laptop;

   @FindBy(linkText= linkText_product)
    private static WebElement Product;

   @FindBy(xpath = xpath_addToCart)
   private static WebElement AddToCart;

    public SearchPage() {
        PageFactory.initElements(driver,this);
    }

    public static void SearchProduct() throws InterruptedException {
        click(Laptop);
        Thread.sleep(2000);
        click(Product);
        Thread.sleep(2000);
        click(AddToCart);
        waitExplicitAlert(10);
        getAlertText();
}
}
