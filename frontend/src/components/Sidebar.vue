<template>
  <div class="sidebar">
    <div class="title text-4xl text-center m-6 whitespace-nowrap">Trine bachelor</div>
    <div class="border-b border-b-amber-100/20 shadow-lg"/>
    <div class="menu-entries ml-6 mt-12">
      <router-link class="menu-entry" to="/">Home</router-link>
      <router-link class="menu-entry" to="/hello">Hello?</router-link>
    </div>
  </div>
</template>

<script setup lang="ts">

</script>

<style lang="scss">
.sidebar {
  @apply bg-gradient-to-br from-neutral-700 to-neutral-800 shadow-xl;
}

.menu-entry {
  @apply block p-4 rounded-l-full text-xl text-amber-200/60;

  &:hover:not(.router-link-active) {
    @apply text-amber-200;
  }

  &.router-link-active {
    @apply bg-amber-200 text-neutral-900;
  }
}

</style>