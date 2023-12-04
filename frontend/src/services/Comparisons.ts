

import format
  from "date-fns/format";

export type ComparisonResults = {

  bankTransactionEntity: {
    id: number;
    date: number[];
    amount: number;
    description: string;
  };
  accountingTransactionEntity: {
    id: number;
    date: number[];
    amount: number;
    description: string;
  };
  result: string;
  status: string;
}


const formattedDate = (dateStr: string) => {
  return format(new Date(dateStr), 'yyyy-MM-dd')
}



export {
  formattedDate,
}