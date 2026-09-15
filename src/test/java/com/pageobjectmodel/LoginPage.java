package com.pageobjectmodel;

import com.base.Base_Class;
import com.interfaceelements.LoginPageInterfaceElements;
import com.pageobjectmanager.PageObjectManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Base_Class implements LoginPageInterfaceElements {
    @FindBy(id = login_id)
    private  WebElement login;

    @FindBy(css = username_css)
    private  WebElement username;

    @FindBy(id = password_id)
    private  WebElement password;

    @FindBy(xpath = signin_button_xpath)
    private  WebElement signin;

    @FindBy(id = nameofuser_id)
    private  WebElement nameofuser;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void validLogin() throws  InterruptedException {
        click(login);
        Thread.sleep(2000); // Wait for 2 seconds to ensure the login modal is displayed
        sendText(username, PageObjectManager.getPageObjectManager().getFileReaderManager().getDataProperty("username"));
        sendText(password, PageObjectManager.getPageObjectManager().getFileReaderManager().getDataProperty("password"));
        click(signin);
        Thread.sleep(2000);// Wait for 2 seconds to ensure the login process is completed
        screenShot("loginPage");
        webpageText(nameofuser);
    }
}
