<template>
  <button class="bg-amber-500 hover:bg-amber-400 text-neutral-700 py-2 px-3 rounded" @click="emit('click')">
    <slot/>
  </button>
</template>

<script setup>
const emit = defineEmits(['click'])
</script>

<style lang="scss">

</style>