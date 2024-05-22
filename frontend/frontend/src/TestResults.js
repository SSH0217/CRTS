import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Box, Typography, List, ListItem, ListItemText, CircularProgress } from '@mui/material';

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
