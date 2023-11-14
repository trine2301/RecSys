<template>
  <div class="flex">
    <div class="mt-2 border m-5">
      You have a total discrepancy of:
      <div class="text-3xl">
        {{ resultTotalAmount }}

      </div>
      <div>
        {{ result }}
      </div>
    </div>
  </div>
  <div v-if="result != 0">
    <PopupComponent>
      You have discrepancies :/
    </PopupComponent>
  </div>


</template>

<script setup lang="ts">


import { onMounted, ref } from "vue";
import PopupComponent from "@/components/PopupComponent.vue";
import { result, resultTotalAmount, fetchComparisonResults, fetchPeriodComparison } from "@/services/FrontendService";



 onMounted(async () => {
   await fetchComparisonResults()
   await fetchPeriodComparison()
 })


</script>

<style lang="scss">

</style>