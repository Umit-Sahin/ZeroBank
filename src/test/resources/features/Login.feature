@Regression @Login
Feature: Only authorized users should be able to login to the application

  Background:
    Given the user accesses the Log in page

  Scenario Outline: Authorized users should be able to login
    When the user logged in as valid "<UserName>" "<Password>"
    Then  Account summary page should be displayed
    Examples:
      | UserName | Password |
      | username | password |


  Scenario Outline: Wrong username or wrong password should not be able to login <user>
    When the user logged in as invalid "<UserName>" "<Password>"
    Then error message "Login and/or password are wrong." should be displayed
    Examples:
      | UserName  | Password |
      |           |          |
      |           | password |
      | usermusur | password |
