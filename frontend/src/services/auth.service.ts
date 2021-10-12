import axios from 'axios';

const API_URL = 'http://localhost:8080/api/public/';

export const register = (username: string, email: string, password: string) => {
  return axios.post<any>(API_URL + 'register', {
    username,
    email,
    password,
  });
};

export const login = (username: string, password: string) => {
  return axios
    .post<any>(API_URL + 'authenticate', {
      username,
      password,
    })
    .then((response) => {
      if (response.data.token) {
        localStorage.setItem('user', JSON.stringify(response.data));
      }

      return response.data;
    });
};

export const logout = () => {
  localStorage.removeItem('user');
};

export const getCurrentUser = () => {
  const userStr = localStorage.getItem('user');
  if (userStr) return JSON.parse(userStr);

  return null;
};
