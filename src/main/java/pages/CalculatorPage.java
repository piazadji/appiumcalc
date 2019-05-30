package pages;

import infra.DriverHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class CalculatorPage extends BasePage {

    public CalculatorPage(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "UIAKeyboard")
    private AndroidElement mainPage;

    @AndroidFindBy(id = "UIAKeyboard")
    private List<AndroidElement> calcButtons;

    @AndroidFindBy(id = "UIAKeyboard")
    private AndroidElement result;

    @AndroidFindBy(id = "UIAKeyboard")
    private AndroidElement scientificExpandButton;

    @AndroidFindBy(id = "UIAKeyboard")
    private AndroidElement moreButton;

    @AndroidFindBy(id = "UIAKeyboard")
    private List<AndroidElement> moreMenu;

    public boolean isCalculatorOpened() {
        return mainPage.isDisplayed();
    }

    public String sumOf(String a, String b) {
        getButton(a).click();
        getButton("+").click();
        getButton(b).click();
        getButton("=");
        return getResult();
    }

    public String subOf(String a, String b) {
        getButton(a).click();
        getButton("-").click();
        getButton(b).click();
        getButton("=");
        return getResult();
    }

    public String subAndMultiply(String a, String b, String m) {
        getButton(a).click();
        getButton("-").click();
        getButton(b).click();
        getButton("=");
        getButton("*").click();
        getButton(m).click();
        getButton("=").click();
        return getResult();
    }

    public String sinOf(String a) {
        expandScientific();
        switchToDeg();
        getButton("sin").click();
        getButton(a).click();
        getButton("=");
        return getResult();
    }

    public void selectMoreMenuItem(String item) {
        result.click();
        DriverHelper.waitForClickableElement(moreButton, 3);
        moreButton.click();
        clickMoreMenu(item);
    }

    private void clickMoreMenu(String menu) {
        AndroidElement button = moreMenu.stream().filter(item -> item.getText().equalsIgnoreCase(menu)).findAny().orElse(null);
        assert button != null;
        button.click();
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

    private void clickBackButton() {
        driver.navigate().back();
    }

    private AndroidElement getButton(String buttonText) {
        AndroidElement button = calcButtons.stream().filter(item -> item.getText().equalsIgnoreCase(buttonText)).findAny().orElse(null);
        assert button != null;
        return button;
    }
}
