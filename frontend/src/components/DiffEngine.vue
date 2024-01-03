<template>
  <div class=" p-10 h-screen rounded scroll-auto overflow-y-scroll">
    <period-picker
        v-model="date"
        data-cy="period-picker"
    />

    <PrimaryButton
        class="my-5" @click="toggleDiffEngineResults()"
        data-cy="match-transactions-button"
    >
      Reconcile period
    </PrimaryButton>
    <div>
      <div>
        <div class="flex" v-if="!isMatchTransactionsButtonPressed">
          <div>
              <ListOfTransactions/>
          </div>
        </div>
        <div  v-else>
          <div class="flex">
            <div>
              <AlertBox @accept-reco="resultForPeriod" message="This is the overview:" class="ml-auto mr-auto">
                <div>
                  Bank total:
                  {{ resultBankAmount }}
                </div>
                <div>
                  Acc total:
                  {{ resultAccAmount }}
                </div>
                <div data-cy="total-discrepancy">
                  Total discrepancy:
                  {{ resultDiscrepancy }}
                </div>
              </AlertBox>
              <ComparingResults :result-from-comparison="resultPeriod" />
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script setup lang="ts">


import { ref, inject, onMounted } from "vue"
import ListOfTransactions from "@/components/ListOfTransactions.vue"

import '@vuepic/vue-datepicker/dist/main.css'
import PeriodPicker from "@/components/PeriodPicker.vue";
import ComparingResults from "@/components/ComparingResults.vue";
import AlertBox from "@/components/AlertBox.vue";

import { formattedDate } from "@/services/FrontendService";
import PrimaryButton from "@/components/PrimaryButton.vue";




const isMatchTransactionsButtonPressed = ref(false)
const date = ref(new Date())
const get = inject('get') as Function
const post = inject('post') as Function

const resultPeriod = ref()
const resultDiscrepancy = ref(0)
const resultBankAmount = ref(0)
const resultAccAmount = ref(0)


const fetchComparisonResults = async (startDate: string, endDate: string) => {
  const response = await get(`/period_comparison/results?startDate=${startDate}&endDate=${endDate}`)
  resultPeriod.value = response.data
}

const fetchTotalDiscrepancyForPeriod = async (startDate: string, endDate: string) => {
  const response = await get(`/period_comparison/total_discrepancy?startDate=${startDate}&endDate=${endDate}`)
  const result = Math.round(response.data * 100) / 100;
  resultDiscrepancy.value = result;
}

const fetchTotalBankAmountForPeriod = async (startDate: string, endDate: string) => {
  const response = await get(`/period_comparison/total_amount_bank?startDate=${startDate}&endDate=${endDate}`)
  const result = Math.round(response.data * 100) / 100;
  resultBankAmount.value = result;
}

const fetchTotalAccAmountForPeriod = async (startDate: string, endDate: string) => {
  const response = await get(`/period_comparison/total_amount_accounting?startDate=${startDate}&endDate=${endDate}`)
  const result = Math.round(response.data * 100) / 100;
  resultAccAmount.value = result;
}

const fetchPeriodComparison = async (startDate: string, endDate: string, bankTotal: number, accTotal: number, totalDiscrepancyAmount: number) => {
  const response = await post(`/period_comparison/results_for_period?startDate=${startDate}&endDate=${endDate}`, {
    bankTotal: bankTotal,
    accTotal: accTotal,
    totalDiscrepancyAmount: totalDiscrepancyAmount,
  })
  console.log(response)
}

const resultForPeriod = async () => {
  await fetchPeriodComparison(formattedDate(date.value[0]), formattedDate(date.value[1]), resultBankAmount.value, resultAccAmount.value, resultDiscrepancy.value)
}


const toggleDiffEngineResults = () => {
  fetchComparisonResults(formattedDate(date.value[0]), formattedDate(date.value[1]))
  fetchTotalDiscrepancyForPeriod(formattedDate(date.value[0]), formattedDate(date.value[1]))
  fetchTotalBankAmountForPeriod(formattedDate(date.value[0]), formattedDate(date.value[1]))
  fetchTotalAccAmountForPeriod(formattedDate(date.value[0]), formattedDate(date.value[1]))
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