import React, { useState } from 'react';
import axios from 'axios';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { FaFileUpload } from 'react-icons/fa'; // Importing icons for better visuals

function App() {
  const [resume, setResume] = useState(null);
  const [jobDescription, setJobDescription] = useState(null);
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

    if (!resume || !jobDescription) {
      setError('Please upload both resume and job description.');
      setLoading(false);
      return;
    }

    const formData = new FormData();
    formData.append('resume', resume);
    formData.append('jobDescription', jobDescription);

    try {
      const response = await axios.post('http://localhost:8001/api/analyze', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      setResult(response.data);
    } catch (err) {
      setError('An error occurred while analyzing the files.');
    } finally {
      setLoading(false);
    }
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
            <span className="input-group-text"><FaFileUpload /></span>
            <input type="file" className="form-control" accept="application/pdf" onChange={(e) => handleFileChange(e, setResume)} />
          </div>
        </div>
        <div className="mb-3">
          <label className="form-label">Upload Job Description:</label>
          <div className="input-group">
            <span className="input-group-text"><FaFileUpload /></span>
            <input type="file" className="form-control" accept="application/pdf" onChange={(e) => handleFileChange(e, setJobDescription)} />
          </div>
        </div>
        <button type="submit" className="btn btn-primary w-100">Analyze</button>
      </form>

      {loading && <div className="text-center my-4"><div className="spinner-border" role="status"><span className="visually-hidden">Loading...</span></div></div>}

      {error && <p className="alert alert-danger">{error}</p>}

      {result && (
        <div className="card shadow-sm">
          <div className="card-body">
            <h2 className="card-title">Analysis Result</h2>
            <div className="progress mb-3">
              <div
                className={`progress-bar ${result.matchScore < 40 ? 'bg-danger' : 'bg-success'}`}
                role="progressbar"
                style={{ width: `${result.matchScore}%` }}
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
