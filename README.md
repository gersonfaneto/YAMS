<h1 align="center">
    Y.A.M.S.
</h1>

<div align="center">

[![Activity](https://img.shields.io/github/last-commit/gersonfaneto/YAMS?style=for-the-badge)](https://github.com/gersonfaneto/YAMS/commit/main)
[![License](https://img.shields.io/github/license/gersonfaneto/YAMS?style=for-the-badge)](https://github.com/gersonfaneto/YAMS/blob/main/LICENSE)
[![Stars](https://img.shields.io/github/stars/gersonfaneto/YAMS?style=for-the-badge)](https://github.com/gersonfaneto/YAMS)
![Language](https://img.shields.io/static/v1?label=LANGUAGE&message=Java&color=informational&style=for-the-badge)
![Version](https://img.shields.io/static/v1?label=VERSION&message=1.1.0&color=informational&style=for-the-badge)

</div>

> **Y.A.M.S.** stands for "Yet Another Managements System". This project is being developed for educational purposes, so don't expect much of it 🙃.

<h4 align="center">
  <a href="#about">About</a>
  ·
  <a href="https://gersonfaneto.github.io/YAMS/">Documentation</a>
  ·
  <a href="#usage">Usage</a>
  ·
  <a href="#license">License</a>
</h4>

## About

**Y.A.M.S.** is a management system developed to make the day to day of a microcomputer assistance
more practical and efficient. The final product should be capable of:

- Registering and keeping important information from the "Clients" of the assistance.
- Creation of "Work Orders" from the "Clients", which must be carried out in order of arrival.
  - The "Work Orders" must contain one or more of the following "Services":
    - Assembly - Component Installation:
      - RAM - Price: R$ 20.00
      - Motherboard - Price: R$ 100.00
      - Power Supply - Price: R$ 30.00
      - Graphics Card - Price: R$ 100.00
      - HD/SSD - Price: R$ 30.00
      - Others - Price: _To be defined!_
    - Cleaning - Price: R$ 70.00
    - Formatting - Price: R$ 50.00
    - Programs Installation - Price: R$ 10.00
- Generating "Invoices" referring to the "Work Orders" and receiving "Payments" for them from
  different methods.
- Creating "Purchase Orders" for the "Components" needed for the realization of the "Services".
- Generating "Reports" for the "Services" containing relevant information, like: time waited, client
  rating and used components.

The development was divided in the following phases:

- [x] Phase I: Modeling of the system trough Class and Use Case Diagrams.
- [x] Phase II: Implementation of the base models and DAOs for the CRUD operations,
      as well as the creation of the Unit Tests.
- [x] Phase III: Assure the persistence of the system's information.
- [ ] Phase IV: Construction of the GUI using JavaFX.

## Usage

> **Warning**: **Y.A.S.M.** is currently under development, so you won't probably be able
> to do much with it until I reach Phase IV 🙃.

#### Dependencies

- [OpenJDK 17](https://openjdk.org/projects/jdk/17/)
- [JavaFX](https://gluonhq.com/products/javafx/)
- [Maven](https://maven.apache.org/download.cgi)
- [Intellij IDEA](https://www.jetbrains.com/idea/download/)

1. Download the latest [release](https://github.com/gersonfaneto/YAMS/releases) of the project or
   clone it into your machine.

```bash
git clone https://github.com/gersonfaneto/YAMS --branch main --depth 1
```

2. Open it with Intellij and wait for the setup.

3. Run the `App.java` class and have fun 😄.

## License

Released under [MIT](https://github.com/gersonfaneto/YAMS/blob/main/LICENSE)
by [gersonfaneto](https://github.com/gersonfaneto).
