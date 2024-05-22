import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Box, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, CircularProgress } from '@mui/material';

const TestResults = ({ supervision }) => {
  const [testResults, setTestResults] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

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
              <TableRow key={result.id}>
                <TableCell>{result.testSubjectName}</TableCell>
                <TableCell>{new Date(result.testStartTime).toLocaleString()}</TableCell>
                <TableCell>{new Date(result.testEndTime).toLocaleString()}</TableCell>
                <TableCell>
                  {result.aTestResult && <Typography>A Test</Typography>}
                  {result.bTestResult && <Typography>B Test</Typography>}
                  {result.cTestResult && <Typography>C Test</Typography>}
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
};

export default TestResults;
