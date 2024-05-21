import React from 'react';
import { Box, Typography } from '@mui/material';
import { Line, Pie } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  ArcElement,
  Tooltip,
  Legend,
} from 'chart.js';

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  ArcElement,
  Tooltip,
  Legend
);

const Dashboard = () => {
  const lineData = {
    labels: ['2024-02-21', '2024-02-22', '2024-03-20', '2024-05-10'],
    datasets: [
      {
        label: 'Time Spent (Seconds)',
        data: [0, 20, 10, 90],
        borderColor: 'blue',
        backgroundColor: 'rgba(0, 0, 255, 0.1)',
      },
      {
        label: 'Performance Score (%)',
        data: [0, 30, 20, 120],
        borderColor: 'orange',
        backgroundColor: 'rgba(255, 165, 0, 0.1)',
      },
    ],
  };

  const pieData = {
    labels: ['온도에 따른 반응속도', '아이오딘 폭탄', '로켓 캔디', '지층이 만들어지는 원리', '아이오딘 물질의 상태(브라운)', 'Deleted'],
    datasets: [
      {
        data: [15, 5, 10, 25, 10, 35],
        backgroundColor: ['#42A5F5', '#66BB6A', '#FFA726', '#EF5350', '#AB47BC', '#BDBDBD'],
        hoverBackgroundColor: ['#64B5F6', '#81C784', '#FFB74D', '#E57373', '#CE93D8', '#E0E0E0'],
      },
    ],
  };

  return (
    <Box sx={{ flexGrow: 1, p: 3 }}>
      <Typography variant="h4" gutterBottom>
        Hi, Welcome back 👋
      </Typography>
      <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
        <Box sx={{ width: '60%' }}>
          <Typography variant="h6">Total Time Spent</Typography>
          <Line data={lineData} />
        </Box>
        <Box sx={{ width: '35%' }}>
          <Typography variant="h6">Content Metrics</Typography>
          <Pie data={pieData} />
        </Box>
      </Box>
    </Box>
  );
};

export default Dashboard;
