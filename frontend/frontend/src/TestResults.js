import React from 'react';
import { Box, Typography } from '@mui/material';

const TestResults = () => {
  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" gutterBottom>
        검사 결과
      </Typography>
      {/* 여기에 검사 결과 관련 내용 추가 */}
    </Box>
  );
};

export default TestResults;
