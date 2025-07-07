# Swag Labs Test Automation Framework


[![Test Report](https://img.shields.io/badge/Test%20Report-View%20Latest-blue?style=flat-square)](https://burn-kr.github.io/SwagLabsTest/)

## Project description
The SwagLabsTest project is a comprehensive set of automated End-to-End (E2E) tests for the Swag Labs Demo Website. Its primary goal is to ensure the quality and stability of the web application's user flows and functionality by verifying key interactions.

The tests are developed in Java using the Spring Boot framework, TestNG for test orchestration, and Selenium WebDriver for browser automation. Detailed test results are generated using Allure Report, providing clear visualizations of test outcomes and aiding in debugging. Sensitive configurations are securely managed using SOPS.

## Table of contents

1. [Features](#features)
2. [Technologies Used](#technologies)
3. [Getting Started](#gettingStarted)
   * [Prerequisites](#prerequisites)
   * [Installation](#installation)
4. [Running Tests](#runningTests)
   * [Local Run](*localRun)
   * [CI/CD Run (GitHub Actions)](*cicd)
5. [Allure Reports](#allure)
   * [Local Generation](#localGeneration)
   * [Viewing Report Locally](#viewLocally)
   * [Viewing Online](#viewOnline)
6. [Secret Management (SOPS)](#sops)

## Features

* Automated E2E tests for core Swag Labs functionalities (e.g., login, product Browse, adding to cart, checkout).
* Flexible test configuration using Spring Boot profiles.
* Detailed and interactive reporting with Allure Framework.
* Secure handling of sensitive data (like credentials) using SOPS.
* Automated test execution and report deployment via GitHub Actions.

## Technologies Used

* Java 21
* Maven - Project management and build automation
* Spring Boot - Framework for rapid application development and dependency management
   * `spring-boot-starter-test` - For testing Spring Boot applications
* TestNG - Testing framework
* Selenium WebDriver - For browser automation
* WebDriverManager - Automatic management of browser binaries
* Allure Framework - For generating interactive test reports
   * `allure-testng` - Allure integration with TestNG
   * `allure-maven` - Maven plugin for Allure
* AspectJ Weaver - For Aspect-Oriented Programming support (used by Allure)
* SOPS (Secrets OPerationS) - For secure encryption and decryption of configuration files.

## Getting Started

### Prerequisites

Before you begin, ensure you have the following installed on your system:

* Java Development Kit (JDK) 21 or later.
* Apache Maven (version 3.6.3 or later recommended).
* Git (for cloning the repository).
* Allure Commandline (optional, for viewing reports locally).

### Installation

1. Clone the repository:
```bash
git clone https://github.com/burn-kr/SwagLabsTest.git
cd SwagLabsTest
```
2. Configure PGP Key for SOPS:
   
This project uses SOPS to encrypt sensitive data in src/main/resources/application-dev.enc.yml. To decrypt it, you'll need the corresponding PGP key.

* If you already possess the private PGP key used for encryption, ensure it's available in your GnuPG keyring.
* If you need to generate a new key and re-encrypt the file (for local development or if setting up a new environment):
* Generate a key pair: `gpg --full-generate-key`
* Obtain your key's ID (fingerprint): `gpg --list-secret-keys --keyid-format LONG`
* Update the .sops.yaml file located in the project root with your new PGP ID:
```yaml
# .sops.yaml
# creation rules are evaluated sequentially, the first match wins
creation_rules:
  - file_regex: ^(application-.*\.yml)$
    encrypted_regex: ^(.*Password|password)$
    key_groups:
      - pgp: "ADD_YOUR_PGP_ID_HERE" 
```
> **_IMPORTANT:_** Before encrypting, make sure you have an unencrypted 
> `src/main/resources/application-dev.yml` available (temporarily).

* Encrypt the file using your `sops.sh` script

```bash
chmod +x ./sops.sh
./sops.sh e # This will encrypt application-dev.yml into application-dev.enc.yml
```
> **_CRUCIAL:_** After encryption, never commit the unencrypted `application-dev.yml` to Git! 
> Make sure it's to your .gitignore.

## Running Tests

### Local Run

To execute the tests locally from your terminal:

```bash
mvn cleat test
```

it will execute all the existing tests

To execute a specific group of tests run the following:

```bash
mvn clean test -Dgroups=Login
```

The testing framework has the following groups:
* `Login`
* `Products`
* `Burger`
* `Cart`
* `Checkout`
* `CheckoutOverview`
* `CheckoutFinish`

## CI/CD Run (GitHub Actions)

Tests are automatically executed as part of the CI/CD pipeline defined in `.github/workflows/scheduled-tests.yml`.

The workflow runs:

* On a daily schedule (e.g., 01:00 UTC).
* Manually via `workflow_dispatch` with an `environment` input (currently `dev`).

1. The pipeline handles:
2. Checkout Code: Cloning the repository. 
3. Set up JDK 21: Configuring the Java environment. 
4. Install GnuPG & SOPS: Installing necessary tools. 
5. Make sops.sh executable & Decrypt application*.yml: Decrypting the application-dev.enc.yml using the sops.sh script and a private key from GitHub Secrets (SOPS_PGP_KEY). 
6. Maven Cache: Leveraging Maven's local repository cache. 
7. Run Maven Tests: Executing the TestNG suite. 
8. Allure Report Generation & Deployment: Building and publishing the Allure report to GitHub Pages.

## Allure Reports

Allure reports provide a detailed and interactive overview of test execution.

### Local Generation

After running tests locally with `mvn clean test`, generate the Allure report:

```bash
mvn allure:report
```

This will generate the report files in the `target/site/allure-maven` directory.

### Viewing Report Locally

To view the generated report in your web browser:

```bash
mvn allure:serve
```

This command starts a local web server and opens the report in your default browser.

### Viewing Online

The latest Allure report from GitHub Actions is automatically deployed to GitHub Pages. You can view it here:

https://burn-kr.github.io/SwagLabsTest

## Secret Management (SOPS)

Sensitive configuration data, such as API keys or database credentials, 
are securely stored within `src/main/resources/application-dev.enc.yml`. This file is encrypted using SOPS.

* Encryption Key: The encryption relies on a PGP key.
* CI/CD Decryption: For automated decryption in GitHub Actions, 
* the private PGP key (without a passphrase) is securely stored as a [GitHub Secret](https://docs.github.com/en/actions/how-tos/security-for-github-actions/security-guides/using-secrets-in-github-actions) named `SOPS_PGP_KEY`. 
* This ensures that sensitive information is never exposed directly in your repository.