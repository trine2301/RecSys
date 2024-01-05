<template>
    <div class="bg-blue-200 p-5 rounded w-350">
      <Datepicker
          v-model="date"
          placeholder="Pick a date"
          format="yyyy-MM-dd"
          data-cy="calendar-date"
          range multi-calendars
      />
    </div>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import Datepicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'


/**
 * Define a computed property 'date' that gets and sets a 'modelValue' prop.
 * When the 'date' property is set, it emits an 'update:modelValue' event with the new value.
 */
const date = computed({
  get: () => props.modelValue,
  set: val => {
    emit('update:modelValue', val)
  }
})

/**
 * Defines the events that this component can emit.
 */
const emit = defineEmits(['update:modelValue'])

/**
 * Define the props that this component accepts.
 */
const props = defineProps(['modelValue'])

</script>


<style lang="scss">

</style>