# Aconex
Selenium automation scripts for Aconex Web Application

# Create a new Mail and send to a recipient
Steps to automate:
1. Login to Aconcex Application.
2. Click on Mail Tab and then select the Create New Mail.
3. Search To recipient and selct the name
4. Enter required details(Subject etc..)
5. Send Mail.

# Upload a new document
Steps to automate:
1. Login to Aconex Application.
2. Click on Document tab and then select Upload New Document.
3. Enter required details
4. Upload Document and a number is genrated.
5. Search the same document number to verify if its present in the list returned from search.

# Excute steps
mvn test 

# Reports
Extent report feature added to generate execution report.
Output directory would contain a file **`Aconex-TestAutomation-Report.html`**
