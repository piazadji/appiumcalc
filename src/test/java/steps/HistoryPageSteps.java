package steps;

import cucumber.api.java.en.Then;
import infra.TestContext;
import pages.HistoryPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HistoryPageSteps {
    private TestContext testContext;
    private HistoryPage historyPage;

    public HistoryPageSteps(TestContext context) {
        this.testContext = context;
        this.historyPage = new HistoryPage(testContext.getDriver());
    }

    @Then("I see '(\\d+)' formulas? in History")
    public void I_see_x_formula(int count) {
        assertThat("Formulas count is not correct", historyPage.getHistoryFormulasCount(), is(count));
    }
}
