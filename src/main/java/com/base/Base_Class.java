package com.base;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Base_Class {

    public static WebDriver driver;
    static JavascriptExecutor javaScript = (JavascriptExecutor) driver;
    static Robot robot;

    //1.Launch Browser
    protected static WebDriver launchBrowser(String browserName) {
        try {
            if (browserName.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            }
        } catch (Exception e) {
            Assert.fail("ERROR: Unable to launch browser");
        }
        driver.manage().window().maximize();
        return driver;
    }

    //2.Launch URL
    protected static void launchUrl(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            Assert.fail("ERROR: Unable to launch URL");
        }
    }

    //3.Get title() & Get current url()
    protected static void urlInfo() {
        try {
            String title = driver.getTitle();
            String url = driver.getCurrentUrl();
            System.out.println("Title of the page is: " + title);
            System.out.println("URL of the Current Page is : " + url);
        } catch (Exception e) {
            Assert.fail("ERROR: Unable to get URL info");
        }
    }

    //4.Quit()
    protected static void closeEntireBrowser() {
        try {
            driver.quit();
        } catch (Exception e) {
            Assert.fail("ERROR: Unable to close the entire browser");
        }
    }

    //5.Close()
    protected static void closeCurrentBrowser() {
        try {
            driver.close();
        } catch (Exception e) {
            Assert.fail("ERROR: Unable to close the current browser");
        }
    }

    //6.Window Handling
    protected static void windowsHandling(int num) {
        try {
            List<String> allWindow = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(allWindow.get(num));
        } catch (Exception e) {
            Assert.fail("ERROR: Unable to Open the Window" + num);
        }
    }

    //7.Get Text
    protected static void webpageText(WebElement element) {
        try {
            String text = element.getText();
            System.out.println(text);
        } catch (Exception e) {
            Assert.fail("ERROR: Could not get text ");
        }
    }

    //8.Navigation To
    protected static void navigateTo(String url) {
        try {
            driver.navigate().to(url);
            System.out.println("Navigated the Page-1 successfully to : " + url);
        } catch (Exception e) {
            Assert.fail("ERROR: Unable to navigate the page to " + url);
        }
    }

    //9/10/11. Navigation Options Back,Forward
    protected static void navigationOptions(String option) {
        try {
            if (option.equalsIgnoreCase("back")) {
                driver.navigate().back();
            } else if (option.equalsIgnoreCase("forward")) {
                driver.navigate().forward();
            } else if (option.equalsIgnoreCase("refresh")) {
                driver.navigate().refresh();
            } else {
                System.out.println("Invalid navigation options");
            }
        } catch (Exception e) {
            Assert.fail("ERROR:Unable to perform the navigation options");
        }
    }

    //12. Screenshot
    protected static void screenShot(String filename) {
        try {
            Date currentDate = new Date();
            String newDate = currentDate.toString().replaceAll(":", "_").replace(" ", "_");
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File(".//" + filename + "_" + newDate + ".png"));
        } catch (Exception e) {
            Assert.fail("ERROR: Unable to take the screenshot "+ e.getMessage());
        }
    }

    //13. Send keys
    protected static void sendText(WebElement element, String values_to_send) {
        try {
            //element.clear();
            element.sendKeys(values_to_send);
        } catch (Exception e) {
            Assert.fail("ERROR: Unable to send the values to the element.");
        }
    }

    //14.1 JavaScript Executor--Sending Text/Values
    protected static void js_Enter_Text(WebElement element, String text) {
        try {
            javaScript.executeScript("arguments[0].value= arguments[1];", element, text);
        } catch (Exception e) {
            Assert.fail("ERROR: Could not enter the Text/value.");
        }
    }

    //14.2 JavaScript Executor--Click()
    protected static void js_Click(WebElement element) {
        try {
            javaScript.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            Assert.fail("ERROR: Could not click the element.");
        }
    }

    //14.3 JavaScript Executor--Scroll_into_View(true) 14.4 JavaScript Executor--Scroll_into_View(false)
    protected static void scrollPage(boolean value, WebElement element) {
        try {
            javaScript.executeScript("arguments[0].scrollIntoView(arguments[1]);", element, value);
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to scroll the page. ");
        }
    }

    //14.5 getAttribute() using JavaScript Executor
    protected static String getElementAttribute(WebElement element, String attribute_to_be_returned) {
        try {
            return element.getAttribute(attribute_to_be_returned);
        } catch (Exception e) {
            Assert.fail("ERROR: Could not fetch the attribute value");
        }
        return null;
    }

    //15.click()
    public static void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            Assert.fail("ERROR: Unable to click the element. Root cause: " + e.getMessage());
        }
    }

    //16.Robot Class
    protected static void keyBoardActions(String keyAction, int repeatCount) {
        try {
            robot = new Robot();
            String Action = keyAction.toLowerCase();
            int keyValue;
            if (Action.equals("down")) {
                keyValue = KeyEvent.VK_DOWN;
            } else if (Action.equals("up")) {
                keyValue = KeyEvent.VK_UP;
            } else if (Action.equals("enter")) {
                keyValue = KeyEvent.VK_ENTER;
            } else if (Action.equals("tab")) {
                keyValue = KeyEvent.VK_TAB;
            } else if (Action.equals("escape")) {
                keyValue = KeyEvent.VK_ESCAPE;
            } else {
                System.out.println("ERROR: Invalid keyboard action ");
                return;
            }
            for (int i = 0; i < repeatCount; i++) {
                robot.keyPress(keyValue);
                robot.keyRelease(keyValue);
            }
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to press key ");
        }
    }

    //17.Element is enabled or not
    protected static boolean isElementEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to check if the element is enabled.");
        }
        return false;
    }

    //18.Element is selected or not
    protected static boolean isElementSelected(WebElement element) {
        try {
            return element.isSelected();
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to check if the element is selected.");
        }
        return false;
    }

    //19.Element is enabled or not
    protected static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to check if the element is displayed.");
        }
        return false;
    }

    //20.Drop-Down
    protected static void selectOption(WebElement dropdownelement, String type, String value) {
        Select select = new Select(dropdownelement);
        try {
            if (type.equalsIgnoreCase("value")) {
                select.selectByValue(value);
            } else if (type.equalsIgnoreCase("visibletext")) {
                select.selectByVisibleText(value);
            } else if (type.equalsIgnoreCase("index")) {
                select.selectByIndex(Integer.parseInt(value));
            } else {
                System.out.println("ERROR: Invalid selection type.");
            }
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to select option from dropdown.");
        }
    }

    //21.De-Select Option
    protected static void deselectOption(WebElement dropdownelement, String type, String value) {
        Select deselect = new Select(dropdownelement);
        try {
            if (type.equalsIgnoreCase("value")) {
                deselect.deselectByValue(value);
            } else if (type.equalsIgnoreCase("visibletext")) {
                deselect.deselectByVisibleText(value);
            } else if (type.equalsIgnoreCase("index")) {
                deselect.deselectByIndex(Integer.parseInt(value));
            } else {
                System.out.println("ERROR: Invalid deselection type.");
            }
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to deselect option from dropdown.");
        }
    }

    //22.Get First Selected Option
    protected static void getSelectedOptionText(WebElement element) {
        try {
            Select dropdown = new Select(element);
            System.out.println("The First selected List: " + dropdown.getFirstSelectedOption().getText());
        } catch (Exception e) {
            Assert.fail("ERROR: Could not get selected option text: ");
        }
    }

    //23.Get All Selected Options
    protected static void getAllSelectedOptionText(WebElement element) {
        try {
            Select dropdown = new Select(element);
            List<WebElement> allSelectedOptions = dropdown.getAllSelectedOptions();
            for (WebElement allSelectedItems : allSelectedOptions) {
                System.out.println("Selected Option: " + allSelectedItems.getText());
            }
        } catch (Exception e) {
            Assert.fail("ERROR: Could not get All selected option text: ");
        }
    }

    //24.Is Multiple
    protected static void isMultiple(WebElement element) {
        try {
            Select dropdown = new Select(element);
            boolean result = dropdown.isMultiple();
            if (result == true) {
                System.out.println("The dropdown is multi-selectable");
            } else {
                System.out.println("The dropdown is not multi-selectable");
            }
        } catch (Exception e) {
            Assert.fail("ERROR: Could not determine if the dropdown is multi-selectable: ");
        }
    }

    //25.1Alert --Prompt
    protected static void getAlertPrompt(String text_to_enter) {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println(alert.getText());
            alert.sendKeys(text_to_enter);
            alert.accept();
        } catch (Exception e) {
            Assert.fail("ERROR: Could not enter the value in Textbox. ");
        }
    }

    //25.2/3 Alert -- Handle Accept or Dismiss
    protected static void handleAlert(String action) {
        try {
            Alert alert = driver.switchTo().alert();
            if (action.equalsIgnoreCase("accept")) {
                alert.accept();
            } else {
                alert.dismiss();
            }
        } catch (Exception e) {
            Assert.fail("ERROR: Could not handle the alert box.");
        }
    }

    //25.4Alert --Get Text
    protected static void getAlertText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            alert.accept();
            System.out.println(alertText);
        } catch (Exception e) {
            Assert.fail("ERROR: COULD NOT PRINT THE TEXT OF ALERT BOX");
        }
    }

    //26.Scroll UP and Scroll Down
    protected static void scrollVertical(boolean scrollDown, int pixels) {
        try {
            if (scrollDown) {
                javaScript.executeScript("window.scrollBy(0, arguments[0]);", pixels);
            } else {
                javaScript.executeScript("window.scrollBy(0, -arguments[0]);", pixels);
            }
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to scroll the page.");
        }
    }

    //27.1Frames-Parent Frame
    protected static void switchToMainPage() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            Assert.fail("ERROR: Unable to switch back to main page.");
        }
    }

    //27.2Frames-Frame by Index
    protected static void switchToFrameByIndex(int index) {
        try {
            driver.switchTo().frame(index);
        } catch (Exception e) {
            Assert.fail("ERROR: Unable to switch back to main page.");
        }
    }

    //27.3Frames-Frame by Name or ID
    protected static void switchToFrameByNameOrId(String nameOrId) {
        try {
            driver.switchTo().frame(nameOrId);
        } catch (Exception e) {
            Assert.fail("ERROR: Unable to switch back to main page.");
        }
    }

    //27.4Frames-Frame by WebElement
    protected static void switchToFrameByWebElement(WebElement element) {
        try {
            driver.switchTo().frame(element);
        } catch (Exception e) {
            Assert.fail("ERROR: Unable to switch back to main page.");
        }
    }

    //28. Explicit Wait -- Wait for element to be visible
    protected static void waitExplicit(WebElement element, int seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            Assert.fail("ERROR: Element was not visible within .");
        }
    }

    //29. Explicit Wait -- Wait for element to be Clickable
    protected static void waitExplicitClickable(WebElement element, int seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            Assert.fail("ERROR: Element was not clickable.");
        }
    }

    //30. Explicit Wait -- Wait for element to be Selected
    protected static void waitExplicitSelected(WebElement element, int seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.elementToBeSelected(element));
        } catch (Exception e) {
            Assert.fail("ERROR: Element was not selected.");
        }
    }

    //31.Explicit Wait -- alert to be present
    protected static void waitExplicitAlert(int seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (Exception e) {
            Assert.fail("ERROR: Alert was not present.");
        }
    }

    //31.Actions
    protected static void mouseActions(WebElement element, String actionsType) {
        Actions actions = new Actions(driver);
        String Type = actionsType.toLowerCase();
        try {
            if (Type.contains("hover")) {
                actions.moveToElement(element).perform();
            } else if (Type.contains("rightclick")) {
                actions.contextClick(element).perform();
            } else if (Type.contains("doubleclick")) {
                actions.doubleClick(element).perform();
            }
        } catch (Exception e) {
            Assert.fail("ERROR:FAILED TO DO ACTIONS CLICK");
        }
        }
        //drag and drop
        //keys up and keys down

    //Implicit wait
    protected static void implicitWait(int seconds) {
        try {
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(seconds));
        } catch (Exception e) {
            Assert.fail("ERROR: Implicit wait failed. Details: " + e.getMessage());
        }
    }
}




