<template>
  <div class=" p-10 h-screen rounded scroll-auto overflow-y-scroll">
    <period-picker/>
    <MatchTransactionButton class="my-5" @click="toggleDiffEngineResults()">
      See results
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
              <ComparingResults/>
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
import MatchTransactionButton from "@/components/PrimaryButton.vue"

import '@vuepic/vue-datepicker/dist/main.css'
import PeriodPicker from "@/components/PeriodPicker.vue";
import ComparingResults from "@/components/ComparingResults.vue";
import MyModal from './MyModal.vue';
import PopupComponent from "@/components/PopupComponent.vue";


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