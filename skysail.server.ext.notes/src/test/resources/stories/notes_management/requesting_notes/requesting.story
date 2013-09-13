Meta:
@tag domain:tobedone

Narrative:

In order to organize notes, 
As a user 
I want to be able to search and find them.

Scenario: Requesting note via browser

Given the user has created a note in the folder afolder
When the user submits a get request with the search parameter
Then the note is returned


