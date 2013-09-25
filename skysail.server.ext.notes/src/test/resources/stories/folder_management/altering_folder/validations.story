Meta:
@tag domain:validations

Narrative:

In order to organize notes, 
As a user 
I want to assign them to folders which I need to be able to change.

Scenario: Providing foldername with leading and trailing whitespace

Given the testuser Izzy wants to change a folder
When the user opens the existing folder foldername 
And the user submits the form with the foldername  foldername2 
Then the new folder should have the name foldername2

!--Scenario: Providing empty foldername
!--Given the testuser Linus wants to change a folder
!--When the user submits the form with the foldername 
!--Then the request is not successful

