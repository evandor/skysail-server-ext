Meta:
@tag capability:a capability

Narrative:

In order to manage notes,
As a user 
I want to create a new note.

Scenario: Posting valid new Note with browser
Meta:
@tags domain:a domain, iteration: iteration 1

Given the user wants to add a new Note
When the user submits the form with the title <input>
Then the request is successful
And the new note should have the title <foldername>

Examples:
|input|foldername|
|myNewFolder|myNewFolder|
|folder with blanks|folder with blanks|
|αβγ|αβγ|
|как си|как си|

Scenario: Posting valid new Note with browser, retrieving json

Given the user wants to add a new note via ajax
When the user submits an ajax request with the title 'foldername'
Then the request is successful
And the request has the media type json
