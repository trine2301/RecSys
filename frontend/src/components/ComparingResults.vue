<template>
  <div class="flex">
    <div class="mt-2 border m-5">
      You have a total discrepancy of:
      <div class="text-3xl">
        {{ resultTotalAmount }}

      </div>
      <table class="bg-white">
        <thead>
        <tr class="text-left border-b border-gray-300">
          <th>ACCOUNTING TRANSACTIONS</th>
          <th>BANK TRANSACTIONS</th>
          <th>RESULT</th>
          <th>STATUS</th>
        </tr>
        </thead>
        <tbody class="">
        <tr class="p-2 bg-neutral even:bg-neutral-200 " v-for="transactionLine in transactionInfo">
          <td>
            {{ transactionLine.accountingTransactionEntity.date }},
            {{ transactionLine.accountingTransactionEntity.amount }},
            {{ transactionLine.accountingTransactionEntity.description }}
          </td>
          <td>
            {{ transactionLine.bankTransactionEntity.date }},
            {{ transactionLine.bankTransactionEntity.amount }},
            {{ transactionLine.bankTransactionEntity.description }}
          </td>
          <td>{{ transactionLine.result }}</td>
          <td>{{ transactionLine.status }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
  <div v-if="resultTotalAmount > 10000">
    <PopupComponent>
      You have discrepancies :/
    </PopupComponent>
  </div>
</template>

<script setup lang="ts">


import { onMounted, ref, computed, reactive } from "vue";
import PopupComponent from "@/components/PopupComponent.vue";
import { result, resultTotalAmount, fetchComparisonResults, fetchPeriodComparison } from "@/services/FrontendService";

const transactionInfo: Transaction[] = reactive([]);

fetch('http://localhost:8080' + '/period_comparison' + '/results')
    .then(response => response.json())
    .then(data => {
      transactionInfo.push(...data);
    });


 onMounted(async () => {
   await fetchComparisonResults()
   await fetchPeriodComparison()
 })


</script>

<style lang="scss">

</style>