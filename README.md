# Car Rental System

A desktop application built with Java Swing to practice and consolidate object-oriented programming concepts through a realistic car rental workflow. The system combines a modern UI layer with persistent storage to let administrators and clients manage cars, reservations, and accounts in real time.

## Project Overview
- **Purpose:** Capstone project for mastering OOP in Java, Swing component customization, and database-backed application design.
- **Users:** Supports administrators (fleet management, user oversight) and clients (self-service rentals and account management).
- **Architecture:** Layered into `Model` (domain entities, Swing component wrappers, persistence), and `Controller` (screens, flows, validation, business rules).

## Core Features
- **Authentication & Accounts:** Login screen backed by the `userss` table, with flows for creating new accounts and updating credentials (`AddNewAccount`, `ChangePassword`, `EditUserData`).
- **Fleet Operations:** Administrators can add, update, soft-delete, and list cars with validation and availability flags (`AddNewCar`, `UpdateCar`, `DeleteCar`, `ViewCars`).
- **Rental Lifecycle:** Clients can browse available cars, start rentals with pricing per hour, and return vehicles, updating the `cars` and `rents` tables accordingly (`RentCar`, `ReturnCar`, `ShowUserRents`).
- **Administrative Insights:** Dedicated views display all rentals and user-specific histories to support customer service and reporting (`ShowAllRents`, `ShowSpecUserRents`).
- **Reusable UI Components:** Custom `Model.JButton`, `Model.JLabel`, and related classes encapsulate consistent styling, font sizing, and layout ergonomics across screens.

## Tech Highlights
- **Java Swing:** Event-driven UI with frames, dialogs, and custom components tailored to the brand palette.
- **OOP Patterns:** Domain models (`Car`, `User`, `Admin`, `Client`, `Rent`) encapsulate state and behavior, while the `Operation` interface unifies actions triggered from menus.
- **MySQL & JDBC:** `Model.Database` centralizes the connection and statement creation with scroll-insensitive result sets for reliable CRUD operations.
- **Validation & Feedback:** Controllers perform step-by-step validation with contextual dialogs to guide users and prevent data corruption.

## Getting Started
### Prerequisites
- Java Development Kit (JDK) 8 or newer.
- MySQL Server (or compatible MariaDB instance).
- IDE such as IntelliJ IDEA or Eclipse for running Swing applications (optional but recommended).

### Database Setup
1. Create a database named `carrentalsystem` (or adjust the JDBC URL in `Model/Database.java`).
2. Provision tables for `userss`, `cars`, and `rents` with columns referenced throughout the controllers (e.g., IDs, names, pricing, availability, and rental status fields).
3. Update the `user` and `pass` fields in `Model/Database.java` if your MySQL credentials differ from the defaults.

> Tip: Seed the tables with a default administrator account to gain access to the management screens immediately after launching.

### Run the Application
1. Import the project into your IDE or compile from the command line with `javac` targeting the `src` directory.
2. Ensure the MySQL server is running and accessible.
3. Run `Controller.Main` to launch the login screen.
4. Authenticate with an existing account or create a new one to explore the admin and client workflows.

## Project Structure
```
src/
├── Controller/   # Swing screens, event handlers, and business workflows
└── Model/        # Domain entities, shared UI components, and JDBC helpers
```
Additional directories such as `out/` contain compiled artifacts when building locally.

## Learning Outcomes
- Applied encapsulation, inheritance, and polymorphism to separate UI logic from domain models.
- Practiced Swing layout management, custom component creation, and event-driven programming.
- Strengthened SQL skills by integrating CRUD operations, input validation, and transactional flows.
- Delivered a cohesive desktop experience mirroring real-world rental business requirements.

## Roadmap Ideas
- Replace raw JDBC statements with a DAO or ORM layer for improved maintainability.
- Introduce role-based dashboards, reporting charts, or PDF invoice exports.
- Add automated tests and continuous integration to safeguard future enhancements.

## License
This project is shared for educational purposes. Adapt it freely to support your learning journey or as a starting point for production-ready rental solutions.
