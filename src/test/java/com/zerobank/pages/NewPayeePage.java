package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NewPayeePage {

    public NewPayeePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "#pay_bills_tab>a")
    public WebElement payBills;

    @FindBy(xpath = "//a[@href=\"#ui-tabs-1\"]")
    public WebElement paySavedPayeeLink;

    @FindBy(xpath = "//a[@href=\"#ui-tabs-2\"]")
    public WebElement addNewPayeeLink;

    @FindBy(xpath = "//a[@href=\"#ui-tabs-3\"]")
    public WebElement purchaseForeignCurrencyLink;

    @FindBy(id = "np_new_payee_name")
    public WebElement payeeNameInputbox;

    @FindBy(id = "np_new_payee_address")
    public WebElement payeeAddressInputbox;

    @FindBy(id = "np_new_payee_account")
    public WebElement payeeAccountInputbox;

    @FindBy(id = "np_new_payee_details")
    public WebElement payeeDetailsInputbox;

    @FindBy(id = "add_new_payee")
    public WebElement addButton;

    @FindBy(id = "pc_currency")
    public WebElement currencyDropbox;

    @FindBy(id = "pc_amount")
    public WebElement amountInputbox;

    @FindBy(id = "pc_inDollars_true")
    public WebElement usDollarRadioButton;

    @FindBy(id = "pc_inDollars_false")
    public WebElement selectCurrencyRadioButton;

    @FindBy(id = "pc_calculate_costs")
    public WebElement calculateCostButton;

    @FindBy(id = "purchase_cash")
    public WebElement purchaseButton;

    @FindBy(id = "alert_content")
    public WebElement alertContent;

    @FindBy (xpath = "//select[@id='pc_currency']//option")
    public List<WebElement> currencyOptions;


}
