
<template>
  <div class="bg-blue-200 p-10 h-screen rounded scroll-auto overflow-y-scroll" >
    <MatchTransactionButton class="my-5" @click="toggleDiffEngine()">
      Match transactions
    </MatchTransactionButton>
    <div class="flex">
      <div class="bg-blue-100 rounded mx-1 w-80">
        <div class="text-xl p-2">
          Accounting transactions
        </div>
        <ListOfTransactions v-if="isVisible"/>
      </div>
      <div class="bg-blue-100 rounded h-auto mx-1 w-80">
        <div class="text-xl p-2">
          Bank transactions
        </div >
        <ListOfTransactions v-if="isVisible"/>
      </div>
    </div>

  </div>
</template>

<script setup lang="ts">


import { ref } from "vue"
import ListOfTransactions from "@/components/ListOfTransactions.vue";
import MatchTransactionButton from "@/components/MatchTransactionButton.vue";

const isVisible = ref(false)

const toggleDiffEngine = () => {
  isVisible.value = !isVisible.value
}

</script>

<style lang="scss">
</style>