Meta:
@tag domain:security

Narrative:

In order to organize notes, 
As a user 
I want to assign them to folders which I need to create.

Scenario: Providing foldername containing javascript

Given the user Izzy is logged in
When the user submits the form with the foldername ' xss<script>alert("hi")</script> '
Then the request is not successful
