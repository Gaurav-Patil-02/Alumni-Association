

import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { fetchWithToken } from '../../utils/api.js';
import '../../styles/EventList.css'

const EventList = () => {
    const [events, setEvents] = useState([]);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchEvents = async () => {
            try {
                const response = await fetchWithToken('/events', { method: 'GET' });
                const data = await  response.data;
                setEvents(data);
            } catch (error) {
                setError('Error fetching events: ' + error.message);
            }
        };

        fetchEvents();
    }, []);

    const handleEventDetail = (eventId) => {
        navigate(`/events/${eventId}`);
    };

   
    const handleCreateEvent = () => {
        navigate('/events/create');
    };

    return (
        <div className="events-container">
            <h2 className="events-header">All Events</h2>
            {error && <p className="error">{error}</p>}

           

            <ul className="events-list">
                {events.map((event) => (
                    <li key={event.id} className="event-item">
                        <p><strong>Title:</strong> {event.title}</p>
                        <p><strong>Date:</strong> {event.date}</p>
                        <button
                            className="event-detail-button"
                            onClick={() => handleEventDetail(event.id)}
                        >
                            Event Details
                        </button>
                    </li>
                ))}
            </ul>

            <button className="create-event-button" onClick={handleCreateEvent}>
                Create New Event
            </button>
        </div>
    );
};

export default EventList;


    