import {
  ref
} from "vue";
import axios
  from "axios";



const result = ref()


const fetchPeriodComparison = async () => {
  return await axios.get('http://localhost:8080' + '/period_comparison')
}

const fetchComparisonResults = async () => {
  const response = await fetchPeriodComparison()
  result.value = response.data
}


export {
  fetchPeriodComparison,
  fetchComparisonResults,
  result
}