# Overview

**Genesys API Automation** consists of suites of src and API method automation.

This framework designed for Genesys API automation with the combination of **Rest Assured**, **Java**, **POM ( Page Object Model)**, **TestNG**.


## Setup Instructions:
``` 
1.	Clone/Pull Genesys API automation project from GIT.
2.	Make sure you have installed the below softwares in your machine
        a) Install Java 11 from https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html as per your system configuration
        b) Install eclipse IDE (https://www.eclipse.org/downloads/packages/release/2023-09/r/eclipse-ide-java-developers)
        c) Launch Eclipse and install Maven and TestNG plugin's from Help ---> Eclipse marketplace
3.	Open Eclipse and import the cloned project as mentioned below
            - File -> Import -> Maven (Expand)-> Select Existing Maven Project and click Next 
            - In root Directory browse the project cloned folder.
            - Click finish and Wait till the project is successfully imported.
```
## Suite Execution Instructions:
``` 
Open the project 

    - Under Test_xml folder you will see the RunnerClassTestxml file for execution.
    - Open the xml file in the eclipse editor window.
    - Right click in the eclipse editor window -> Run As -> TestNG
    - Open Report from test-output -> emailable-report.html for Suite execution report. 
     
```
## Folder Structure:
```
- Maven Dependencies (It contains all installed software libraries)
- JRE System Libraries (It contains java libraries)
- test-output  (It contains suite-results)
- src/test/java (It contains all the reusable methods and test cases file)
    - Reusables ( It contains all the reusable methods needed for Runnerclass)
    - Tests ( It contains all the automated test scripts related to test case )

```