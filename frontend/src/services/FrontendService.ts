import {
  ref
} from "vue";
import axios
  from "axios";



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


export {
  fetchPeriodComparison,
  fetchComparisonResults,
  result,
  resultTotalAmount
}