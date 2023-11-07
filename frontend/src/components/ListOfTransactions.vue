<template>
  <div class="bg-blue-200 p-5 rounded w-350">
    <Datepicker
        v-model="date"
        default="Pick a dateaa"
        placeholder="Pick a date"
        format="dd/MM/yyyy"
        range multi-calendars
    />
  </div>
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
import { ref, provide, inject, onMounted, computed } from "vue"
import { BankTransaction, formattedDate } from "@/services/BankTransaction"
import Datepicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'
import { AccountingTransaction } from "@/services/AccountingTransaction";

const date = ref(new Date())
const get = inject('get') as Function
const bankTransactions = ref<Array<BankTransaction>>([])
const accountingTransactions = ref<Array<AccountingTransaction>>([])
//const filteredBankTransactions = ref<Array<BankTransaction>>([])
const startDate = ref(null)
const endDate = ref(null)


const fetchBankTransactions = async () => {
  const response = await get('/bank_transactions')
  bankTransactions.value = response.data
  //console.log(formattedDate(bankTransactions.value[0].date))
}

const fetchAccountingTransactions = async () => {
  const response = await get('/accounting_transactions')
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

onMounted(fetchBankTransactions)
onMounted(fetchAccountingTransactions)



onMounted(() => {
  const startDate = new Date()
  const endDate = new Date(new Date().setDate(startDate.getDate() + 7))
  date.value = [startDate, endDate]
})


</script>

<style lang="scss">

</style>