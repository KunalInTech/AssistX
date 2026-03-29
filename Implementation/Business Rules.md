# Assignment 7 - Question 2: Business Rules, Validation & Data Transformation

---

## A. BUSINESS RULES IMPLEMENTATION 

### Definition
Business rules are specific conditions and logic that govern how the application operates based on business requirements.

---

### 1. User Management Business Rules

#### Rule 1.1: Username Requirements

**Why:** Ensures unique user identification, prevents confusion, database compatibility.

---

#### Rule 1.2: Email Validation

**Why:** Prevents fake accounts, ensures contactability, maintains data quality.

---

#### Rule 1.3: Password Security

**Why:** Security compliance (GDPR), admin cannot see passwords, prevents brute-force attacks.

---

### 2. Query Processing Business Rules

#### Rule 2.1: Query Submission Limits

**Why:** Prevents abuse, ensures quality, fair usage, protects resources.

---

#### Rule 2.2: AI Classification Rules

**Why:** Enables automated routing, consistent categorization, reduces manual work.

---

#### Rule 2.3: Priority Calculation

**Why:** Ensures critical issues get immediate attention, meets SLA requirements.

---

#### Rule 2.4: Escalation Rules

**Why:** Protects revenue/security, ensures specialist handling, meets SLA.

---

### Business Rules Summary

| Module | Rule | Purpose |
|--------|------|---------|
| **User Management** | Username unique, 3-50 chars | Data quality, user identification |
| | Email valid format, unique | Contactability, prevent fake accounts |
| | Password BCrypt encrypted | Security, admin cannot see passwords |
| **Query Processing** | Max 50 queries/day | Fair usage, prevent abuse |
| | Query 10-2000 characters | Quality assurance |
| | Spam detection | System protection |
| **AI Classification** | Keyword-based categorization | Automated routing |
| | 4 priority levels | Workload balancing |
| **Escalation** | CRITICAL/HIGH auto-escalate | SLA compliance |
| | BILLING/ACCOUNT sensitive | Revenue/security protection |


---

## B. VALIDATION LOGIC 

### Definition
Validation ensures data entering the system is correct, consistent, and properly formatted before processing.

---

### Validation Architecture (3 Layers)

```
┌─────────────────────────────────────┐
│   Layer 1: Frontend (HTML5 + JS)    │
│   Immediate feedback, no server     │
└──────────────┬──────────────────────┘
               ↓
┌─────────────────────────────────────┐
│   Layer 2: Entity (JPA Annotations) │
│   Automatic validation by Spring    │
└──────────────┬──────────────────────┘
               ↓
┌─────────────────────────────────────┐
│   Layer 3: Service (Business Logic) │
│   Custom rules, security, database  │
└─────────────────────────────────────┘
```

---

### Layer 1: Frontend Validation (HTML5)


**Benefits:** Immediate feedback, reduced server load, better UX.

---

### Layer 2: Entity-Level Validation (JPA)


**Benefits:** Declarative, automatic validation, clear error messages.

---

### Layer 3: Service-Level Validation (Business Logic)

---

### Query Validation

---

### Validation Summary

| Field | Validations Applied | Error Examples |
|-------|---------------------|----------------|
| **Username** | Length (3-50), format, uniqueness, reserved words | "Username too short", "Username already taken" |
| **Email** | Format, uniqueness, disposable check | "Invalid email", "Email already registered" |
| **Password** | Length (min 6), common password check, encryption | "Password too common", "Password too short" |
| **Phone** | Format (10 digits), no repetition | "Phone must be 10 digits", "Invalid format" |
| **Query Text** | Length (10-2000), spam check, duplicate check | "Query too short", "Contains spam" |
 
**Validation Layers:** 3 (Frontend, Entity, Service)

---

## C. DATA TRANSFORMATION (10 Marks)

### Definition
Data transformation converts data between different formats to ensure compatibility across application layers.

---

### Transformation Flow

```
DATABASE (Raw Data)
    ↓
ENTITY (Java Objects)
    ↓
DTO (Data Transfer Objects)
    ↓
PRESENTATION (HTML/JSON)
```

---

### 1. Entity → DTO Transformation

**Purpose:** Remove sensitive data, add display fields, format for UI.


**Example:**
```
INPUT (Database):
{
    userId: 1,
    username: "john_doe",
    password: "$2a$12$encrypted...",    // SENSITIVE
    registrationDate: 2024-01-15
}

OUTPUT (DTO):
{
    id: 1,
    username: "john_doe",
    memberSince: "January 15, 2024",   // ← FORMATTED
    membershipDays: 60                 // ← CALCULATED
    // password removed                // ← SECURITY
}
```

---

### 2. Query Entity → Query DTO

**Purpose:** Add visual indicators, format timestamps.

**Example:**
```
INPUT:
{
    category: "BILLING",
    priority: "CRITICAL",
    status: "ESCALATED",
    timestamp: 2024-03-15T08:30:00
}

OUTPUT:
{
    category: "🔴 BILLING",         // ← EMOJI ADDED
    priority: "🚨 CRITICAL",        // ← EMOJI ADDED
    status: "🚨 ESCALATED",         // ← EMOJI ADDED
    timeAgo: "2 hours ago"          // ← RELATIVE TIME
}
```

---

### 3. Statistics Transformation

**Purpose:** Convert raw counts to meaningful metrics.


**Example:**
```
INPUT:
total: 100
resolved: 70

OUTPUT:
resolutionRate: "70.0%"          // ← CALCULATED
performance: "🟡 GOOD"           // ← DERIVED
```


