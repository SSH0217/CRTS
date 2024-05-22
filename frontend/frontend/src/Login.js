import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { Container, TextField, Button, Box, Typography, Alert } from '@mui/material';

const Login = ({ setIsLoggedIn, setSupervision }) => {
  const [loginId, setUsername] = useState('');
  const [loginPw, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setError(''); // Reset the error message
    try {
      const response = await axios.post(
        '/api/login-real',
        { loginId, loginPw },
        {
          headers: {
            'Content-Type': 'application/json'
          },
          withCredentials: true // 자격 증명 포함 (필요 시)
        }
      );
      if (response.status === 200) {
        const supervisionResponse = await axios.get('/api/supervision-info', {
          params: { loginId },
          headers: {
            'Content-Type': 'application/json'
          },
          withCredentials: true
        });

        if (supervisionResponse.status === 200) {
          setSupervision(supervisionResponse.data); // Supervision 데이터 상태로 설정
        }

        setIsLoggedIn(true);
        navigate('/dashboard');
      }
    } catch (error) {
      if (error.response && error.response.status === 401) {
        setError('Invalid credentials');
      } else {
        console.error('Login failed', error);
        setError('아이디 또는 비밀번호 오류');
      }
    }
  };

  return (
    <Container maxWidth="xs">
      <Box
        display="flex"
        flexDirection="column"
        alignItems="center"
        justifyContent="center"
        minHeight="100vh"
      >
        <Typography variant="h4" component="h1" gutterBottom>
          CRTS
        </Typography>
        {error && <Alert severity="error">{error}</Alert>}
        <form onSubmit={handleLogin}>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="username"
            label="Username"
            name="username"
            autoComplete="username"
            autoFocus
            value={loginId}
            onChange={(e) => setUsername(e.target.value)}
          />
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            autoComplete="current-password"
            value={loginPw}
            onChange={(e) => setPassword(e.target.value)}
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            sx={{ mt: 3, mb: 2 }}
          >
            로그인
          </Button>
        </form>
      </Box>
    </Container>
  );
}

export default Login;
