<template>
  <div class="flex flex-col p-5">
    <div class="text-2xl">
      This is all the transactions we have in the system:
    </div>
    <div class="my-2">
      <ListOfTransactions />
    </div>
  </div>
</template>

<script setup lang="ts">
import ListOfTransactions from "@/components/ListOfTransactions.vue"

</script>

<style lang="scss">

</style>