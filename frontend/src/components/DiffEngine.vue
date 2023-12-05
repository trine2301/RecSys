<template>
  <div class=" p-10 h-screen rounded scroll-auto overflow-y-scroll">
    <period-picker
        v-model="date"
    />

    <MatchTransactionButton class="my-5" @click="toggleDiffEngineResults()">
      See results
    </MatchTransactionButton>
    <div>
      <div>
        <div class="flex" v-if="!isMatchTransactionsButtonPressed">
          <div class="rounded mx-1">
            <ListOfTransactions/>
          </div>
        </div>
        <div class="bg-blue-100" v-else>
          <div class="flex">
            <div>
              <ComparingResults :result-from-comparison="resultPeriod"/>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script setup lang="ts">


import { ref, reactive, inject, onMounted } from "vue"
import ListOfTransactions from "@/components/ListOfTransactions.vue"
import MatchTransactionButton from "@/components/PrimaryButton.vue"

import '@vuepic/vue-datepicker/dist/main.css'
import PeriodPicker from "@/components/PeriodPicker.vue";
import ComparingResults from "@/components/ComparingResults.vue";
import MyModal from './MyModal.vue';
import PopupComponent from "@/components/PopupComponent.vue";
import { result, formattedDate } from "@/services/FrontendService";
//import { fetchComparisonResults } from "@/services/FrontendService";


const isMatchTransactionsButtonPressed = ref(false)
const date = ref(new Date())
const startDate = ref(null)
const endDate = ref(null)
const transactionInfo: Transaction[] = reactive([]);
const get = inject('get') as Function

const resultPeriod = ref()


const fetchComparisonResults = async (startDate: string, endDate: string) => {

  const response = await get(`/period_comparison/results?startDate=${startDate}&endDate=${endDate}`)
  resultPeriod.value = response.data
}

//  v-if="isVisible"
const toggleDiffEngineResults = () => {
  fetchComparisonResults(formattedDate(date.value[0]), formattedDate(date.value[1]))
  isMatchTransactionsButtonPressed.value = !isMatchTransactionsButtonPressed.value
}

/*const fetchResults = async ()  => {
  await fetchComparisonResults("2023-01-01", "2023-12-31")
}*/

onMounted(() => {
  const startDate = new Date()
  const endDate = new Date(new Date().setDate(startDate.getDate() + 7))
  date.value = [startDate, endDate]
})


</script>

<style lang="scss">
</style>