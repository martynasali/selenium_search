import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.security.auth.login.AccountLockedException;

public class Selenium {
    private static WebDriver browser;
    public static final int WAIT_FOR_TIME = 2;
    public static final String BING_SEARCH_BUTTON_XPATH = "//*[@id=\"sb_form_go\"]";

    public static void main(String args[]) {
        System.out.println("Selenium");

        setup();
        searchKeyword("Baranauskas");
        compareResultsNumber();
        close();
    }

    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver 87v");
        browser = new ChromeDriver();
        browser.get("https://www.bing.com");
    }

    public static void searchKeyword(String keyword) {
        waitForVisibilityElementLocated("//*[@id=\"sb_form_q\"]");
        WebElement searchField = browser.findElement(By.xpath("//*[@id=\"sb_form_q\"]"));
        searchField.sendKeys(keyword);
//        searchField.sendKeys(Keys.ENTER);

        WebElement searchBtn = browser.findElement(By.xpath(BING_SEARCH_BUTTON_XPATH));

        // pirmas budas
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) browser;
        javascriptExecutor.executeScript("arguments[0].click();", searchBtn);

//        // antras budas
//        waitForVisibilityElementLocated(BING_SEARCH_BUTTON_XPATH);
//        searchBtn.click();
//
//        // trecias budas
//        waitForElementToBeClickable(BING_SEARCH_BUTTON_XPATH);
//        searchBtn.click();
    }

    public static String compareResultString() {
        WebElement countResult = browser.findElement(By.className("sb_count"));

        String resultStrWithoutNumbers = countResult.getText()
                .replaceAll("[0-9]", "")
                .replaceAll("[ ,]", "");    //searchKeyword("Baranauskas");
        return resultStrWithoutNumbers;
    }

    public static int compareResultsNumber() {
        WebElement countResult = browser.findElement(By.className("sb_count"));

        System.out.println(countResult.getText());
        String resultStr = countResult.getText()
                .replaceAll("[a-zA-z]", "")
                .replaceAll("[ ,]", "");    //searchKeyword("Baranauskas");

        int resultInt = Integer.parseInt(resultStr);
        System.out.println(resultInt);

        if (resultInt > 50000) {
            System.out.println("Rasytojas yra pakankamai zinomnas");
        } else {
            System.out.println("Nelabai zinomas");
        }
        return resultInt;

    }

    public static void waitForVisibilityElementLocated(String xpath) {
        WebDriverWait wait = new WebDriverWait(browser, WAIT_FOR_TIME);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public static void waitForElementToBeClickable(String xpath) {
        WebDriverWait wait = new WebDriverWait(browser, WAIT_FOR_TIME);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }


    public static void close() {
        browser.close();
    }
}