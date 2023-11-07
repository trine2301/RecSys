import format
  from "date-fns/format";

export type AccountingTransaction = {

  id: string,
  date: string, //Date ?
  amount: number,
  description: string
}


const formattedDate = (dateStr: string) => {
  return format(new Date(dateStr), 'dd.MM.yyyy')
}



export {
  formattedDate
}