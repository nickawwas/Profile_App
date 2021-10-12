# Consultation_App

Build a Simple Android App with a SQLite Database

## Documentation

### Overview

> Simple Android App with Two Activities and SQLite Database
> Main Activity - Launcher Activity
>
> - Display Total Number of Profiles Stored in DB
> - Toggle Between Profile and Id Display Mode
>   - Profile Display Mode (Default): Displays Profile Surname followed by Name, Sorted Alphabetically
>   - Id Display Mode: Displays Id, Sorted Numerically
> - Display Sorted, Clickable List of All User Profiles
>   - Click User Profile to Open Profile Activity
> - Floating Action Button Opens Dialog Fragment
>   - Fill Dialog Fragment Form
>   - Can Add User Profile Entry to DB

> Profile Activity - Child Activity of Main
>
> - Display Details of Selected User Profile
> - Display Scrollable List of All Times Profile Was Accessed
>   - Includes Date, Time, Access Type (Created, Opened, Closed, Deleted)
> - Navigate Back to Main Activity with Up Navigation

> Insert Profile Dialog Fragment

### Database

> Database Helper
>
> - Create SQLite DB with Two Tables

> Profile Table Schema

| ProfileID (PK) | Name | Surname | Profile GPA | Profile Creation Date |
| -------------- | ---- | ------- | ----------- | --------------------- |

> Access Table Schema

| AccessID (PK) | ProfileID (FK) | Access Type | Time Stamp |
| ------------- | -------------- | ----------- | ---------- |

## Dependencies

Used SQLite to Create a Database and Gradle to Build the Java Project
