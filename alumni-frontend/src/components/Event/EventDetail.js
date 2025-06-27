import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import { jwtDecode } from "jwt-decode";
import EventRegistration from "./EventRegistration.js";
import "../../styles/EventDetail.css";
import { fetchWithToken, putWithToken } from "../../utils/api.js";


const EventDetail = () => {
  const { id } = useParams();
  const [event, setEvent] = useState(null);
  const [role, setRole] = useState(null);
  const [error, setError] = useState(null);
  const [showRegistration, setShowRegistration] = useState(false);
 

  useEffect(() => {
    const fetchEventDetails = async () => {
      try {
        const token = localStorage.getItem("token");

        if (token) {
          const decodedToken = jwtDecode(token);
          setRole(decodedToken.role);
        }

        const response = await fetchWithToken(`/events/${id}`, { method: 'GET' });
        setEvent(response.data);
      } catch (error) {
        setError("Error fetching event details");
      }
    };

    fetchEventDetails();
  }, [id]);

  const handleApprove = async () => {
    try {
      putWithToken(`/events/${id}/approve`, null);
      setEvent((prevEvent) => ({ ...prevEvent, status: "APPROVED" }));
      alert("Event Approved successfully!");
    } catch (error) {
      alert("Event Approving successfully!");
    }
  };

  const handleReject = async () => {
    try {
      putWithToken(`/events/${id}/reject`, null);
      setEvent((prevEvent) => ({ ...prevEvent, status: "REJECTED" }));
      alert("Event rejected successfully!");
    } catch (error) {
      alert("Error rejecting event");
    }
  };

  const handleRegister = () => {
    setShowRegistration(true);
  };

  const shouldShowApproveRejectButtons = () => {
    return event && event.status === "PENDING" && role === "ROLE_ADMIN";
  };

  

  if (error) return <p>{error}</p>;

  return (
    <div className="event-details-container">
      <h1 className="event-title">{event ? event.title : "Loading..."}</h1>
      {event && (
        <>
          <p className="event-description">
            <strong>Description:</strong> {event.description}
          </p>
          <p className="event-date">
            <strong>Date:</strong> {event.date}
          </p>
          <p className="event-location">
            <strong>Location:</strong> {event.location}
          </p>
          <p className="event-capacity">
            <strong>Capacity:</strong> {event.capacity}
          </p>
          <p className="event-organizer">
            <strong>Organizer:</strong> {event.organizer}
          </p>
          <p className="event-status">
            <strong>Status:</strong> {event.status}
          </p>

          {event.status === "APPROVED" && !showRegistration ? (
            <button onClick={handleRegister} className="register-event-btn">
              Participate in Event
            </button>
          ) : event.status === "REJECTED" ? (
            <p className="event-rejected-message">
              This event has been rejected for some reasons.
            </p>
          ) : event.status === "PENDING" ? (
            <p className="event-pending-message">
              You can participate once it is approved by your higher
              authorities.
            </p>
          ) : null}

          {showRegistration && event.status === "APPROVED" && (
            <EventRegistration eventId={id} />
          )}

          {shouldShowApproveRejectButtons() && (
            <div className="approve-reject-buttons">
              <button onClick={handleApprove} className="approve-btn">
                Approve
              </button>
              <button onClick={handleReject} className="reject-btn">
                Reject
              </button>
            </div>
          )}
        </>
      )}
    </div>
  );
};

export default EventDetail;
