package steps;

import cucumber.api.java.en.Then;
import io.appium.java_client.android.AndroidDriver;
import pages.HistoryPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HistoryPageSteps extends BaseSteps {

    private HistoryPage historyPage;

    public HistoryPageSteps(AndroidDriver driver) {
        super(driver);
        this.historyPage = new HistoryPage(driver);
    }

    @Then("I see '(\\d+)' formulas? in History")
    public void I_see_x_formula(int count) {
        assertThat("Formulas count is not correct", historyPage.getHistoryFormulasCount(), is(count));
    }


}
