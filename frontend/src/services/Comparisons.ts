

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
}


const formattedDate = (dateStr: string) => {
  return format(new Date(dateStr), 'dd.MM.yyyy')
}



export {
  formattedDate,
}