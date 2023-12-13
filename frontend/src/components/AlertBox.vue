<template>
    <div v-if="showAlertBox" class="fixed z-10 inset-0 overflow-y-auto flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
      <div class="absolute inset-0 bg-blue-300 opacity-75"></div>
        <div class="inline-block px-4 align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full bg-white pt-5 pb-4 sm:p-6 sm:pb-4">
          <div>
            <div>{{ message }}</div>
            <slot></slot>
          </div>
          <button class="cursor-pointer text-2xl " @click="closeAlert">×</button>
          <button>
            <div class="flex">
              <div>
                Accept reconcilliation?
              </div>
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                <path stroke-linecap="round" stroke-linejoin="round" d="M4.5 12.75l6 6 9-13.5" />
              </svg>
            </div>
          </button>
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
