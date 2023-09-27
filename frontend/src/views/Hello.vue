<template>
  <div>
    <primary-button @click="fetchHello">Hello?</primary-button>
    <div class="mt-4 text-xl w-fit p-2 rounded">{{ result }}</div>
  </div>
</template>

<script setup lang="ts">
import {ref} from 'vue'
import axios from 'axios'
import PrimaryButton from '@/components/PrimaryButton.vue'

const result = ref()

const fetchHello = async () => {
  const response = await axios.get('http://localhost:8082/hello')
  result.value = response.data
}

</script>

<style lang="scss">

</style>