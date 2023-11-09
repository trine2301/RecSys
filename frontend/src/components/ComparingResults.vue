<template>
  <div class="flex">
    <div class="mt-2 border">
      <div class="text-xl">
        Results:
      </div>
      <div class="p-2 bg-neutral even:bg-neutral-200" v-for="comparison in comparisons">
        {{ comparison.id }}
      </div>
      {{ }}
    </div>
  </div>

</template>

<script setup lang="ts">


import { ref, inject } from "vue";
import { Comparisons } from "@/services/Comparisons";


const get = inject('get') as Function
const comparisons = ref<Array<Comparisons>>([])

const fetchResults = async () => {
  const response = await get('/period_comparison')
  comparisons.value = response.data
  console.log(comparisons.value.length)
}

</script>

<style lang="scss">

</style>