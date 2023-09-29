<template>
    <ul>
      <li v-for="account in accounts" :key="account.id">
        {{ account.name }}
      </li>
    </ul>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const accounts = ref([]);

async function fetchAccounts() {
  const response = await fetch('/account');
  accounts.value = await response.json();
}

fetchAccounts();

</script>

<style lang="scss">

</style>