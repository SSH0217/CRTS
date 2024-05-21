import React from 'react';
import { Box } from '@mui/material';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './Login';
import Sidebar from './Sidebar';
import Dashboard from './Dashboard';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/dashboard" element={<Box sx={{ display: 'flex' }}>
      <Sidebar />
      <Dashboard />
    </Box>} />
      </Routes>
    </Router>
  );
}

export default App;