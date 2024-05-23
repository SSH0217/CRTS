import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Box, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Modal, Button, Select, MenuItem, FormControl, InputLabel } from '@mui/material';

const ManageUsers = ({ supervision }) => {
  const [patients, setPatients] = useState([]);
  const [devices, setDevices] = useState([]);
  const [selectedPatient, setSelectedPatient] = useState(null);
  const [open, setOpen] = useState(false);
  const [device, setDevice] = useState('');
  const [deviceId, setDeviceId] = useState(''); // deviceId를 추가합니다.
  const [testType, setTestType] = useState('');

  const handleOpen = (patient) => {
    setSelectedPatient(patient);
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    setSelectedPatient(null);
  };

  const handleDeviceChange = (event) => {
    const selectedDeviceNum = event.target.value;
    setDevice(selectedDeviceNum);
    const selectedDevice = devices.find(d => d.deviceNum === selectedDeviceNum);
    if (selectedDevice) {
      setDeviceId(selectedDevice.id); // deviceId를 설정합니다.
    }
  };

  const handleTestTypeChange = (event) => {
    setTestType(event.target.value);
  };

  const handleConfirm = async () => {
    // 확인 버튼 클릭 시 수행할 작업 추가
    try {
      const response = await axios.post('/api/test-setting', {
        patientId: selectedPatient.id,
        deviceId: deviceId, // deviceId를 사용합니다.
        testType: testType
      });
      console.log(response.data); // 서버 응답 처리
    } catch (error) {
      console.error('Failed to send test setting', error);
    }
    handleClose();
  };

  useEffect(() => {
    const fetchPatients = async () => {
      try {
        const response = await axios.get('/api/patient-info', {
          params: { supervisionId: supervision.id }
        });
        setPatients(response.data);
      } catch (error) {
        console.error('Failed to fetch patients', error);
      }
    };

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
      fetchPatients();
      fetchDevices();
    }
  }, [supervision]);

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" gutterBottom>
        피검사자 관리
      </Typography>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>이름</TableCell>
              <TableCell>나이</TableCell>
              <TableCell>성별</TableCell>
              <TableCell>생성일</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {patients.map((patient) => (
              <TableRow key={patient.id} onClick={() => handleOpen(patient)} style={{ cursor: 'pointer' }}>
                <TableCell>{patient.name}</TableCell>
                <TableCell>{patient.age}</TableCell>
                <TableCell>{patient.gender}</TableCell>
                <TableCell>{new Date(patient.createdDate).toLocaleString()}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={{ position: 'absolute', top: '50%', left: '50%', transform: 'translate(-50%, -50%)', width: 400, bgcolor: 'background.paper', border: '2px solid #000', boxShadow: 24, p: 4 }}>
          {selectedPatient && (
            <>
              <Typography id="modal-modal-title" variant="h6" component="h2">
                피검사자 테스트 설정
              </Typography>
              <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                이름: {selectedPatient.name}
              </Typography>
              <Typography>나이: {selectedPatient.age}</Typography>
              <Typography>성별: {selectedPatient.gender}</Typography>
              <Typography>생성일: {new Date(selectedPatient.createdDate).toLocaleString()}</Typography>

              <FormControl fullWidth sx={{ mt: 2 }}>
                <InputLabel id="device-label">디바이스</InputLabel>
                <Select
                  labelId="device-label"
                  value={device}
                  onChange={handleDeviceChange}
                  label="디바이스"
                >
                  {devices.map((device) => (
                    <MenuItem key={device.deviceNum} value={device.deviceNum}>
                      {device.deviceNum} 번 {device.deviceName}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>

              <FormControl fullWidth sx={{ mt: 2 }}>
                <InputLabel id="test-type-label">테스트 타입</InputLabel>
                <Select
                  labelId="test-type-label"
                  value={testType}
                  onChange={handleTestTypeChange}
                  label="테스트 타입"
                >
                  <MenuItem value="TypeA">TypeA</MenuItem>
                  <MenuItem value="TypeB">TypeB</MenuItem>
                  <MenuItem value="TypeC">TypeC</MenuItem>
                </Select>
              </FormControl>

              <Box display="flex" justifyContent="flex-end" sx={{ mt: 2 }}>
                <Button onClick={handleConfirm} variant="contained" color="primary" sx={{ mr: 2 }}>확인</Button>
                <Button onClick={handleClose}>닫기</Button>
              </Box>
            </>
          )}
        </Box>
      </Modal>
    </Box>
  );
};

export default ManageUsers;
