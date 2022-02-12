package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PayBillPage {

    public PayBillPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(id = "sp_amount")
    public WebElement payBillAmountInputbox;

    @FindBy(id = "sp_date")
    public WebElement payBillDateInputbox;

    @FindBy(id = "pay_saved_payees")
    public WebElement payBillPayButton;

    @FindBy (xpath = "//div[@id='alert_content']")
    public WebElement successfullyMessage;

    @FindBy(id = "alert_content")
    public WebElement fillOutMessage;

}
