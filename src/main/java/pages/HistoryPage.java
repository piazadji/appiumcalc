package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class HistoryPage extends BasePage {

    public HistoryPage(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.android.calculator2:id/history_formula")
    private List<AndroidElement> historyFormulas;

    public int getHistoryFormulasCount() {
        return historyFormulas.size();
    }
}
