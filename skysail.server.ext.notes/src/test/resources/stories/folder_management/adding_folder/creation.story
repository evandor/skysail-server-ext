Meta:

Narrative:

In order to organize notes, 
As a user 
I want to assign them to folders which I need to create.

Scenario: Posting valid new Folder with browser
Meta:
@tags domain:a domain, iteration: iteration 1

Given the testuser Linus wants to add a new Folder
When the user submits the form with the foldername <input>
Then the request is successful
And the new folder should have the name <foldername>

Examples:
|input|foldername|
|myNewFolder|myNewFolder|
