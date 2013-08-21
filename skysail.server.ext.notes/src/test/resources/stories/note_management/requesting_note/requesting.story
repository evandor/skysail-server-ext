Meta:
@tag domain:tobedone

Narrative:

In order to organize notes, 
As a user 
I want to be able to request and open them.

Scenario: Requesting note via browser

Given the user has created a note
When the user submits a get request for the notes id
Then the note is returned


