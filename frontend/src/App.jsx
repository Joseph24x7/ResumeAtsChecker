import React, {useState} from 'react';
import axios from 'axios';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {FaFileUpload} from 'react-icons/fa'; // Importing icons for better visuals

function App() {
    const [resume, setResume] = useState(null);
    const [jobDescription, setJobDescription] = useState(null);
    const [jobDescriptionText, setJobDescriptionText] = useState(''); // State for plain text job description
    const [result, setResult] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false); // Loading state for spinner

    const handleFileChange = (e, setFile) => {
        setFile(e.target.files[0]);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(null);
        setResult(null);
        setLoading(true);

        if (!resume || (!jobDescription && !jobDescriptionText)) {
            setError('Please upload a resume and provide a job description (file or plain text).');
            setLoading(false);
            return;
        }

        const formData = new FormData();
        formData.append('resume', resume);
        if (jobDescription) {
            formData.append('jobDescription', jobDescription);
        } else {
            formData.append('jobDescriptionText', jobDescriptionText);
        }

        try {
            const response = await axios.post('https://resumevalidator.onrender.com/api/analyze', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });
            setResult(response.data);
        } catch (err) {
            if (err.response && err.response.data && err.response.data.error) {
                setError(err.response.data.error);
            } else {
                setError('An error occurred while analyzing the files.');
            }
        } finally {
            setLoading(false);
        }
    };

    const handleClear = () => {
        setResume(null);
        setJobDescription(null);
        setJobDescriptionText('');
        setResult(null);
        setError(null);

        // Clear file input fields
        document.getElementById('resumeInput').value = '';
        document.getElementById('jobDescriptionInput').value = '';
    };

    return (
        <div className="container mt-5">
            <header className="text-center mb-4">
                <h1 className="display-4">Resume ATS Checker</h1>
                <p className="lead">Optimize your resume for Applicant Tracking Systems</p>
            </header>

            <form onSubmit={handleSubmit} className="card p-4 shadow-sm mb-4">
                <div className="mb-3">
                    <label className="form-label">Upload Resume:</label>
                    <div className="input-group">
                        <span className="input-group-text"><FaFileUpload/></span>
                        <input id="resumeInput" type="file" className="form-control" accept=".pdf,.doc,.docx,.txt,.odt,.rtf"
                               onChange={(e) => handleFileChange(e, setResume)}/>
                    </div>
                    <small className="form-text text-muted">Supported formats: PDF, DOC, DOCX, TXT, ODT, RTF</small>
                </div>
                <div className="mb-3">
                    <label className="form-label">Upload Job Description (File):</label>
                    <div className="input-group">
                        <span className="input-group-text"><FaFileUpload/></span>
                        <input id="jobDescriptionInput" type="file" className="form-control" accept=".pdf,.doc,.docx,.txt,.odt,.rtf"
                               onChange={(e) => handleFileChange(e, setJobDescription)}/>
                    </div>
                    <small className="form-text text-muted">Supported formats: PDF, DOC, DOCX, TXT, ODT, RTF</small>
                </div>
                <div className="mb-3">
                    <label className="form-label">Or Enter Job Description (Plain Text):</label>
                    <textarea className="form-control" rows="5" value={jobDescriptionText}
                              onChange={(e) => setJobDescriptionText(e.target.value)}
                              placeholder="Enter job description here..."></textarea>
                </div>
                <div className="d-flex justify-content-between">
                    <button type="submit" className="btn btn-primary">Analyze</button>
                    <button type="button" className="btn btn-secondary" onClick={handleClear}>Clear All</button>
                </div>
            </form>

            {loading && <div className="text-center my-4">
                <div className="spinner-border" role="status"><span className="visually-hidden">Loading...</span></div>
            </div>}

            {error && <p className="alert alert-danger">{error}</p>}

            {result && (
                <div className="card shadow-sm">
                    <div className="card-body">
                        <h2 className="card-title">Analysis Result</h2>
                        <div className="progress mb-3">
                            <div
                                className={`progress-bar ${result.matchScore < 40 ? 'bg-danger' : 'bg-success'}`}
                                role="progressbar"
                                style={{width: `${result.matchScore}%`}}
                                aria-valuenow={result.matchScore}
                                aria-valuemin="0"
                                aria-valuemax="100"
                            >
                                {result.matchScore}%
                            </div>
                        </div>
                        <div>
                            <h3>Matched Skills:</h3>
                            <p>{result.matchedSkills.join(', ')}</p>
                        </div>
                        <div>
                            <h3>Missing Skills:</h3>
                            <p>{result.missingSkills.join(', ')}</p>
                        </div>
                    </div>
                </div>
            )}

            <footer className="text-center mt-5">
                <p>&copy; 2025 Resume ATS Checker. All rights reserved.</p>
            </footer>
        </div>
    );
}

export default App;
