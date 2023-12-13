<template>
  <div class=" p-10 h-screen rounded scroll-auto overflow-y-scroll">
    <period-picker
        v-model="date"
        data-cy="period-picker"
    />

    <MatchTransactionButton
        class="my-5" @click="toggleDiffEngineResults()"
        data-cy="match-transactions-button"
    >
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
              <AlertBox message="This is the overview:" class="ml-auto mr-auto">
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
                <div v-if="resultAccAmount >= 0">
                  <button>
                    Accept reconcilliation?
                  </button>
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
import MatchTransactionButton from "@/components/PrimaryButton.vue"

import '@vuepic/vue-datepicker/dist/main.css'
import PeriodPicker from "@/components/PeriodPicker.vue";
import ComparingResults from "@/components/ComparingResults.vue";
import AlertBox from "@/components/AlertBox.vue";

import { formattedDate } from "@/services/FrontendService";



const isMatchTransactionsButtonPressed = ref(false)
const date = ref(new Date())
const get = inject('get') as Function

const resultPeriod = ref()
const resultDiscrepancy = ref()
const resultBankAmount = ref()
const resultAccAmount = ref()


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