package pages;

import infra.DriverHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class CalculatorPage extends BasePage {

    public CalculatorPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.android.calculator2:id/drag_layout")
    private MobileElement pageContainer;

    @AndroidFindBy(className = "android.widget.Button")
    private List<MobileElement> calcButtons;

    @AndroidFindBy(id = "com.android.calculator2:id/result")
    private MobileElement result;

    @AndroidFindBy(id = "com.android.calculator2:id/pad_advanced")
    private MobileElement scientificExpandButton;

    @AndroidFindBy(className = "android.widget.ImageButton")
    private MobileElement moreButton;

    @AndroidFindBy(id = "android:id/title")
    private List<MobileElement> moreMenuOptions;

    @AndroidFindBy(id = "com.android.calculator2:id/op_mul")
    private MobileElement multButton;

    @AndroidFindBy(id = "com.android.calculator2:id/op_sub")
    private MobileElement subButton;

    @AndroidFindBy(id = "com.android.calculator2:id/op_add")
    private MobileElement addButton;

    @AndroidFindBy(id = "com.android.calculator2:id/eq")
    private MobileElement resultButton;

    @AndroidFindBy(id = "com.android.calculator2:id/lparen")
    private MobileElement leftBracketButton;

    @AndroidFindBy(id = "com.android.calculator2:id/rparen")
    private MobileElement rightBracketButton;

    @AndroidFindBy(id = "com.android.calculator2:id/fun_sin")
    private MobileElement sinusButton;

    @AndroidFindBy(id = "com.android.calculator2:id/toggle_mode")
    private MobileElement toggleModeButton;


    public boolean isCalculatorOpened() {
        return pageContainer.isDisplayed();
    }

    /**
     * Sum of two numbers
     *
     * @param a first number String
     * @param b second number String
     * @return result of the sum
     */
    public String sumOf(String a, String b) {
        inputDigit(a);
        tapAddButton();
        inputDigit(b);
        tapResultButton();
        return getResult();
    }

    /**
     * Substitution of two numbers
     *
     * @param a first number String
     * @param b second number String
     * @return result of the substitution
     */
    public String subOf(String a, String b) {
        inputDigit(a);
        tapMinusButton();
        inputDigit(b);
        tapResultButton();
        return getResult();
    }

    /**
     * Calculation of (a-b)*m
     *
     * @param a first number String
     * @param b second number String
     * @param m multiplier String
     * @return result of the (a-b)*m
     */
    public String subAndMultiply(String a, String b, String m) {
        expandScientific();
        tapLeftBracket();
        tapBackButton();
        inputDigit(a);
        tapMinusButton();
        inputDigit(b);
        expandScientific();
        tapRightBracket();
        tapBackButton();
        tapMultButton();
        inputDigit(m);
        tapResultButton();
        return getResult();
    }

    /**
     * Calculation of sin(a)
     *
     * @param a string of RAD
     * @return result of sin(a)
     */
    public String sinOf(String a) {
        expandScientific();
        tapSinusButton();
        switchToDeg();
        tapBackButton();
        inputDigit(a);
        tapResultButton();
        return getResult();
    }

    /**
     * Click on the button to open more menu  and select and item from More menu
     *
     * @param item title string
     */
    public void selectMoreMenuItem(String item) {
        result.click();
        DriverHelper.waitForClickableElement(driver, moreButton, Duration.ofSeconds(3));
        moreButton.click();
        clickMoreMenuItem(item);
    }

    private void clickMoreMenuItem(String menuItem) {
        MobileElement button = moreMenuOptions.stream().
                filter(item -> item.getText().equalsIgnoreCase(menuItem)).findAny().
                orElseThrow(() -> new NoSuchElementException(String.format("No menu item '%s' found", menuItem)));
        button.click();
    }

    private void inputDigit(String digit) {
        String[] ary = digit.split("");
        for (String num : ary) {
            getButton(num).click();
        }
    }

    private void tapMultButton() {
        multButton.click();
    }

    private void tapMinusButton() {
        subButton.click();
    }

    private void tapAddButton() {
        addButton.click();
    }

    private void tapResultButton() {
        resultButton.click();
    }

    private String getResult() {
        return result.getText();
    }

    private void expandScientific() {
        scientificExpandButton.click();
    }

    private void tapLeftBracket() {
        leftBracketButton.click();
    }

    private void tapRightBracket() {
        rightBracketButton.click();
    }

    private void tapSinusButton() {
        sinusButton.click();
    }

    private void switchToDeg() {
        toggleModeButton.click();
    }

    private void tapBackButton() {
        this.driver.navigate().back();
    }

    private MobileElement getButton(String buttonText) {
        return calcButtons.stream().
                filter(item -> item.getText().equalsIgnoreCase(buttonText)).findAny().
                orElseThrow(() -> new NoSuchElementException(String.format("No button with text '%s' found", buttonText)));
    }
}
