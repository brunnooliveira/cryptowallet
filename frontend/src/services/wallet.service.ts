import axios from 'axios';
import authToken from './auth-token';

const API_URL = 'api/wallets';

export const getWallet = () => {
  return axios.get(API_URL, {
    headers: {
      Authorization: authToken(),
    },
  });
};
