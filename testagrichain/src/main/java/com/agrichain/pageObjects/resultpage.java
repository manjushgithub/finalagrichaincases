//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.agrichain.pageObjects;

import com.agrichain.actionDriver.Action;
import com.agrichain.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class resultpage extends BaseClass {
    static Action Action = new Action();
    @FindBy(
        css = "a[class='output']"
    )
    WebElement outputis;
    @FindBy(
        id = "backhomebutton"
    )
    WebElement backhome;

    public resultpage() {
        PageFactory.initElements(driver, this);
    }

    public String getOutput() {
        return this.outputis.getText();
    }

    public String getcurrnturl() {
        return Action.getCurrentURL(driver);
    }

    public HomePage login(String data) throws InterruptedException {
        Action.click(driver, this.backhome);
        return new HomePage();
    }
}
