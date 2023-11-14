import {
  ref
} from "vue";
import axios
  from "axios";
import transactions
  from "@/views/Transactions.vue";
import format
  from "date-fns/format";



const result = ref()
const resultTotalAmount = ref()


const fetchPeriodComparison = async () => {
  const response = await axios.get('http://localhost:8080' + '/period_comparison')
  resultTotalAmount.value = response.data
}


const fetchComparisonResults = async () => {
  const response = await axios.get('http://localhost:8080' + '/period_comparison' + '/results')
  result.value = response.data
}

const formattedDate = (dateStr: string) => {
  return format(new Date(dateStr), 'dd.MM.yyyy')
}



export {
  fetchPeriodComparison,
  fetchComparisonResults,
  formattedDate,
  result,
  resultTotalAmount
}