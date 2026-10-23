# Smart Pantry Manager

# Github link


## 1. Project Overview

Smart Pantry Manager is an Android application developed using Java and SQLite to help users manage pantry items efficiently. The application is designed to make it easier to keep track of food products, monitor available stock, and manage pantry information from an Android device.

The project aims to improve pantry organisation, reduce food waste, and help users keep track of the items they have available.

## 2. Project Objectives

The main objectives of Smart Pantry Manager are to:

- Provide a simple interface for managing pantry items.
- Store pantry information in a local SQLite database.
- Allow users to organise and track available food products.
- Make it easier to monitor pantry stock levels.
- Support better household food management and reduce unnecessary purchases.

## 3. Technologies Used

- **Programming Language:** Java
- **Development Environment:** Android Studio
- **Database:** SQLite
- **Build System:** Gradle
- **Platform:** Android

## 4. Main Features

The application is intended to support the following functionality, depending on the features implemented in the current version:

- Adding pantry items.
- Viewing saved pantry items.
- Updating item information.
- Removing items from the pantry.
- Storing and retrieving data using SQLite.
- Providing a user-friendly Android interface.

## 5. Project Structure

The project follows the standard Android Studio project structure.

```text
SmartPantryManager2/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   ├── build.gradle.kts
│   └── .gitignore
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── gradlew
├── gradlew.bat
└── README.md
```

*Note: The exact structure may differ depending on the files included in the project.*

## 6. Installation and Setup

Follow these steps to open the project:

1. Install Android Studio on your computer.
2. Clone or download this repository from GitHub.
3. Extract the downloaded ZIP file if necessary.
4. Open Android Studio.
5. Select **Open** and choose the `SmartPantryManager2` project folder.
6. Allow Android Studio to synchronise the Gradle files.
7. Connect an Android device or create an Android Virtual Device using the Device Manager.
8. Click **Run** to build and launch the application.

An internet connection may be required during the initial Gradle synchronisation to download dependencies.

## 7. Database

Smart Pantry Manager uses SQLite for local data storage. SQLite allows the application to store and retrieve pantry information directly on the device without requiring a separate database server.

The database structure and operations are managed by the application's Java code.

## 8. Requirements

To build and run the application, you need:

- A computer running Windows, macOS, or Linux.
- Android Studio.
- A compatible Android SDK.
- A Java development environment supported by the project's Gradle configuration.
- An Android emulator or physical Android device.

## 9. Testing

The application should be tested to verify that:

- Pantry items can be added and displayed correctly.
- Item information can be updated and deleted where supported.
- Data remains available after closing and reopening the application.
- Invalid input is handled appropriately.
- The interface works correctly on the target Android device.

## 10. Future Improvements

Possible future improvements include:

- Expiry-date reminders for food products.
- Low-stock notifications.
- Search and filtering of pantry items.
- Categorisation of food products.
- Shopping list generation.
- Improved reporting and pantry inventory summaries.

## 11. Author

**Project Name:** Smart Pantry Manager  
**Project Type:** Android Application  
**Programming Language:** Java  
**Database:** SQLite  
**Development Tool:** Android Studio

## 12. License

This project was developed for educational purposes. Unless a separate license is provided, all rights to the project remain with its author.
