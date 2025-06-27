import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { fetchWithToken, putWithToken } from '../../utils/api.js';
import '../../styles/updateProfile.css';

const UpdateProfile = () => {
    const initialValues = {
        fullName: '',
        graduationYear: '',
        fieldOfStudy: '',
        currentOccupation: '',
        profilePicture: null,
    };

    const [formData, setFormData] = useState(initialValues);
    const [errors, setErrors] = useState({});
    const [updateSuccess, setUpdateSuccess] = useState(false);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const response = await fetchWithToken('/user/profile', { method: 'GET' });
                const data = await response.data;
                console.log("Update profile response:", data);
                setFormData(data);
            } catch (error) {
                setError('Error fetching profile: ' + error.message);
            }
        };
        fetchProfile();
    }, []);


    const handleInputChange = (e) => {
        const { name, value, files } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: files ? files[0] : value,
        }));
    };

   
    const validateForm = () => {
        const newErrors = {};
        if (!formData.fullName) newErrors.fullName = 'Full Name is required';
        if (!formData.graduationYear) newErrors.graduationYear = 'Graduation Year is required';
        if (!formData.fieldOfStudy) newErrors.fieldOfStudy = 'Field of Study is required';
        if (!formData.currentOccupation) newErrors.currentOccupation = 'Current Occupation is required';
        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

   
    const handleUpdate = async () => {
        if (!validateForm()) {
            console.log("Validation failed:", errors);
            return;
        }

        try {
            setLoading(true);
            const response = await putWithToken('/user/profile/update', formData);
            setUpdateSuccess(true);
            navigate('/user/profile'); 
        } catch (error) {
            setError('Error updating profile: ' + error.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="profile-container">
            <h2>Update Profile</h2>
            {error && <p className="error">{error}</p>}
            <form onSubmit={(e) => { e.preventDefault(); handleUpdate(); }} className="update-profile-form">
                <div className="form-group">
                    <label>Full Name:</label>
                    <input
                        type="text"
                        name="fullName"
                        value={formData.fullName || ''}
                        onChange={handleInputChange}
                    />
                    {errors.fullName && <p className="error">{errors.fullName}</p>}
                </div>
                <div className="form-group">
                    <label>Graduation Year:</label>
                    <input
                        type="text"
                        name="graduationYear"
                        value={formData.graduationYear || ''}
                        onChange={handleInputChange}
                    />
                    {errors.graduationYear && <p className="error">{errors.graduationYear}</p>}
                </div>
                <div className="form-group">
                    <label>Field of Study:</label>
                    <input
                        type="text"
                        name="fieldOfStudy"
                        value={formData.fieldOfStudy || ''}
                        onChange={handleInputChange}
                    />
                    {errors.fieldOfStudy && <p className="error">{errors.fieldOfStudy}</p>}
                </div>
                <div className="form-group">
                    <label>Current Occupation:</label>
                    <input
                        type="text"
                        name="currentOccupation"
                        value={formData.currentOccupation || ''}
                        onChange={handleInputChange}
                    />
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
