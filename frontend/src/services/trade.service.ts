import axios from 'axios';
import ITrade from '../types/trade.type';
import authToken from './auth-token';

const API_URL = 'api/';

export const save = (trade: ITrade) => {
  return axios.post<any>(API_URL + 'trades', trade, {
    headers: {
      Authorization: authToken(),
    },
  });
};

export const list = () => {
  return axios.get(API_URL + 'trades', {
    headers: {
      Authorization: authToken(),
    },
  });
};
