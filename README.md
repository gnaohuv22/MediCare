# CHANGE LOG

##### This document started writing from 4-10-2023 to take note about changes made in the project branch.

### CHANGELOG 04-10

##### - Database Structure:
+ Added TopLevelMenu and SubLevelMenu tables for satisfying teacher requirements. Now it's applying into header (navbar) and footer (everything except 'Bác sĩ tiêu biểu'). Those information not change too frequently.
+ Removed Contacts, UsefulLinks, SocialNetworks, NavigationItem and more. (Replaced with tables above).

##### Code changelog
- Added associated classes with added tables (Object, DAO).
- Updated `user-header.jsp` and `user-footer.jsp` for displaying using TopLevelMenu and SubLevelMenu tables/classes.
- Added missing `UserServiceDetailServlet.java`
- Added pagination for `user-search-result.jsp` and `user-list-all-doctor.jsp`

