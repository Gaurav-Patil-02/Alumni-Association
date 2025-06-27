# 🎓 Alumni Association Web Application

A full-stack web application for managing alumni registrations, event participation, and admin control. Built using **Spring Boot** and **React.js**, this system offers JWT-based authentication, event creation, user management, and profile handling.

---

## 📌 Tech Stack

**Backend:**
- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- MySQL
- JPA/Hibernate
- Maven

**Frontend:**
- React.js
- React Router DOM
- Axios
- HTML5, CSS3, JavaScript (ES6+)

---

## 🔐 Features

### 👥 User (Alumni)
- Register and login securely using JWT
- View and edit personal profile
- View available events
- Register for events
- See registration status

### 🛡️ Admin
- Admin login
- Create and manage events
- View list of registered alumni
- View user registrations per event

---

## 📁 Project Structure

```
/Alumni-Association
│
├── alumni-association/         # Spring Boot backend
│   ├── src/main/java/...
│   ├── src/main/resources/
│   └── application.properties
│
├── alumni-frontend/            # React frontend
│   ├── public/
│   └── src/
│       ├── components/
│       ├── context/
│       ├── routes/
│       ├── styles/
│       └── utils/
│
├── .gitignore
├── README.md
├── package.json (frontend)
└── pom.xml (backend)
```

---

## ⚙️ Getting Started

### 🧩 Prerequisites
- Node.js and npm
- Java 17+
- MySQL running locally
- Maven

---

### 🚀 Backend Setup

1. Create a MySQL database:
```sql
CREATE DATABASE alumni_db;
```

2. Edit `alumni-association/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/alumni_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
jwt.secret=your_jwt_secret
```

3. Run the Spring Boot server:
```bash
cd alumni-association
./mvnw spring-boot:run
```
> Or run directly from your IDE (IntelliJ, Eclipse, VSCode)

---

### 🌐 Frontend Setup

1. Navigate to the frontend folder:
```bash
cd alumni-frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm start
```

Frontend will be running on: `http://localhost:3000`

---

## 🔒 Authentication

- JWT tokens are used to secure API routes.
- Token is stored in localStorage on the client side.
- Protected routes are implemented using middleware filters on the backend and private routes on the frontend.

---

## 🧪 Future Enhancements

- Email verification & password reset
- Upload user profile picture
- Event participant analytics (charts)
- Admin dashboard UI
- Pagination for alumni and events list
- Feedback collection after event completion

---
