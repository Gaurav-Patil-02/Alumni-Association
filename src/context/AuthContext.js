
import React, { createContext, useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [role, setRole] = useState(null);
    const [token, setToken] = useState(localStorage.getItem('token')); 
    const navigate = useNavigate();

    useEffect(() => {
        if (token) {
            const decodedToken = JSON.parse(atob(token.split('.')[1])); 
            setRole(decodedToken.role); 
            setIsAuthenticated(true);
        } else {
            setIsAuthenticated(false); 
        }
    }, [token]);

    const login = (token) => {
        setToken(token);
        localStorage.setItem('token', token); 
        const decodedToken = JSON.parse(atob(token.split('.')[1]));
        setRole(decodedToken.role);
        setIsAuthenticated(true); 
    };

    const logout = () => {
        setToken(null);
        setRole(null);
        setIsAuthenticated(false);
        localStorage.removeItem('token'); 
        navigate("/auth/login");
    };

    return (
        <AuthContext.Provider value={{ isAuthenticated, role, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};


export const useAuth = () => {
    return useContext(AuthContext); 
};
