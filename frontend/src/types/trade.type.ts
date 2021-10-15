export default interface ITrade {
  id: string;
  date: Date;
  ticker: string;
  operationType: string;
  price: number;
  amount: number;
  exchange: string;
}
