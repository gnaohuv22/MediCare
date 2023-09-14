# MEDICAL SERVICE PROJECT DATABASE NOTE: #

=================================================================

<<<<<<< HEAD
### v3.1.1: ###

- Change the rest of '-Id' to 'id' for higher synchronization 
- Change associated foreign keys

=================================================================


=======
>>>>>>> origin/database
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
