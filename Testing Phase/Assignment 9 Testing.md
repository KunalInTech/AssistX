# Assignment 9: Test Plan & Testing
 
## TEST PLAN & TEST CASES 
 
## 1. OBJECTIVE OF TESTING
 
### Primary Objectives
1. **Verify Functionality:** Ensure all features work as per requirements
2. **Validate Data Integrity:** Confirm data is correctly stored and retrieved
3. **Security Testing:** Verify authentication, authorization, and data protection
4. **Performance Testing:** Ensure system handles expected load
5. **User Experience:** Validate UI/UX meets usability standards
### Specific Goals
- Test user registration and authentication (including OAuth)
- Test query submission and AI classification
- Test admin dashboard functionality
- Verify JWT token generation and validation
- Test database operations (CRUD)
- Validate API endpoints
- Test error handling and edge cases
---
 
## 2. SCOPE
 
### Modules/Features to be Tested
 
#### **In Scope:**
 
| Module | Features | Priority |
|--------|----------|----------|
| **Authentication Module** | Registration, Login, OAuth, JWT, Logout | HIGH |
| **Query Management** | Create query, View queries, AI classification, Status update | HIGH |
| **User Management** | Profile update, Email change, Username change, Password change | MEDIUM |
| **Admin Dashboard** | View all queries, User management, Statistics | MEDIUM |
| **Security** | JWT validation, Role-based access, Password encryption | HIGH |
| **Database Layer** | User CRUD, Query CRUD, Data validation | HIGH |
| **AI Service** | Query classification, Category prediction | MEDIUM |
 
#### **Out of Scope:**
- Performance/Load testing (requires separate infrastructure/ manual testing)
- Third-party service testing (Google OAuth servers)
- Browser compatibility testing (requires manual testing)
---
 
## 3. TYPES OF TESTING
 
### 3.1 Unit Testing
**Purpose:** Test individual components in isolation  
**Coverage:**
- Service layer methods (AuthService, QueryService, AIService)
- Repository operations
- Utility functions (JWT generation, password encryption)
**Tools:** JUnit 5, Mockito
 
### 3.2 Integration Testing
**Purpose:** Test interaction between components  
**Coverage:**
- Controller → Service → Repository flow
- Database connectivity
- External API calls (AI classification service)
**Tools:** Spring Boot Test, @SpringBootTest
 
### 3.3 System Testing
**Purpose:** Test complete end-to-end workflows  
**Coverage:**
- Complete user registration flow
- Full query submission workflow
- Admin dashboard operations
- OAuth login integration
**Tools:** TestRestTemplate, Selenium (optional)
 
### 3.4 Security Testing
**Purpose:** Verify security measures  
**Coverage:**
- JWT token validation
- Password encryption (BCrypt)
- SQL injection prevention
 
### 3.5 API Testing
**Purpose:** Test REST API endpoints  
**Coverage:**
- All `/api/auth/**` endpoints
- All `/api/queries/**` endpoints
- All `/api/users/**` endpoints
**Tools:** Postman, TestRestTemplate
 
---
 
## 4. TESTING TOOLS
 
### Development Tools
| Tool | Purpose | Version |
|------|---------|---------|
| **JUnit 5** | Unit testing framework | 5.9.3 |
| **Mockito** | Mocking framework | 5.3.1 |
| **Spring Boot Test** | Integration testing | 3.2.0 |
| **H2 Database** | In-memory test database | 2.2.220 |
| **Talend API Tester** | REST API testing | Spring Boot |
| **MySQL Workbench** | Database testing | 8.0 |
 

---
 
## 5. ENTRY CRITERIA
 
### Prerequisites for Starting Testing
 
**Development Complete:**
- All features implemented
- Code committed to repository
- No compilation errors
- Test database configured
- Test data loaded
- Dependencies installed (`mvn clean install`)
- API documentation complete
- Database schema documented
- User stories available
- Test user accounts created
- Sample queries prepared
- Edge case data identified
- JUnit dependencies added
- Test profiles configured
- Mock services ready
---
 
## 6. EXIT CRITERIA
 
### Criteria for Completing Testing
 
**Test Execution:**
- All planned test cases executed
- Minimum 80% code coverage achieved
- All critical/high priority tests passed
- All critical bugs fixed
- High severity bugs <= 2
- Medium/Low bugs documented
- Test results documented
- Defect log maintained
- Test coverage report generated
- Test summary report approved
- Known issues documented
- Release notes prepared

---