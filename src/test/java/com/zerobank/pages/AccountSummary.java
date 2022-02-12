package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;


public class AccountSummary {

    public AccountSummary(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "#account_summary_tab>a")
    public WebElement accountSummary;

    @FindBy(xpath = "//table//tbody//tr//a")
    public List<WebElement> accounts;

    @FindBy(xpath = "//*[@href='/bank/account-activity.html?accountId=1']")
    public WebElement firstAccount;

    @FindBy (xpath = "(//table[@class='table'])[3]//th")
    public List<WebElement> creditAccounts;

    @FindBy (xpath = "//h2")
    public List<WebElement> headerAccounts;


    public String getPageSubTitle() {
        //ant time we are verifying page name, or page subtitle, loader mask appears
        BrowserUtils.waitFor(2);
        return Driver.get().getTitle();
    }

    public void chooseAccount(String accountName){
        WebElement choosen=null;
        for (WebElement account : accounts) {
            System.out.println("account.getText() = " + account.getText());
            if (account.getText().equals(accountName)){
                choosen=account;
                break;
            }
        }
        System.out.println("choosen.getText() = " + choosen.getText());
        choosen.click();
    }

}
