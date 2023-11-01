<template>
  <div class="flex">
    <sidebar/>
    <router-view class="content grow"></router-view>
  </div>
</template>

<script setup lang="ts">

import Sidebar from '@/components/Sidebar.vue'
</script>

<style lang="scss">
</style>