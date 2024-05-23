import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Box, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';
import { Bar, Pie } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  ArcElement,
  Tooltip,
  Legend,
} from 'chart.js';

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  ArcElement,
  Tooltip,
  Legend
);

const Dashboard = ({ supervision }) => {
  const [testResults, setTestResults] = useState([]);
  const [atestCount, setAtestCount] = useState(0);
  const [btestCount, setBtestCount] = useState(0);
  const [ctestCount, setCtestCount] = useState(0);
  const [chartData, setChartData] = useState({ labels: [], datasets: [] });
  const [recentTests, setRecentTests] = useState([]);

  useEffect(() => {
    const fetchTestResults = async () => {
      try {
        const response = await axios.get('/api/all-test-result', {
          params: { supervisionId: supervision.id },
          headers: {
            'Content-Type': 'application/json'
          }
        });

        const data = response.data;
        setTestResults(data);

        const atestCount = data.filter(result => result.atestResult).length;
        const btestCount = data.filter(result => result.btestResult).length;
        const ctestCount = data.filter(result => result.ctestResult).length;

        setAtestCount(atestCount);
        setBtestCount(btestCount);
        setCtestCount(ctestCount);

        const chartData = await Promise.all(data.map(async (result) => {
          let details;
          if (result.atestResult) {
            details = await axios.get('/api/a-test-result-detail', { params: { id: result.atestResult.id } });
          } else if (result.btestResult) {
            details = await axios.get('/api/b-test-result-detail', { params: { id: result.btestResult.id } });
          }

          return {
            ...result,
            details: details ? details.data : null,
          };
        }));

        setChartData(processChartData(chartData));

        setRecentTests(data.slice(-10).reverse()); // 최근 테스트 10개를 역순으로 저장
      } catch (err) {
        console.error('Failed to fetch test results', err);
      }
    };

    fetchTestResults();
  }, [supervision]);

  const processChartData = (data) => {
    const labels = [...new Set(data.map(result => new Date(result.testStartTime).toLocaleDateString()))];

    const datasets = [
      {
        label: 'ATest 기억력 점수',
        data: labels.map(label => {
          const result = data.find(result => new Date(result.testStartTime).toLocaleDateString() === label && result.atestResult);
          return result ? result.details.memoryResult1 : null;
        }),
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
      },
      {
        label: 'BTest 기억력 점수',
        data: labels.map(label => {
          const result = data.find(result => new Date(result.testStartTime).toLocaleDateString() === label && result.btestResult);
          return result ? result.details.memoryResult1 : null;
        }),
        backgroundColor: 'rgba(54, 162, 235, 0.5)',
      },
      {
        label: 'CTest 기억력 점수',
        data: labels.map(label => {
          const result = data.find(result => new Date(result.testStartTime).toLocaleDateString() === label && result.ctestResult);
          return result ? result.ctestResult.memoryScore : null;
        }),
        backgroundColor: 'rgba(75, 192, 192, 0.5)',
      },
      {
        label: 'ATest 시공간 능력 점수',
        data: labels.map(label => {
          const result = data.find(result => new Date(result.testStartTime).toLocaleDateString() === label && result.atestResult);
          return result ? result.details.visuospatialCount : null;
        }),
        backgroundColor: 'rgba(255, 206, 86, 0.5)',
      },
      {
        label: 'BTest 시공간 능력 점수',
        data: labels.map(label => {
          const result = data.find(result => new Date(result.testStartTime).toLocaleDateString() === label && result.btestResult);
          return result ? result.details.visuospatialCount : null;
        }),
        backgroundColor: 'rgba(153, 102, 255, 0.5)',
      },
      {
        label: 'CTest 시공간 능력 점수',
        data: labels.map(label => {
          const result = data.find(result => new Date(result.testStartTime).toLocaleDateString() === label && result.ctestResult);
          return result ? result.ctestResult.visuospatialScore : null;
        }),
        backgroundColor: 'rgba(255, 159, 64, 0.5)',
      },
      {
        label: 'ATest 주의력 점수',
        data: labels.map(label => {
          const result = data.find(result => new Date(result.testStartTime).toLocaleDateString() === label && result.atestResult);
          return result ? result.details.attentionCount : null;
        }),
        backgroundColor: 'rgba(75, 192, 192, 0.5)',
      },
      {
        label: 'BTest 주의력 점수',
        data: labels.map(label => {
          const result = data.find(result => new Date(result.testStartTime).toLocaleDateString() === label && result.btestResult);
          return result ? result.details.attentionCount : null;
        }),
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
      },
      {
        label: 'CTest 주의력 점수',
        data: labels.map(label => {
          const result = data.find(result => new Date(result.testStartTime).toLocaleDateString() === label && result.ctestResult);
          return result ? result.ctestResult.attentionScore : null;
        }),
        backgroundColor: 'rgba(54, 162, 235, 0.5)',
      },
      {
        label: 'ATest 집행기능 경과 시간',
        data: labels.map(label => {
          const result = data.find(result => new Date(result.testStartTime).toLocaleDateString() === label && result.atestResult);
          return result ? result.details.executeTime : null;
        }),
        backgroundColor: 'rgba(153, 102, 255, 0.5)',
      },
      {
        label: 'BTest 집행기능 경과 시간',
        data: labels.map(label => {
          const result = data.find(result => new Date(result.testStartTime).toLocaleDateString() === label && result.btestResult);
          return result ? result.details.executeTime : null;
        }),
        backgroundColor: 'rgba(255, 159, 64, 0.5)',
      },
      {
        label: 'CTest 집행기능 점수',
        data: labels.map(label => {
          const result = data.find(result => new Date(result.testStartTime).toLocaleDateString() === label && result.ctestResult);
          return result ? result.ctestResult.executeScore : null;
        }),
        backgroundColor: 'rgba(75, 192, 192, 0.5)',
      },
    ];

    return { labels, datasets };
  };

  const pieData = {
    labels: ['ATest', 'BTest', 'CTest'],
    datasets: [
      {
        data: [atestCount, btestCount, ctestCount],
        backgroundColor: ['#42A5F5', '#66BB6A', '#FFA726'],
        hoverBackgroundColor: ['#64B5F6', '#81C784', '#FFB74D'],
      },
    ],
  };

  return (
    <Box sx={{ flexGrow: 1, p: 3 }}>
      <Typography variant="h4" gutterBottom>
        인지기능 평가
      </Typography>
      <Box sx={{ width: '100%', marginBottom: '20px' }}>
        {chartData.labels.length > 0 && <Bar data={chartData} />}
      </Box>
      <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
        <Box sx={{ width: '40%' }}>
          <Typography variant="h6">최근 테스트</Typography>
          <TableContainer component={Paper}>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Test Type</TableCell>
                  <TableCell>Start Time</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {recentTests.map((record, index) => (
                  <TableRow key={index}>
                    <TableCell>
                      {record.atestResult ? 'ATest' : record.btestResult ? 'BTest' : 'CTest'}
                    </TableCell>
                    <TableCell>{new Date(record.testStartTime).toLocaleString()}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </Box>
        <Box sx={{ width: '50%' }}>
          <Typography variant="h6">테스트 비율</Typography>
          <Pie data={pieData} />
        </Box>
      </Box>
    </Box>
  );
};

export default Dashboard;
