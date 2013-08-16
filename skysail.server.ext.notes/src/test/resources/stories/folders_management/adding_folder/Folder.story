Meta:

Narrative:
Handling folders is a basic feature to organize notes. There are some
basic requirements when adding a new folder which are described in this story.

Scenario: Posting new Folder with browser

Given the user wants to add a new Folder
When the user submits the form with the foldername 'foldername'
Then the request is successful

Scenario: FolderName is trimmed

Given the user wants to add a new Folder
When the user submits the form with the foldername ' foldername '
Then the new Folder has the name 'foldername'
