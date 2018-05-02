# OneDriveProject

This project is written in java and will serve an example of implementing a Selenium test project with Selenium and Maven.

Prerequisites
--------------
1) Java/JDK 1.6 and above installed
2) Maven 

Dependencies
------------
1) Selenium-java 3.6.0
2) Testng 6.8
3) Extent Reports 2.41.2


Steps to run OneDriveproject
--------------------------------------
1) Create a folder Workspace in C:\\
2) Clone OneDriveProject from https:\\github.com\anushag26\OneDriveProject In C:\\Workspace\
3) Use Eclipe to import project
4) Run HomeTest.java as TestNG Test
5) Please provide UserName and Password in src/main/java/com/qa/config/config.properties

Maven 
-----
Maven is used for dependency jar files required to run the program.

Browser- Chrome
---------------

Page Object Design pattern
--------------------------
Page Object design pattern is used to have reusable WebElements/small helper methods separated from actual test classes and give the opportunity to have nice structured and easily readable tests.

TestNG
------
TestNG testing framework is used to write testcases with TestNG annotations.
Extent Reports
Used Extent Reporting for Reports

Reports
-------
In test-output folder
index.html
OneDriveTest_Extent.html

Data 
-----
Folder with the name Data is present inside project. It consists of files which are required for the project
1.	Contentfile
2.	Emptyfile
3.	ChomeDriver.exe

Description of Tests:
---------------------
Testcase 1: Upload_EmptyFile()
------------------------------
Logins to the OneDrive application with the user credentials and tries to upload an empty file.
Used Emptyfile.txt in the Data folder for Upload.
Validation is done to capture and verify the error message that is displayed

Testcase 2: Upload_ContentFile()
--------------------------------
Tries to upload a file with content.
Used Contentfile.txt in the Data folder for Upload.
Validates if the file is uploaded and checks for the uploaded message that is displayed.

Testcase 3: validate_MetaData()
-------------------------------
Selects a file and checks for the metadata of the file.
Validation is done to compare the metadata of the selected file with the source file.

Testcase 4: updateContent()
---------------------------
Selects a file from the documents folder edits it using the text editor and saves it.
Checks if file is getting updated with the Text Editor is getting saved.

Testcase 5: verify_download()
-----------------------------
Selects an updated file and -from version history downloads both the versions.
Verifies if the files are downloaded correctly and compares the contents and displays what is the difference in the contents
Verify dowloaded files under Downloads package

Testcase 6: verify_delete_file()
--------------------------------
Selects a file and deletes it from the Documents folder.
Verifies if the file is deleted by checking the message displayed after deleting.
	
