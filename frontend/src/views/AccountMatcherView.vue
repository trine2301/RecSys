<template>
    <div>
      <PeriodPicker/>
    </div>
</template>

<script setup lang="ts">
import { inject, ref, provide, onMounted } from "vue"
import PeriodPicker from "@/components/PeriodPicker.vue";


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