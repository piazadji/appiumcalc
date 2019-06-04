package pages;

import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HistoryPage extends BasePage {

    public HistoryPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.android.calculator2:id/history_formula")
    private List<AndroidElement> historyFormulas;

    public int getHistoryFormulasCount() {
        return historyFormulas.size();
    }
}
