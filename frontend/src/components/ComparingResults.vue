<template>
  <div v-if="result != 0">
    <PopupComponent>
      You have discrepancies :/
    </PopupComponent>
  </div>
  <div class="flex">
    <div class="mt-2 border m-5">
      You have a total discrepancy of:
      <div class="text-3xl">
        {{ result }}
      </div>
    </div>
  </div>


</template>

<script setup lang="ts">


import { onMounted } from "vue";
import PopupComponent from "@/components/PopupComponent.vue";
import { result, fetchComparisonResults } from "@/services/FrontendService";



 onMounted(async () => {
   await fetchComparisonResults()
 })


</script>

<style lang="scss">

</style>