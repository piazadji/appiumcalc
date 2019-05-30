package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import org.hamcrest.Matcher;
import pages.CalculatorPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class CalculatorPageSteps extends BaseSteps {
    private CalculatorPage calculatorPage;

    public CalculatorPageSteps(AndroidDriver driver) {
        super(driver);
        this.calculatorPage = new CalculatorPage(driver);
    }

    @Given("I see calculator opened")
    public void I_see_calculator_opened() {
        assertThat("Calculator is not opened", calculatorPage.isCalculatorOpened(), is(true));
    }

    @Then("^I verify that SUM of '(.*)' and '(.*)' equals to '(.*)'$")
    public void I_verify_sum(String a, String b, String result) {
        assertThat(String.format("Sum of %s and %s should be equal to %s", a, b, result), calculatorPage.sumOf(a, b), is(result));
    }

    @Then("^I verify that SUBTRACTION of '(.*)' and '(.*)' equals to '(.*)'$")
    public void I_verify_subs(String a, String b, String result) {
        assertThat(String.format("Subtraction of %s and %s should be equal to %s", a, b, result), calculatorPage.subOf(a, b), is(result));
    }

    @Then("^I verify that result of SUBTRACTION of '(.*)' and '(.*)' MULTIPLIED by '(.*)' (is not )?equals? to '(.*)'$")
    public void I_verify_subs_multi(String a, String b, String m, String condition, String result) {
        Matcher match = (condition == null) ? is(result) : not(result);
        assertThat("Incorrect result", calculatorPage.subAndMultiply(a, b, m), match);
    }

    @Then("^I verify that SIN of '(.*)' is equal to '(.*)'$")
    public void I_verify_sin(String a, String result) {
        assertThat(String.format("SIN of %s should be equal to %s", a, result), calculatorPage.sinOf(a), is(result));
    }

    @When("I select '(History|Answer as fraction)' item in more menu")
    public void I_open_calculator_history(String item) {
        calculatorPage.selectMoreMenuItem(item);
    }
}
