import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../../styles/adminPage.css'; 

const AdminPage = () => {
    const [user, setUser] = useState(null);
    const [users, setUsers] = useState([]); 
    const [error, setError] = useState('');
    const navigate = useNavigate();

    
   
    const fetchAdminData = async () => {
        try {
            const token = localStorage.getItem('token');
            const response = await fetch('http://localhost:8081/user/profile', {
                method: 'GET',
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
            });
            if (!response.ok) {
                throw new Error('Failed to fetch admin data');
            }
            const data = await response.json();
            setUser(data);
        } catch (error) {
            setError('Error fetching admin data: ' + error.message);
        }
    };

  
    const fetchAllUsers = async () => {
        try {
            const token = localStorage.getItem('token');
            const response = await fetch('http://localhost:8081/admin/users', {
                method: 'GET',
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
            });
            if (!response.ok) {
                throw new Error('Failed to fetch users');
            }
            const data = await response.json();
            setUsers(data);
        } catch (error) {
            setError('Error fetching users: ' + error.message);
        }
    };

    useEffect(() => {
        fetchAdminData(); 
        fetchAllUsers();
    }, []); 

    const handleViewProfile = () => {
        navigate('/user/profile');
    };

    const handleGoToEvents = () => {
        navigate('/events');
    };

    const handlePromoteUser = async (username) => {
        const token = localStorage.getItem('token');
        try {
            const response = await fetch(`http://localhost:8081/admin/promote/${username}`, {
                method: 'PUT',
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
            });

            if (response.ok) {
                alert('User promoted to Admin successfully!');
                fetchAllUsers(); // Refetch user list after promotion
            } else {
                const errorMessage = await response.text();
                alert(errorMessage);
            }
        } catch (error) {
            alert('Error promoting user: ' + error.message);
        }
    };

    const handleLogout = () => {
        localStorage.removeItem('authToken');
        navigate('/auth/login');
    };


   

    return (
      <div className="admin-container">
      <h2 className="admin-header">Welcome, {user ? user.fullName : 'Admin'}</h2>
      {error && <p className="error">{error}</p>}
      {user ? (
          <div className="admin-profile-info">
              <p><strong>Full Name:</strong> {user.fullName}</p>
              <p><strong>Role:</strong> Admin</p>
              <button className="view-profile-button" onClick={handleViewProfile}>
                  View Profile
              </button>
              <button className="events-button" onClick={handleGoToEvents}>
                  Go to Events
              </button>
          </div>
      ) : (
          <p>Loading...</p>
      )}
  
      <h3>All Users</h3>
      {users.length === 0 ? (
          <p>No users found.</p>
      ) : (
          <div className="user-table">
              <div className="user-table-header">
                  <div className="user-column">Full Name</div>
                  <div className="user-column">Username</div>
                  <div className="user-column">Role</div>
                  <div className="user-column">Actions</div>
              </div>
              {users.map((user) => (
                  <div className="user-table-row" key={user.username}>
                      <div className="user-column">{user.fullName}</div>
                      <div className="user-column">{user.username}</div>
                      <div className="user-column">{user.role}</div>
                      <div className="user-column">
                          <button
                              onClick={() => handlePromoteUser(user.username)}
                              disabled={user.role === 'ADMIN'}
                          >
                              {user.role === 'ADMIN' ? 'Already Admin' : 'Promote to Admin'}
                          </button>
                         
                      </div>
                  </div>
              ))}
          </div>
      )}
  
      <button className="logout-button" onClick={handleLogout}>Logout</button>
  </div>
  
    );
};

export default AdminPage;
