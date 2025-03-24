# 🏥 **Medical Management System**  

The **Medical Management System** is a **Java-based desktop application** designed to help manage **patients, medical facilities, and procedures** efficiently. Built using **Java Swing**, it provides an interactive **Graphical User Interface (GUI)** with features like **data entry, updating, and file management** for hospitals, clinics, and other healthcare organizations.  

---

## 🚀 **Features**  

✅ **Patient Management** – Store and manage patient details such as name, age, contact, and medical history.  
✅ **Facility Management** – Track healthcare facilities, including hospitals, clinics, and their capacities.  
✅ **Procedure Management** – Maintain a record of medical procedures, their costs, and durations.  
✅ **Medical Facility Details** – List all available facilities with their services.  
✅ **Data Persistence** – Save and load records using file handling techniques.  
✅ **User-Friendly Interface** – Uses **tables, buttons, and text fields** for seamless interaction.  

---

## 🏡 **Project Structure**  

The project consists of several **core classes**, each responsible for a specific feature of the system.  

### 🔹 **Core Classes and Their Responsibilities**  

#### 1️⃣ **MedicalGUI.java** 🖥️ (Main GUI Class)  
- The **entry point** of the application.  
- Implements **Java Swing** components like `JTabbedPane`, `JTable`, and `JButton`.  
- Handles **user interactions**.  
- Implements **file reading and writing** to store records permanently.  
- Supports **CSV/Text file** format for exporting and importing data.  
- Ensures data **persistence across sessions**.  

#### 2️⃣ **HealthService.java** ⚙️ (Data Management)  
- Responsible for **managing all records**, including **patients, facilities, and procedures**.  
- Implements **file handling** methods for saving and retrieving data.  
- Provides core functionalities like **adding, deleting, and updating records**.  

#### 3️⃣ **Patient.java** 👨‍⚕️ (Patient Details)  
- Represents an **individual patient** with attributes:  
  - `name` – Full name of the patient.  
  - `age` – Age of the patient.  
  - 'iselective'- check elective or not.

#### 4️⃣ **MedicalFacility.java** 🏥 (Facility Management)  
- Represents a **hospital or clinic** with attributes:  
  - `name` – Name of the facility.  
  - `isPrivate` – Indicates if the facility is **private or public**.  
  - `costOfProcedures` – Estimated cost for medical procedures.  

#### 6️⃣ **Procedure.java** ⚕️ (Medical Procedures)  
- Represents a **specific medical procedure** with details:  
  - `procedureName` – Name of the procedure.  
  - `cost` – The estimated cost of the treatment.  
  - `duration` – Time required to complete the procedure.  

### **Operations in Procedure Management:**  
1️⃣ **Visit** – Admits the patient to a **hospital or clinic**.  
2️⃣ **Operate** – Performs **medical procedures** on the patient (for hospitals).  

## 🖥️ **Graphical User Interface (GUI) Overview**  

The **Medical Management System** provides a **well-structured** and **interactive** GUI with multiple components:  

- **JTabbedPane** – Divides the interface into **three sections**:  
  - **Patients** 🏥  
  - **Facilities** 🏨  
  - **Procedures** ⚕️  
  - **Visit/Operate**  
- **JTable** – Displays data in a **tabular format** for easy viewing.  
- **JTextArea** – Shows logs, system messages, and notifications.  
- **JButton** – Allows **adding, editing, deleting, and saving** records.  
- **JFileChooser** – Enables **import/export** of records from files.  
- **JScrollPane** – Provides **scrolling capabilities** for handling large datasets.  
- **JDialog Boxes** and **JPanels** – Used for interactive user dialogs and layouts.  

---

## 📚 **File Handling & Data Persistence**  

The system supports **file-based storage**, ensuring that user data is saved across multiple sessions.  

- **Save Data** 📝 – Patient, facility, and procedure records are stored in text files.  
- **Load Data** 📂 – Records are automatically retrieved when the application starts.  
- **File Selection** 🗁 – Users can manually choose files using a **file selection dialog**.  

---

## 🎯 **How to Run the Application**  

1️⃣ **Install Java** – Ensure **Java 8 or higher** is installed on your system.  
2️⃣ **Clone or Download the Project** – Use Git or download the ZIP file.  
   ```bash
   git clone https://github.com/your-repo/MedicalManagementSystem.git
   cd MedicalManagementSystem
   ```  
3️⃣ **Compile the Source Code** – Run the following command to compile the Java files:  
   ```bash
   javac MedicalGUI.java HealthService.java Patient.java Facility.java MedicalFacility.java Procedure.java FileHandler.java
   ```  
4️⃣ **Run the Application** – Launch the system with:  
   ```bash
   java MedicalGUI
   ```  

---

## 📚 **References & Documentation**  

- [Java Swing API](https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html)  
- [JTable Documentation](https://docs.oracle.com/javase/8/docs/api/javax/swing/JTable.html)  
- [Java File Handling Guide](https://www.geeksforgeeks.org/file-handling-in-java/)  

---

## 🎯 **Future Enhancements**  

🔹 **Database Integration** – Replace file-based storage with **MySQL or MongoDB**.  
🔹 **User Authentication** – Implement **login/logout functionality** for security.  
🔹 **Search & Filter** – Add search functionality for quick record retrieval.  
🔹 **Report Generation** – Generate medical reports in **PDF format**.  

---

## 💡 **Contributions & Feedback**  

This project is **open-source**, and contributions are welcome! If you find a bug or have an improvement idea:  

1️⃣ **Fork the repository.**  
2️⃣ **Create a new branch.**  
3️⃣ **Make modifications and submit a pull request.**  

For questions or feedback, **open an issue** in the repository.  

🚀 **Happy Coding!** 🎉💻  

---

