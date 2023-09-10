MEDICAL SERVICE PROJECT DATABASE NOTE:

=================================================================

v2.1.1:

- Add table PlanSpecialist (planId, specialistId)

=================================================================

v2.1:

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

v2.0.1:

- Add 'password' attribute to 'Employee' table.


=================================================================

v2:

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

v1:

- The first database originated.