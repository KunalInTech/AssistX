# 🤖 AssistX – AI-Based Customer Support System

AssistX is an **AI-driven customer support system** designed to automate query handling, generate intelligent responses, and seamlessly escalate complex issues to human agents when required.

The project follows the **Software Development Life Cycle (SDLC)** and is currently in the **Implementation and Testing Phase**, with core modules developed and testing actively in progress.

---

## 📌 Project Status

🚧 **Current Phase:** Implementation + Testing  

### 📄 Completed Artifacts:
- ✅ Use Case Diagram (UCD)  
- ✅ Data Flow Diagrams (Context Diagram & Level 1 DFD)  
- ✅ UML Class Diagram (10 classes, 12 relationships)  
- ✅ Draw.io Editable UML Files  
- ✅ Core Module Implementations:
  - Query Management  
  - AI Agent  
- ✅ Functional Database Schema (SQLite / MySQL-ready)  
- ✅ UI Components (Login, Dashboard, Query Form)  

### 🧪 Testing Progress:
- ✅ White Box Testing (Unit-level logic testing)  
- ✅ Black Box Testing (Functional testing using test cases)  
- ⏳ Integration Testing (In Progress)  

---

## 🎯 Project Objective

The goal of **AssistX** is to design a **scalable and intelligent customer support platform** that:

- ⚡ Reduces response time using AI-generated replies  
- 📊 Handles large volumes of customer queries efficiently  
- 🔄 Escalates complex or low-confidence cases to human agents  
- 📈 Enables administrators to monitor system performance  

---

## ✨ Key Features

### 👤 User / Customer
- Register and login securely  
- Submit queries through UI  
- Receive automated AI responses  

---

### 🤖 AI System
- Classifies queries using NLP techniques  
- Performs sentiment analysis (Positive / Negative / Neutral)  
- Detects user intent (Inquiry, Complaint, Request, Feedback)  
- Generates automated responses  
- Escalates queries when confidence is low  

---

### 🧑‍💼 Support Agent
- Handles escalated queries  
- Resolves issues manually  
- Provides feedback for AI improvement  

---

### 🛠️ Admin
- Monitors system performance  
- Tracks query resolution metrics  
- Manages system configuration  

---

## 🧩 System Design Overview

### 👥 Actors:
- User / Customer  
- Support Agent  
- Admin  

### 🔑 Major Use Cases:
- Register / Login  
- Submit Query  
- AI Classification  
- Auto Response Generation  
- Escalation Handling  
- Ticket Resolution  
- System Monitoring  

---

## 🛠️ Technology Stack

| Layer       | Technology |
|------------|-----------|
| Frontend   | HTML, CSS, JavaScript |
| Backend    | Python (Core Modules), Flask (Planned Integration) |
| Database   | SQLite (Development), MySQL (Production) |
| AI / ML    | NLP, Sentiment Analysis, Pattern Matching |

---

## 📂 Repository Structure

```
ASSISTX/
│── Requirements and Analysis Phase/
│   └── requirements.txt
│
│── Design Phase/
│   ├── Use Case Diagram
│   ├── Data Flow Diagrams
│   ├── UML Class Diagram
│   ├── Draw.io Files
│   └── Novelty
|
│── Developement Phase
│   ├── AI Agent.java
│   ├── Session.java
│   ├── Main.java
│   ├── Query.java
│   ├── Response.java
│   └── README.md
│
│── Implementation/
│   ├── Business Rules
│   ├── Business Logic
│   ├── README.md
│   ├── WhiteBox and BlackBox Testing.md
│   ├── ui/
│   │   ├── index.html
│   │   ├── style.css
│   │   └── script.js
│
│── README.md
```

---

## 🔄 SDLC Mapping

| Phase                     | Status        |
|--------------------------|--------------|
| Requirement Analysis     | ✅ Completed |
| System Design            | ✅ Completed |
| Implementation           | ✅ Completed |
| White Box Testing        | ✅ Completed |
| Black Box Testing        | ✅ Completed |
| Integration Testing      | ⏳ In Progress |
| Deployment               | ⏳ Pending |

---

## 🧪 Testing Details

### 🔍 White Box Testing
- Focuses on internal code structure and logic  
- Tested:
  - Query processing logic  
  - AI decision flow  
  - Database operations  

### 🔍 Black Box Testing
- Focuses on input-output behavior  
- Tested:
  - Valid and invalid inputs  
  - Query submission functionality  
  - System response accuracy
    
---

## 📈 Future Improvements

- 📱 Responsive UI for mobile devices  
- 🔔 Real-time notifications  
- 🔗 Full backend integration (Flask APIs)  
- 🧠 Advanced AI models (Transformers)  
- ☁️ Deployment on cloud platforms  

---

## 👥 Collaborators

- **Kunal Purohit**  
- **Aniket**  
- **Aryadeep**  

---

## ⭐ Note

This project demonstrates a **complete SDLC approach**, including **Design, Implementation, and Testing phases**.  

The system is being developed incrementally with a focus on **scalability, maintainability, and real-world applicability**.

---

## 🌟 Support

If you find this project useful, consider giving it a ⭐ on GitHub!
