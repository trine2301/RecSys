

import format
  from "date-fns/format";

export type PeriodComparison = {
  id: string,
  startDate: string,
  endDate: string,
  totalDiscrepancyAmount: number,
  bankTotal: number,
  accTotal: number,

}


const formattedDate = (dateStr: string) => {
  return format(new Date(dateStr), 'yyyy-MM-dd')
}



export {
  formattedDate,
}