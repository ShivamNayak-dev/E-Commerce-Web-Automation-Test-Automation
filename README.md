# 🧪 ShopFlow — E-Commerce Test Automation Platform

> A production-style **end-to-end test automation project** built with Java, Selenium 4, TestNG, REST Assured, JDBC, Apache POI, and GitHub Actions.
>
> One unified Maven repository containing both **ShopFlow** (the Spring Boot + PostgreSQL system under test) and the **automation framework** that validates it across the UI, API, and database layers, with data-driven testing, cross-browser execution, parallel execution, reporting, and CI/CD — plus Selenium-based Power BI dashboard validation.

---

## 🚀 Why This Project Stands Out

Most Selenium projects are a folder of test scripts. This one is a reusable automation **framework** built the way a QA automation engineer would build it for a real product.

| Engineering Challenge | Solution Used |
|---|---|
| UI tests become difficult to maintain | Page Object Model |
| Dynamic pages cause flaky tests | Explicit waits + reusable `WaitUtils` |
| Multiple test datasets are required | Apache POI + TestNG `DataProvider` |
| UI state must match database state | JDBC validation layer |
| UI tests need backend test data fast | REST Assured API setup |
| Tests must run on multiple browsers | Chrome / Firefox / Edge |
| Parallel execution causes driver conflicts | `ThreadLocal<WebDriver>` |
| Failed tests need debugging evidence | Automatic screenshot capture on failure |
| Execution results need visibility | Allure / ExtentReports + SLF4J/Logback |
| Tests should run without manual triggering | GitHub Actions CI/CD |
| BI dashboards also need functional coverage | Selenium-based Power BI UI validation |

---

## 🏗️ Automation Architecture

```
                         ┌─────────────────────────┐
                         │         TestNG           │
                         │    Suite / Execution     │
                         └────────────┬─────────────┘
                                      │
                                      ▼
                         ┌─────────────────────────┐
                         │       Test Layer         │
                         │ Registration / Login      │
                         │ Search / Product / Cart   │
                         │ Checkout / Orders          │
                         │ Power BI Validation        │
                         └────────────┬─────────────┘
                                      │
                                      ▼
                         ┌─────────────────────────┐
                         │    Page Object Layer     │
                         │ LoginPage, HomePage       │
                         │ ProductPage, CartPage     │
                         │ CheckoutPage, OrderPage   │
                         └────────────┬─────────────┘
                                      │
             ┌────────────────────────┼────────────────────────┐
             ▼                        ▼                        ▼
      ┌─────────────┐         ┌─────────────┐         ┌─────────────┐
      │ Selenium 4  │         │ REST Assured│         │    JDBC     │
      │ UI Testing  │         │ API Testing │         │ DB Testing  │
      └──────┬──────┘         └──────┬──────┘         └──────┬──────┘
             │                       │                       │
             └───────────────────────┼───────────────────────┘
                                     ▼
                              ┌──────────────┐
                              │  PostgreSQL  │
                              └──────────────┘
```

Each layer has one job: the test layer describes business flow, the page layer knows the UI, and Selenium/REST Assured/JDBC each validate one part of the system independently.

---

## ✨ Key Features

### 🛒 E-Commerce UI Automation
Full business workflow coverage: registration, login, product search, product details, cart management, checkout, and order verification — with both positive and negative scenarios for each.

### 🔐 Authentication Testing
```
Valid Email + Valid Password ──▶ Successful Login
Invalid Email / Invalid Password / Empty Fields ──▶ Expected Error Validation
```

### 🔎 Search & Product Validation
```
Search Keyword ──▶ Results List ──▶ Select Product ──▶ Validate Name, Price, Availability
```

### 🛒 Cart & 💳 Checkout Automation
```
Add to Cart ──▶ Validate Quantity & Price ──▶ Checkout ──▶ Customer + Payment Details ──▶ Order Confirmation
```

### 🔌 API + UI Integration
REST Assured creates test data before UI tests run, avoiding slow, brittle UI-only setup:
```
REST Assured ──▶ Create Test Data ──▶ ShopFlow ──▶ Selenium UI ──▶ Validate
```

### 🗄️ Database Validation
JDBC confirms that what the UI reports actually landed in PostgreSQL:
```
UI Action ──▶ ShopFlow ──▶ PostgreSQL ──▶ JDBC Query ──▶ Database Assertion
```
Example: registering a user through the UI, then querying PostgreSQL directly to confirm the row exists.

### 📊 Data-Driven Testing
```
Excel ──▶ Apache POI ──▶ TestNG DataProvider ──▶ Multiple Test Executions
```
Login and checkout run against multiple datasets from a single test method.

### 🌐 Cross-Browser & ⚡ Parallel Execution
```
                   TestNG
                     │
       ┌─────────────┼─────────────┐
       ▼             ▼             ▼
    Chrome        Firefox         Edge
   Thread-1      Thread-2       Thread-3
   Driver-1      Driver-2       Driver-3
```
`ThreadLocal<WebDriver>` gives each parallel thread its own browser instance, avoiding the driver conflicts a shared `static WebDriver` would cause.

### 📸 Failure Handling & Reporting
```
Test Failure ──▶ TestNG Listener ──▶ ScreenshotUtils ──▶ Attach to Report
```
SLF4J + Logback log every step; Allure/ExtentReports turn each run into a browsable report with pass/fail counts, durations, and attached screenshots.

### 📈 Power BI UI Automation
Selenium also drives functional validation of Power BI dashboards embedded in the product:
```
Open Power BI Report ──▶ Validate Report Loaded ──▶ Locate Visuals ──▶ Apply Filter ──▶ Validate Updated Dashboard State
```
This covers report availability, visual rendering, filter interaction, and data presentation — treated as its own UI automation surface inside the same framework.

---

## 🧠 Engineering Deep Dive

### 1. Why Page Object Model
```
Test Class ──▶ Page Object ──▶ Locators + Page Actions
```
Tests read as business steps (`loginPage.enterEmail(email)`) instead of raw locators repeated across files. Locator changes are fixed in one place instead of every test that touches that page.

### 2. Why Explicit Waits, Not `Thread.sleep()`
```java
wait.until(ExpectedConditions.elementToBeClickable(locator));
```
Fixed sleeps either waste time or aren't long enough. Condition-based waits (visibility, clickability, text, URL) wait exactly as long as needed and no longer.

### 3. `ThreadLocal<WebDriver>` for Parallel Execution
A shared `static WebDriver` means two parallel threads fight over the same browser session. `ThreadLocal` gives every thread its own driver instance, so Chrome/Firefox/Edge runs never interfere with each other.

### 4. API → UI → Database Validation
```
REST Assured (create data) ──▶ Selenium (perform UI flow) ──▶ JDBC (verify persisted state)
```
This checks the system across three independent layers instead of trusting whatever the UI happens to display — the strongest signal that the framework tests real application behavior, not just page rendering.

### 5. Failure Debugging Loop
```
Failure ──▶ Log ──▶ Screenshot ──▶ Attach to Report ──▶ Investigate
```
Every failure comes with evidence attached automatically, so debugging doesn't start with "let me re-run it and watch."

---

## 📋 Test Coverage

| Module | Positive | Negative | UI | API | DB |
|---|---|---|---|---|---|
| Registration | ✓ | ✓ | ✓ | ✓ | ✓ |
| Login | ✓ | ✓ | ✓ | ✓ | ✓ |
| Search | ✓ | ✓ | ✓ | — | — |
| Product | ✓ | ✓ | ✓ | — | — |
| Cart | ✓ | ✓ | ✓ | — | ✓ |
| Checkout | ✓ | ✓ | ✓ | ✓ | ✓ |
| Orders | ✓ | ✓ | ✓ | ✓ | ✓ |
| Power BI | ✓ | ✓ | ✓ | — | — |

---

## 🛠️ Tech Stack

| Category | Technology |
|---|---|
| Language | Java 17 |
| UI Automation | Selenium 4 |
| Test Framework | TestNG |
| Build Tool | Maven |
| API Testing | REST Assured |
| Database | PostgreSQL |
| DB Validation | JDBC |
| Data-Driven Testing | Apache POI |
| Design Pattern | Page Object Model |
| Parallel Execution | ThreadLocal WebDriver |
| Logging | SLF4J + Logback |
| Reporting | Allure / ExtentReports |
| CI/CD | GitHub Actions |
| BI Automation | Selenium + Power BI |
| Version Control | Git / GitHub |

---

## ⚙️ Local Setup

### Prerequisites
- Java 17+
- Maven
- PostgreSQL
- Git
- Chrome, Firefox, Microsoft Edge

**1. Clone the repository**
```bash
git clone https://github.com/ShivamNayak-dev/E-Commerce-Web-Automation-Test-Automation.git
cd E-Commerce-Web-Automation-Test-Automation
```

**2. Configure PostgreSQL**
```sql
CREATE USER shopflow WITH PASSWORD 'shopflow';
CREATE DATABASE shopflow OWNER shopflow;
GRANT ALL PRIVILEGES ON DATABASE shopflow TO shopflow;
```

**3. Start ShopFlow**
```bash
mvn spring-boot:run
```
Application runs at `http://localhost:8081`.

**4. Run the automation suite** (in a second terminal, keep ShopFlow running)
```bash
mvn clean test
```

**5. Run a specific test class**
```bash
mvn -Dtest=LoginTest test
```

---

## 📁 Project Structure

```
E-Commerce-Web-Automation-Test-Automation/
├── pom.xml
├── testng.xml
├── README.md
├── .gitignore
│
├── src/
│   ├── main/
│   │   ├── java/com/shopflow/          # ShopFlow application (SUT)
│   │   └── resources/
│   │       ├── config/
│   │       └── testdata/
│   │
│   └── test/
│       └── java/com/shopflow/automation/
│           ├── tests/                  # RegistrationTest, LoginTest, CartTest, ...
│           ├── pages/                  # LoginPage, ProductPage, CartPage, ...
│           ├── utils/                  # WaitUtils, ScreenshotUtils, ExcelUtils
│           ├── drivers/                # DriverManager (ThreadLocal WebDriver)
│           ├── api/                    # ApiClient, TestDataApi
│           ├── database/               # DatabaseUtils, UserRepository
│           └── listeners/              # TestListener
│
├── test-data/
│   └── test-data.xlsx
│
├── reports/
├── screenshots/
└── .github/workflows/automation.yml
```

---

## 🔄 CI/CD Pipeline

```
Git Push ──▶ GitHub Actions ──▶ Build Maven Project ──▶ Start Environment
   ──▶ Run TestNG Suite (UI + API + DB) ──▶ Generate Reports ──▶ Upload Artifacts
```

Every push triggers the full suite, builds a fresh report, and uploads screenshots/logs as workflow artifacts.

---

## 🧩 What This Project Demonstrates

Selenium WebDriver and locator strategy, TestNG and the Page Object Model, data-driven testing, API and database validation layered on top of UI tests, cross-browser and parallel execution, structured logging and reporting, screenshot-based failure analysis, CI/CD automation, and Power BI UI validation — end to end, on one working application.

---

## 🔮 Future Improvements

- [ ] Selenium Grid / cloud browser execution
- [ ] Dockerized test execution
- [ ] Expanded API contract testing
- [ ] Performance testing integration
- [ ] Execution trend dashboards across CI runs

---

## 📄 License

MIT License — free to use, modify, and distribute.

---

*Built by [Shivam Nayak](https://github.com/ShivamNayak-dev) | [LinkedIn](https://www.linkedin.com/in/shivam-nayak-886495297/)*
