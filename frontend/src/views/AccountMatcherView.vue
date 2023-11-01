<template>
  <div class="flex col">
    <div class="m-10">
      <PeriodPicker>
      </PeriodPicker>

      <primary-button class="my-5" @click="toggleDiffEngine()">
        Match transactions
      </primary-button>

      <DiffEngine v-if="isVisible">
      </DiffEngine>
    </div>
  </div>
  <div>
  </div>
</template>

<script setup lang="ts">

import PeriodPicker from "@/components/PeriodPicker.vue"
import PrimaryButton from "@/components/PrimaryButton.vue";
import DiffEngine from "@/components/DiffEngine.vue";
import { ref } from "vue";

const isVisible = ref(false)

const toggleDiffEngine = () => {
  isVisible.value = !isVisible.value
}

</script>

<style lang="scss">
</style>