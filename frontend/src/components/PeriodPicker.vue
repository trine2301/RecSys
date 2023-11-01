<template>
    <div class="bg-blue-200 p-10 rounded w-96 ">
      <Datepicker
          v-model="date"
          default="Pick a dateaa"
          placeholder="Pick a date"
          format="dd/MM/yyyy"
          range multi-calendars
      />
    </div>
</template>

<script setup lang="ts">
import {onMounted, ref} from 'vue'
import Datepicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'

const date = ref(new Date())

onMounted(() => {
  const startDate = new Date()
  const endDate = new Date(new Date().setDate(startDate.getDate() + 7))
  date.value = [startDate, endDate]
})


</script>






<style lang="scss">

</style>