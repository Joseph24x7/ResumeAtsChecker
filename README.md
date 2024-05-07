# ResumeATSchecker
ResumeATSchecker is a Java-based web application that utilizes the Apache OpenNLP library for keyword extraction and matching against the contents of a resume PDF file. The application takes a resume PDF and a job description as inputs, and then extracts the text from the PDF file and identifies the relevant keywords from the job description using OpenNLP. It then calculates the percentage of matched keywords in the resume and identifies any unmatched keywords.

The project is designed to assist job seekers in ensuring that their resumes are optimized for applicant tracking systems (ATS) used by many employers. By identifying the relevant keywords from the job description, the application helps job seekers to tailor their resumes to meet the requirements of the specific job.

## Getting Started
### Prerequisites

To build and run the application, you will need:

- Java 17
- Apache Maven

## Installation:

- Clone this repository to your local machine
- Navigate to the project directory
- Run mvn package to build the application
- Run java -jar target/resumeatschecker.jar to start the application

## Usage:

- Open a REST API client tool such as Postman
- Send a POST request to http://localhost:8001/check-resume
- Attach a resume PDF file to the request using the "file" parameter
- Provide the job description as a string using the "desc" parameter
- Send the request
- The application will return the percentage of matched keywords and any unmatched keywords found in the resume in JSON format.

## Acknowledgments
This project was inspired by the need to optimize resumes for ATS systems, and is made possible by the Apache OpenNLP library.
