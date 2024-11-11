// src/App.js
import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext.js';
import AppRoutes from './routes/AppRoutes.js';

const App = () => {
    return (
      <Router>
        <AuthProvider>
          <AppRoutes />
        </AuthProvider>
      </Router>
    );
  }

export default App;
