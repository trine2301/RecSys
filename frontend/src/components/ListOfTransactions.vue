<template>
  <div class="flex">
    <div class="mt-2 border">
      <div class="text-xl">
        Accounting Transactions:
      </div>
      <div class="p-2 bg-neutral even:bg-neutral-200" v-for="transaction in accountingTransactions">
        {{ transaction.id }}:
        {{ formattedDate(transaction.date) }}
        {{ transaction.amount }}:
        {{ transaction.description }}
      </div>
    </div>
    <ul class="mt-2 border">
      <div class="text-xl">
        Banking Transactions:
      </div>
      <li class="p-2 bg-neutral even:bg-neutral-200" v-for="transaction in filteredBankTransactions">
        {{ transaction.id }}:
        {{ formattedDate(transaction.date) }}
        {{ transaction.amount }}:
        {{ transaction.description }}
      </li>
    </ul>
  </div>

</template>

<script setup lang="ts">
import { BankTransaction, formattedDate } from "@/services/BankTransaction"
import '@vuepic/vue-datepicker/dist/main.css'
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

const fetchPeriodComparison = async () => {
  const response = await get('/transactions/period_comparison')
  periodComparisons.value = response.data
}

const fetchBankTransactions = async () => {
  const response = await get('/transactions/bank')
  bankTransactions.value = response.data
}

const fetchAccountingTransactions = async () => {
  const response = await get('/transactions/accounting')
  accountingTransactions.value = response.data
}

const filteredBankTransactions = computed(() => {
  if (!startDate.value || !endDate.value) {
    return bankTransactions.value
  }
  return bankTransactions.value.filter(transaction => {
    const transactionDate = new Date(transaction.date)
    return transactionDate >= startDate.value! && transactionDate <= endDate.value!
  })
})



provide('fetchBankTransactions', fetchBankTransactions)
provide('fetchAccountingTransactions', fetchAccountingTransactions)
provide('fetchPeriodComparison', fetchPeriodComparison)

onMounted(fetchBankTransactions)
onMounted(fetchAccountingTransactions)
onMounted(fetchPeriodComparison)





</script>

<style lang="scss">

</style>