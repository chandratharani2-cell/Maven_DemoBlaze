package com.pageobjectmanager;

import com.pageobjectmodel.AddToCartProduct;
import com.pageobjectmodel.LoginPage;
import com.pageobjectmodel.PurchaseOrder;
import com.pageobjectmodel.SearchPage;
import com.utility.FileReaderManager;

public class PageObjectManager {
    private FileReaderManager fileReaderManager;
    private static PageObjectManager pageObjectManager;
    private LoginPage loginPage;
    private SearchPage searchPage;
    private AddToCartProduct addToCartProduct;
    private PurchaseOrder purchaseOrder;

    public FileReaderManager getFileReaderManager() {
       if(fileReaderManager==null){
            fileReaderManager=new FileReaderManager();
        }
        return fileReaderManager;
    }
    public static PageObjectManager getPageObjectManager() {
        if(pageObjectManager==null){
            pageObjectManager=new PageObjectManager();
        }
        return pageObjectManager;
    }
    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }
    public SearchPage getSearchPage() {
        if (searchPage == null) {
            searchPage = new SearchPage();
        }
        return searchPage;
    }
    public AddToCartProduct getAddToCartProduct() {
        if (addToCartProduct == null) {
            addToCartProduct = new AddToCartProduct();
        }
        return addToCartProduct;
    }
    public PurchaseOrder getPurchaseOrder() {
        if (purchaseOrder == null) {
            purchaseOrder = new PurchaseOrder();
        }
        return purchaseOrder;
    }
}
