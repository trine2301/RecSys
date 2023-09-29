<template>
  <select class="bg-neutral-500 p-1 rounded" @change="onSelect">
    <option v-for="account in accounts" :value="account.accountNumber">
      {{account.accountNumber}}: {{account.name}}
    </option>
  </select>
</template>

<script setup lang="ts">
import {Account} from '@/Account'
import {PropType} from 'vue'

defineProps({
  accounts: { type: Array as PropType<Array<Account>>, default: () => []}
})

const emit = defineEmits(['select'])
const onSelect = (ev: Event) => {
  emit('select', (ev.target as HTMLSelectElement).value)
}
</script>

<style scoped>

</style>