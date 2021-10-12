import axios from 'axios';
import authToken from './auth-token';

const API_URL = 'http://localhost:8080/api/test/';

export const getPublicContent = () => {
  return axios.get(API_URL + 'all');
};

export const getUserBoard = () => {
  return axios.get(API_URL + 'user', {
    headers: {
      Authorization: authToken(),
    },
  });
};

export const getModeratorBoard = () => {
  return axios.get(API_URL + 'mod', {
    headers: {
      Authorization: authToken(),
    },
  });
};

export const getAdminBoard = () => {
  return axios.get(API_URL + 'admin', {
    headers: {
      Authorization: authToken(),
    },
  });
};
