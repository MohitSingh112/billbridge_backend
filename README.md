# 💸 BillBridge - Expense Sharing Backend

**BillBridge** is a backend service for a Splitwise-like expense-sharing application. Built with **Spring Boot**, it allows users to create groups, track shared expenses, split costs, and settle balances.

---

## 🚀 Features

- ✅ User Registration & Login (JWT Authentication)
- ✅ Create and Manage Groups
- ✅ Add Users to Groups
- ✅ Add & View Group Expenses
- ✅ Equal and Custom Expense Splits (by percentage)
- ✅ Track Who Owes Whom
- ✅ Settle Balances via Payments
- ✅ Dashboard Summary of Balances
- ✅ Role & Group-based Access Control

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Security (JWT)**
- **MySQL**
- **JPA / Hibernate**
- **Lombok**
- **Maven**

---

## 📂 Project Structure

```
src/
├── controller/ # API controllers
├── service/ # Business logic
├── model/ # JPA entities
├── repository/ # Data access layers
├── security/ # JWT security configuration
└── BillBridgeApplication.java
```

---

## 🔐 Authentication

- Uses **JWT (JSON Web Tokens)** for stateless authentication.
- Secure endpoints with `Authorization: Bearer <token>` header.
- `/api/auth/**` endpoints are public (register/login).
- All other endpoints require authentication.

---

## 📦 API Endpoints (Sample)

### 🔐 Auth
```
POST /api/auth/register
POST /api/auth/login
```

---

## 👥 Groups

- POST   `/api/groups/create`
- POST   `/api/groups/{groupId}/add-user`
- GET    `/api/groups/{groupId}/expenses`

---

## 💰 Expenses

- POST   `/api/expenses/add`
- POST   `/api/expenses/split/custom`
- POST   `/api/expenses/settle`
- GET    `/api/expenses/summary`

---

## 🧪 Running Locally

1. Clone the repository

```
git clone https://github.com/yourusername/billbridge-backend.git
cd billbridge-backend
```

2. Configure the database
Edit src/main/resources/application.properties:

```
spring.datasource.url=jdbc:mysql://localhost:3306/billbridge
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
```

3. Build and run the app
```
./mvnw clean install
./mvnw spring-boot:run
Server starts at: http://localhost:8080
```
---

## 📌 Future Enhancements

- 🔄 Edit/Delete Expenses

- 📊 Monthly Charts & Stats

- 📤 Export Expenses (PDF/CSV)

- 📧 Email 

- 🌐 Group Invite Links

---

## 📃 License
MIT License – feel free to use and modify!

---

## 🤝 Contributing

Contributions are welcome! Open issues, submit PRs, or just give feedback.

---

## 🙌 Acknowledgments

Inspired by Splitwise, but built for learning and fun! 🎉

---

