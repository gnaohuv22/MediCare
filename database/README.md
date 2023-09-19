# MEDICAL SERVICE PROJECT DATABASE NOTE: #

=================================================================

<<<<<<< HEAD
=======
=================================================================

### v3.2.2: ###

- Added "name" in AccessLog table
- Remove "accessTime" in AccessLog table
- Remove "ipAccess" in AccessLog table

=================================================================

### v3.2.1: ###

- Added "password" in Doctor table

=================================================================

>>>>>>> 2f681407c42dc67e6e2ccf4341da0d17d11e8266
### v3.2 MAJOR UPDATE!!! ###

#### ADDED: ####
- Add table Employee (id, email, password, name, birthDate, gender, address, workplace, phone, ethnic, roleId, createAt)
- Add table ServiceTag (id, name, description, departmentId) which stands for services that doctor can do treatment.
- Add table DoctorService(doctorId, serviceId) that represents all the services that doctor can do treatment.
- Add table Department that represents the departments provided in the medical system.
- Add an attribute named 'status' for Doctor table, which will decide this Specialist is temporary working or official employee for the organization.
- Add table 'Branch' for the hospitals branch.
- Add table 'Province' representing the provinces in Vietnam.
- Add attribute 'provinceId' for the table 'Employee', 'User'.
- Add 'appointmentId' attribute to the Reviews table, a complete Appointment should left a Review.
- Add AccessLog table to tracking the accesses of web users.

#### CHANGE LOGS: ####
- Table AdminRole now changes to EmployeeRole, now represents the Role of Employee in the system (which may include CEO, Content Creator, Manager, or Administrator...)
- Table Category now changes to NewsCategory
- Table Plan now changes to ServicePlan
- CancelledRequest now has 'status' attribute.
- Table 'Specialist' now becomes 'Doctor'
- All 'Specialist'-related now become 'Doctor'-related 

#### REMOVED: ####
- Remove 'Speciality' table, because 'ServiceTag' is overwritten its job. Also remove its associated attributes from other tables.
- Remove 'accepted' and 'completed' in Appointments table. 'status' will now replace them.
- Remove 'ServicePlan and PlanSpecialist' tables from the database.
- Remove 'SpecialistSchedule' tables from the database, replace them with 'DoctorWorkingDay'

###### Special thanks to binhntt, thaitn and ynt4 for contributions to this update. #######

=================================================================

### v3.1.2: ###

- Remove 'certId' from Specialist

=================================================================


### v3.1.1: ###

- Change the rest of '-Id' to 'id' for higher synchronization 
- Change associated foreign keys

=================================================================


### v3.1: ###

- Add 'phone' attribute to Specialist (thanks to Thu)
- Remove 'address' attribute from Specialist
- 'description' in Plan and SpecialistSchedule now TEXT instead of VARCHAR(255)
- All -email attributes now become -Id (thanks to Jack97)
- Table 'SalaryCert' removed (thanks to Thai Trinh)
- Add 'wage' attribute into Certificate and AcademicRank table (thanks to Thai Trinh)
- Table CertificateSpecialist (certId, specialistId) added to database (thanks to Thai Trinh)
- Table 'Role' now become 'AdminRole' for easier understanding

=================================================================

### v3: ###

- Analyze and rearrange the database tables
- Remove HealthcareSpecialist
- Replace Admin (username, password) with another Admin with more attributes (email, password, roleId, displayName)
- Replace primary key of human object tables from 'username' to 'email'
- Change every attributes related with ID with type int to 'id'
- Adjust User table's attributes. Now contains (email, password, name, birthDate, gender, address, identity, medicalId, ethnic, phone, profilePicture, createAt)
- 'HealthcareSpecialist' now is 'Specialist' (id, email, displayName, specialityId, address, certId, ARId, CVId, salary, workplace, profilePicture, duty)
- 'specialistId' change to 'specialistEmail'
- 'userId' change to 'userEmail'
- Table 'Plan change to 'TreatmentPlan'
- Add table Category (id, name)
- Add table News (id, title, content, author, categoryId, createAt, lastModified)
- Add table CurriculumVitae (id, introduce, education, workHistory, startYear) work as specialist's CV in basic form.
- Add table Role (id, role) which decides the role of Admin side

=================================================================

### v2.1.1: ###

- Add table PlanSpecialist (planId, specialistId)

=================================================================

### v2.1: ###

- Table 'Employee' >> 'HealthcareSpecialist'
- doctorId or employeeId >> specialistId
- Update associate foreign keys
- Add table 'SpecialistSchedule' (scheduleId, specialistId, busyDate, description)
- Table 'cancelledRequest' >> 'CancelledRequest'
- Table 'plan' >> 'Plan'
- Table 'appointments' >> 'Appointments'
- Table 'reviews' >> 'Reviews'
- Table 'billingHistory' >> 'BillingHistory'
- Table 'salaryBaseOnCert' >> 'SalaryBaseOnCert'

=================================================================

### v2.0.1: ###

- Add 'password' attribute to 'Employee' table.


=================================================================

### v2: ###

- A whole new database is created. Which contain:
+ User (username, password, firstName, lastName, birthDate, gender, email, phone, profilePicture, createdAt)
+ Admin (username, password)
+ Certificate (certId, name)
+ AcademicRank (ARId, name)
+ Employee (employeeId, username, firstName, lastName, email, phone, certId, ARId, speciality, salary, profilePicture)
+ plan (planId, name, description, price)
+ appointments (appointmentId, userId, doctorId, plannedAt, accepted, completed)
+ reviews (reviewId, userId, doctorId, rating, reviewContent, createdAt)
+ billingHistory (billingId, appointmentId, totalCash, userId, doctorId, createdAt)
+ salaryBaseOnCert (certId, name, associateSalary)
+ cancelledRequest (cancelId, appointmentId, totalRefund, userId, doctorId, cancelledAt)
- The old database which judged as not realistic, lack of fundamental objects is being removed.

=================================================================

### v1: ###

- The first database originated.
