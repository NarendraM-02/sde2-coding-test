# Question 5 – Multithreaded Java Application

##  Problem Statement

Develop a Java-based multithreaded application to process six tasks (A–F) with the following execution flow:

- Tasks **A & B** should run in parallel.
- Tasks **C & D** should run in parallel **after A & B complete**.
- The **output of A & B** should be fed into **C & D**.
- The **output of C & D** should be fed into **F** for final processing.
- The application must handle **success and failure** modes for each task to ensure **robustness and reliability**.

---

##  Features & Enhancements

-  **Parallel execution** using `ExecutorService` and `Callable`
-  **Retry mechanism** for failed tasks (1 retry attempt allowed)
-  **Execution timing** logs for performance insights
-  **Structured logging** using `java.util.logging.Logger`
-  **Graceful shutdown** using executor termination hook
-  **Simulated failure modes** to test robustness

---

---

##  How to Run the Project

###  Prerequisites

- Java JDK 11 or later
- Terminal or Command Prompt

---

###  Step-by-Step 

#### 1. **Compile the Code**

javac EnhancedTask.java TaskRunnerE.java

### 2. **Run the Application**

java TaskRunnerE
