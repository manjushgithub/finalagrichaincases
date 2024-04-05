//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.agrichain.testcases;

import com.agrichain.actionDriver.Action;
import com.agrichain.base.BaseClass;
import com.agrichain.pageObjects.HomePage;
import com.agrichain.pageObjects.resultpage;
import com.agrichain.utility.log;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BaseClass {
    static Action action = new Action();
    HomePage homepage;
    resultpage resultpages;

    public HomePageTest() {
    }

    @BeforeClass
    public void setup() {
        launchApp();
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    @Test(
        dataProvider = "DDT Testing",
        dataProviderClass = DataProvider.class
    )
    public void testLongestSubstring(String data) throws InterruptedException {
        log.startTestCase("testLongestSubstring");
        this.homepage.login("abcabcbb");
        this.homepage.login(data);
        int code = this.homepage.longestsubstring("abcabcbb");
        this.homepage.submit();
        log.info("user is going to submit the button");
        String output = this.resultpages.getOutput();
        String actualurl = this.resultpages.getcurrnturl();
        String expectedurl = "http:agrichain/com/qa/output";
        Assert.assertEquals(actualurl, expectedurl);
        log.info("user is getting the result through app");
        Assert.assertEquals(code, output);
        System.out.println("Output: " + output);
    }
}
