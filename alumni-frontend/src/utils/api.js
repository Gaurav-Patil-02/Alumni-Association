import axios from "axios";

const API_URL = 'http://localhost:8081';

export const fetchWithToken = async (endpoint) => {
    const token = localStorage.getItem('token');
    const response = await axios.get(`${API_URL}${endpoint}`, {
        headers: { 'Authorization': `Bearer ${token}`,
                  'Content-Type': 'application/json', },
      
    });

    console.log("Fetch API: ", response.data );
    return response;
};




export const putWithToken = async (url, body) => {
    const token = localStorage.getItem('token');
    const response = await fetch(`${API_URL}${url}`, {
        method: 'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(body),
    });
    return response;
};

export const postWithToken = async (url, body) => { 

    if(url === "/auth/register" || url === '/auth/login'){
        const response = await fetch(`${API_URL}${url}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(body),
        });
        return response;
    }

    
    const token = localStorage.getItem('token');
    const response = await fetch(`${API_URL}${url}`, {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(body),
    });
    return response;
};
