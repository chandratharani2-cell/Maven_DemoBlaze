package com.runner;

import com.base.Base_Class;
import com.pageobjectmanager.PageObjectManager;

public class TestRunner extends Base_Class {
    public static void main(String args[]) throws InterruptedException {
        launchBrowser(PageObjectManager.getPageObjectManager().getFileReaderManager().getDataProperty("browser"));
        launchUrl(PageObjectManager.getPageObjectManager().getFileReaderManager().getDataProperty("url"));
        PageObjectManager.getPageObjectManager().getLoginPage().validLogin();
        PageObjectManager.getPageObjectManager().getSearchPage().SearchProduct();
        PageObjectManager.getPageObjectManager().getAddToCartProduct().clickCart();
        PageObjectManager.getPageObjectManager().getPurchaseOrder().purchaseOrder();
    }
}
