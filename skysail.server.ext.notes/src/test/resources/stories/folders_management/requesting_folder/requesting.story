Meta:
@tag domain:tobedone

Narrative:

In order to organize notes, 
As a user 
I want to assign them to folders which I need to be able to request and open.

Scenario: Requesting folder via browser

Given the user has created a folder
When the user submits a get request for the folders id
Then the folder is returned


