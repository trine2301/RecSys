<template>
  <div class="flex min-w-[400px] border" data-cy="compare-results">
        <div class="text-3xl">
        </div>
        <table>
          <thead class="bg-blue-200">
          <tr class="text-left">
            <th>
              <div>
                ACCOUNTING TRANSACTIONS
              </div>
              <div class="flex justify-between">
                <div class="flex mx-5">
                  <div class="title">
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
            <div>
              BANK TRANSACTIONS
            </div>
            <div class="flex justify-between">
              <div class="flex mx-5">
                <div class="title">
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
            <th class="mx-5">RESULT</th>
          </tr>
          </thead>
          <tbody v-if="resultFromComparison">
          <tr class="p-2 bg-neutral odd:bg-blue-200/20 border" v-for="transactionLine in resultFromComparison">
            <td v-if="transactionLine.accountingTransactionEntity" class="flex justify-between">
              <div class="flex mx-5">
                <div class="title">
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
              <div class="flex">
                <div class="title">
                  {{ formattedDate(transactionLine.bankTransactionEntity.date) }}
                </div>
                <div>
                  {{ transactionLine.bankTransactionEntity.description }}
                </div>
              </div>
              <div>
                {{ transactionLine.bankTransactionEntity.amount }}
              </div>
            </td>
            <td v-else>
            </td>
            <td >
              <div v-if="transactionLine.result === 'MATCH'" class="flex justify-between my-2" >
                match
                <ph-check-fat :size="20" color="green" weight="fill" class="mx-2"/>
              </div>
              <div v-if="transactionLine.result === 'PARTIAL_MATCH'" class="flex justify-between my-2">
                possible match
                <ph-warning :size="20" color="orange" weight="fill" class="mx-2"/>
              </div>
              <div v-if="transactionLine.result.includes('MISSING')" class="flex justify-between my-2">
                no match
                <ph-warning :size="20" color="red" weight="fill" class="mx-2"/>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
</template>

<script setup lang="ts">
import { PhHeart, PhCheckFat, PhWarning } from "@phosphor-icons/vue"
import { formattedDate } from "../services/FrontendService";


defineProps(['resultFromComparison'])



</script>

<style lang="scss">

.title {
  @apply w-32
}

</style>