package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class ZeroBankBasePage {
    public ZeroBankBasePage() {
        PageFactory.initElements(Driver.get(), this);
    }


    @FindBy(css = "#account_summary_tab>a")
    public WebElement accountSummary;

    @FindBy(css = "#account_activity_tab>a")
    public WebElement accountActivity;

    @FindBy(css = "#transfer_funds_tab>a")
    public WebElement transferFunds;

    @FindBy(css = "#pay_bills_tab>a")
    public WebElement payBills;

    @FindBy(css = "#money_map_tab>a")
    public WebElement myMoneyMap;

    @FindBy(css = "#online_statements_tab>a")
    public WebElement onlineStatements;

    @FindBy(xpath = "//a[@class='dropdown-toggle']")
    public WebElement username;

    @FindBy(xpath = "//title")
    public WebElement pageSubTitle;

    @FindBy(xpath = "//*[@href='/index.html']")
    public WebElement zeroBankHeader;

    @FindBy (id = "searchTerm")
    public WebElement searchBox;

    @FindBy (xpath = "//div[@class='top_offset']/ul/li")
    public List<WebElement> searchResults;


    public void waitUntilPageLoad() {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(), 5);
            wait.until(ExpectedConditions.visibilityOf(zeroBankHeader));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getPageSubTitle() {
        //ant time we are verifying page name, or page subtitle, loader mask appears
        waitUntilPageLoad();
        return pageSubTitle.getText();
    }

}
