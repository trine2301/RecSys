<template>
    <div class="bg-blue-200 p-5 rounded w-350">
      <Datepicker
          v-model="date"
          placeholder="Pick a date"
          format="yyyy-MM-dd"
          range multi-calendars
      />
    </div>
</template>

<script setup lang="ts">
import { onMounted, computed } from 'vue'
import Datepicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'

const date = computed({
  get: () => props.modelValue,
  set: val => {
    emit('update:modelValue', val)
  }
})

//const date = ref(new Date())
const emit = defineEmits(['update:modelValue'])
const props = defineProps(['modelValue'])



onMounted(() => {

})

</script>


<style lang="scss">

</style>