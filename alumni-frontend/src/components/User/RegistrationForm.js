import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../../styles/AuthPage.css'; 
import { postWithToken } from '../../utils/api.js';

const RegistrationForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [graduationYear, setGraduationYear] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const navigate = useNavigate();

 

  const handleSubmit = async (e) => {
    e.preventDefault();

 
    if (!username || !password || !graduationYear) {
        setError('All fields are required!');
        return;
    }

    const currentYear = new Date().getFullYear();
    const role = graduationYear > currentYear ? 'STUDENT' : 'ALUMNI';

   
    const graduationYearInt = parseInt(graduationYear, 10);
    if (isNaN(graduationYearInt)) {
        setError('Graduation year must be a valid number!');
        return;
    }

    try {
        
        const response = await postWithToken('/auth/register', {username, password, graduationYear: graduationYearInt, role });
        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Registration failed');
        }

        const data = await response.json();
        console.log(data);
        setSuccess(data.message);
        setError('');
        setUsername('');
        setPassword('');
        setGraduationYear('');
    } catch (error) {
        setError(error.message);
        setSuccess('');
    }
};


  const handleReturnToLogin = () => {
    navigate('/auth/login');
  };

  return (
    <div className="auth-page">
      <div className="content">
        <h1 className="title">Create an Account</h1>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="input-field"
            required
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="input-field"
            required
          />
          <input
            type="number"
            placeholder="Graduation Year"
            value={graduationYear}
            onChange={(e) => setGraduationYear(e.target.value)}
            className="input-field"
            required
          />
          <button type="submit" className="sign-in">Sign In</button>
        </form>

        {error && <p style={{ color: 'red' }}>{error}</p>}
        {success && (
          <div>
            <p style={{ color: 'green' }}>User registered successfully!</p>
            <button onClick={handleReturnToLogin} className="btn login-btn">
              Return to Login Page
            </button>
          </div>
        )}

        <div className="redirect">
          <p>Already have an account? <a href="/auth/login" className="link">Login here</a></p>
        </div>
      </div>
    </div>
  );
};

export default RegistrationForm;
