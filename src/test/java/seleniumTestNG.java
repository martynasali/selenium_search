import org.testng.annotations.*;



public class seleniumTestNG {

    @BeforeTest
    public void doBeforeRunTest()
    {
        Selenium.setup();
    }
    @Test (priority = 0)
    public void searchByKeywordTest()
    {
        Selenium.searchKeyword("Baranauskas");
    }
    @Test (priority = 1)
    public void compareResultsNumber()
    {
        Selenium.compareResultsNumber();
    }
    @AfterTest
    public void closeBrowser()
    {
        Selenium.close();
    }
}
