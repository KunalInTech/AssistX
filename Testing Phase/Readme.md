# AssistX | Testing Phase 🛡️

This repository contains the comprehensive testing documentation, bug tracking, and verification results for the **AssistX** AI-based customer support automation platform.

## 📋 Table of Contents
* [Objective](#objective)
* [Testing Scope](#scope)
* [Types of Testing](#types-of-testing)
* [Testing Tools](#testing-tools)
* [Bug Tracking & Issues](#bug-tracking--issues)
* [Verification & Evidence](#verification--evidence)
* [Exit Criteria](#exit-criteria)

---

## 🎯 Objective
The primary goal of this phase was to ensure the reliability, security, and performance of the AssistX application.
* **Functional Verification:** Ensuring all features (Registration, OAuth, AI Classification) work as intended.
* **Data Integrity:** Confirming successful CRUD operations and SQL database consistency.
* **Security:** Validating JWT token generation, role-based access control, and password encryption.
* **User Experience:** Ensuring frontend logic correctly handles both success states and error exceptions.

## 🔍 Scope

| Module | Features Tested | Priority |
| :--- | :--- | :--- |
| **Authentication** | Registration, Login, OAuth, JWT, Logout | **HIGH** |
| **Security** | JWT Validation, Role-based Access, BCrypt Encryption | **HIGH** |
| **Database Layer** | User & Query CRUD, Data Validation | **HIGH** |
| **Query Management** | Submission, AI Classification, Status Updates | **HIGH** |
| **User Management** | Profile & Credential Updates | **MEDIUM** |
| **AI Service** | Category Prediction accuracy | **MEDIUM** |

---

## ⚙️ Types of Testing
* **Unit Testing:** Individual service layer methods (AuthService, QueryService) using **JUnit 5** and **Mockito**.
* **Integration Testing:** Controller-to-Repository flow and database connectivity via **Spring Boot Test**.
* **API Testing:** Manual and automated validation of REST endpoints using **Postman** and **Talend API Tester**.
* **Security Testing:** Robust verification of SQL injection prevention and JWT integrity.

---

## 🛠 Testing Tools
* **Frameworks:** JUnit 5, Mockito, Spring Boot Test
* **Database:** H2 (In-memory testing), MySQL Workbench
* **API Tools:** Postman, Talend API Tester
* **Build Tool:** Maven (`mvn clean install`)

---

## 🐞 Bug Tracking & Issues (Defect Log)

During the testing phase, several critical and medium-severity bugs were identified and documented:

- [AX-001] User Profile Loading Failure

- [AX-002] Raise Query API 404 Error

- [AX-003] Duplicate Email Error Handling

---

## 📸 Verification & Evidence

Testing logs confirm the following successful operations:
* **OAuth Success:** Logs indicate `OAUTH SUCCESS HANDLER` triggered correctly for users.
* **JWT Generation:** Successful token generation with logged lengths (e.g., Length 177).
* **Hibernate Operations:** SQL traces show active user selection and verification queries.
* **Admin Verification:** Secure admin access key validation is functional.

> [!TIP]
> Refer to `Testcases.pdf` for full console logs and screenshots of the authentication flow and database selects.

---

## Exit Criteria
Testing is considered complete when:
- All planned test cases executed.
- Minimum **80% code coverage** achieved.
- All **High Severity** bugs are documented or resolved.
- Known issues and edge cases are recorded in the defect log.
- API documentation and Release Notes are prepared.

---
*Developed as part of Assignment 9 - Quality Assurance Phase.*
