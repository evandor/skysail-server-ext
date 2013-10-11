Meta:
@tag domain:tobedone

Narrative:

In order to organize notes, 
As a user 
I want to assign them to folders which I need to be able to request and open.

Scenario: Requesting folder via browser

Given the user Izzy is logged in
When the user opens her existing folder foldername
Then the folder is returned

Scenario: Requesting own folder via browser

Given the folder izzysFolder was created by Izzy
And the user Izzy is logged in
When the user submits a GET request for the folders id
Then the request is successful

Scenario: Requesting others users folder via browser

Given the folder linusFolder was created by Linus
And the user Izzy is logged in
When the user submits a GET request for the folders id
Then the request is not successful


