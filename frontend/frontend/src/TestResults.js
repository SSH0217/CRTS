import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Box, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, CircularProgress, Modal, Button } from '@mui/material';

const TestResults = ({ supervision }) => {
  const [testResults, setTestResults] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedResult, setSelectedResult] = useState(null);
  const [open, setOpen] = useState(false);
  const [testDetail, setTestDetail] = useState(null);
  const [logDetails, setLogDetails] = useState([]); // logDTOList 저장을 위한 상태
  const [testType, setTestType] = useState(null); // ATest인지 BTest인지 구분하기 위한 상태 추가

  const fieldNames = {
    memoryResult1: "첫 번째 기억입력 결과",
    memoryResult2: "두 번째 기억입력 결과",
    memoryResult3: "세 번째 기억입력 결과",
    correctItem1: "첫 번째 기억입력 정답 개수",
    correctItem2: "두 번째 기억입력 정답 개수",
    correctItem3: "세 번째 기억입력 정답 개수",
    rememberResult: "기억 회상 결과",
    rememberCorrectItem: "기억 회상 정답 개수",
    visuospatialResult: "시공간 능력 결과",
    visuospatialCount: "시공간 능력 정답 개수",
    visuospatialOptionCount1: "시공간 능력 옵션 1 정답 개수",
    visuospatialOptionCount2: "시공간 능력 옵션 2 정답 개수",
    visuospatialOptionCount3: "시공간 능력 옵션 3 정답 개수",
    visuospatialOptionCount4: "시공간 능력 옵션 4 정답 개수",
    visuospatialTime: "시공간 능력 경과 시간",
    attentionResult: "주의력 결과",
    attentionCount: "주의력 정답 개수",
    executeResult: "집행기능 결과",
    executeTime: "집행기능 경과 시간",
    logDTOList: "로그",
    memoryScore: "기억입력 점수",
    visuospatialScore: "시공간 능력 점수",
    attentionScore: "주의력 점수",
    executeScore: "집행기능 점수",
    rememberScore: "기억회상 점수"
  };

  const handleOpen = async (result) => {
    setSelectedResult(result);
    setOpen(true);

    let apiUrl = '';
    let params = {};

    if (result.atestResult) {
      apiUrl = '/api/a-test-result-detail';
      params = { id: result.atestResult.id };
      setTestType('A'); // ATest임을 설정
    } else if (result.btestResult) {
      apiUrl = '/api/b-test-result-detail';
      params = { id: result.btestResult.id };
      setTestType('B'); // BTest임을 설정
    } else if (result.ctestResult) {
      apiUrl = '/api/c-test-result-detail';
      params = { id: result.ctestResult.id };
      setTestType('C'); // CTest임을 설정
    }

    if (apiUrl) {
      try {
        const response = await axios.get(apiUrl, { params });
        setTestDetail(response.data);
        if (response.data.logDTOList) {
          setLogDetails(response.data.logDTOList); // 로그 정보 저장
        } else {
          setLogDetails([]); // 로그 정보가 없으면 초기화
        }
      } catch (err) {
        setError('세부 정보를 가져오는 중 오류가 발생했습니다.');
      }
    }
  };

  const handleClose = () => {
    setOpen(false);
    setSelectedResult(null);
    setTestDetail(null); // 모달을 닫을 때 세부 정보도 초기화
    setLogDetails([]); // logDTOList 초기화
    setTestType(null); // 테스트 타입 초기화
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

  const renderLogTable = () => {
    if (!logDetails.length) return null;

    const logsGroupedByTest = logDetails.reduce((acc, log) => {
      const logTime = new Date(log.logTime).toLocaleString();
      const logContent = log.log;
      if (logContent.includes('테스트 시작')) {
        acc.currentTest = { startTime: logTime, logs: [] };
        acc.tests.push(acc.currentTest);
      }
      acc.currentTest.logs.push({ logTime, logContent });
      return acc;
    }, { tests: [], currentTest: { startTime: '', logs: [] } });

    return (
      <>
        {logsGroupedByTest.tests.map((test, index) => (
          <TableContainer component={Paper} key={index} sx={{ mt: 2 }}>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>테스트 시작 시간</TableCell>
                  <TableCell>로그 시간</TableCell>
                  <TableCell>로그 내용</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {test.logs.map((log, logIndex) => (
                  <TableRow key={logIndex}>
                    <TableCell>{logIndex === 0 ? test.startTime : ''}</TableCell>
                    <TableCell>{log.logTime}</TableCell>
                    <TableCell>{log.logContent}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        ))}
      </>
    );
  };

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
        <Box sx={{
          position: 'absolute',
          top: '50%',
          left: '50%',
          transform: 'translate(-50%, -50%)',
          width: '80vw', // 모달 너비를 80%로 설정
          maxWidth: 800, // 최대 너비 설정
          bgcolor: 'background.paper',
          border: '2px solid #000',
          boxShadow: 24,
          p: 4,
          maxHeight: '80vh', // 최대 높이 설정
          overflowY: 'auto' // 스크롤 추가
        }}>
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

              {testDetail && (
                <>
                  <Typography>테스트 세부 정보:</Typography>
                  <TableContainer component={Paper}>
                    <Table>
                      <TableHead>
                        <TableRow>
                          <TableCell>항목</TableCell>
                          <TableCell>결과</TableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {Object.entries(testDetail)
                          .filter(([key]) => key !== 'logDTOList') // logDTOList 제외
                          .filter(([key]) => testType === 'B' ? (key !== 'visuospatialOptionCount3' && key !== 'visuospatialOptionCount4') : true) // BTest인 경우 visuospatialOptionCount3 및 visuospatialOptionCount4 제외
                          .map(([key, value]) => (
                            <TableRow key={key}>
                              <TableCell>{fieldNames[key] || key}</TableCell> {/* 필드 이름 매핑 */}
                              <TableCell>
                                {typeof value === 'boolean' ? (value ? '성공' : '실패') : (typeof value === 'object' && !Array.isArray(value) ? JSON.stringify(value) : value)}
                              </TableCell>
                            </TableRow>
                          ))}
                      </TableBody>
                    </Table>
                  </TableContainer>
                </>
              )}

              {renderLogTable()}

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
