```markdown
__   __        _         __  __       ___     
\ \ / /       /_\       |  \/  |     / __|      # Yet Another Management System.
 \ V /   _   / _ \   _  | |\/| |  _  \__ \  _   
  |_|   (_) /_/ \_\ (_) |_|  |_| (_) |___/ (_)  # by @gersonfaneto

```

> **Y.A.M.S.** stands for "Yet Another Managements System". This project was
> developed for educational purposes, so don't expect much of it 🙃.

<div align="center">

[![Activity](https://img.shields.io/github/last-commit/gersonfaneto/YAMS?color=blue&style=for-the-badge&logo=git)](https://github.com/gersonfaneto/YAMS/commit/main)
[![License](https://img.shields.io/github/license/gersonfaneto/YAMS?color=blue&style=for-the-badge)](https://github.com/gersonfaneto/YAMS/blob/main/LICENSE)
[![Stars](https://img.shields.io/github/stars/gersonfaneto/YAMS?style=for-the-badge&logo=github)](https://github.com/gersonfaneto/YAMS)
[![Language](https://img.shields.io/static/v1?label=LANGUAGE&message=Java&color=informational&style=for-the-badge&logo=openjdk)](https://www.java.com/en/)
[![Version](https://img.shields.io/static/v1?label=VERSION&message=2.0.0&color=informational&style=for-the-badge)](https://github.com/gersonfaneto/YAMS/releases/latest)

</div>

<h4 align="center">
  <a href="#about">About</a>
  ·
  <a href="#development">Development</a>
  ·
  <a href="https://gersonfaneto.github.io/YAMS/">Documentation</a>
  ·
  <a href="#usage">Usage</a>
  ·
  <a href="#license">License</a>
</h4>

## About

**Y.A.M.S.** is a management system developed to make the day to day of a
microcomputer assistance more practical and efficient. The final product
should be capable of:

- Registering and keeping important information from the `Clients` of the
  assistance.
- Creation of `Work Orders` from the `Clients`, which must be carried out
  in order of arrival.
    - The `Work Orders` must contain one or more of the following `Services`:
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
- Generating `Invoices` referring to the `Work Orders` and receiving
  `Payments` for them from different methods.
- Updating the stock with `Components` needed for the realization of
  some of the `Services`.
- Generating `Reports` for the `Services` containing relevant
  information, like: Wait time, `Client` rating and used `Components`.

## Development

The development was divided in the following phases:

- [x] Phase I: Modeling of the system trough Class and Use Case Diagrams.
- [x] Phase II: Implementation of the base models and DAOs for the CRUD
  operations, as well as the creation of the Unit Tests.
- [x] Phase III: Assure the persistence of the system's information.
- [x] Phase IV: Construction of the GUI using JavaFX.

## Usage

Make **sure** you have the following dependencies installed before proceeding.

- [OpenJDK 17](https://openjdk.org/projects/jdk/17/)
- [Maven](https://maven.apache.org/download.cgi)

Afterwards, simply follow the steps ahead: 

- Download the latest [release](https://github.com/gersonfaneto/YAMS/releases/latest)
  of the project or clone it into your machine using `git`.

```console
git clone https://github.com/gersonfaneto/YAMS --branch V2.0.0 --depth 1
```

- Move into the project folder and build it using `maven`.

```console
maven clean install
```

> **Note**: This will download all dependencies, compile the project as well
> as run the tests. It will take some time on the first time, but should be
> relatively faster after the caching is made.

- Run the program using the following command and have fun 😄.

```console
maven clean javafx:run
```

> **Note**: Alternatively you can open the project using you preferred IDE
> and follow its steps on building and running using the GUI.

## License

Released under [MIT](https://github.com/gersonfaneto/YAMS/blob/main/LICENSE)
by [gersonfaneto](https://github.com/gersonfaneto).
