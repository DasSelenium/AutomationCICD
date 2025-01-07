@tag
  Feature: Purchase the order from ecommerce website

    Background:
      Given I landed on Ecommerce Page

    @Regression
    Scenario Outline:Positive Test of submitting the order
      Given Logged in with usernname <name> and Password <password>
      When I add the product <productname> to Cart
      And checkout <productname> and submit the order
      Then "THANKYOU FOR THE ORDER." massage is displayed on confirmation page

      Examples:
        | name            | password | productname     |
        | Mithi@gmail.com | Miland@6 | ADIDAS ORIGINAL |
