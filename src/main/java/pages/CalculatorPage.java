package pages;

import infra.DriverHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CalculatorPage extends BasePage {

    public CalculatorPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.android.calculator2:id/drag_layout")
    private WebElement mainPage;

    @AndroidFindBy(className = "android.widget.Button")
    private List<WebElement> calcButtons;

    @AndroidFindBy(id = "com.android.calculator2:id/result")
    private WebElement result;

    @AndroidFindBy(id = "com.android.calculator2:id/pad_advanced")
    private WebElement scientificExpandButton;

    @AndroidFindBy(className = "android.widget.ImageButton")
    private WebElement moreButton;

    @AndroidFindBy(id = "android:id/title")
    private List<WebElement> moreMenu;

    @AndroidFindBy(id = "com.android.calculator2:id/op_mul")
    private WebElement multButton;

    @AndroidFindBy(id = "com.android.calculator2:id/op_sub")
    private WebElement subButton;

    @AndroidFindBy(id = "com.android.calculator2:id/op_add")
    private WebElement addButton;

    @AndroidFindBy(id = "com.android.calculator2:id/eq")
    private WebElement resultButton;

    public boolean isCalculatorOpened() {
        return mainPage.isDisplayed();
    }

    public String sumOf(String a, String b) {
        inputDigit(a);
        tapAddButton();
        inputDigit(b);
        tapResultButton();
        return getResult();
    }

    public String subOf(String a, String b) {
        inputDigit(a);
        tapMinusButton();
        inputDigit(b);
        tapResultButton();
        return getResult();
    }

    public String subAndMultiply(String a, String b, String m) {
        expandScientific();
        getButton("(").click();
        tapBackButton();
        inputDigit(a);
        tapMinusButton();
        inputDigit(b);
        expandScientific();
        getButton(")").click();
        tapBackButton();
        tapMultButton();
        inputDigit(m);
        tapResultButton();
        return getResult();
    }

    public String sinOf(String a) {
        expandScientific();
        getButton("sin").click();
        switchToDeg();
        tapBackButton();
        inputDigit(a);
        tapResultButton();
        return getResult();
    }

    public void selectMoreMenuItem(String item) {
        result.click();
        DriverHelper.waitForClickableElement(driver, moreButton, 3);
        moreButton.click();
        clickMoreMenu(item);
    }

    private void clickMoreMenu(String menu) {
        WebElement button = moreMenu.stream().filter(item -> item.getText().equalsIgnoreCase(menu)).findAny().orElse(null);
        assert button != null;
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

    private void switchToDeg() {
        getButton("DEG").click();
    }

    private void tapBackButton() {
        this.driver.navigate().back();
    }

    private WebElement getButton(String buttonText) {
        WebElement button =
                calcButtons.stream().filter(item -> item.getText().equalsIgnoreCase(buttonText)).findAny().orElse(null);
        assert button != null;
        return button;
    }
}
