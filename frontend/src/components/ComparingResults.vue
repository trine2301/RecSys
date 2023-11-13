<template>
  <div class="flex">
    <div class="mt-2 border">
      <div class="text-xl">
        Results:
      </div>
      {{ result }}
    </div>
  </div>

</template>

<script setup lang="ts">


import { ref, inject, onMounted } from "vue";
import { Comparisons } from "@/services/Comparisons";
import { fetchPeriodComparison } from "@/services/FrontendController";


const get = inject('get') as Function
const comparisons = ref<Array<Comparisons>>([])
const result = ref()

const fetchComparisonResults = async () => {
  const response = await fetchPeriodComparison()
  result.value = response.data
}

onMounted(async () => {
  await fetchComparisonResults()
})

</script>

<style lang="scss">

</style>