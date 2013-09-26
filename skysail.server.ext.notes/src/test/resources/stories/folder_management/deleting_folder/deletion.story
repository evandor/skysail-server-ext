Meta:
@tag domain:tobedone

Narrative:

In order to organize notes, 
As a user 
I want to assign them to folders which I need to be able to delete.

Scenario: Deleting folder via browser

Given the user Izzy is logged in
When the user wants to delete his existing folder foldername
And the user submits a delete request for the folders id
Then the folder is deleted


