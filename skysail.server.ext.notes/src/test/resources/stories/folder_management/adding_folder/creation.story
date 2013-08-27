Meta:

Narrative:

In order to organize notes, 
As a user 
I want to assign them to folders which I need to create.

Scenario: Posting valid new Folder with browser
Meta:
@tags domain:a domain, iteration: iteration 1

Given the testuser Steve wants to add a new Folder
When the user submits the form with the foldername <input>
Then the request is successful
And the new folder should have the name <foldername>

Examples:
|input|foldername|
|myNewFolder|myNewFolder|
|folder with blanks|folder with blanks|
|αβγ|αβγ|
|как си|как си|

Scenario: Posting valid new Folder with browser, retrieving json

Given the user wants to add a new Folder via ajax
When the user submits an ajax request with the foldername aFolder
Then the request is successful
And the request has the media type json
