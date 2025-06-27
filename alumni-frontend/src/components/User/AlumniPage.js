import React, { useEffect, useState } from 'react';
import { useAuth } from '../../context/AuthContext.js';
import { useNavigate } from 'react-router-dom';
import { fetchWithToken } from '../../utils/api.js';
import '../../styles/alumniPage.css';

const AlumniPage = () => {
    const { role } = useAuth();
    const [user, setUser] = useState(null);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    console.log("Rendering Alumni Page");

    useEffect(() => {
        if (role === 'ROLE_ALUMNI') {
            const fetchAlumniData = async () => {
                try {
                    const response = await fetchWithToken('/user/profile', { method: 'GET' });
                   
                    const data = await response.data;
                    setUser(data);
                } catch (error) {
                    setError('Error fetching alumni data: ' + error.message);
                }
            };

            fetchAlumniData();
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
        <div className="alumni-container">
            <h2 className="alumni-header">Welcome, {user ? user.fullName : 'Alumni'}</h2>
            {error && <p className="error">{error}</p>}
            
            {role === 'ROLE_ALUMNI' && user ? (
                <div className="alumni-profile-info">
                    <p><strong>Full Name:</strong> {user.fullName}</p>
                    <p><strong>Graduation Year:</strong> {user.graduationYear}</p>
                    <p><strong>Field of Study:</strong> {user.fieldOfStudy}</p>

                    <h3 className="alumni-resources-header">Alumni Resources</h3>
                    <ul className="alumni-resources-list">
                        <li>Networking Opportunities</li>
                        <li>Job Postings</li>
                        <li>Alumni Mentorship</li>
                    </ul>

                    {/* Button for viewing profile */}
                    <button className="view-profile-button" onClick={handleViewProfile}>View Profile</button>

                    {/* Button for going to events */}
                    <button className="events-button" onClick={handleGoToEvents}>Go to Events</button>
                    <button className="logout-button" onClick={handleLogout}>Logout</button>
                </div>
            ) : (
                <p>Loading alumni data...</p>
            )}

            {role !== 'ROLE_ALUMNI' && <p>You do not have access to this page.</p>}
        </div>
    );
};

export default AlumniPage;
