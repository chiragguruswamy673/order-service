# 🛒 Marketplace Order Service

![Java CI](https://github.com/chiragguruswamy673/order-service/actions/workflows/ci.yml/badge.svg)

A Spring Boot microservice that manages orders in a marketplace.  
Includes full CRUD endpoints and automated QA tests with RestAssured + TestNG, integrated into GitHub Actions CI/CD.

---

## 🚀 Features
- **Create Order** → `POST /orders`
- **Retrieve Order** → `GET /orders/{id}`
- **List Orders** → `GET /orders`
- **Update Status** → `PATCH /orders/{id}/status`
- **Delete Order** → `DELETE /orders/{id}`

---

## 🧪 QA Automation
- Written with **RestAssured** + **TestNG**
- Covers:
    - Happy path (create + retrieve)
    - Validation errors (missing userId)
    - Listing all orders
    - Deletion with 404 check
    - Status update
- Runs automatically in **GitHub Actions** on every push

---

## 📦 Tech Stack
- **Java 17**
- **Spring Boot 3.3**
- **Maven**
- **TestNG + RestAssured**
- **GitHub Actions CI/CD**

---
## 📊 CI/CD
- Workflow: .github/workflows/ci.yml
- Steps:
- Build service
- Start service
- Run API tests
- Upload test reports as artifacts
