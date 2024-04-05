//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.agrichain.pageObjects;

import com.agrichain.actionDriver.Action;
import com.agrichain.base.BaseClass;
import java.util.HashSet;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseClass {
    static Action Action = new Action();
    @FindBy(
        xpath = "//a[@class='textbox']"
    )
    WebElement textbox;
    @FindBy(
        xpath = "//submit']"
    )
    WebElement submitbtn;
    @FindBy(
        xpath = "//img[@class='logo img-responsive']"
    )
    WebElement agrichainLOGO;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public boolean validateLogo() {
        return Action.isDisplayed(driver, this.agrichainLOGO);
    }

    public String getagrichainTitle() {
        String agrichaintitle = driver.getTitle();
        return agrichaintitle;
    }

    public int longestsubstring(String str) {
        int n = str.length();
        HashSet<Character> hs = new HashSet();
        int maxlength = 0;
        int l = 0;
        int r = 0;

        while(r < n) {
            if (!hs.contains(str.charAt(r))) {
                hs.add(str.charAt(r));
                ++r;
                maxlength = Math.max(maxlength, hs.size());
            } else {
                hs.remove(str.charAt(l));
                ++l;
            }
        }

        return maxlength;
    }

    public resultpage login(String data) throws InterruptedException {
        Action.type(this.textbox, data);
        Action.click(driver, this.submitbtn);
        return new resultpage();
    }

    public void submit() {
        this.submitbtn.click();
    }
}
