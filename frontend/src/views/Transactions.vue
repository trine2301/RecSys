<template>
<div>
  Accounts
<!--  <div>-->
<!--    {{ bankTransactions }}-->
<!--  </div>-->
<!--  <div>-->
<!--    {{ fetchBankTransactions }}-->
<!--  </div>-->

  <ul class="mt-2 border bg-red-500/10">
    <li class="p-2 bg-neutral-800 even:bg-neutral-900" v-for="transactions in bankTransactions">
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

// const fetchBankTransactions = async () => {
//   const response = await get('/banktransactions')
//   bankTransactions.value = response.data
// }
//
//
// provide('fetchBankTransactions', fetchBankTransactions)
//
// onMounted(fetchBankTransactions)


</script>

<style lang="scss">

</style>