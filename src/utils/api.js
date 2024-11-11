// src/utils/api.js
const API_URL = 'http://localhost:8081';

export const fetchWithToken = async (endpoint, options = {}) => {
    const token = localStorage.getItem('token');
    console.log(token); 
    const headers = {
        'Content-Type': 'application/json',
        ...options.headers,
    };

    if (token) {
        headers['Authorization'] = `Bearer ${token}`; // Include the token in the Authorization header
    }

    const response = await fetch(`${API_URL}${endpoint}`, {
        ...options,
        headers,
    });

    if (response.status === 401) {

        window.location.href = '/auth/login'; // Redirect to login
    }

    return response;
};

export const putWithToken = async (url, body) => {
    const token = localStorage.getItem('authToken');
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(body),
    });
    return response;
};

