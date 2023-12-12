<template>
  <div class="sidebar h-screen w-200">
    <router-link class="title text-4xl text-center m-6 whitespace-nowrap text-blue-900" to="/home">
      Home </router-link>
    <div class="border-b border-b-amber-100/20 shadow-lg"/>
    <div class="menu-entries ml-6 mt-12">
      <router-link class="menu-entry" to="/transactions">See transactions</router-link>
      <router-link data-cy="account-matcher-site" class="menu-entry" to="/accountMatcher">Account-matcher</router-link>
    </div>
  </div>
</template>



<script setup lang="ts">

</script>



<style lang="scss">


.sidebar {
  @apply bg-gradient-to-br from-neutral-200 to-neutral-300 shadow-xl;
}

.menu-entry {
  @apply block p-4 rounded-l-full text-xl text-blue-800/60;

  &:hover:not(.router-link-active) {
    @apply text-blue-800;
  }

  &.router-link-active {
    @apply bg-blue-200 text-blue-800;
  }
}


</style>