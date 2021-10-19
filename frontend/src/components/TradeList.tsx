import React, { useEffect, useState } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { list } from '../services/trade.service';
import ITrade from '../types/trade.type';

interface RouterProps {
  history: string;
}

type Props = RouteComponentProps<RouterProps>;

const TradeList: React.FC<Props> = ({ history }) => {
  const [loading, setLoading] = useState<boolean>(false);
  const [message, setMessage] = useState<string>('');
  const [trades, setTrades] = useState<ITrade[]>();

  useEffect(() => {
    listTrades();
  }, []);

  const listTrades = async () => {
    setLoading(true);
    try {
      const response = await list();
      setTrades(response.data);
      setLoading(false);
    } catch (error: any) {
      console.info(JSON.stringify(error));
      const resMessage =
        (error.response &&
          error.response.data &&
          error.response.data.message) ||
        error.message ||
        error.toString();

      setLoading(false);
      setMessage(resMessage);
    }
  };

  return (
    <div className="col-md-12">
      {message && (
        <div className="form-group">
          <div className="alert alert-danger" role="alert">
            {message}
          </div>
        </div>
      )}
      <div className="card">
        <h2>Trades</h2>
        {loading && <span className="spinner-border spinner-border-sm"></span>}
        <table className="table">
          <thead>
            <tr>
              <th scope="col">Date</th>
              <th scope="col">Ticker</th>
              <th scope="col">Op</th>
              <th scope="col">Price</th>
              <th scope="col">Amount</th>
              <th scope="col">Exchage</th>
            </tr>
          </thead>
          <tbody>
            {trades?.map((trade) => (
              <tr key={trade.id}>
                <td>{new Date(trade.date).toLocaleDateString()}</td>
                <td>{trade.ticker}</td>
                <td>{trade.operationType}</td>
                <td>{trade.price}</td>
                <td>{trade.amount}</td>
                <td>{trade.exchange}</td>
                <td>
                  <Link to={`/trade/${trade.id}`}>edit</Link>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default TradeList;
