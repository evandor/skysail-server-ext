Meta:
@tag domain:security

Narrative:

In order to organize notes, 
As a user 
I want to assign them to folders which I need to change.

Scenario: Providing foldername containing javascript for update

Given the user Izzy is logged in
When the user submits the form with the foldername  xss<script>alert("hi")</script> 
Then the new folder should have the name xss
