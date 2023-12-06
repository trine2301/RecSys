<template>
  <div class=" p-10 h-screen rounded scroll-auto overflow-y-scroll">
    <period-picker
        v-model="date"
    />
    {{ resultDiscrepancy }}

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
import { result, formattedDate, resultTotalAmount } from "@/services/FrontendService";
import axios from "axios/index";
//import { fetchComparisonResults } from "@/services/FrontendService";


const isMatchTransactionsButtonPressed = ref(false)
const date = ref(new Date())
const get = inject('get') as Function

const resultPeriod = ref()
const resultDiscrepancy = ref()


const fetchComparisonResults = async (startDate: string, endDate: string) => {

  const response = await get(`/period_comparison/results?startDate=${startDate}&endDate=${endDate}`)
  resultPeriod.value = response.data
}

const fetchTotalDiscrepancyForPeriod = async (startDate: string, endDate: string) => {
  const response = await get(`/period_comparison/total_discrepancy?startDate=${startDate}&endDate=${endDate}`)
  resultDiscrepancy.value = response.data
}


const toggleDiffEngineResults = () => {
  fetchComparisonResults(formattedDate(date.value[0]), formattedDate(date.value[1]))
  fetchTotalDiscrepancyForPeriod(formattedDate(date.value[0]), formattedDate(date.value[1]))
  isMatchTransactionsButtonPressed.value = !isMatchTransactionsButtonPressed.value
}




onMounted(() => {
  const startDate = new Date()
  const endDate = new Date(new Date().setDate(startDate.getDate() + 7))
  date.value = [startDate, endDate]
})


</script>

<style lang="scss">
</style>