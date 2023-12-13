<template>
  <AlertBox message="This is the overview:">
    <div>
      Bank total:
      -93773.01
    </div>
    <div>
      Acc total:
      9393.04
    </div>
    <div data-cy="total-discrepancy">
      Total discrepancy:
      234.09
    </div>
  </AlertBox>
</template>

<script setup lang="ts">
import AlertBox from "@/components/AlertBox.vue";

</script>

<style lang="scss">

</style>