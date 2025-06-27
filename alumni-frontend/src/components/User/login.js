import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext.js';
import '../../styles/AuthPage.css';
import {  postWithToken } from '../../utils/api.js';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const { login } = useContext(AuthContext); 
    const navigate = useNavigate();


    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await postWithToken('/auth/login', { username, password });
            

           if (!response.ok) {
                if (response.status === 401) {
                    setError('Invalid username or password');
                    return;
                } else {
                    throw new Error(`Login failed with status ${response.status}`);
                }
            }
    
            const data = await response.json();
    
            let token = data.token;
            console.log('User Role:', data.role);
            
            if (token && token.startsWith("Bearer ")) {
                token = token.slice(7); 
            }
    
            console.log('Token received:', token);
    
            if (token) {
                localStorage.setItem('token', token);
                login(token, data.role);
    
               
                if (data.role === 'ROLE_ALUMNI') {
                    navigate('/alumni');
                } else if (data.role === 'ROLE_STUDENT') {
                    navigate('/student');
                } else if (data.role === 'ROLE_ADMIN') {
                    navigate('/admin');
                } else {
                    navigate('/');
                }
            } else {
                throw new Error('Invalid token received');
            }
        } catch (error) {
            setError('An error occurred. Please try again later.');
            console.error('Login error:', error);
        }
    };
    

    return (
        <div className="auth-page">
            <div className="content">
                <h1 className="title">Login to Alumni Association</h1>
                <form onSubmit={handleLogin}>
                    <input
                        type="text"
                        placeholder="Username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        className="input-field"
                    />
                    <input
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="input-field"
                    />
                    <button type="submit" className="btn login-btn">Login</button>
                </form>
                <div className="redirect">
                    <p>Don't have an account? <a href="/auth/register" className="link">Register here</a></p>
                </div>
            </div>
        </div>
    );
};

export default Login;
