package com.agrichain.utility;

public class ExtentManager {
	public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    public ExtentManager() {
    }

    public static void setExtent() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/ExtentReport/" + "MyReport.html");
        htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
        extent = new ExtentReports();
        extent.attachReporter(new ExtentReporter[]{htmlReporter});
        extent.setSystemInfo("HostName", "MyHost");
        extent.setSystemInfo("ProjectName", "MyStoreProject");
        extent.setSystemInfo("Tester", "Hitendra");
        extent.setSystemInfo("OS", "Win10");
        extent.setSystemInfo("Browser", "Chrome");
    }

    public static void endReport() {
        extent.flush();
    }
}

