import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { fetchWithToken } from '../../utils/api.js';
import '../../styles/updateProfile.css';

const UpdateProfile = () => {
    const initialValues = {
        fullName: '',
        graduationYear: '',
        fieldOfStudy: '',
        currentOccupation: '',
        profilePicture: ''
    };
    const { formData, setFormData, errors, handleInputChange, handleSubmit } = (initialValues);
    const [updateSuccess, setUpdateSuccess] = useState(false);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const response = await fetchWithToken('/user/profile', { method: 'GET' });
                if (!response.ok) throw new Error('Network response was not ok');
                
                const data = await response.json();
                setFormData(data);
            } catch (error) {
                setError('Error fetching profile: ' + error.message);
            }
        };
        fetchProfile();
    }, [setFormData]);

    const handleUpdate = async () => {
        const isValid = await handleSubmit(async (data) => {
            try {
                setLoading(true);
                const response = await fetchWithToken('/user/profile/update', {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(data)
                });
                if (!response.ok) throw new Error('Failed to update profile');
                
                setUpdateSuccess(true);
                navigate('/user/profile');
            } catch (error) {
                setError('Error updating profile: ' + error.message);
            } finally {
                setLoading(false);
            }
        });

        if (isValid) console.log('Form is valid and ready to submit');
    };

    return (
        <div className="profile-container">
            <h2>Update Profile</h2>
            {error && <p className="error">{error}</p>}
            <form onSubmit={(e) => { e.preventDefault(); handleUpdate(); }} className="update-profile-form">
                <div className="form-group">
                    <label>Full Name:</label>
                    <input type="text" name="fullName" value={formData.fullName} onChange={handleInputChange} />
                    {errors.fullName && <p className="error">{errors.fullName}</p>}
                </div>
                <div className="form-group">
                    <label>Graduation Year:</label>
                    <input type="text" name="graduationYear" value={formData.graduationYear} onChange={handleInputChange} />
                    {errors.graduationYear && <p className="error">{errors.graduationYear}</p>}
                </div>
                <div className="form-group">
                    <label>Field of Study:</label>
                    <input type="text" name="fieldOfStudy" value={formData.fieldOfStudy} onChange={handleInputChange} />
                    {errors.fieldOfStudy && <p className="error">{errors.fieldOfStudy}</p>}
                </div>
                <div className="form-group">
                    <label>Current Occupation:</label>
                    <input type="text" name="currentOccupation" value={formData.currentOccupation} onChange={handleInputChange} />
                    {errors.currentOccupation && <p className="error">{errors.currentOccupation}</p>}
                </div>
                <div className="form-group">
                    <label>Profile Picture:</label>
                    <input type="file" name="profilePicture" onChange={handleInputChange} />
                    {errors.profilePicture && <p className="error">{errors.profilePicture}</p>}
                </div>
                <button type="submit" className="btn" disabled={loading}>Update Profile</button>
                {loading && <p>Updating...</p>}
                {updateSuccess && <p className="success-message">Profile updated successfully!</p>}
            </form>
        </div>
    );
};

export default UpdateProfile;
