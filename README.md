# Producer-Consumer Application

This project implements a **Producer-Consumer** pattern using a message queue in Core Java. It is designed to process messages asynchronously, where the **Producer** generates messages and the **Consumer** processes them. The application uses the SLF4J logging framework to log metrics every 5 minutes, ensuring efficient monitoring.

---

## Features
- **Message Queue**: Thread-safe communication between Producer and Consumer.
- **Thread Pool**: Manages threads efficiently using `ExecutorService`.
- **Metrics Logging**: Logs total successful messages and errors every 5 minutes using SLF4J.
- **Graceful Shutdown**: Ensures all threads shut down cleanly on application termination.

---

## Architecture

```plaintext
+------------------+                +------------------+
|   Producer Task  |   Messages     |   Consumer Task  |
| (Generates Data) +--------------->| (Processes Data) |
+------------------+                +------------------+
           |                                |
           |   Thread Pool (ExecutorService) |
           +---------------------------------+
```

---

## Prerequisites

### Tools and Libraries
- **Java 8 or higher**
- **SLF4J** for logging

---

## Setup and Usage

### Running the Application
1. **Obtain the JAR File**:
   - Ensure you have the compiled JAR file for the project.
   
2. **Execute the JAR**:
   - Run the following command in your terminal:
     ```bash
     java -jar MotaDataQueue.jar
     ```

3. **Monitor Logs**:
   - The application logs are generated in the `logs/` directory.
   - Metrics are logged every 5 minutes, detailing successful messages and errors.

### Expected Output
1. Producer generates messages:
   ```plaintext
   [INFO] Producer: Generated message "Hello, Vishu!"
   ```

2. Consumer processes messages:
   ```plaintext
   [INFO] Consumer: Processed message "Hello, Vishu!"
   ```

3. Metrics logged every 5 minutes:
   ```plaintext
   [INFO] Total Successful Messages: 100
   [INFO] Total Errors: 0
   ```

4. Application shutdown:
   ```plaintext
   [INFO] Application shutting down.
   ```

---

## Code Overview

### Key Components

1. **ProducerTask**:
   - Generates messages and adds them to the queue.

2. **ConsumerTask**:
   - Retrieves messages from the queue and processes them.

3. **MetricsConfig**:
   - Tracks successful messages and errors.
   - Logs metrics every 5 minutes using `ScheduledExecutorService`.

4. **Shutdown Hook**:
   - Cleans up resources and ensures graceful termination.

---

## Logging Configuration

The application uses **SLF4J** for logging. Logs are stored in the `logs/` directory. To modify the logging behavior, update the `logback.xml` file in the resources directory.

### Example logback.xml Configuration
```xml
<configuration>
    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>logs/application.log</file>
        <encoder>
            <pattern>%d [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="info">
        <appender-ref ref="FILE"/>
    </root>
</configuration>
```

---

## How It Works

1. The application starts with two main tasks:
   - Producer generates messages.
   - Consumer processes the messages.

2. A thread pool (`ExecutorService`) manages the Producer and Consumer tasks.

3. Metrics are logged every 5 minutes using a `ScheduledExecutorService`. This includes:
   - Total number of successfully processed messages.
   - Total number of errors encountered.

4. When the application receives a termination signal (e.g., `Ctrl+C`), the shutdown hook ensures all threads are stopped gracefully.

---

## How to Build from Source

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd producer-consumer-app
   ```


2. **Run the JAR**:
   ```bash
   java -jar Executable_JAR/MotaDataQueue.jar
   ```

---

## Troubleshooting

1. **Log File Not Created**:
   - Ensure the `logs/` directory exists and the application has write permissions.

2. **Application Not Starting**:
   - Check if Java 8 or higher is installed:
     ```bash
     java -version
     ```

3. **Metrics Not Logged**:
   - Verify the `logback.xml` configuration.

---

## Future Enhancements
- Add support for multiple Producers and Consumers.
- Implement a web-based dashboard for real-time monitoring of metrics.

---

## License
This project is licensed under the [MIT License](LICENSE).

---

Enjoy using the **Producer-Consumer Application**! 😊

