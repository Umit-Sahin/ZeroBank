package com.zerobank.pages;


import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class AccountActivity extends ZeroBankBasePage{

    public AccountActivity() {
        PageFactory.initElements(Driver.get(), this);
    }


    @FindBy(xpath = "//*[@href='/index.html']")
    public WebElement zeroBankHeader;

    @FindBy(xpath = "//title")
    public WebElement pageTitle;

    @FindBy(xpath = "(//div[@id='tabs']/ul/li/a)[1]")
    public WebElement showTransactions;

    @FindBy(xpath = "(//div[@id='tabs']/ul/li/a)[2]")
    public WebElement findTransactions;

    @FindBy( id = "aa_description")
    public WebElement descriptionInputBox;

    @FindBy( id = "aa_fromDate")
    public WebElement fromDatesInbox;

    @FindBy( id = "aa_toDate")
    public WebElement toDatesInbox;

    @FindBy( id = "aa_fromAmount")
    public WebElement fromAmountsInbox;

    @FindBy( id = "aa_toAmount")
    public WebElement toAmountsInbox;

    @FindBy( id = "aa_type")
    public WebElement typeDropbox;

    @FindBy (id = "aa_accountId")
    public WebElement accountDropbox;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement findButton;

    @FindBy (xpath = "//div[@id='filtered_transactions_for_account']//td[1]")
    public List<WebElement> verifyDate;

    @FindBy (xpath = "//table[@class='table table-condensed table-hover']//th")
    public List<WebElement> transactionColumns;



    public void selectAccount(String account){
        Select select = new Select(accountDropbox);
        select.getFirstSelectedOption().getText();
        select.selectByVisibleText(account);
    }

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
        return Driver.get().getTitle();
    }

    public boolean verifyDateInRange2(String fromDate, String toDate) throws ParseException {
        boolean isInRange= false;
        DateFormat zeroBankFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1   = zeroBankFormat.parse(fromDate);
        Date date2   = zeroBankFormat.parse(toDate);
        for (int i = 0; i < verifyDate.size(); i++) {
            Date date3   = zeroBankFormat.parse(verifyDate.get(i).getText());
            System.out.println("date3 = " + date3);
            int comparison1= date3.compareTo(date1);
            int comparison2= date3.compareTo(date2);
            if (comparison1<=0 || comparison2>=0){
                isInRange= true;
            }else {
                isInRange= false;
            }
        }
        return isInRange;
    }


    public boolean verifyDateInOrder() {
        boolean isInOrder=false;
        AccountActivity accountActivity = new AccountActivity();
        int firstDate = Integer.parseInt(verifyDate.get(0).getText().replace("-", ""));
        for (int i = 1; i < verifyDate.size() - 1; i++) {
            if (Integer.parseInt(verifyDate.get(i).getText().replace("-", "")) < firstDate) {
                isInOrder=true;
            }else {
                isInOrder=false;
            }

        }
        return isInOrder;
    }

    public boolean isDateLineEmpty(String date) {
        boolean isLineEmpty=false;
        List <WebElement> transactionsRecord = Driver.get().findElements(By.xpath("//div[@id='filtered_transactions_for_account']//tbody//tr"));
        for (int i = 0; i < transactionsRecord.size(); i++) {
        transactionsRecord.set(i, Driver.get().findElement(By.xpath("//td[contains(text(),'"+date+"')]/../td["+(i+2)+"]")));
        }
        if (!transactionsRecord.isEmpty()){
            isLineEmpty=true;
        }

        return isLineEmpty;
    }

    public boolean isAllDepositLineEmpty() {
        //This method returns true if all column under the Deposit are empty
        int notEmptycount =0;
        boolean isAllDepositLineEmpty=false;
        List <WebElement> transactionsRecord = Driver.get().findElements(By.xpath("//div[@id='filtered_transactions_" +
              "                                                                     for_account']//tbody//tr//td[3]"));
        System.out.println("Number of deposit line = " + transactionsRecord.size());
        for (int i=0 ; i< transactionsRecord.size() ; i++) {
            if ( transactionsRecord.get(i).getText().isEmpty()) {
                notEmptycount++;
            }
        }
        if (notEmptycount==transactionsRecord.size()){
            isAllDepositLineEmpty=true;
        }
        return isAllDepositLineEmpty;
    }

    public boolean isThereAnyDepositLine() {
        //This method returns true even if one column under the Deposit is not empty
        boolean isAnyLine=false;
        List <WebElement> transactionsRecord = Driver.get().findElements(By.xpath("//div[@id='filtered_transactions_for_account']//tbody//tr//td[3]"));
        System.out.println("Number of deposit line = " + transactionsRecord.size());
        for (int i=0 ; i< transactionsRecord.size() ; i++) {
            if (!(transactionsRecord.get(i).getText().isEmpty())) {
                isAnyLine = true;
                break;
            }
        }
        System.out.println("isAnyLine = " + isAnyLine);
        return isAnyLine;
    }

    public boolean isAllWithdrawalLineEmpty() {
        //This method returns true if all column under the Withdrawal are empty
        int notEmptycount =0;
        boolean isAllWithdrawalLineEmpty=false;
        List <WebElement> transactionsRecord = Driver.get().findElements(By.xpath("//div[@id='filtered_transactions_for_account']//tbody//tr//td[4]"));
        System.out.println("Number of withdrawal line = " + transactionsRecord.size());
        for (int i=0 ; i< transactionsRecord.size() ; i++) {
            if (transactionsRecord.get(i).getText().isEmpty()) {
                notEmptycount++;
            }
        }
        if (notEmptycount==transactionsRecord.size()){
            isAllWithdrawalLineEmpty=true;
        }
        return isAllWithdrawalLineEmpty;
    }

    public boolean isThereAnyWithdrawalLine() {
        //This method returns true even if one column under the Withdrawal is not empty
        boolean isAnyLine=false;
        List <WebElement> transactionsRecord = Driver.get().findElements(By.xpath("//div[@id='filtered_transactions_for_account']//tbody//tr//td[4]"));
        System.out.println("Number of withdrawal line = " + transactionsRecord.size());
        for (int i=0 ; i< transactionsRecord.size() ; i++) {
            if (!(transactionsRecord.get(i).getText().isEmpty())) {
                isAnyLine = true;
                break;
            }
        }
        return isAnyLine;
    }

    public void selectType(String type){
        Select select= new Select(typeDropbox);
        select.selectByVisibleText(type);
    }

    public boolean isDescriptionContain(String str) {
        // This method return 'true' if there is/are str in all columns under the Description
        int notContainCount =0;
        boolean isContain=false;
        List <WebElement> transactionsRecord = Driver.get().findElements(By.xpath("//div[@id='filtered_transactions_" +
                                                                                    "for_account']//tbody//tr//td[2]"));
        System.out.println("transactionsRecord.size() = " + transactionsRecord.size());
        for (int i=0 ; i< transactionsRecord.size() ; i++) {
            if (transactionsRecord.get(i).getText().contains(str)) {
                notContainCount++;
            }
        }
        if (notContainCount !=0){
            isContain=true;
        }
        System.out.println("isContain = " + isContain);
        return isContain;
    }

}
