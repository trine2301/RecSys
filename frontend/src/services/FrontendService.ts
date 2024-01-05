import { ref, inject } from "vue";
import axios from "axios";
import transactions from "@/views/ComparedPeriods.vue";
import format from "date-fns/format";



const result = ref()
const resultTotalAmount = ref()
//const get = inject('get') as Function

/*const fetchPeriodComparison = async (startDate: string, endDate: string) => {
  const response = await axios.get(`/period_comparison/total_discrepancy?startDate=${startDate}&endDate=${endDate}`)
  resultTotalAmount.value = response.data
}*/


/*const fetchComparisonResults = async (fromDate: string, toDate: string) => {

  const response = await get('/period_comparison' + '/results')
  result.value = response.data
}*/


/**
 * Function to format a date string into a 'yyyy-MM-dd'-format.
 * @param dateStr - The date string to be formatted.
 * @returns The formatted date string.
 */
const formattedDate = (dateStr: string) => {
  return format(new Date(dateStr), 'yyyy-MM-dd')
}



export {
  //fetchPeriodComparison,
  //fetchComparisonResults,
  formattedDate,
  result,
  resultTotalAmount
}