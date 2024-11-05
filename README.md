# Added removing User by email 
## Also:
- Rewrited Repository mechanism of user storage. Now it's using Map instead of array[] (so deleting doesn't mess with order of users in repository + fast access/deleting)
- Added exception handling
- Added validation of email & password in service layer
- Added password salting
- Added Spring style repositories via Inheritance, Abstract classes and Interfaces, Generics

## ToDo
- Catch User collisions (when they have same password || email)
- Implement AuthService
- Implement NotificationService
- Implement Application Context as Collection of Instances

## BugFixes
- Removed public access modifier in interface method
- Getter/Setter moved up as code conventions says.

## Ideas
- Implement Generic StOut Stream class (for managing what and when is send to output)
- Rewrite all find methods in repositories to Optional<?> for better handling null result
