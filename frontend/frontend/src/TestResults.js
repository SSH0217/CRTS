import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Box, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, CircularProgress, Modal, Button } from '@mui/material';

const TestResults = ({ supervision }) => {
  const [testResults, setTestResults] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedResult, setSelectedResult] = useState(null);
  const [open, setOpen] = useState(false);

  const handleOpen = (result) => {
    setSelectedResult(result);
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    setSelectedResult(null);
  };

  useEffect(() => {
    const fetchTestResults = async () => {
      if (!supervision) return;
      try {
        const response = await axios.get('/api/all-test-result', {
          params: { supervisionId: supervision.id },
          headers: {
            'Content-Type': 'application/json'
          }
        });
        setTestResults(response.data);
      } catch (err) {
        setError('데이터를 가져오는 중 오류가 발생했습니다.');
      } finally {
        setLoading(false);
      }
    };

    fetchTestResults();
  }, [supervision]);

  if (loading) {
    return <CircularProgress />;
  }

  if (error) {
    return <Typography color="error">{error}</Typography>;
  }

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" gutterBottom>
        검사 결과
      </Typography>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>피검사자</TableCell>
              <TableCell>테스트 시작 시각</TableCell>
              <TableCell>테스트 종료 시각</TableCell>
              <TableCell>테스트 종류</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {testResults.map((result) => (
              <TableRow key={result.id} onClick={() => handleOpen(result)} style={{ cursor: 'pointer' }}>
                <TableCell>{result.testSubjectName}</TableCell>
                <TableCell>{new Date(result.testStartTime).toLocaleString()}</TableCell>
                <TableCell>{new Date(result.testEndTime).toLocaleString()}</TableCell>
                <TableCell>
                  {result.atestResult && <Typography>A Test</Typography>}
                  {result.btestResult && <Typography>B Test</Typography>}
                  {result.ctestResult && <Typography>C Test</Typography>}
                </TableCell>
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
          {selectedResult && (
            <>
              <Typography id="modal-modal-title" variant="h6" component="h2">
                상세 검사 결과
              </Typography>
              <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                피검사자: {selectedResult.testSubjectName}
              </Typography>
              <Typography>테스트 시작 시각: {new Date(selectedResult.testStartTime).toLocaleString()}</Typography>
              <Typography>테스트 종료 시각: {new Date(selectedResult.testEndTime).toLocaleString()}</Typography>
              
              {selectedResult.atestResult && (
                <>
                  <Typography>A Test 결과:</Typography>
                  <TableContainer component={Paper}>
                    <Table>
                      <TableHead>
                        <TableRow>
                          <TableCell>항목</TableCell>
                          <TableCell>결과</TableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {Object.entries(selectedResult.atestResult)
                          .filter(([key]) => key !== 'id')
                          .map(([key, value]) => (
                            <TableRow key={key}>
                              <TableCell>{key}</TableCell>
                              <TableCell>{value}</TableCell>
                            </TableRow>
                          ))}
                      </TableBody>
                    </Table>
                  </TableContainer>
                </>
              )}

              {selectedResult.btestResult && (
                <>
                  <Typography>B Test 결과:</Typography>
                  <TableContainer component={Paper}>
                    <Table>
                      <TableHead>
                        <TableRow>
                          <TableCell>항목</TableCell>
                          <TableCell>결과</TableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {Object.entries(selectedResult.btestResult)
                          .filter(([key]) => key !== 'id')
                          .map(([key, value]) => (
                            <TableRow key={key}>
                              <TableCell>{key}</TableCell>
                              <TableCell>{value}</TableCell>
                            </TableRow>
                          ))}
                      </TableBody>
                    </Table>
                  </TableContainer>
                </>
              )}

              {selectedResult.ctestResult && (
                <>
                  <Typography>C Test 결과:</Typography>
                  <TableContainer component={Paper}>
                    <Table>
                      <TableHead>
                        <TableRow>
                          <TableCell>필드</TableCell>
                          <TableCell>값</TableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {Object.entries(selectedResult.ctestResult)
                          .filter(([key]) => key !== 'id')
                          .map(([key, value]) => (
                            <TableRow key={key}>
                              <TableCell>{key}</TableCell>
                              <TableCell>{value}</TableCell>
                            </TableRow>
                          ))}
                      </TableBody>
                    </Table>
                  </TableContainer>
                </>
              )}

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

export default TestResults;
