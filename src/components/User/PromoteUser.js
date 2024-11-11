
import React, { useEffect, useState } from 'react';
import { fetchWithToken, putWithToken } from '../../utils/api';
import '../../styles/promoteUser.css';

const PromoteUser = () => {
    const [users, setUsers] = useState([]);
    const [error, setError] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

    console.log("Rendering Promote User Page");

 
    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await fetchWithToken('/admin/users', { method: 'GET' });
                if (!response.ok) {
                    throw new Error('Failed to fetch users');
                }
                const data = await response.json();
                setUsers(data);
            } catch (error) {
                setError('Error fetching users: ' + error.message);
            }
        };

        fetchUsers();
    }, []);

    // Handle user promotion to admin
    const handlePromoteUser = async (username) => {
        try {
            const response = await putWithToken(`/admin/promote/${username}`, {});
            if (!response.ok) {
                const errorMessage = await response.text();
                throw new Error(errorMessage || 'Failed to promote user');
            }
            setSuccessMessage(`User ${username} promoted to admin successfully.`);
            setUsers(users.map(user => user.username === username ? { ...user, role: 'Admin' } : user));
        } catch (error) {
            setError('Error promoting user: ' + error.message);
        }
    };

    return (
        <div className="promote-user-container">
            <h2>Promote User to Admin</h2>
            {error && <p className="error-message">{error}</p>}
            {successMessage && <p className="success-message">{successMessage}</p>}
            <table className="user-table">
                <thead>
                    <tr>
                        <th>Full Name</th>
                        <th>Username</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {users.map(user => (
                        <tr key={user.username}>
                            <td>{user.fullName}</td>
                            <td>{user.username}</td>
                            <td>
                                {user.role !== 'Admin' && (
                                    <button 
                                        onClick={() => handlePromoteUser(user.username)} 
                                        className="promote-button"
                                    >
                                        Promote to Admin
                                    </button>
                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default PromoteUser;
