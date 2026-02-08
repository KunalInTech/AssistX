# 🤖 AssistX – Key Module Implementation

This repository contains the implementation of two core modules of the AssistX AI-based customer support system.

---

## 📌 Implemented Modules

### 1️⃣ Query Handling Module
This module handles user sessions and query management.

**Responsibilities:**
- Create and validate user queries  
- Assign priority based on query category  
- Handle query escalation  
- Manage user sessions  

**Classes Implemented:**
- Query  
- Session  

---

### 2️⃣ AI Response Generation Module
This module simulates AI-based processing and response generation.

**Responsibilities:**
- Process user queries  
- Generate automated responses  
- Assign confidence scores  
- Validate generated responses  

**Classes Implemented:**
- AIAgent  
- Response  

---

## 📌 Working of the System
1. A user session is created.
2. A query is submitted and validated.
3. The AI agent processes the query.
4. A response is generated with a confidence score.
5. Data is stored temporarily in memory during execution.

> Note: No database is used in this implementation. Objects simulate database records for academic demonstration.

---

## 🛠️ Technology Used
- Language: Java  
- Concepts: Object-Oriented Programming, UML-based design, modular implementation  

---

## ▶️ How to Run
1. Place all `.java` files in the same directory.
2. Compile the files:
   ```bash
   javac *.java
3. Run the program:
   ```bash
   java Main
   
---
   
## 📌 Note
- This is a prototype-level implementation for academic purposes.
- Database integration can be added in future phases.
