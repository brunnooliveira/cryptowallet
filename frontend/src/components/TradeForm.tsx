import React, { useEffect, useState } from 'react';
import { Formik, Field, Form, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import { RouteComponentProps } from 'react-router-dom';
import DatePicker from 'react-datepicker';
import CurrencyInput from 'react-currency-input-field';

import ITrade from '../types/trade.type';
import { save, get } from '../services/trade.service';

interface RouterProps {
  // history: string;
  id: string;
}

type Props = RouteComponentProps<RouterProps>;

const TradeForm: React.FC<Props> = ({ history, match }) => {
  const [loading, setLoading] = useState<boolean>(false);
  const [message, setMessage] = useState<string>('');
  const [initialValues, setInitialValues] = useState<ITrade>({
    id: '',
    date: new Date(),
    ticker: 'BTCUSDT',
    operationType: 'BUY',
    price: 0,
    amount: 0,
    exchange: 'BITCOINTRADE',
  });

  useEffect(() => {
    loadTrade(match.params.id);
  }, [match.params.id]);

  const validationSchema = Yup.object().shape({
    date: Yup.string().required('This field is required!'),
    ticker: Yup.string().required('This field is required!'),
    operationType: Yup.string().required('This field is required!'),
    price: Yup.string().required('This field is required!'),
    amount: Yup.string().required('This field is required!'),
    exchange: Yup.string().required('This field is required!'),
  });

  const loadTrade = async (id: string) => {
    try {
      if (id) {
        var response = await get(id);
        if (response) {
          response.data.date = new Date(response.data.date);
          setInitialValues(response.data);
        }
      }
    } catch (error: any) {
      console.error(error.message);
    }
  };

  const handleSave = async (formValue: ITrade) => {
    setMessage('');
    setLoading(true);

    console.info(formValue);
    try {
      await save(formValue);
      setLoading(false);
      history.push('/trades');
    } catch (error: any) {
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
      <div className="card card-container">
        <h2>Trade</h2>
        <Formik
          initialValues={initialValues}
          validationSchema={validationSchema}
          onSubmit={handleSave}
          enableReinitialize={true}
        >
          {({ values, setFieldValue }) => (
            <Form>
              <div className="form-group">
                <label htmlFor="date">Date</label>
                <DatePicker
                  selected={values.date}
                  dateFormat="yyyy-MM-dd"
                  className="form-control"
                  name="date"
                  onChange={(date) => setFieldValue('date', date)}
                />
                <ErrorMessage
                  name="date"
                  component="div"
                  className="alert alert-danger"
                />
              </div>

              <div className="form-group">
                <label htmlFor="ticker">Ticker</label>
                <Field name="ticker" type="text" className="form-control" />
                <ErrorMessage
                  name="ticker"
                  component="div"
                  className="alert alert-danger"
                />
              </div>

              <div className="form-group">
                <label htmlFor="operationType">Operation</label>
                <Field
                  name="operationType"
                  type="text"
                  className="form-control"
                />
                <ErrorMessage
                  name="operationType"
                  component="div"
                  className="alert alert-danger"
                />
              </div>

              <div className="form-group">
                <label htmlFor="price">Price</label>
                <CurrencyInput
                  name="price"
                  className="form-control"
                  value={values.price}
                  decimalsLimit={8}
                  disableGroupSeparators={true}
                  onValueChange={(value) => setFieldValue('price', value)}
                />
                <ErrorMessage
                  name="price"
                  component="div"
                  className="alert alert-danger"
                />
              </div>

              <div className="form-group">
                <label htmlFor="amount">Amount</label>
                <CurrencyInput
                  name="amount"
                  className="form-control"
                  value={values.amount}
                  decimalsLimit={8}
                  disableGroupSeparators={true}
                  onValueChange={(value) => setFieldValue('amount', value)}
                />
                <ErrorMessage
                  name="amount"
                  component="div"
                  className="alert alert-danger"
                />
              </div>

              <div className="form-group">
                <label htmlFor="exchange">Exchange</label>
                <Field name="exchange" type="text" className="form-control" />
                <ErrorMessage
                  name="exchange"
                  component="div"
                  className="alert alert-danger"
                />
              </div>

              <div className="form-group">
                <button
                  type="submit"
                  className="btn btn-primary btn-block"
                  disabled={loading}
                >
                  {loading && (
                    <span className="spinner-border spinner-border-sm"></span>
                  )}
                  <span>Save</span>
                </button>
              </div>

              {message && (
                <div className="form-group">
                  <div className="alert alert-danger" role="alert">
                    {message}
                  </div>
                </div>
              )}
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
};

export default TradeForm;
