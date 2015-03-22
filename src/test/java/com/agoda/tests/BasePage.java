package com.agoda.tests;

import com.web.coreframework.TestData;
import com.web.coreframework.WebPageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

@ContextConfiguration(locations = {"classpath:applicationContext-pages.xml"})
public abstract class BasePage extends AbstractTestNGSpringContextTests {

    @Autowired
    protected WebPageFactory webPageFactory;

    @Autowired
    protected TestData testData;

    @BeforeClass(alwaysRun = true)
    public void initTestData() {
        try {
            testData.load("testdata.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshPage() {
        webPageFactory.getDriver().navigate().refresh();
    }

}
