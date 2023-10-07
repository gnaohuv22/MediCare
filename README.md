# CHANGE LOG

##### This document started writing from 4-10-2023 to take note about changes made in the project branch.

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

