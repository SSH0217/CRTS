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

const App = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [supervision, setSupervision] = useState(null);

  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login setIsLoggedIn={setIsLoggedIn} setSupervision={setSupervision} />} />
        {isLoggedIn ? (
          <Route path="*" element={
            <Box sx={{ display: 'flex' }}>
              <Sidebar setIsLoggedIn={setIsLoggedIn} supervision={supervision} />
              <Box sx={{ flexGrow: 1 }}>
                <Routes>
                  <Route path="/dashboard" element={<Dashboard supervision={supervision} />} />
                  <Route path="/manage-users" element={<ManageUsers supervision={supervision} />} />
                  <Route path="/training-list" element={<TrainingList />} />
                  <Route path="/test-results" element={<TestResults />} />
                  <Route path="/device-list" element={<DeviceList supervision={supervision} />} />
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
