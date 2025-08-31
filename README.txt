LoginApp — Java Swing Login (NetBeans-friendly)
================================================
This project contains a complete, runnable Java Swing login application.
It includes:
  - LoginForm (JFrame) with username & password fields, tooltips, centered window.
  - AuthService (simple in-memory "database"; easy to replace with real DB).
  - Dashboard (JFrame) shown after successful login.
  - HashUtil (for password hashing; you can switch to plain text if desired).
  - Main (LoginApp) that launches the UI with Nimbus look & feel.

How to open in NetBeans
-----------------------
1) Open NetBeans IDE.
2) File > Open Project... > select the 'LoginApp' folder in this zip.
   (If NetBeans expects an Ant structure, you can create a new "Java with Ant > Java Application"
   project named LoginApp, then copy the 'src/loginapp' folder into the project's 'src' and refresh.)
3) Right-click the project > Clean and Build.
4) Run (F6).

How to run from the command line
--------------------------------
1) Ensure you have a JDK (17+ recommended).
2) cd into this folder and compile:
     javac -d out $(find src -name "*.java")
3) Run:
     java -cp out loginapp.LoginApp

Default credentials (stored in AuthService)
-------------------------------------------
- admin / admin123
- tejal / tejal@123

Notes
-----
* The UI uses GroupLayout and matches the required components: title, username, password, and Login button.
* Window size is set to 400x300 and centered.
* Tooltips added to both fields.
* You can add DB connectivity later by replacing AuthService's store with JDBC calls.
