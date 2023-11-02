<template>
  <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" @click="emit('click')">
    <slot/>
  </button>
</template>

<script setup lang="ts">
const emit = defineEmits(['click'])
</script>



<style lang="scss">

</style>