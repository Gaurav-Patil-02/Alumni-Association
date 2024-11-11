
import React, { useEffect, useState } from 'react';
import { useAuth } from '../../context/AuthContext.js';
import { useNavigate } from 'react-router-dom';
import { fetchWithToken } from '../../utils/api.js';
import '../../styles/studentPage.css';

const StudentPage = () => {
    const { role } = useAuth();
    const [user, setUser] = useState(null);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    console.log("Rendering Student Page");

    useEffect(() => {
        if (role === 'ROLE_STUDENT') {
            const fetchStudentData = async () => {
                try {
                    const response = await fetchWithToken('/user/profile', { method: 'GET' });
                    if (!response.ok) {
                        throw new Error('Failed to fetch student data');
                    }
                    const data = await response.json();
                    setUser(data);
                } catch (error) {
                    setError('Error fetching student data: ' + error.message);
                }
            };

            fetchStudentData();
        }
    }, [role]);

    const handleViewProfile = () => {
        navigate('/user/profile');
    };

    const handleGoToEvents = () => {
        navigate('/events');
    };

    const handleLogout = () => {
      localStorage.removeItem('token');
      navigate('/auth/login');
  };

    return (
        <div className="student-container">
            <h2 className="student-header">Welcome, {user ? user.fullName : 'Student'}</h2>
            {error && <p className="error">{error}</p>}
            
            {role === 'ROLE_STUDENT' && user ? (
                <div className="student-profile-info">
                    <p><strong>Full Name:</strong> {user.fullName}</p>
                    <p><strong>Graduation Year:</strong> {user.graduationYear}</p>
                    <p><strong>Field of Study:</strong> {user.fieldOfStudy}</p>

                    <h3 className="student-resources-header">Student Resources</h3>
                    <ul className="student-resources-list">
                        <li>Career Counseling</li>
                        <li>Internship Opportunities</li>
                        <li>Resume Building Workshop</li>
                    </ul>

                    {/* Button for viewing profile */}
                    <button className="view-profile-button" onClick={handleViewProfile}>View Profile</button>

                    {/* Button for going to events */}
                    <button className="events-button" onClick={handleGoToEvents}>Go to Events</button>
                    <button className="logout-button" onClick={handleLogout}>Logout</button>
                </div>
            ) : (
                <p>Loading student data...</p>
            )}

            {role !== 'ROLE_STUDENT' && <p>You do not have access to this page.</p>}
        </div>
    );
};

export default StudentPage;
