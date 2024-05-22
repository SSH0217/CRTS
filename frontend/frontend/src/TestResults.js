import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Box, Typography, List, ListItem, ListItemText, CircularProgress } from '@mui/material';

const TestResults = () => {
  const [testResults, setTestResults] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const supervisionId = 1; // 실제 사용 시에는 적절한 supervisionId를 사용하세요

  useEffect(() => {
    const fetchTestResults = async () => {
      try {
        const response = await axios.get('/api/all-test-result', {
          params: { supervisionId },
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
  }, [supervisionId]);

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
      <List>
        {testResults.map((result) => (
          <ListItem key={result.id}>
            <ListItemText
              primary={`Test Subject: ${result.testSubjectName}`}
              secondary={`Start: ${new Date(result.testStartTime).toLocaleString()} - End: ${new Date(result.testEndTime).toLocaleString()}`}
            />
          </ListItem>
        ))}
      </List>
    </Box>
  );
};

export default TestResults;
