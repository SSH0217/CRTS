import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Box, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';

const DeviceList = ({ supervision }) => {
  const [devices, setDevices] = useState([]);

  useEffect(() => {
    const fetchDevices = async () => {
      try {
        const response = await axios.get('/api/device-list', {
          params: { supervisionId: supervision.id }
        });
        setDevices(response.data);
      } catch (error) {
        console.error('Failed to fetch devices', error);
      }
    };

    if (supervision) {
      fetchDevices();
    }
  }, [supervision]);

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" gutterBottom>
        디바이스 목록
      </Typography>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>디바이스 번호</TableCell>
              <TableCell>디바이스</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {devices.map((device) => (
              <TableRow key={device.deviceNum}>
                <TableCell>{device.deviceNum}</TableCell>
                <TableCell>{device.deviceName}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
};

export default DeviceList;
