Meta:
@tag domain:tobedone

Narrative:

In order to organize notes, 
As a user 
I want to assign them to folders which I need to be able to delete.

Scenario: Deleting folder via browser

Given the user has created a folder
And the user wants to delete this folder
When the user submits a delete request for the folders id
Then the folder is deleted


