import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Drawer, List, ListItem, ListItemIcon, ListItemText, Typography, Avatar, Box } from '@mui/material';
import { Dashboard, People, Class, Assignment, Devices, ExitToApp } from '@mui/icons-material';

const Sidebar = ({ setIsLoggedIn, supervision }) => {
  const navigate = useNavigate();

  const menuItems = [
    { text: '대시보드', icon: <Dashboard />, path: '/dashboard' },
    { text: '피검사자 관리', icon: <People />, path: '/manage-users' },
    { text: '인지훈련 목록', icon: <Class />, path: '/training-list' },
    { text: '검사 결과', icon: <Assignment />, path: '/test-results' },
    { text: '디바이스 목록', icon: <Devices />, path: '/device-list' },
    { text: '로그아웃', icon: <ExitToApp />, path: '/login', action: () => setIsLoggedIn(false) },
  ];

  return (
    <Drawer variant="permanent" sx={{ width: 240, flexShrink: 0 }}>
      <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', p: 2 }}>
        <Typography variant="h6">CRTS</Typography>
        <Avatar alt="User" src="/static/images/avatar/1.jpg" />
        <Typography variant="body1">{supervision?.supervisionName || 'Unknown'}</Typography>
      </Box>
      <List>
        {menuItems.map((item, index) => (
          <ListItem
            button
            key={index}
            onClick={() => {
              if (item.action) item.action();
              navigate(item.path);
            }}
          >
            <ListItemIcon>{item.icon}</ListItemIcon>
            <ListItemText primary={item.text} />
          </ListItem>
        ))}
      </List>
    </Drawer>
  );
};

export default Sidebar;
