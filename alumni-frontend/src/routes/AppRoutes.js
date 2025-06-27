
import React from "react";
import { Routes, Route } from "react-router-dom";
import Login from "../components/User/login.js";
import Profile from "../components/User/profile.js";
import UpdateProfile from "../components/User/UpdateProfile.js";
import RegistrationForm from "../components/User/RegistrationForm.js";
import LandingPage from "../components/User/LandingPage.js";
import AdminPage from "../components/User/AdminPage.js";
import AlumniPage from "../components/User/AlumniPage.js";
import StudentPage from "../components/User/StudentPage.js";
import EventDetail from "../components/Event/EventDetail.js";
import ProtectedRoute from "../components/User/ProtectedRoute.js";
import EventList from "../components/Event/EventList.js";
import EventForm from "../components/Event/EventForm.js";
import EventRegistration from "../components/Event/EventRegistration.js";

const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/auth/login" element={<Login />} />
      <Route path="/auth/register" element={<RegistrationForm />} />
      
      <Route path="/events" element={<EventList />} />
      <Route path="/events/:id" element={<EventDetail />} />
      <Route path="/events/create" element={<EventForm/>} />
      <Route path="/events/:id/register" element={<RegistrationForm/>} />
      <Route path="/events/:id/register" element={<EventRegistration />} />


    
      <Route
        path="/admin"
        element={
          <ProtectedRoute allowedRoles={["ROLE_ADMIN"]}>
            <AdminPage />
          </ProtectedRoute>
        }
      />

      

      <Route
        path="/student"
        element={
          <ProtectedRoute allowedRoles={["ROLE_STUDENT"]}>
            <StudentPage />
          </ProtectedRoute>
        }
      />

      <Route
        path="/alumni"
        element={
          <ProtectedRoute allowedRoles={["ROLE_ALUMNI"]}>
            <AlumniPage />
          </ProtectedRoute>
        }
      />

      <Route
        path="/user/profile"
        element={
          <ProtectedRoute allowedRoles={["ROLE_ALUMNI", "ROLE_STUDENT", "ROLE_ADMIN"]}>
            <Profile />
          </ProtectedRoute>
        }
      />

      <Route
        path="/user/profile/update"
        element={
          <ProtectedRoute allowedRoles={["ROLE_ALUMNI", "ROLE_STUDENT", "ROLE_ADMIN"]}>
            <UpdateProfile />
          </ProtectedRoute>
        }
      />
    </Routes>
  );
};

export default AppRoutes;
