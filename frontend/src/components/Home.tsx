import React, { useState, useEffect } from 'react';
// import { AdvancedRealTimeChart } from 'react-ts-tradingview-widgets';

import { getWallet } from '../services/wallet.service';

const Home: React.FC = () => {
  const [loading, setLoading] = useState<boolean>(false);
  const [content, setContent] = useState<any>('');

  useEffect(() => {
    getWallet().then(
      (response) => {
        setContent(response.data);
        setLoading(false);
      },
      (error) => {
        const _content =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

        setContent(_content);
        setLoading(false);
      }
    );
  }, []);

  return (
    <>
      <div className="row">
        <div className="col">
          <div className="card">
            <h3>Summary</h3>
            <table className="table">
              <thead>
                <tr>
                  <th scope="col">Exchange</th>
                  <th scope="col">Investment</th>
                  <th scope="col">Actual Value</th>
                  <th scope="col">Profit USD %</th>
                  <th scope="col">Actual value (BRL)</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>TOTAL</td>
                  <td>{content['totalPaid']?.toFixed(2)}</td>
                  <td>{content['actualValue']?.toFixed(2)}</td>
                  <td>{content['profitability']?.toFixed(2)}</td>
                  <td>{content['actualValueBRL']?.toFixed(2)}</td>
                </tr>
                {content['exchangeWallets']?.map((wallet: any) => (
                  <tr key={wallet.exchange}>
                    <td>{wallet.exchange}</td>
                    <td>{wallet.totalPaid?.toFixed(2)}</td>
                    <td>{wallet.actualValue?.toFixed(2)}</td>
                    <td>{wallet.profitability?.toFixed(2)}</td>
                    <td>{wallet.actualValueBRL?.toFixed(2)}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
      {/* <div className="row">
        <div className="col">
          <AdvancedRealTimeChart
            theme="light"
            symbol="BINANCE:BTCUSDT"
            interval="W"
            width="auto"
            height="350"
            hide_side_toolbar={true}
            hide_top_toolbar={true}
          ></AdvancedRealTimeChart>
        </div>
      </div> */}

      <div className="card">
        <h3>Assets</h3>
        {loading && <span className="spinner-border spinner-border-sm"></span>}
        <table className="table">
          <thead>
            <tr>
              <th scope="col">Ticker</th>
              <th scope="col">Amount</th>
              <th scope="col">Avg Price</th>
              <th scope="col">Actual Price</th>
              <th scope="col">Investment</th>
              <th scope="col">Actual Value</th>
              <th scope="col">Profit</th>
            </tr>
          </thead>
          <tbody>
            {content['assets']?.map((asset: any) => (
              <tr key={asset.ticker}>
                <td>{asset.ticker}</td>
                <td>{asset.amount}</td>
                <td>{asset.price?.toFixed(2)}</td>
                <td>{asset.actualPrice?.toFixed(2)}</td>
                <td>{asset.totalPaid?.toFixed(2)}</td>
                <td>{asset.actualValue?.toFixed(2)}</td>
                <td>{asset.profitability?.toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
};

export default Home;
