<template>
  <PhHeart :size="32" />
  <div class="flex" data-cy="compare-results">
    <div class="mt-2 border m-5">
      <div class="text-3xl">
      </div>
      <table class="bg-white">
        <thead>
        <tr class="text-left border-b border-gray-300">
          <th>
            <div>
              ACCOUNTING TRANSACTIONS
            </div>
            <div class="flex justify-between">
              <div class="flex">
                <div class="w-32">
                  date
                </div>
                <div>
                  Description
                </div>
              </div>
              <div>
                Amount
              </div>
            </div>

          </th>
          <th>BANK TRANSACTIONS</th>
          <th>RESULT</th>
        </tr>
        </thead>
        <tbody v-if="resultFromComparison">
        <tr class="p-2 bg-neutral even:bg-neutral-200/50 " v-for="transactionLine in resultFromComparison">
          <td v-if="transactionLine.accountingTransactionEntity" class="flex justify-between">
            <div class="flex">
              <div class="w-32">
                {{ formattedDate(transactionLine.accountingTransactionEntity.date) }}
              </div>
              <div>
                {{ transactionLine.accountingTransactionEntity.description }}
              </div>
            </div>
            <div>
              {{ transactionLine.accountingTransactionEntity.amount }}
            </div>


          </td>
          <td v-if="transactionLine.bankTransactionEntity">
            {{ transactionLine.bankTransactionEntity.date }},
            {{ transactionLine.bankTransactionEntity.amount }},
            {{ transactionLine.bankTransactionEntity.description }}
          </td>
          <td v-else>

          </td>
          <td >
            <div v-if="transactionLine.result === 'MATCH'" class="flex justify-between p-2" >
              match
              <ph-check-fat :size="20" color="green" weight="fill"/>
            </div>
            <div v-if="transactionLine.result === 'PARTIAL_MATCH'" class="flex justify-between p-2" >
              partial match
              <ph-warning :size="20" color="orange" weight="fill"/>
            </div>
            <div v-if="transactionLine.result.includes('MISSING')" class="flex justify-between p-2">
              not match
              <ph-warning :size="20" color="red" weight="fill"/>
            </div>


          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup lang="ts">
import { PhHeart, PhCheckFat, PhWarning } from "@phosphor-icons/vue"
import { formattedDate } from "../services/FrontendService";


defineProps(['resultFromComparison'])




</script>

<style lang="scss">

</style>