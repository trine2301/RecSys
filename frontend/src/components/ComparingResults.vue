<template>
  <div class="flex" data-cy="compare-results">
    <div class="mt-2 border m-5">
      <div class="text-3xl">


      </div>
      <table class="bg-white">
        <thead>
        <tr class="text-left border-b border-gray-300">
          <th>ACCOUNTING TRANSACTIONS</th>
          <th>BANK TRANSACTIONS</th>
          <th>RESULT</th>
        </tr>
        </thead>
        <tbody class="" v-if="resultFromComparison">
        <tr class="p-2 bg-neutral even:bg-neutral-200 " v-for="transactionLine in resultFromComparison">
          <td v-if="transactionLine.accountingTransactionEntity">
            {{ transactionLine.accountingTransactionEntity.date }},
            {{ transactionLine.accountingTransactionEntity.amount }},
            {{ transactionLine.accountingTransactionEntity.description }}
          </td>
          <td v-if="transactionLine.bankTransactionEntity">
            {{ transactionLine.bankTransactionEntity.date }},
            {{ transactionLine.bankTransactionEntity.amount }},
            {{ transactionLine.bankTransactionEntity.description }}
          </td>
          <td v-else>

          </td>
          <td v-if="">
            {{ transactionLine.result }}
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
<!--  <div v-if="resultTotalAmount > 0">-->
<!--    <PopupComponent>-->
<!--      You have discrepancies :/-->
<!--    </PopupComponent>-->
<!--  </div>-->
</template>

<script setup lang="ts">


import { onMounted, ref, computed, reactive , inject} from "vue";
import PopupComponent from "@/components/PopupComponent.vue";
import { result, resultTotalAmount } from "@/services/FrontendService";

defineProps(['resultFromComparison'])





/*
 onMounted(async () => {
   await fetchComparisonResults("2023-01-01", "2023-12-31")
   //await fetchPeriodComparison()
 })*/

/*onMounted(async () => {
  await fetchPeriodComparison()
})*/



</script>

<style lang="scss">

</style>