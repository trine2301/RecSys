<template>
  <div class="fixed z-10 inset-0 overflow-y-auto flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block" v-if="showModal" >
    <div class="absolute inset-0 bg-blue-300 opacity-75"></div>
    <div>
      <div class="inline-block px-4 text-center align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full bg-white pt-5 pb-4 sm:p-6 sm:pb-4">
        <div class="text-2xl py-4">
          {{ message }}
        </div>
        <slot></slot>
        <div>
          <div class="flex flex items-end justify-center mt-5">
            <primary-button @click="closeModal">
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="red" class="w-6 h-6">
                <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
              </svg>
            </primary-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss">

</style>


<script lang="ts" setup>
import { ref, defineProps } from 'vue'
import PrimaryButton from "@/components/PrimaryButton.vue";

const showModal = ref(true)

const closeModal = () => {
  showModal.value = false
}

/**
 * Defines property 'message' for a component.
 *
 * @param message - The message is the title of the modal.
 */
defineProps({
  message: {
    type: String,
    required: true
  }
})



</script>
