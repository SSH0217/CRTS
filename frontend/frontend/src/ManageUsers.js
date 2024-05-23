import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Box, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Modal, Button } from '@mui/material';

const ManageUsers = ({ supervision }) => {
  const [patients, setPatients] = useState([]);
  const [selectedPatient, setSelectedPatient] = useState(null);
  const [open, setOpen] = useState(false);

  const handleOpen = (patient) => {
    setSelectedPatient(patient);
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    setSelectedPatient(null);
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

    if (supervision) {
      fetchPatients();
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
                피검사자 상세 정보
              </Typography>
              <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                이름: {selectedPatient.name}
              </Typography>
              <Typography>나이: {selectedPatient.age}</Typography>
              <Typography>성별: {selectedPatient.gender}</Typography>
              <Typography>생성일: {new Date(selectedPatient.createdDate).toLocaleString()}</Typography>
              <Box display="flex" justifyContent="flex-end" sx={{ mt: 2 }}>
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
