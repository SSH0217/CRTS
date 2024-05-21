import React from 'react';
import { Drawer, List, ListItem, ListItemIcon, ListItemText, Typography, Avatar, Box } from '@mui/material';
import { Dashboard, People, ShoppingCart, Class, Quiz, Assignment, School, Devices, ExitToApp } from '@mui/icons-material';

const Sidebar = () => {
  const menuItems = [
    { text: 'Dashboard', icon: <Dashboard /> },
    { text: 'Students', icon: <People /> },
    { text: 'Contents', icon: <ShoppingCart /> },
    { text: 'Classes', icon: <Class /> },
    { text: 'Quiz', icon: <Quiz /> },
    { text: 'Quiz Results', icon: <Assignment /> },
    { text: 'Teacher', icon: <School /> },
    { text: 'Devices', icon: <Devices /> },
    { text: 'Log Out', icon: <ExitToApp /> },
  ];

  return (
    <Drawer variant="permanent" sx={{ width: 240, flexShrink: 0 }}>
      <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', p: 2 }}>
        <Typography variant="h6">VR LMS</Typography>
        <Avatar alt="User" src="/static/images/avatar/1.jpg" />
        <Typography variant="body1">Saurav</Typography>
      </Box>
      <List>
        {menuItems.map((item, index) => (
          <ListItem button key={index}>
            <ListItemIcon>{item.icon}</ListItemIcon>
            <ListItemText primary={item.text} />
          </ListItem>
        ))}
      </List>
    </Drawer>
  );
};

export default Sidebar;
