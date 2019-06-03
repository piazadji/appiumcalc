package steps;

import cucumber.api.java.en.Then;
import io.appium.java_client.android.AndroidDriver;
import pages.HistoryPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HistoryPageSteps extends ContextSteps {
    private ContextSteps contextSteps;
    private HistoryPage historyPage;

    public HistoryPageSteps(ContextSteps context) {
        this.contextSteps = context;
        this.historyPage = new HistoryPage(contextSteps.getDriver());
    }

    @Then("I see '(\\d+)' formulas? in History")
    public void I_see_x_formula(int count) {
        assertThat("Formulas count is not correct", historyPage.getHistoryFormulasCount(), is(count));
    }


}
