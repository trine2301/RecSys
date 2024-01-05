<template>
  <div class="flex min-w-[400px] border" data-cy="compare-results">
        <table>
          <thead class="bg-blue-200">
          <tr class="text-left">
            <th>
              <div class="mx-2">
                ACCOUNTING TRANSACTIONS
              </div>
              <div class="flex justify-between">
                <div class="flex mx-2">
                  <div class="w-[120px]">
                    Date
                  </div>
                  <div>
                    Description
                  </div>
                </div>
                <div class="px-2">
                  Amount
                </div>
              </div>
            </th>
            <th class="border">
              <div class="mx-2">
                BANKING TRANSACTIONS
              </div>
              <div class="flex justify-between">
                <div class="flex mx-2">
                  <div class="w-[120px]">
                    Date
                  </div>
                  <div>
                    Description
                  </div>
                </div>
                <div class="px-2">
                  Amount
                </div>
              </div>
            </th>
            <th class="px-5">
              RESULT
            </th>
          </tr>
          </thead>
          <tbody v-if="resultFromComparison">
          <tr class="p-2 bg-neutral odd:bg-blue-200/20 border rounded" v-for="transactionLine in resultFromComparison">
            <td v-if="transactionLine.accountingTransactionEntity" class="flex justify-between">
              <div class="flex mx-2">
                <div class="my-2 mr-5">
                  {{ formattedDate(transactionLine.accountingTransactionEntity.date) }}
                </div>
                <div class="m-2">
                  {{ transactionLine.accountingTransactionEntity.description }}
                </div>
              </div>
              <div class="my-2">
                {{ transactionLine.accountingTransactionEntity.amount }}
              </div>
            </td>
            <td v-if="transactionLine.bankTransactionEntity" class="border">
              <div class="flex mx-2">
                <div class="my-2">
                  {{ formattedDate(transactionLine.bankTransactionEntity.date) }}
                </div>
                <div class="my-2 mx-9 w-[130px]">
                  {{ transactionLine.bankTransactionEntity.description }}
                </div>
                <div class="my-2">
                  {{ transactionLine.bankTransactionEntity.amount }}
                </div>
              </div>
            </td>

            <td v-else>
            </td>
            <td>
              <div v-if="transactionLine.result === 'MATCH'" class="flex justify-between mx-5" >
                match
                <ph-check-fat :size="20" color="green" weight="fill" class="mx-2"/>
              </div>
              <div v-if="transactionLine.result === 'PARTIAL_MATCH'" class="flex justify-between">
                partial match
                <ph-warning :size="20" color="orange" weight="fill" class="mx-2"/>
              </div>
              <div v-if="transactionLine.result.includes('MISSING')" class="flex justify-between">
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
import { PhCheckFat, PhWarning } from "@phosphor-icons/vue"
import { formattedDate } from "../services/FrontendService";

/**
 * Defines properties 'resultFromComparison' for the component.
 * This prop should contain the result of a comparison.
 */
defineProps(['resultFromComparison'])



</script>

<style lang="scss">


</style>