<template>
  <div class="bg-red-500 p-10 max-w-screen">
    <div>
      Pick the period:
    </div>
    <div>
      <Datepicker
          v-model="date"
          range multi-calendars
      />
    </div>
  </div>
</template>

<script setup lang="ts">
import {onMounted, ref} from 'vue'
import Datepicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'

const date = ref(new Date());

const format = (date: any) => {
  const day = date.getDate()
  const month = date.getMonth() + 1
  const year = date.getFullYear()

  return `${day}/${month}/${year}`
}

onMounted(() => {
  const startDate = new Date();
  const endDate = new Date(new Date().setDate(startDate.getDate() + 7));
  date.value = [startDate, endDate];
})


</script>






<style lang="scss">

</style>