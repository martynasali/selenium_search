import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SeleniumTest {
    @Before
    public void doBeforeTest() {
        Selenium.setup();
    }

    @Test
    public void searchKeywordTest(){
        Selenium.searchKeyword("Baranauskas");
        //compareResultsNumberTest();
        compareResultsStringTest();
    }

    public void compareResultsNumberTest() {
        int results = Selenium.compareResultsNumber();
        Assert.assertEquals(157000, results);
    }
    public void compareResultsStringTest() {
        String results = Selenium.compareResultString();
        Assert.assertEquals("Results", results); // tas pats kas results.equals("Results")

        //jeigu reikia surasti eilutes dali, naudoti contains metoda - results.contains("Results")
        // tas pats kas sql LIKE %
    }

    @After
    public void close() { Selenium.close();}

}