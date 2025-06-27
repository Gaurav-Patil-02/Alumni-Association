
import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext.js';

const ProtectedRoute = ({ children, allowedRoles }) => {
    const { isAuthenticated, role } = useAuth();

    console.log("Rendering Protected Route Page");

   
    if (!isAuthenticated) {
        return <Navigate to="/auth/login" replace />;
    }

   
    if (!allowedRoles.includes(role)) {
        return <Navigate to="/" replace />;
    }

    return children;  
};

export default ProtectedRoute;
