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
    <div className="container">
      <header className="jumbotron">
        <p>Total paid: {content['totalPaid']?.toFixed(2)}</p>
        <p>Actual value: {content['actualValue']?.toFixed(2)}</p>
        <p>Actual value BRL: {content['actualValueBRL']?.toFixed(2)}</p>
        <p>Profitability USD: {content['profitability']?.toFixed(2)}</p>
      </header>
    </div>
  );
};

export default Home;
