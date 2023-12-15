<template>
  <button class="bg-blue-200 hover:border-blue-400 base-200 rounded border font-bold py-2 px-4 cursor-pointer" @click="emit('click')">
    <slot/>
  </button>
</template>

<script setup lang="ts">

const emit = defineEmits(['click'])

</script>



<style lang="scss">

</style>