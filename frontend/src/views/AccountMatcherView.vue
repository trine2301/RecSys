<template>
  <div class="flex col">
    <div class="mx-5">
      <PeriodPicker class="my-5">
      </PeriodPicker>


      <DiffEngine >
      </DiffEngine>
    </div>
  </div>
  <div>
  </div>
</template>

<script setup lang="ts">

import PeriodPicker from "@/components/PeriodPicker.vue"
import PrimaryButton from "@/components/MatchTransactionButton.vue"
import DiffEngine from "@/components/DiffEngine.vue"
import { ref } from "vue"



</script>

<style lang="scss">
</style>