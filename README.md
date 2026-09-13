# 💼 JobPortal

<p align="center">
  <h1 align="center">💼 JobPortal</h1>
  <p align="center">
    <b>Online Job Recruitment & Application Management System</b>
  </p>
  <p align="center">
    A full-stack web application built using Core Java, JDBC, MySQL, HTML, CSS and JavaScript.
  </p>
</p>

---

## 📌 Project Overview

**JobPortal** is a web-based job recruitment platform that connects **Job Seekers** and **Recruiters** through a centralized system.

The application allows job seekers to register, log in, search for available jobs, apply for jobs and track their applications.

Recruiters can log in and manage job postings by creating, viewing, updating and deleting job listings.

The project demonstrates a complete **Frontend → Backend → Database** workflow using:

- HTML
- CSS
- JavaScript
- Core Java
- Java HTTP Server
- JDBC
- MySQL

---

# 🚀 Features

## 👨‍💻 Job Seeker

- User Registration
- User Login
- Browse Available Jobs
- Search Jobs
- View Job Information
- Apply for Jobs
- View Applied Jobs
- Track Application Status
- Personal Dashboard
- Application Statistics

### Job Seeker Dashboard

The dashboard displays:

```text
┌──────────────────────────────┐
│       My Dashboard           │
├──────────────────────────────┤
│ Total Applications           │
│ Pending Applications         │
│ Selected Applications        │
│ Rejected Applications        │
└──────────────────────────────┘
```

---

# 🏢 Recruiter

Recruiters can manage job postings.

### Recruiter Operations

```text
Create Job
    ↓
View Job
    ↓
Edit Job
    ↓
Delete Job
```

Recruiters can add:

- Job Title
- Job Description
- Required Skills
- Salary
- Location
- Job Type

---

# 🛠️ Technology Stack

| Technology | Purpose |
|------------|---------|
| Java | Backend development |
| Core Java | Application logic |
| HttpServer | HTTP server |
| JDBC | Database connectivity |
| MySQL | Relational database |
| SQL | Database operations |
| HTML5 | Frontend structure |
| CSS3 | UI design |
| JavaScript | Client-side functionality |
| Fetch API | Frontend-backend communication |
| IntelliJ IDEA | Development environment |
| Git | Version control |
| GitHub | Source code hosting |

---

# 🏗️ System Architecture

```text
                     ┌─────────────────────┐
                     │      Web Browser    │
                     │    HTML/CSS/JS      │
                     └──────────┬──────────┘
                                │
                                │ HTTP Request
                                ▼
                     ┌─────────────────────┐
                     │    Java Backend     │
                     │      HttpServer     │
                     └──────────┬──────────┘
                                │
                                │ DAO Layer
                                ▼
                     ┌─────────────────────┐
                     │        JDBC         │
                     │ Database Connectivity│
                     └──────────┬──────────┘
                                │
                                │ SQL Queries
                                ▼
                     ┌─────────────────────┐
                     │       MySQL         │
                     │      Database       │
                     └─────────────────────┘
```

---

# 🧠 Application Architecture

The project follows a simple layered architecture.

```text
Presentation Layer
        │
        │ HTML / CSS / JavaScript
        ▼
HTTP API Layer
        │
        │ Java HttpServer
        ▼
DAO Layer
        │
        │ JDBC
        ▼
Database Layer
        │
        │ SQL
        ▼
MySQL Database
```

---

# 📂 Project Structure

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
│   │
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

# 🗄️ Database Design

The application uses **MySQL** as its relational database.

## Database

```sql
CREATE DATABASE job_portal;

USE job_portal;
```

---

## Main Tables

```text
users
companies
jobs
applications
```

---

## 👤 Users Table

Stores user account information.

```text
users
│
├── id
├── name
├── email
├── password
└── role
```

Possible roles:

```text
JOB_SEEKER
RECRUITER
```

---

## 🏢 Companies Table

Stores company information.

```text
companies
│
├── id
├── company_name
├── location
├── website
└── recruiter_id
```

---

## 💼 Jobs Table

Stores job posting information.

```text
jobs
│
├── id
├── title
├── description
├── skills
├── salary
├── location
├── job_type
├── company_id
└── posted_date
```

---

## 📄 Applications Table

Stores job application information.

```text
applications
│
├── id
├── job_id
├── user_id
├── application_date
└── status
```

Application statuses include:

```text
APPLIED
PENDING
SELECTED
REJECTED
```

---

# 🔗 Database Relationships

```text
                 USERS
                   │
                   │
             1     │     N
                   ▼
             APPLICATIONS
                   ▲
                   │
             1     │     N
                   │
                  JOBS
                   ▲
                   │
             N     │     1
                   │
               COMPANIES
```

### Relationships

```text
User       → Applications
Company    → Jobs
Job        → Applications
```

---

# 🔄 Application Workflow

## 🔐 Login Flow

```text
User
 │
 ▼
Login Page
 │
 ▼
JavaScript
 │
 ▼
HTTP POST Request
 │
 ▼
Java Login API
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
 ├── Valid User
 │       │
 │       ▼
 │   Dashboard
 │
 └── Invalid User
         │
         ▼
       Error
```

---

# 🔎 Job Search Flow

```text
User
 │
 ▼
Job Portal
 │
 ▼
Search / Filter
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

# 📝 Job Application Flow

```text
Job Seeker
    │
    ▼
Browse Jobs
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

# 🏢 Recruiter Job Posting Flow

```text
Recruiter
    │
    ▼
Post Job Form
    │
    ▼
JavaScript
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
    ▼
Job Appears on Portal
```

---

# 🔌 API Endpoints

The backend exposes HTTP API endpoints for frontend communication.

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/register` | POST | Register a new user |
| `/api/login` | POST | Authenticate user |
| `/api/jobs` | GET | Retrieve all jobs |
| `/api/apply` | POST | Apply for a job |
| `/api/applications` | GET | Retrieve user applications |
| `/api/post-job` | POST | Create a job |
| `/api/update-job` | POST | Update a job |
| `/api/delete-job` | POST | Delete a job |

---

# 🧩 DAO Architecture

Database operations are separated using the **DAO (Data Access Object) pattern**.

```text
UserDAO
   │
   └── User Database Operations


JobDAO
   │
   └── Job Database Operations


CompanyDAO
   │
   └── Company Database Operations


ApplicationDAO
   │
   └── Application Database Operations
```

### Benefits

- Separation of concerns
- Better code organization
- Easier maintenance
- Reusable database operations
- Cleaner backend architecture

---

# 🔗 JDBC Architecture

The project uses **JDBC (Java Database Connectivity)** to communicate with MySQL.

```text
Java Application
       │
       ▼
DatabaseConnection
       │
       ▼
MySQL JDBC Driver
       │
       ▼
MySQL Server
       │
       ▼
SQL Query
       │
       ▼
ResultSet / Update Count
```

The project uses:

- `Connection`
- `PreparedStatement`
- `ResultSet`
- `SQLException`

Parameterized `PreparedStatement` queries are used for database operations.

---

# 💻 Frontend Architecture

The frontend consists of:

```text
HTML
 │
 ├── Page Structure
 │
CSS
 │
 ├── Layout
 ├── Components
 └── Responsive Design
 │
JavaScript
 │
 ├── API Requests
 ├── Job Search
 ├── Login
 ├── Registration
 ├── Applications
 └── Dashboard
```

---

# ⚙️ Local Setup

## 1. Clone the Repository

```bash
git clone https://github.com/govind-kevat/JobPortal.git
```

Move into the project:

```bash
cd JobPortal
```

---

# 2. Open in IntelliJ IDEA

Open the cloned project in IntelliJ IDEA.

Make sure Java/JDK is configured.

Recommended:

```text
JDK 26
```

---

# 3. Configure MySQL

Start MySQL Server.

Create the database:

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

# 4. Configure Database Connection

Open:

```text
src/main/java/com/jobportal/DatabaseConnection.java
```

Configure:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/job_portal";

private static final String USER =
        "root";

private static final String PASSWORD =
        "YOUR_MYSQL_PASSWORD";
```

Replace:

```text
YOUR_MYSQL_PASSWORD
```

with your local MySQL password.

> ⚠️ Do not commit your real password to a public GitHub repository.

---

# 5. MySQL Connector/J

Make sure **MySQL Connector/J** is available to the project.

If using Maven, configure the MySQL JDBC dependency in `pom.xml`.

---

# 6. Run the Backend

Run:

```text
JobPortalServer.java
```

The server should start on:

```text
http://localhost:8080
```

Expected console output:

```text
Job Portal started at http://localhost:8080
```

---

# 7. Open the Website

Open:

```text
http://localhost:8080/index.html
```

---

# 🧪 Testing

## Job Seeker Testing

```text
1. Register
2. Login
3. Browse Jobs
4. Search Jobs
5. Click Apply
6. Open Dashboard
7. Check Applications
```

---

## Recruiter Testing

```text
1. Login as Recruiter
2. Open Recruiter Page
3. Add Job
4. View Job
5. Edit Job
6. Delete Job
```

---

# 📊 Example Project Flow

```text
                    JOBPORTAL
                        │
          ┌─────────────┴─────────────┐
          │                           │
      JOB SEEKER                   RECRUITER
          │                           │
      Register                       Login
          │                           │
        Login                    Post Job
          │                           │
      Search Jobs                 Edit Job
          │                           │
      Apply Job                  Delete Job
          │
      Dashboard
          │
 Track Applications
          │
          └─────────────┐
                        ▼
                  JAVA BACKEND
                        │
                        ▼
                      JDBC
                        │
                        ▼
                     MYSQL
```

---

# 🔐 Security Considerations

This project is primarily designed for **academic, learning and portfolio purposes**.

For production deployment, the following improvements should be implemented:

- Password hashing using BCrypt or Argon2
- Secure session management
- JWT or server-side authentication
- Server-side authorization
- Role-based access control
- Environment variables for database credentials
- Input validation
- SQL injection protection
- HTTPS
- CSRF protection
- Rate limiting
- Secure HTTP headers
- Duplicate application prevention

---

# 🚧 Current Limitations

The current version is intentionally simple and focuses on demonstrating the complete application workflow.

Potential improvements include:

```text
Authentication
      ↓
Authorization
      ↓
Recruiter Ownership
      ↓
Application Management
      ↓
Admin Module
```

---

# 🔮 Future Enhancements

## 👨‍💻 Job Seeker

- Resume upload
- Profile management
- Saved jobs
- Advanced filters
- Job recommendations
- Email notifications
- Application withdrawal

## 🏢 Recruiter

- Recruiter dashboard
- Applicant management
- Application status updates
- Candidate search
- Resume viewing
- Company profile

## 👑 Admin

- Admin dashboard
- User management
- Recruiter verification
- Job moderation
- Application monitoring
- Analytics and reports

---

# 📸 Screenshots

Add your screenshots in a folder:

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

Then add them to this README:

```markdown
## 🏠 Home Page

![Home Page](screenshots/home.png)

## 🔐 Login Page

![Login Page](screenshots/login.png)

## 👤 Job Seeker Dashboard

![Dashboard](screenshots/dashboard.png)

## 🏢 Recruiter Dashboard

![Recruiter](screenshots/recruiter.png)
```

---

# 🎓 Learning Outcomes

This project demonstrates practical understanding of:

- Core Java
- Object-Oriented Programming
- Java HTTP Server
- JDBC
- SQL
- MySQL
- DAO Pattern
- CRUD Operations
- HTTP Request/Response
- API Design
- JavaScript Fetch API
- Frontend-Backend Integration
- Relational Database Design
- Git
- GitHub

---

# 💡 Project Highlights

### Backend

```text
Core Java
   +
HttpServer
   +
DAO Pattern
   +
JDBC
```

### Frontend

```text
HTML
   +
CSS
   +
JavaScript
   +
Fetch API
```

### Database

```text
MySQL
   +
Relational Tables
   +
Primary Keys
   +
Foreign Keys
   +
SQL Queries
```

---

# 📈 Development Workflow

```text
Requirement
    ↓
Database Design
    ↓
Java Model Classes
    ↓
DAO Layer
    ↓
HTTP APIs
    ↓
Frontend
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

# ⭐ Project Status

```text
Frontend              ✅ Completed
Backend               ✅ Completed
JDBC Integration      ✅ Completed
MySQL Database        ✅ Completed
Authentication        ✅ Implemented
Job Management        ✅ Implemented
Job Applications      ✅ Implemented
Job Seeker Dashboard  ✅ Implemented
GitHub Repository     ✅ Available
```

---

# 👨‍💻 Author

## Govind Kevat

MCA Student | Java Developer

### Skills

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

### GitHub

https://github.com/govind-kevat

### Repository

https://github.com/govind-kevat/JobPortal

---

# ⭐ Support

If you find this project useful, consider giving the repository a ⭐.

---

# 📜 License

This project is created for **educational and portfolio purposes**.

---

<p align="center">
  <b>💼 JobPortal — Connecting Talent With Opportunities</b>
</p>
