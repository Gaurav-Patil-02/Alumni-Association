import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../../styles/EventForm.css';
import { postWithToken } from '../../utils/api.js';

const EventForm = ({ initialEvent, onSubmit }) => {
    const [title, setTitle] = useState(initialEvent ? initialEvent.title : '');
    const [description, setDescription] = useState(initialEvent ? initialEvent.description : '');
    const [date, setDate] = useState(initialEvent ? initialEvent.date : '');
    const [location, setLocation] = useState(initialEvent ? initialEvent.location : '');
    const [capacity, setCapacity] = useState(initialEvent ? initialEvent.capacity : '');
    const [error, setError] = useState(null); 
    const navigate = useNavigate();

    console.log("Rendering Event Form");

    const handleSubmit = (e) => {
        e.preventDefault();
        const eventData = { title, description, date, location, capacity };
        postWithToken('/events/create', eventData,)
            .then(response => {
                alert("Event created successfully!");
                onSubmit && onSubmit(response.data);
                resetForm(); 
            })
            
            .catch(error => {
                console.error("Error creating event:", error);
                setError("An error occurred while creating the event. Please try again later."); 
            });
    };

    const resetForm = () => {
        setTitle('');
        setDescription('');
        setDate('');
        setLocation('');
        setCapacity('');
    };

    const returnToEvents = () => {
        navigate('/events');
    };

    return (
        <div className="auth-page">
            <div className="content">
                <h2 className="title">Create Event</h2>
                <form onSubmit={handleSubmit} className="event-form">
                    <input 
                        type="text" 
                        value={title} 
                        onChange={(e) => setTitle(e.target.value)} 
                        placeholder="Title" 
                        required 
                        className="input-field"
                    />
                    <textarea 
                        value={description} 
                        onChange={(e) => setDescription(e.target.value)} 
                        placeholder="Description" 
                        required 
                        className="input-field"
                    />
                    <input 
                        type="datetime-local" 
                        value={date} 
                        onChange={(e) => setDate(e.target.value)} 
                        required 
                        className="input-field"
                    />
                    <input 
                        type="text" 
                        value={location} 
                        onChange={(e) => setLocation(e.target.value)} 
                        placeholder="Location" 
                        required 
                        className="input-field"
                    />
                    <input 
                        type="number" 
                        value={capacity} 
                        onChange={(e) => setCapacity(e.target.value)} 
                        placeholder="Capacity" 
                        required 
                        className="input-field"
                    />
                    {error && <p className="error-message">{error}</p>} 
                    <button type="submit" className="btn submit-btn">Submit</button>
                    <button className="btn submit-btn" onClick={returnToEvents}>
                  Return to Events
              </button>
                </form>
            </div>
        </div>
    );
};

export default EventForm;
