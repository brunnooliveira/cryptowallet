import React, { useState, useEffect } from 'react';

import { getWallet } from '../services/wallet.service';

const Home: React.FC = () => {
  const [content, setContent] = useState<any>('');

  useEffect(() => {
    getWallet().then(
      (response) => {
        setContent(response.data);
      },
      (error) => {
        const _content =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

        setContent(_content);
      }
    );
  }, []);

  return (
    <div className="col-md-12">
      <div className="card">
        <h5>Total paid: {content['totalPaid']?.toFixed(2)}</h5>
        <h5>Actual value: {content['actualValue']?.toFixed(2)}</h5>
        <h5>Actual value BRL: {content['actualValueBRL']?.toFixed(2)}</h5>
        <h5>Profitability USD: {content['profitability']?.toFixed(2)}</h5>
      </div>
    </div>
  );
};

export default Home;
