<template>
  <div class="min-w-[600px]">
    <div class="flex">
      <div class="mt-2 border">
        <div class="text-xl p-2 mx-5 min-w-[400px]">
          Accounting Transactions
        </div>
        <div class="p-2 flex justify-between">
          <div class="flex mx-5">
            <div class="w-32">
              Date
            </div>
            <div>
              Description
            </div>
          </div>
          <div>
            Amount
          </div>
        </div>
        <div class="p-2 bg-neutral odd:bg-blue-200/20 flex justify-between border" v-for="transaction in accountingTransactions">
          <div class="flex mx-5">
            <div class="w-32">
              {{ formattedDate(transaction.date) }}
            </div>
            <div>
              {{ transaction.description }}
            </div>
          </div>
          <div>
            {{ transaction.amount }}
          </div>
        </div>
      </div>
      <div class="mt-2 border min-w-[400px]">
        <div class="text-xl p-2 mx-5">
          Banking Transactions:
        </div>
        <div class="p-2 flex justify-between mx-5">
          <div class="flex">
            <div class="w-32">
              Date
            </div>
            <div>
              Description
            </div>
          </div>
          <div>
            Amount
          </div>
        </div>
        <div class="p-2 bg-neutral odd:bg-blue-200/20 flex justify-between border" v-for="transaction in accountingTransactions">
          <div class="flex mx-5">
            <div class="w-32">
              {{ formattedDate(transaction.date) }}
            </div>
            <div>
              {{ transaction.description }}
            </div>
          </div>
          <div>
            {{ transaction.amount }}
          </div>
        </div>
      </div>
    </div>
  </div>


</template>

<script setup lang="ts">
import { BankTransaction } from "@/services/BankTransaction"
import '@vuepic/vue-datepicker/dist/main.css'
import {formattedDate} from "@/services/FrontendService";
import { AccountingTransaction } from "@/services/AccountingTransaction"
import { PeriodComparison } from "@/services/PeriodComparisons"
import { ref, onMounted, computed, provide, inject } from "vue"

const date = ref(new Date())
const get = inject('get') as Function
const bankTransactions = ref<Array<BankTransaction>>([])
const accountingTransactions = ref<Array<AccountingTransaction>>([])
const periodComparisons = ref<Array<PeriodComparison>>([])

const startDate = ref(null)
const endDate = ref(null)

/**
 * Fetch the comparison results for a given period.
 */
const fetchPeriodComparison = async () => {
  const response = await get('/transactions/period_comparison')
  periodComparisons.value = response.data
}

/**
 * Fetch all bank transctions.
 */
const fetchBankTransactions = async () => {
  const response = await get('/transactions/bank')
  bankTransactions.value = response.data
}

/**
 * Fetch all accounting transactions.
 */
const fetchAccountingTransactions = async () => {
  const response = await get('/transactions/accounting')
  accountingTransactions.value = response.data
}

/**
 * Filter bank transactions based on a date range.
 * If start- and end dates are not provided, all bank transactions are returned.
 */
const filteredBankTransactions = computed(() => {
  if (!startDate.value || !endDate.value) {
    return bankTransactions.value
  }
  return bankTransactions.value.filter(transaction => {
    const transactionDate = new Date(transaction.date)
    return transactionDate >= startDate.value! && transactionDate <= endDate.value!
  })
})


/**
 * Provide the 'fetchBankTransactions', 'fetchAccountingTransactions', and 'fetchPeriodComparison' functions
 * to child components. These functions fetch different types of transactions.
 */
provide('fetchBankTransactions', fetchBankTransactions)
provide('fetchAccountingTransactions', fetchAccountingTransactions)
provide('fetchPeriodComparison', fetchPeriodComparison)


/**
 * Call the 'fetchBankTransactions', 'fetchAccountingTransactions', and 'fetchPeriodComparison' functions
 * when the component is mounted, so that the necessary data is fetched as soon as the component is rendered.
 */
onMounted(fetchBankTransactions)
onMounted(fetchAccountingTransactions)
onMounted(fetchPeriodComparison)





</script>

<style lang="scss">

</style>