# E-Commerce Web Automation Test Automation

## 🔒 One unified major project

This is **one Maven project and one GitHub repository**. It contains both **ShopFlow (the System Under Test)** and the **automation code that tests ShopFlow**.

### Target final stack
- Java 17
- Spring Boot + PostgreSQL
- Selenium 4
- TestNG
- Page Object Model
- Explicit waits
- REST Assured
- JDBC
- Apache POI + TestNG DataProvider
- Screenshots
- Chrome / Firefox / Edge
- Parallel execution with ThreadLocal WebDriver
- SLF4J/Logback
- Allure/ExtentReports
- Maven
- GitHub Actions
- Power BI UI automation with Selenium

Only technologies needed for the current phase are implemented now; later layers will be added in their phases.

## Structure
```text
E-Commerce-Web-Automation-Test-Automation
├── pom.xml
├── testng.xml
├── docker-compose.yml
├── README.md
└── src
    ├── main
    │   ├── java
    │   └── resources
    └── test
        └── java
            └── com/shopflow/automation
                └── FirstSeleniumTest.java
```

## PostgreSQL
Docker is optional. With the existing local PostgreSQL setup:
```sql
CREATE USER shopflow WITH PASSWORD 'shopflow';
CREATE DATABASE shopflow OWNER shopflow;
GRANT ALL PRIVILEGES ON DATABASE shopflow TO shopflow;
```

## Run ShopFlow
```powershell
mvn spring-boot:run
```
Open `http://localhost:8081`.

## Run automation
Keep ShopFlow running and use a second terminal:
```powershell
mvn clean test
```

## Locked learning workflow
Understand business flow → build working version → learn the needed concept → implement → execute → debug → refactor → document.
Resume claims are finalized only after the corresponding functionality is actually implemented and verified.
