import React, { useContext } from 'react';
import '../../styles/home.css'; // Importing the same CSS file for styling
import { AuthContext } from '../../context/AuthContext.js'; // Assuming you have an AuthContext

const Home = () => {
    const { isAuthenticated } = useContext(AuthContext);
    console.log('Home component rendered. User Authenticated:', isAuthenticated);

    return (
        <div className="home-container">
            <header className="home-header">
                <h1>Welcome to the Alumni Association</h1>
                <nav>
                    <ul>
                        {isAuthenticated ? (
                            <li><a href="/user/profile">Profile</a></li>
                        ) : (
                            <li><a href="/auth/login">Login</a></li>
                        )}
                        <li><a href="/events">Events</a></li>
                        <li><a href="/about">About Us</a></li>
                    </ul>
                </nav>
            </header>

            <main className="home-main">
                <section className="intro">
                    <h2>About Us</h2>
                    <p>We are dedicated to connecting and supporting our alumni community.</p>
                </section>

                <section className="featured-alumni">
                    <h2>Featured Alumni</h2>
                    <div className="alumni-cards">
                        {/* Add alumni cards here */}
                        <div className="alumni-card">
                            <h3>John Doe</h3>
                            <p>Class of 2005</p>
                            <p>CEO at ABC Corp</p>
                        </div>
                        <div className="alumni-card">
                            <h3>Jane Smith</h3>
                            <p>Class of 2010</p>
                            <p>CTO at XYZ Inc</p>
                        </div>
                    </div>
                </section>

                <section className="events">
                    <h2>Upcoming Events</h2>
                    <ul>
                        <li>Annual Alumni Gathering - December 10, 2024</li>
                        <li>Networking Event - January 15, 2025</li>
                    </ul>
                </section>

                <div className="cta">
                    <button onClick={() => window.location.href='/auth/register'}>Join Us Today!</button>
                </div>
            </main>

            <footer className="home-footer">
                <p>Contact us: info@alumniassociation.com</p>
                <p>Follow us on social media!</p>
            </footer>
        </div>
    );
};

export default Home;
