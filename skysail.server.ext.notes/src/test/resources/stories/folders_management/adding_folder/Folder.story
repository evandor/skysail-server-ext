Scenario: Posting new Folder with browser

Given the user wants to add a new Folder
When the user submits the form with the foldername 'foldername'
Then the request is successful
