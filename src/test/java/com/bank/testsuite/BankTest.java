package com.bank.testsuite;

import com.bank.pages.*;
import com.bank.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BankTest extends BaseTest {
    HomePage homePage = new HomePage();
    BankManagerLoginPage bankManagerLoginPage = new BankManagerLoginPage();
    AddCustomerPage addCustomerPage = new AddCustomerPage();
    OpenAccountPage openAccountPage = new OpenAccountPage();
    CustomerLoginPage customerLoginPage = new CustomerLoginPage();
    AccountPage accountPage = new AccountPage();

    @Test
    public void bankManagerShouldAddCustomerSuccessfully() throws InterruptedException {
        // click On "Bank Manager Login" Tab
        homePage.clickOnBankManagerLoginButton();
        //	click On "Add Customer" Tab
        bankManagerLoginPage.clickOnAddCustomerButton();
        //	enter FirstName
        Thread.sleep(1000);
        addCustomerPage.sendFirstName("Jaynika");
        //	enter LastName
        Thread.sleep(1000);
        addCustomerPage.sendLastName("Patel");
        //	enter PostCode
        Thread.sleep(1000);
        addCustomerPage.sendPostCode("HA53SL");
        //	click On "Add Customer" Button
        addCustomerPage.clickAddCustomerButton();
        //	popup display & verify message "Customer added successfully"
        Assert.assertTrue(addCustomerPage.getTextFromCustomerAddedPopUp().contains("Customer added successfully"), "customer added successfully message not displayed");
        //	click on "ok" button on popup.
        addCustomerPage.clickOKOnAlert();
    }

    @Test
    public void bankManagerShouldOpenAccountSuccessfully() {
        // click On "Bank Manager Login" Tab
        homePage.clickOnBankManagerLoginButton();
        // click On "Open Account" Tab
        bankManagerLoginPage.clickOpenAccountButton();
        // Search customer that created in first test
        openAccountPage.selectCustomerNameByVisibleText("Harry Potter");
        // Select currency "Pound"
        openAccountPage.selectCurrencyFromDropDown("Pound");
        // click on "process" button
        openAccountPage.clickOnProcessButton();
        // popup displayed & verify message "Account created successfully"
        Assert.assertTrue(openAccountPage.getTextFromAccountCreatedSuccessfullyPopup().contains("Account created successfully"), "Popup not displayed");
        // click on "ok" button on popup.
        openAccountPage.clickOKOnPopup();
    }

    @Test
    public void customerShouldLoginAndLogoutSuccessfully() {
        // click on "Customer Login" Tab
        homePage.clickCustomerLoginButton();
        // search customer that you created.
        customerLoginPage.selectYourName("Harry Potter");
        // click on "Login" Button
        customerLoginPage.clickOnLogin();
        // verify "Logout" Tab displayed.
        Assert.assertEquals(accountPage.getLogoutText(), "Logout", "Logout text not displayed");
        // click on "Logout"
        accountPage.clickLogout();
        // verify "Your Name" text displayed.
        Assert.assertTrue(customerLoginPage.getYourNameText().contains("Your Name"), "Your Name not displayed");
    }

    @Test
    public void customerShouldDepositMoneySuccessfully() {
        // click on "Customer Login" Tab
        homePage.clickCustomerLoginButton();
        // search customer that you created.
        customerLoginPage.selectYourName("Harry Potter");
        // click on "Login" Button
        customerLoginPage.clickOnLogin();
        // click on "Deposit" Tab
        accountPage.clickOnDeposit();
        // Enter amount 100
        accountPage.sendAmountToInputBox("100");
        // click on "Deposit" Button
        accountPage.clickOnDepositAfterAmount();
        // verify message "Deposit Successful"
        Assert.assertEquals(accountPage.getTextFromDepositMessage(), "Deposit Successful", "Deposit successful message not displayed");
    }

    @Test
    public void customerShouldWithdrawMoneySuccessfully() {
        // click on "Customer Login" Tab
        homePage.clickCustomerLoginButton();
        // search customer that you created.
        customerLoginPage.selectYourName("Harry Potter");
        // click on "Login" Button
        customerLoginPage.clickOnLogin();
        // click on "Withdrawal" Tab
        accountPage.clickOnDeposit();
        // Enter amount 100
        accountPage.sendAmountToInputBox("100");
        // click on "Deposit" Button
        accountPage.clickOnDepositAfterAmount();
        // click on transactions
        accountPage.clickOnTransactions();
        // click on Back button
        accountPage.clickOnBack();
        // click on withdrawal button
        accountPage.clickOnWithdrawalButton();
        // Enter amount 50
        accountPage.sendAmountToInputBox("50");
        // click on "Withdraw" Button
        accountPage.clickOnWithdrawButton();
        // verify message "Transaction Successful"
        Assert.assertEquals(accountPage.getWithdrawMessage(), "Transaction successful", "Transaction Successful message not displayed");
    }
}
