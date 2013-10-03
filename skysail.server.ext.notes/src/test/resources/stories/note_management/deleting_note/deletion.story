Meta:
@tag domain:tobedone

Narrative:

In order to organize notes, 
As a user 
I want to assign to be able to delete them.

Scenario: Deleting note via browser

Given the user Izzy is logged in
When the user wants to delete his existing note noteTitle
And the user submits a delete request for the notes id
Then the note is deleted


