# 💼 JobPortal

<p align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:2563EB,100:7C3AED&height=220&section=header&text=JobPortal&fontSize=60&fontColor=ffffff&animation=fadeIn&fontAlignY=38&desc=Online%20Job%20Recruitment%20Platform&descAlignY=58&descSize=20"/>

</p>

<p align="center">

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&size=22&duration=3000&pause=1000&color=2563EB&center=true&vCenter=true&width=750&lines=Connecting+Job+Seekers+with+Recruiters;Core+Java+%7C+JDBC+%7C+MySQL;HTML+%7C+CSS+%7C+JavaScript;Complete+Frontend+%2B+Backend+%2B+Database+Project"/>

</p>

<p align="center">

<a href="https://github.com/govind-kevat/JobPortal">
<img src="https://img.shields.io/github/stars/govind-kevat/JobPortal?style=for-the-badge&logo=github&label=STARS"/>
</a>

<a href="https://github.com/govind-kevat/JobPortal">
<img src="https://img.shields.io/github/forks/govind-kevat/JobPortal?style=for-the-badge&logo=github&label=FORKS"/>
</a>

<img src="https://img.shields.io/badge/Java-26-orange?style=for-the-badge&logo=openjdk"/>
<img src="https://img.shields.io/badge/MySQL-Database-blue?style=for-the-badge&logo=mysql"/>

</p>

---

## 🌟 About The Project

**JobPortal** is a full-stack web-based recruitment platform designed to connect **Job Seekers** and **Recruiters** through a centralized job management system.

The application provides two major user flows:

```text
                    💼 JOBPORTAL
                         │
              ┌──────────┴──────────┐
              │                     │
         👨‍💻 JOB SEEKER         🏢 RECRUITER
              │                     │
        Register/Login            Login
              │                     │
         Browse Jobs            Post Job
              │                  Edit Job
          Search Jobs           Delete Job
              │
          Apply Job
              │
          Dashboard
              │
      Track Applications
```

---

# ✨ Features

<table>
<tr>
<td width="50%">

### 👨‍💻 Job Seeker

- 🔐 Registration & Login
- 🔎 Search Jobs
- 💼 Browse Job Listings
- 📝 Apply for Jobs
- 📋 View Applications
- 📊 Application Dashboard
- ⏳ Track Application Status
- ✅ Selected Applications
- ❌ Rejected Applications

</td>

<td width="50%">

### 🏢 Recruiter

- 🔐 Recruiter Login
- ➕ Post New Jobs
- 👀 View Job Listings
- ✏️ Edit Job Details
- 🗑️ Delete Job Listings
- 💰 Add Salary Information
- 📍 Add Job Location
- 🛠️ Add Required Skills

</td>
</tr>
</table>

---

# 🛠️ Technology Stack

<p align="center">

<img src="https://skillicons.dev/icons?i=java,mysql,html,css,js,git,github,idea"/>

</p>

| Technology | Usage |
|---|---|
| ☕ **Java** | Backend development |
| 🌐 **HttpServer** | HTTP server |
| 🔌 **JDBC** | Database connectivity |
| 🗄️ **MySQL** | Relational database |
| 📜 **SQL** | Database queries |
| 🧱 **HTML5** | Web structure |
| 🎨 **CSS3** | UI & responsive design |
| ⚡ **JavaScript** | Client-side functionality |
| 🔄 **Fetch API** | Frontend ↔ Backend communication |
| 🧠 **IntelliJ IDEA** | Development |
| 🔧 **Git** | Version control |
| 🐙 **GitHub** | Repository & collaboration |

---

# 🏗️ System Architecture

```text
┌───────────────────────────────────────────────────┐
│                   WEB BROWSER                     │
│                                                   │
│             HTML + CSS + JavaScript               │
└───────────────────────┬───────────────────────────┘
                        │
                        │ HTTP Requests
                        ▼
┌───────────────────────────────────────────────────┐
│                 JAVA BACKEND                      │
│                                                   │
│              Java HttpServer                     │
│                                                   │
│       /api/login   /api/jobs   /api/apply        │
│       /api/register /api/post-job                │
└───────────────────────┬───────────────────────────┘
                        │
                        ▼
┌───────────────────────────────────────────────────┐
│                    DAO LAYER                      │
│                                                   │
│ UserDAO | JobDAO | CompanyDAO | ApplicationDAO   │
└───────────────────────┬───────────────────────────┘
                        │
                        ▼
┌───────────────────────────────────────────────────┐
│                     JDBC                          │
│                                                   │
│ Connection | PreparedStatement | ResultSet        │
└───────────────────────┬───────────────────────────┘
                        │
                        ▼
┌───────────────────────────────────────────────────┐
│                    MySQL                          │
│                                                   │
│ Users | Companies | Jobs | Applications           │
└───────────────────────────────────────────────────┘
```

---

# 🧠 Architecture Pattern

The project uses a simple **layered architecture** with the **DAO (Data Access Object) pattern**.

```text
Presentation Layer
        │
        ▼
HTML / CSS / JavaScript
        │
        ▼
HTTP API Layer
        │
        ▼
Java HttpServer
        │
        ▼
DAO Layer
        │
        ▼
JDBC
        │
        ▼
MySQL
```

### Why DAO?

The DAO layer separates database logic from the rest of the application.

```text
UserDAO
     ↓
User Operations

JobDAO
     ↓
Job Operations

CompanyDAO
     ↓
Company Operations

ApplicationDAO
     ↓
Application Operations
```

Benefits:

- Better separation of concerns
- Cleaner code
- Easier maintenance
- Reusable database operations
- Better project structure

---

# 🗄️ Database Architecture

## Database

```text
job_portal
```

### Tables

```text
users
companies
jobs
applications
```

### Entity Relationship

```text
             ┌──────────────┐
             │    USERS     │
             └──────┬───────┘
                    │
                    │ 1 : N
                    ▼
             ┌──────────────┐
             │ APPLICATIONS │
             └──────┬───────┘
                    ▲
                    │ N : 1
                    │
             ┌──────┴───────┐
             │     JOBS     │
             └──────┬───────┘
                    │
                    │ N : 1
                    ▼
             ┌──────────────┐
             │  COMPANIES   │
             └──────────────┘
```

---

# 📊 Database Tables

### 👤 Users

```text
id
name
email
password
role
```

### 🏢 Companies

```text
id
company_name
location
website
recruiter_id
```

### 💼 Jobs

```text
id
title
description
skills
salary
location
job_type
company_id
posted_date
```

### 📝 Applications

```text
id
job_id
user_id
application_date
status
```

---

# 🔌 API Endpoints

| Endpoint | Method | Description |
|---|---|---|
| `/api/register` | POST | Register user |
| `/api/login` | POST | Authenticate user |
| `/api/jobs` | GET | Get all jobs |
| `/api/apply` | POST | Apply for job |
| `/api/applications` | GET | Get user applications |
| `/api/post-job` | POST | Create job |
| `/api/update-job` | POST | Update job |
| `/api/delete-job` | POST | Delete job |

---

# 🔄 Complete Application Flow

## 🔐 Authentication

```text
Login Form
    │
    ▼
JavaScript
    │
    ▼
HTTP POST
    │
    ▼
Java HttpServer
    │
    ▼
UserDAO
    │
    ▼
JDBC
    │
    ▼
MySQL
    │
    ├── Valid ──► Dashboard
    │
    └── Invalid ► Error
```

---

## 🔎 Job Search

```text
Search Input
      │
      ▼
JavaScript
      │
      ▼
GET /api/jobs
      │
      ▼
JobDAO
      │
      ▼
MySQL
      │
      ▼
Job List
      │
      ▼
Browser
```

---

## 📝 Job Application

```text
Job Seeker
    │
    ▼
Select Job
    │
    ▼
Apply Now
    │
    ▼
POST /api/apply
    │
    ▼
ApplicationDAO
    │
    ▼
MySQL
    │
    ▼
Application Saved
```

---

## 🏢 Recruiter Job Management

```text
Recruiter
    │
    ▼
Post Job
    │
    ▼
POST /api/post-job
    │
    ▼
JobDAO
    │
    ▼
MySQL
    │
    ▼
Job Created
    │
    ├── Edit
    │
    └── Delete
```

---

# 📁 Project Structure

```text
JobPortal/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── jobportal/
│
│                   ├── Application.java
│                   ├── ApplicationDAO.java
│                   │
│                   ├── Company.java
│                   ├── CompanyDAO.java
│                   │
│                   ├── DatabaseConnection.java
│                   │
│                   ├── Job.java
│                   ├── JobDAO.java
│                   │
│                   ├── JobPortalServer.java
│                   ├── Main.java
│                   │
│                   ├── User.java
│                   └── UserDAO.java
│
├── Web/
│   ├── index.html
│   ├── login.html
│   ├── register.html
│   ├── dashboard.html
│   ├── applications.html
│   ├── post-job.html
│   │
│   ├── style.css
│   └── script.js
│
├── pom.xml
└── README.md
```

---

# ⚙️ Installation & Setup

## 1️⃣ Clone Repository

```bash
git clone https://github.com/govind-kevat/JobPortal.git
```

```bash
cd JobPortal
```

---

## 2️⃣ Open Project

Open the project in:

```text
IntelliJ IDEA
```

Recommended JDK:

```text
Java 26
```

---

## 3️⃣ Configure MySQL

Start MySQL Server.

Create database:

```sql
CREATE DATABASE job_portal;

USE job_portal;
```

Create the required tables:

```sql
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE companies (
    id INT PRIMARY KEY AUTO_INCREMENT,
    company_name VARCHAR(100) NOT NULL,
    location VARCHAR(100),
    website VARCHAR(150),
    recruiter_id INT,
    FOREIGN KEY (recruiter_id) REFERENCES users(id)
);

CREATE TABLE jobs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    skills VARCHAR(255),
    salary VARCHAR(50),
    location VARCHAR(100),
    job_type VARCHAR(50),
    company_id INT,
    posted_date DATE,
    FOREIGN KEY (company_id) REFERENCES companies(id)
);

CREATE TABLE applications (
    id INT PRIMARY KEY AUTO_INCREMENT,
    job_id INT,
    user_id INT,
    application_date DATE,
    status VARCHAR(30) DEFAULT 'APPLIED',
    FOREIGN KEY (job_id) REFERENCES jobs(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

---

# 🔌 JDBC Configuration

Open:

```text
src/main/java/com/jobportal/DatabaseConnection.java
```

Configure your local MySQL credentials:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/job_portal";

private static final String USER =
        "root";

private static final String PASSWORD =
        "YOUR_MYSQL_PASSWORD";
```

> ⚠️ Never upload your real MySQL password to a public GitHub repository.

---

# ▶️ Run the Application

Run:

```text
JobPortalServer.java
```

Expected console:

```text
Job Portal started at http://localhost:8080
```

Open:

```text
http://localhost:8080/index.html
```

---

# 🧪 Testing Flow

### Job Seeker

```text
Register
   ↓
Login
   ↓
Browse Jobs
   ↓
Search Job
   ↓
Apply
   ↓
Dashboard
   ↓
Track Application
```

### Recruiter

```text
Login
   ↓
Post Job
   ↓
View Job
   ↓
Edit Job
   ↓
Delete Job
```

---

# 📸 Screenshots

Create this folder:

```text
screenshots/
```

Recommended screenshots:

```text
home.png
login.png
register.png
jobs.png
dashboard.png
applications.png
recruiter.png
post-job.png
```

Then add:

```markdown
## 🏠 Home Page

![Home Page](screenshots/home.png)

## 🔐 Login

![Login](screenshots/login.png)

## 👤 Job Seeker Dashboard

![Dashboard](screenshots/dashboard.png)

## 🏢 Recruiter

![Recruiter](screenshots/recruiter.png)
```

---

# 🔐 Security Considerations

The current project is designed for **academic and portfolio purposes**.

For production deployment, the following improvements should be implemented:

- 🔒 Password hashing using BCrypt/Argon2
- 🔑 Secure session management
- 🛡️ Server-side authorization
- 👥 Role-based access control
- 🔐 Environment variables
- ✅ Input validation
- 🛡️ SQL injection prevention
- 🌐 HTTPS
- 🔄 CSRF protection
- 🚦 Rate limiting
- 🚫 Duplicate application prevention

---

# 🚧 Current Limitations

The project intentionally uses a lightweight architecture suitable for learning and demonstration.

Potential production improvements include:

```text
Current
   │
   ▼
Core Java HttpServer
   │
   ▼
JDBC
   │
   ▼
MySQL

Future
   │
   ▼
Spring Boot
   │
   ▼
REST API
   │
   ▼
Authentication
   │
   ▼
MySQL
```

---

# 🔮 Future Enhancements

### 👨‍💻 Job Seeker

- 📄 Resume upload
- 👤 Profile management
- ⭐ Saved jobs
- 🔎 Advanced filters
- 🤖 Job recommendations
- 📧 Email notifications

### 🏢 Recruiter

- 📊 Recruiter dashboard
- 👥 Applicant management
- 🔄 Application status updates
- 📄 Resume viewing
- 🏢 Company profile

### 👑 Admin

- 📊 Admin dashboard
- 👥 User management
- 🏢 Recruiter verification
- 🛡️ Job moderation
- 📈 Analytics
- 📑 Reports

---

# 🎓 Learning Outcomes

This project demonstrates practical understanding of:

```text
☕ Core Java
🧱 OOP
🌐 HTTP Server
🔌 JDBC
🗄️ MySQL
📜 SQL
🏗️ DAO Pattern
🔄 CRUD Operations
🔗 API Integration
⚡ JavaScript Fetch API
🎨 Frontend Development
🗃️ Database Design
🔧 Git
🐙 GitHub
```

---

# 📈 Development Workflow

```text
Requirement Analysis
        ↓
Database Design
        ↓
Java Model Classes
        ↓
DAO Layer
        ↓
HTTP APIs
        ↓
Frontend Development
        ↓
API Integration
        ↓
Testing
        ↓
Git
        ↓
GitHub
```

---

# ⭐ Project Highlights

<table>
<tr>
<td align="center">💻<br><b>Full Stack</b></td>
<td align="center">☕<br><b>Core Java</b></td>
<td align="center">🔌<br><b>JDBC</b></td>
<td align="center">🗄️<br><b>MySQL</b></td>
</tr>

<tr>
<td align="center">🌐<br><b>HTTP APIs</b></td>
<td align="center">🏗️<br><b>DAO Pattern</b></td>
<td align="center">⚡<br><b>JavaScript</b></td>
<td align="center">🐙<br><b>GitHub</b></td>
</tr>
</table>

---

# 📊 Project Status

| Module | Status |
|---|---|
| Frontend | ✅ Completed |
| Java Backend | ✅ Completed |
| JDBC | ✅ Completed |
| MySQL | ✅ Completed |
| Registration | ✅ Completed |
| Login | ✅ Completed |
| Job Search | ✅ Completed |
| Job Application | ✅ Completed |
| Job Seeker Dashboard | ✅ Completed |
| Recruiter Job Management | ✅ Completed |
| GitHub Repository | ✅ Completed |

---

# 👨‍💻 Author

## Govind Kevat

**MCA Student | Java Developer**

### Technical Skills

```text
Java
JDBC
MySQL
SQL
HTML
CSS
JavaScript
Git
GitHub
```

### 🔗 Connect

<p align="center">

<a href="https://github.com/govind-kevat">
<img src="https://img.shields.io/badge/GitHub-Govind%20Kevat-black?style=for-the-badge&logo=github"/>
</a>

</p>

---

# ⭐ Support

If you find this project useful:

⭐ **Star the repository**

🍴 **Fork the repository**

📢 **Share the project**

---

<p align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:7C3AED,100:2563EB&height=120&section=footer"/>

</p>

<p align="center">
  <b>💼 JobPortal — Connecting Talent With Opportunities</b>
</p>

<p align="center">
  Made with ☕ Java + 🗄️ MySQL + 🌐 Web Technologies
</p>

---

## 📜 License

This project is created for **educational and portfolio purposes**.
