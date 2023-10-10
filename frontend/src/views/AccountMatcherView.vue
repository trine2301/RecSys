<template>
    <div>
      Account matcher-page
    </div>
</template>

<script setup lang="ts">
import { inject, ref, provide, onMounted } from "vue";
import { Account } from "@/Account";

const get = inject('get') as Function

const accounts = ref<Array<Account>>([])

const fetchAccounts = async () => {
  const response = await get('/accounts')
  accounts.value = response.data
}

provide('fetchAccounts', fetchAccounts)

onMounted(fetchAccounts)
</script>

<style lang="scss">
</style>