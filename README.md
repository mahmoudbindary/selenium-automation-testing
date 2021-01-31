# selenium-automation-testing

This is a Selenium testing framework using Java and TestNG

The goal is to test Airbnb website for real time search and filter scenarios

Maven is used to build the project

Webdrivermanager depency is added to handle the browsers without using any local jars

Extentreport depency is added for log and reporting purposes 

To run the script just import the project in Eclipse and run test cases from TestClass.java

NOTE: with each run of the script, a test report is generated in the project's dircetory with the name TestReport.html

If there is a failure in any test case, a screenshot will be captured and added to {Project directory}/screenshots and then be embedded into the generated test report
