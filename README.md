# Added removing User by email 
## Also:
- Rewrited Repository mechanism of user storage. Now it's using Map instead of array[] (so deleting doesn't mess with order of users in repository + fast access/deleting)
- Added exception handling
- Added validation of email & password in service layer
- Added password salting
  
