Feature: Submit Order 

Background:
Given I landed on the URL

@Regression
Scenario Outline: add an item to cart and submit order
Given Logged in with <username> and <password>
When I added <productname> to cart
And check the <productname> in cart and submit order
Then display success message "Thankyou for the order." on order confirmation
Examples:
|username|password|productname|
|qualityassurranceengineer@gmail.com|Qualityassurrance@123|ZARA COAT 3|