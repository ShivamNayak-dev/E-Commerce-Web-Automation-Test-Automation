# 🧪 ShopFlow — E-Commerce Test Automation Platform

> A production-style **end-to-end test automation project** built with Java, Selenium 4, TestNG, REST Assured, JDBC, Apache POI, and GitHub Actions.

> The project automates critical e-commerce workflows while validating application behavior across the **UI, API, and database layers**, with data-driven testing, cross-browser execution, parallel execution, reporting, screenshots, and CI/CD integration.

---

## 🚀 Why This Project Stands Out

This project goes beyond basic Selenium scripts.

It demonstrates how a real QA automation engineer can build and maintain a reusable automation framework that validates an application across multiple layers.

| Engineering Challenge | Solution |
|---|---|
| UI tests become difficult to maintain | **Page Object Model** |
| Dynamic pages cause flaky tests | **Explicit waits + reusable WaitUtils** |
| Multiple test datasets are required | **Apache POI + TestNG DataProvider** |
| UI data must match database state | **JDBC validation** |
| Test data needs to be created quickly | **REST Assured API setup** |
| Tests must run on multiple browsers | **Chrome / Firefox / Edge** |
| Parallel execution causes driver conflicts | **ThreadLocal WebDriver** |
| Failed tests require debugging evidence | **Automatic screenshots** |
| Execution results need visibility | **Allure / ExtentReports** |
| Application behavior needs traceability | **SLF4J + Logback** |
| Tests should execute automatically | **GitHub Actions CI/CD** |
| BI dashboards require validation | **Selenium-based Power BI UI automation** |

---

# 🏗️ Automation Architecture

```text
                         ┌─────────────────────────┐
                         │         TestNG          │
                         │    Suite / Execution    │
                         └────────────┬────────────┘
                                      │
                                      ▼
                         ┌─────────────────────────┐
                         │       Test Layer        │
                         │                         │
                         │ Login / Search / Product│
                         │ Cart / Checkout / Orders │
                         │ Power BI Validation     │
                         └────────────┬────────────┘
                                      │
                                      ▼
                         ┌─────────────────────────┐
                         │    Page Object Layer    │
                         │                         │
                         │ LoginPage               │
                         │ HomePage                │
                         │ ProductPage             │
                         │ CartPage                │
                         │ CheckoutPage            │
                         │ OrderPage               │
                         └────────────┬────────────┘
                                      │
             ┌────────────────────────┼────────────────────────┐
             │                        │                        │
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