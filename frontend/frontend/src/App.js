import React, { useState } from 'react';
import { Box } from '@mui/material';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Login from './Login';
import Sidebar from './Sidebar';
import Dashboard from './Dashboard';
import ManageUsers from './ManageUsers';
import TrainingList from './TrainingList';
import TestResults from './TestResults';
import DeviceList from './DeviceList';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(true);

  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login setIsLoggedIn={setIsLoggedIn} />} />
        {isLoggedIn ? (
          <Route path="*" element={
            <Box sx={{ display: 'flex' }}>
              <Sidebar />
              <Box sx={{ flexGrow: 1 }}>
                <Routes>
                  <Route path="/dashboard" element={<Dashboard />} />
                  <Route path="/manage-users" element={<ManageUsers />} />
                  <Route path="/training-list" element={<TrainingList />} />
                  <Route path="/test-results" element={<TestResults />} />
                  <Route path="/device-list" element={<DeviceList />} />
                  <Route path="*" element={<Navigate to="/dashboard" />} />
                </Routes>
              </Box>
            </Box>
          } />
        ) : (
          <Route path="*" element={<Navigate to="/login" />} />
        )}
      </Routes>
    </Router>
  );
}

export default App;
