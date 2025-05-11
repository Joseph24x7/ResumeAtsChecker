# Resume ATS Checker

Resume ATS Checker is a web application designed to help job seekers optimize their resumes for Applicant Tracking Systems (ATS). The application analyzes a resume PDF and a job description to identify relevant keywords, calculate the match percentage, and highlight missing keywords. This tool empowers users to tailor their resumes to specific job requirements, increasing their chances of passing ATS filters.

## Features

- Extracts text from resume PDFs and job descriptions.
- Identifies matched and unmatched keywords using Apache OpenNLP.
- Calculates the match percentage between the resume and job description.
- Provides a user-friendly frontend for uploading files and viewing results.
- REST API for backend analysis.

## Libraries Used

### Backend:
- **Spring Boot**: Framework for building the backend REST API.
- **Apache OpenNLP**: Library for natural language processing and keyword extraction.
- **Maven**: Build and dependency management tool.

### Frontend:
- **React**: JavaScript library for building the user interface.
- **Vite**: Development environment for fast builds.
- **Bootstrap**: CSS framework for responsive design.
- **React Icons**: Library for adding icons to the UI.

## Getting Started

### Prerequisites

To build and run the application, you will need:

- **Java 17**
- **Apache Maven**

### Installation and Usage

#### Step 1: Clone the Repository

1. Open a terminal and run the following command to clone the repository:
   ```bash
   git clone https://github.com/your-repo/ResumeATSchecker.git
   ```
2. Navigate to the project directory:
   ```bash
   cd ResumeATSchecker
   ```

#### Step 2: Build and Start the Application

1. Clean and build the application using Maven:
   ```bash
   mvn clean install
   ```
2. Start the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```
3. Open your browser and navigate to:
   ```
   http://localhost:8001
   ```

#### Step 3: Use the Application

1. On the frontend, upload your resume PDF and job description as files.
2. Click the "Analyze" button.
3. View the match percentage and keyword analysis results.

### Screenshots

#### Initial View
![Initial View](frontend/screenshots/initial-view.png)

#### File Upload
![File Upload](frontend/screenshots/file-upload.png)

#### Analysis Result
![Analysis Result](frontend/screenshots/analysis-result.png)

## Project Structure

### Backend
- **`src/main/java/com/resume/ats/check`**: Contains the main application, controllers, models, and utility classes.
- **`src/main/resources`**: Configuration files and NLP models.

### Frontend
- **`frontend/src`**: React components and styles.
- **`frontend/public`**: Static assets.

## Acknowledgments

This project was made possible by the following libraries and tools:

- **Apache OpenNLP**: For natural language processing.
- **React**: For building the user interface.
- **Bootstrap**: For responsive design.
- **Spring Boot**: For backend development.
- **Maven**: For build and dependency management.
- **Vite**: For fast frontend development.

## License

This project is licensed under the MIT License.
