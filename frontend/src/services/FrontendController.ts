import {
  inject
} from "vue";
import axios
  from "axios";


const get = inject('get') as Function


const fetchPeriodComparison = async () => {
  return await axios.get('http://localhost:8080' + '/period_comparison')
}


export {
  fetchPeriodComparison
}