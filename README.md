# Simple Banking System

## Overview

This project is a simple banking service that enables various transactions for bank accounts. The service is part of a larger collection of services simulating the operations within a bank. The purpose of this README is to guide you through the project, explain its structure, and provide instructions on how to set it up and use it.

## Project Structure

The project follows a standard structure, and here's a brief explanation of each folder:

- `src`: Contains the source code of the project.
- `images`: Stores images used in documentation.
- `tests`: Holds test cases for the project.
- `docs`: Documentation files, including this README.

## Getting Started

Follow these steps to set up and run the project on your local machine:

### Prerequisites

- Java Development Kit (JDK) installed
- Gradle build tool installed (optional, if not already included in the project)

### Build and Run

1. Clone the repository to your local machine:

```bash
git clone https://github.com/your-username/simple-banking.git
Navigate to the project directory:
bash
Copy code
cd simple-banking
Build the project using Gradle:
bash
Copy code
./gradlew build
Run the application:
bash
Copy code
./gradlew bootRun
The application will start, and you can access it at http://localhost:8080.

Usage
The banking system provides a REST API for handling transactions. Here are some sample API requests:

Deposit money:
bash
Copy code
curl --location --request POST 'http://localhost:8080/account/v1/credit/1234' \
--header 'Content-Type: application/json' \
--data-raw '{
    "amount": 1000.0
}'
Withdraw money:
bash
Copy code
curl --location --request POST 'http://localhost:8080/account/v1/debit/1234' \
--header 'Content-Type: application/json' \
--data-raw '{
    "amount": 50.0
}'
Get account information:
bash
Copy code
curl --location --request GET 'http://localhost:8080/account/v1/1234'
Tests
The project includes unit tests to ensure its functionality. Run the tests using the following command:

bash
Copy code
./gradlew test
Additional Information
For further details and reference documentation, refer to the following:

Official Gradle documentation
Spring Boot documentation
