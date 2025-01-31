# SQL-Ex.ru local setup

This document explains how to set up a local SQLite database in JetBrains DataGrip or IntelliJ IDEA Ultimate Edition for practicing SQL queries on [SQL Exercises](https://www.sql-ex.ru/).

## VSCode Setup (Not the best IDE experience for SQL)

Jetbrains has the best SQL IDE support (IMO). However, if your computer RAM and SSD is not good enough for running Jetbrains IDE, you can also use SQL in VSCode using these extensions:
- [SQLTools](https://marketplace.visualstudio.com/items?itemName=mtxr.sqltools)
- [SQLTools SQLite](https://marketplace.visualstudio.com/items?itemName=mtxr.sqltools-driver-sqlite)

## SQLite database in JetBrains DataGrip / IntelliJ IDEA Ultimate Edition

An SQLite database is essentially a file. You can create an empty file, and turn it into a database.
1. Create an empty file with `.sqlite` or `.db` extension (works with any other extension too, does not matter). This will be our database.
    ![Create new file](./images/jetbrains_new_file.webp)
2. Add SQLite datasource in jetbrains
    ![Add datasource in jetbrains](./images/jetbrains_add_datasource.webp)
    ![DB file name](./images/jetbrains_file_name.webp)
3. Select the file you created in step 1.
    ![Select DB file](./images/jetbrains_select_db_file.webp)
4. Download missing driver files if required.
    ![Download missing driver files](./images/jetbrains_download_drivers.webp)
5. Create a new SQL console file (extension must be `.sql`). This is where you will write your SQL queries and run them. Also, select the database context and local dialect as SQLite for the console
    ![Create new SQL file](./images/jetbrains_new_sql_file.webp)
6. Prepare your local database for the exercises in https://www.sql-ex.ru/ .
    - Copy the SQL seed script for the respective database present in this folder with name `<database-name>.sql`. The headings below point to the respective files.
    - Paste it in the SQL console.
    - Run the script. Tip: You can select some text in the console, and press `Ctrl+Enter` to execute the selected text.
    - You can see the tables and data in the database explorer.
    ![Run SQL script](./images/jetbrains_run_sql_script.webp)

## Databases

These are the databases used by SQL Exercises. You can find the seed scripts in the respective files linked below.

### [Computer DB](./computer.sql)

```mermaid
erDiagram
    Product {
        string maker
        string type
        string model
    }
    PC {
        string model
        int speed
        int ram
        float hd
        string cd
        float price
        int code
    }
    Printer {
        string model
        string color
        string type
        float price
        int code
    }
    Laptop {
        string model
        int speed
        int ram
        float hd
        float price
        int screen
        int code
    }
    
    Product ||--o{ PC : "model"
    Product ||--o{ Printer : "model"
    Product ||--o{ Laptop : "model"
```

### [Ships DB](./ships.sql)

```mermaid
erDiagram
    Classes {
        string type
        string country
        integer numGuns
        real bore
        integer displacement
        string class
    }
    Battles {
        text date
        text name
    }
    Ships {
        string class
        integer launched
        text name
    }
    Outcomes {
        text result
        text ship
        text battle
    }

    Classes ||--o{ Ships : "class"
    Battles ||--o{ Outcomes : "battle:name"
    Ships ||--o{ Outcomes : "ship"
```

### [Inc Out DB](./inc_out.sql)

```mermaid
erDiagram
    Income {
        integer point
        text date 
        decimal inc
        integer code
    }
    Outcome_o {
        decimal out
        integer point
        text date
    }
    Outcome {
        integer point
        decimal out
        text date
        integer code
    }
    Income_o {
        decimal inc
        integer point
        text date
    }
```

### [Painting DB](./painting.sql)

```mermaid
erDiagram
    utV {
        text V_NAME
        text V_COLOR 
        integer V_ID
    }
    utQ {
        text Q_NAME
        integer Q_ID
    }
    utB {
        integer B_VOL
        text B_DATETIME
        integer B_Q_ID
        integer B_V_ID
    }

    utV ||--o{ utB : "B_V_ID"
    utQ ||--o{ utB : "B_Q_ID"
```

### [Aero DB](./aero.sql)

```mermaid
erDiagram
    Passenger {
        text name
        integer ID_psg
    }
    Company {
        text name
        integer ID_comp
    }
    Trip {
        integer ID_comp
        text plane
        text town_from
        text town_to
        text time_out
        text time_in
        integer trip_no
    }
    Pass_in_trip {
        text place
        integer trip_no
        text date
        integer ID_psg
    }

    Passenger }|--|| Pass_in_trip : "ID_psg"
    Company ||--o{ Trip : "ID_comp"
    Trip ||--o{ Pass_in_trip : "trip_no"
```
