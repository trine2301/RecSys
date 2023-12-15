<template>
  <div class="flex flex-col">
    <saved-period-card v-for="periodComparison in periodComparisons" :key="periodComparison.id">
      <template #title> {{ periodComparison.startDate}} - {{ periodComparison.endDate}} </template>
      <template #description>
        <div>
          id: {{ periodComparison.id}}
        </div>
        <div>
          Bank total:
          {{ periodComparison.bankTotal}}
        </div>
        <div >∫
          Acc total:
          {{ periodComparison.accTotal}}
        </div>
        <div data-cy="total-discrepancy">
          Total discrepancy:
          {{ periodComparison.totalDiscrepancyAmount}}
        </div>
      </template>
    </saved-period-card>
  </div>
</template>

<script setup lang="ts">

import { ref, provide, inject, onMounted } from "vue"
import '@vuepic/vue-datepicker/dist/main.css'
import { PeriodComparison } from "@/services/PeriodComparisons";
import SavedPeriodCard from "@/components/SavedPeriodCard.vue";

const date = ref(new Date())
const get = inject('get') as Function
const periodComparisons = ref<Array<PeriodComparison>>([])


const fetchPeriodComparison = async () => {
  const response = await get('/transactions/period_comparison')
  periodComparisons.value = response.data
}


provide('fetchPeriodComparison', fetchPeriodComparison)

onMounted(fetchPeriodComparison)




</script>

<style lang="scss">

</style>