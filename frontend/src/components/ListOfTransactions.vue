<template>
  <div>
    <ul class="mt-2 border">
      <li class="p-2 bg-neutral even:bg-neutral-200" v-for="transactions in bankTransactions">
        {{ transactions.id }}:
        <!-- {{ transactions.date.toLocaleDateString }}: TODO fix the date format! -->
        {{ transactions.amount }}:
        {{ transactions.description }}
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import { ref, provide, inject, onMounted } from "vue"
import { BankTransaction } from "@/services/BankTransaction"

const get = inject('get') as Function


const bankTransactions = ref<Array<BankTransaction>>([])

const fetchBankTransactions = async () => {
  const response = await get('/banktransactions')
  bankTransactions.value = response.data
}


provide('fetchBankTransactions', fetchBankTransactions)

onMounted(fetchBankTransactions)


</script>

<style lang="scss">

</style>