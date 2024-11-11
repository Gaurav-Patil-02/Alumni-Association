import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext.js';
import '../../styles/AuthPage.css';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const { login } = useContext(AuthContext); 
    const navigate = useNavigate();

    console.log("Rendering Logins Page");

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8081/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username, password }),
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || 'Login failed');
            }

            const data = await response.json();
            let token = data.token;
            console.log('User Role:', data.role);
            
            if (token.startsWith("Bearer ")) {
                token = token.slice(7); 
            }

            console.log('Token received:', token);
            if (token) {
                localStorage.setItem('token', token);
                login(token, data.role); 
                
               
                if (data.role === 'ROLE_ALUMNI') {
                    console.log("Redirecting to alumni")
                    navigate('/alumni');
                } else if (data.role === 'ROLE_STUDENT') {
                    navigate('/student');
                }else if(data.role ==='ROLE_ADMIN'){
                    navigate('/admin');
                } 
                else {
                    navigate('/');
                }
            } else {
                throw new Error('Invalid token received');
            }
        } catch (error) {
            setError('Invalid username or password');
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
