<template>
  <div class="flex flex-col">
    <ComparedPeriodCard>
      <template #title> 2023-01-01 - 2023.01.31 </template>
      <template #description>
        <div>
          Bank total:
          xxx.xx
        </div>
        <div>
          Acc total:
          xxx.xx
        </div>
        <div data-cy="total-discrepancy">
          Total discrepancy:
          xxx.xx
        </div>
      </template>
    </ComparedPeriodCard>

  </div>
</template>

<script setup lang="ts">

import ComparedPeriodCard from "@/components/Card.vue";


</script>

<style lang="scss">

</style>