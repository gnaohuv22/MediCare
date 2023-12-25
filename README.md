# CHANGE LOG

##### This document started writing from 4-10-2023 to take note about changes made in the project branch.

### CHANGELOG 16/11 v1.0
+ Finish the project
+ Add a function to add new `Bệnh án` associate with each meeting
+ Fix bugs, enhance stability.

### CHANGELOG 10-11 (v0.9.1)
+ This project changes too many times that I didn't record it.
+ Fix some bugs about the filters from Review and Appointment History page, to make sure that they works perfectly.
+ Fix the header display for associated role, add a filter to slow down the page.

### CHANGELOG 09-10

##### Code changes:
+ Added News viewing screen (view content, view sidebar - in a basic view)
+ Added script for new attributes from `Doctor` and `News`, also `NewsCategory`

##### Database changes:
+ Added `isDelete`, `gender`, `birthDate` for `Doctor`, `symptom` for `Appointment`. ANY OTHER CHANGES ARE NOT ACCEPTED.
+ Updated data for `News` and `NewsCategory`

### CHANGELOG 07-10

##### Database Structure:
+ Added `ownerId` to FamilyProfile
+ Also added a trigger to automatically create a new profile when add a new User object.
+ Alter some attributes' type from `time(7)` to `DATETIME`

##### Code changelog
+ Merged with Jack's code.
+ Minify CSS file. (more minify is recommended)
+ Deleted unnecessary JSP files which remain from conversion to JSP

### CHANGELOG 04-10

##### Database Structure:
+ Added TopLevelMenu and SubLevelMenu tables for satisfying teacher requirements. Now it's applying into header (navbar) and footer (everything except 'Bác sĩ tiêu biểu'). Those information not change too frequently.
+ Removed Contacts, UsefulLinks, SocialNetworks, NavigationItem and more. (Replaced with tables above).

##### Code changelog
- Added associated classes with added tables (Object, DAO).
- Updated `user-header.jsp` and `user-footer.jsp` for displaying using TopLevelMenu and SubLevelMenu tables/classes.
- Added missing `UserServiceDetailServlet.java`
- Added pagination for `user-search-result.jsp` and `user-list-all-doctor.jsp`

