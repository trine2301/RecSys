<template>
  <div class="flex flex-col">
      <saved-period-card v-for="periodComparison in periodComparisons" :key="periodComparison.id" @click="isButtonPressed">
        <template #title> {{ formattedDate(periodComparison.startDate)}} - {{ formattedDate(periodComparison.endDate)}} </template>
        <template #description>
          <div>
            Bank total:
            {{ periodComparison.bankTotal}}
          </div>
          <div>
            Accounting total:
            {{ periodComparison.accTotal}}
          </div>
          <div data-cy="total-discrepancy">
            Total discrepancy:
            {{ periodComparison.totalDiscrepancyAmount}}
          </div>
        </template>
      </saved-period-card>

    <div v-if="isButtonPressed">
      <comparison-modal :isOpen="isButtonPressed.value === true" message="hey" />
    </div>

    </div>
</template>

<script setup lang="ts">

import { ref, provide, inject, onMounted } from "vue"
import '@vuepic/vue-datepicker/dist/main.css'
import { PeriodComparison } from "@/services/PeriodComparisons";
import SavedPeriodCard from "@/components/Card.vue";
import ComparisonModal from "@/components/ComparisonModal.vue";
import { formattedDate } from "@/services/FrontendService"



const isButtonPressed = ref(false)
const date = ref(new Date())
const get = inject('get') as Function
const periodComparisons = ref<Array<PeriodComparison>>([])


/**
 * Fetch period comparisons.
 */
const fetchPeriodComparison = async () => {
  const response = await get('/transactions/period_comparison')
  periodComparisons.value = response.data
}





provide('fetchPeriodComparison', fetchPeriodComparison)

onMounted(fetchPeriodComparison)




</script>

<style lang="scss">

</style>