import React, { useEffect, useState } from 'react';
import { fetchWithToken } from '../../utils/api.js';
import { useNavigate } from 'react-router-dom';
import '../../styles/profile.css';

const Profile = () => {
  
    const [user, setUser] = useState(null);
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(true); 
    const navigate = useNavigate();

    

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const response = await fetchWithToken('/user/profile', { method: 'GET' });
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const data = await response.json();
                setUser(data);
            } catch (error) {
                setError('Error fetching profile: ' + error.message);
                console.error('Error:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchProfile();
    }, []);

    const handleEditProfile = () => {
        navigate('/user/profile/update');
    };

    return (
        <div className="container">
            <h2>User Profile</h2>
            {error && <p className="error">{error}</p>}
            {loading ? (
                <p>Loading...</p>
            ) : user ? (
                <div className="profile-info">
                    <ul className="profile-details">
                        <li><strong>Full Name:</strong> {user.fullName}</li>
                        <li><strong>Graduation Year:</strong> {user.graduationYear}</li>
                        <li><strong>Field of Study:</strong> {user.fieldOfStudy}</li>
                        <li><strong>Current Occupation:</strong> {user.currentOccupation}</li>
                    </ul>

                    <div className="profile-picture-section">
                        <strong>Profile Picture:</strong>
                        <img
                            src={user.profilePicture || 'default-image-url'}
                            alt="Profile"
                            className="profile-picture"
                        />
                    </div>

                  

                    <button onClick={handleEditProfile} className="update-profile-button">Update Profile</button>
                </div>
            ) : (
                <p>No user data available.</p>
            )}
        </div>
    );
};

export default Profile;
