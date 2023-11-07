
<template>
  <div class=" p-10 h-screen rounded scroll-auto overflow-y-scroll">
    <period-picker/>
    <MatchTransactionButton class="my-5" @click="toggleDiffEngineResults()">
      Match transactions
    </MatchTransactionButton>
    <div>
      <div>
        <div class="flex" v-if="!isMatchTransactionsButtonPressed">
          <div class="rounded mx-1">
            <ListOfTransactions/>
          </div>
        </div>
        <div class="bg-blue-100" v-else>
          <div class="flex">
            <div>
              <div class="rounded mx-1">
                <div class="text-xl p-2">
                  Accounting transactions
                </div>
                Listing accounting transactions with comp.results here
              </div>
              <div class="rounded h-auto mx-1">
                <div class="text-xl p-2">
                  Bank transactions
                </div>
                Listing accounting transactions with comp.results here
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script setup lang="ts">


import { ref } from "vue"
import ListOfTransactions from "@/components/ListOfTransactions.vue"
import MatchTransactionButton from "@/components/MatchTransactionButton.vue"

import '@vuepic/vue-datepicker/dist/main.css'
import PeriodPicker from "@/components/PeriodPicker.vue";


const isMatchTransactionsButtonPressed = ref(false)
const date = ref(new Date())
const startDate = ref(null)
const endDate = ref(null)

//  v-if="isVisible"
const toggleDiffEngineResults = () => {
  isMatchTransactionsButtonPressed.value = !isMatchTransactionsButtonPressed.value
}



</script>

<style lang="scss">
</style>