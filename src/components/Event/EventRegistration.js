import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { jwtDecode } from 'jwt-decode';
import { useParams } from 'react-router-dom';
import '../../styles/EventRegistration.css';

const EventRegistration = () => {
    const { id } = useParams();
    const [isRegistered, setIsRegistered] = useState(false);
    const [capacityReached, setCapacityReached] = useState(false);

    console.log("Rendering Event Registration");

    useEffect(() => {
        async function checkRegistration() {
            try {
                const token = localStorage.getItem('token');
                if (!token) {
                    console.error('No token found');
                    return;
                }

                const decodedToken = jwtDecode(token);
                const userId = decodedToken.userId;

                if (!userId) {
                    console.error('No userId found in token');
                    return;
                }

                const response = await axios.get(`http://localhost:8081/events/${id}/registration-status`, {
                    headers: { 'Authorization': `Bearer ${token}` },
                });
                const Responsestatus = response.data.status;
                const status = "User is registered for this event.";
               
               if(Responsestatus === status){
                setIsRegistered(true);
               }
                
                console.log(isRegistered);
                setCapacityReached(response.data.capacityReached);
            } catch (err) {
                console.error('Error checking registration status:', err);
            }
        }
        checkRegistration();
    }, [id]);

    const handleRegister = async () => {
        if (capacityReached) {
            alert('This event is full!');
            return;
        }

        try {
            const token = localStorage.getItem('token');
            if (!token) {
                console.error('No token found');
                return;
            }

            const decodedToken = jwtDecode(token);
            const userId = decodedToken.userId;

            if (!userId) {
                console.error('No userId found in token');
                return;
            }

            await axios.post(`http://localhost:8081/events/${id}/register`, { userId }, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
            });

            setIsRegistered(true);
            alert('Successfully registered!');
        } catch (err) {
            if (err.response) {
                console.error('Error registering for the event:', err.response.data);
                alert(`Error: ${err.response.data.message || 'Something went wrong'}`);
            } else {
                console.error('Error registering for the event:', err);
            }
        }
    };

    const handleUnregister = async () => {
        try {
            const token = localStorage.getItem('token');
            if (!token) {
                console.error('No token found');
                return;
            }

            const decodedToken = jwtDecode(token);
            const userId = decodedToken.userId;

            if (!userId) {
                console.error('No userId found in token');
                return;
            }

            await axios.delete(`http://localhost:8081/events/${id}/unregister`, {
                headers: { 'Authorization': `Bearer ${token}` },
                data: { userId }
            });

            setIsRegistered(false);
            alert('Successfully unregistered!');
        } catch (err) {
            if (err.response) {
                console.error('Error unregistering from the event:', err.response.data);
                alert(`Error: ${err.response.data.message || 'Something went wrong'}`);
            } else {
                console.error('Error unregistering from the event:', err);
            }
        }
    };

    return (
        <div className="event-registration-container">
            <div className="event-registration-form">
                <h2 className="form-title">{isRegistered ? 'Unregister' : 'Register'} for Event</h2>
                <div className="event-registration-buttons">
                    {isRegistered ? (
                        <button className="btn unregister-btn" onClick={handleUnregister}>Unregister</button>
                    ) : (
                        <button className="btn register-btn" onClick={handleRegister} disabled={capacityReached}>
                            {capacityReached ? 'Event Full' : 'Register'}
                        </button>
                    )}
                </div>
            </div>
        </div>
    );
};

export default EventRegistration;
