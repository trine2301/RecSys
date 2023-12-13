<template>
    <div v-if="showAlertBox" class="fixed z-10 inset-0 overflow-y-auto">
      <div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
        <div class="fixed inset-0 transition-opacity" aria-hidden="true">
          <div class="absolute inset-0 bg-blue-300 opacity-75"></div>
        </div>
        <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>
        <div class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full">
          <div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
            <div>{{ message }}</div>
            <slot></slot>
            <button class="cursor-pointer text-2xl " @click="closeAlert">×</button>
          </div>
        </div>
      </div>
    </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { onClickOutside } from '@vueuse/core'

const target = ref(null)

onClickOutside(target, (event) => closeAlert())

const showAlertBox = ref(true)

const closeAlert = () => {
  showAlertBox.value = false
}

const openAlert = () => {
  showAlertBox.value = true
}

defineProps({
  message: {
    type: String,
    required: true
  }
})


const isButtonPressed = ref(false);


const togglePopup = () => {
  isButtonPressed.value = !isButtonPressed.value
}



</script>
