# Selenium Automation Framework

A Selenium with Java project to create automation test cases for complicated search and filter scenarios on [Airbnb](https://www.airbnb.com/) website then generate test reports after each run and screen shots if any failure happens.

### Used Tools

* [Maven](https://maven.apache.org/) is used to build the project
* [Webdrivermanager](https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager) depency is added to handle the browsers without using any local jars
* [Extentreport](https://mvnrepository.com/artifact/com.relevantcodes/extentreports) depency is added for logging and reporting purposes 

## Running the tests

* To run the script just import the project in Eclipse and run test cases from TestClass.java

## Features
* Test report will be generated after each run in the project's dircetory with the name TestReport.html
* Screenshot will be captured if any failure happens and added to {Project directory}/screenshots and then will be embedded into the generated test report

## Authors

* **Mahmoud Bindary**  (https://github.com/mahmoudbindary)
