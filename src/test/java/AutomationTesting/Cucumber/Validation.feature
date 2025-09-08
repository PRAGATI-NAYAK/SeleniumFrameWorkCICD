Feature: Create validation scenarios 

@ErrorValidation
Scenario Outline: error validation scenario
Given I landed on the URL 
When Logged in with <username> and <password>
Then "Incorrect email or password." message should display
Examples:
|username|password|
|qualityassurranceengineer@gmail.com|Qualityassurrance@1234|