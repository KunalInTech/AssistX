# 🤖 AssistX – AI-Based Customer Support System (Design Phase)
AssistX is an **AI-driven customer support system** designed to automate query handling, provide intelligent responses, and seamlessly involve human agents when required. This repository currently represents the **Design Phase** of the project, focusing on system architecture, UML diagrams, and overall workflow planning.
---
## 📌 Project Status
🚧 **Current Phase:** Design Phase (Extended with Implementation)
📄 **Included Artifacts:**
* ✅ Use Case Diagram (UCD)
* ✅ Data Flow Diagrams (Context Diagram & Level 1 DFD)
* ✅ Complete UML Class Diagram (10 classes, 12 relationships)
* ✅ Draw.io Editable UML Diagram
* ✅ Module Implementations (Query Management & AI Agent)
* ✅ Working Database Schema (SQLite)
* ✅ Comprehensive Documentation
* ✅ System workflow and actor interactions
* ✅ AI decision flow (classification, response, escalation)

Implementation of core modules has been completed. Full system integration will be carried out in subsequent phases.
---
## 🎯 Project Objective
The goal of ASSISTX is to design a scalable and intelligent customer support platform that:
* Reduces response time using AI-generated replies
* Handles large volumes of customer queries efficiently
* Escalates complex or low-confidence cases to human agents
* Enables administrators to monitor system performance
---
## ✨ Key Design Highlights
### 👤 User / Customer
* Register and login to the system
* Submit support queries
* Receive automated AI responses
### 🤖 AI System (Implemented)
* Classifies incoming queries using NLP techniques
* Analyzes context and confidence level
* Generates auto-responses for common issues
* Flags queries for escalation when confidence is low
* Performs sentiment analysis (positive, negative, neutral)
* Detects user intent (inquiry, complaint, request, feedback)
### 🧑‍💼 Support Agent
* Views escalated tickets
* Resolves customer issues manually
* Provides feedback that can be used to improve AI responses
### 🛠️ Admin
* Monitors overall system performance
* Tracks query resolution and escalation metrics
* Generates analytics reports
* Manages system configuration
---
## 🧩 System Design – Use Case Overview
**Actors:**
* User / Customer
* Support Agent
* Admin
**Major Use Cases:**
* Register / Login
* Submit Support Query
* Classify Query (AI)
* Generate Auto-Response
* Escalate to Support Agent
* Resolve Ticket
* Monitor System Performance
These use cases are represented using UML Use Case Diagrams to clearly define system behavior and responsibilities.
---
## 🛠️ Proposed Tech Stack (For Implementation Phase)
| Layer    | Technology (Proposed)                       |
| -------- | ------------------------------------------- |
| Frontend | HTML, CSS, Javascript/React                            |
| Backend  | Python (Core Modules), Java Spring Boot (Full System)                           |
| Database | SQLite (Development), MySQL (Production)                                       |
| AI / ML  | NLP, Sentiment Analysis, Pattern Matching, Transformer Models (Future) |
---
## 📂 Repository Structure (Design Phase)
```
ASSISTX/
│── Requirements and Analysis Phase/
│   └── requirements.txt
│
│── Design Phase/
│   ├── UCD Diagram
│   ├── Data Flow Diagrams (Context & Level 1 DFD)
│   ├── UML Class Diagram (Complete with Relationships)
│   ├── Draw.io Editable Diagrams
│   └── Novelty
│
│── Implementation/
│   └── module_implementation.py (Query Management + AI Agent)
│
│── README.md
```
---
## 👥 Collaborators
* **Kunal Purohit** 
* **Aniket**
* **Aryadeep**
---
## 📈 Next Steps
* ✅ ~~Detailed data flow diagrams~~ (Completed)
* ✅ ~~Complete UML class diagrams~~ (Completed)
* ✅ Core module implementation (2 modules Completed)
* 📋 Sequence diagrams for key workflows
* 📋 Activity diagrams for business processes
* 📋 State diagrams for query lifecycle
* 📋 Component and deployment diagrams
* 📋 Complete database schema design for production (MySQL)
* 📋 REST API design and documentation
* 📋 Frontend UI/UX mockups
* 📋 Begin full system integration
* 📋 Testing strategy and test cases
---
## ⭐ Note
This repository focuses on **system design, planning, and core module implementation**. The Query Management and AI Agent modules are fully functional with comprehensive testing. Full system integration and frontend development will be added in later stages as the project progresses.
---
If you like the idea or find the design useful, feel free to ⭐ star the repository!

