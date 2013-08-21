Meta:
@tag domain:tobedone

Narrative:

In order to organize notes, 
As a user 
I want to assign to be able to delete them.

Scenario: Deleting note via browser

Given the user has created a note
And the user wants to delete this note
When the user submits a delete request for the notes id
Then the note is deleted


