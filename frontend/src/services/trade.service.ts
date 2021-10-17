import axios from 'axios';
import ITrade from '../types/trade.type';
import authToken from './auth-token';

const API_URL = '/api/';

export const save = (trade: any) => {
  trade.date = new Date(trade.date).getTime();
  if (trade.id) {
    return axios.put<any>(API_URL + 'trades', trade, {
      headers: {
        Authorization: authToken(),
      },
    });
  }
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

export const get = (id: string) => {
  return axios.get<any>(API_URL + 'trades/' + id, {
    headers: {
      Authorization: authToken(),
    },
  });

  // return axios.get(API_URL + 'trades/' + id, {
  //   headers: {
  //     Authorization: authToken(),
  //   },
  // });
};
