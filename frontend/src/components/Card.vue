<template>
  <div class="p-10">
    <div class=" h-48 bg-base-200 shadow-lg rounded border border-blue-200 ease-in-out duration-300 hover:bg-blue-100">
      <div class="hello text-2xl">
        <slot name="title"/>
      </div>
      <div class="hello">
        <slot name="description"/>
      </div>
    </div>
  </div>
</template>



<style lang="scss">

.hello {
  @apply  p-4 text-blue-800/80
}

</style>