import React from 'react';
import { Link } from 'react-router-dom';
import '../../styles/LandingPage.css'

const LandingPage = () => {
  console.log("Rendering Landing Page");
  return (
    <div className="landing-page">
      <div className="content">
        <h1 className="title">Welcome to Alumni Association</h1>
        <p className="description">Connect with your alumni and stay updated!</p>
        <div className="buttons">
          <Link to="/auth/login">
            <button className="btn login-btn">Login</button>
          </Link>
          <Link to="/auth/register">
            <button className="btn register-btn">Register</button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default LandingPage;
