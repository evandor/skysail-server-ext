Meta:
@tag domain:validations

Narrative:

In order to organize notes, 
As a user 
I want to assign them to folders which I need to create.

Scenario: Providing foldername with leading and trailing whitespace

Given the user wants to add a new Folder
When the user submits the form with the foldername ' foldername '
Then the new Folder has the name 'foldername'

Scenario: Providing empty foldername

Given the user wants to add a new Folder
When the user submits the form with the foldername 
Then the request is not successful

