<template>
  <div class="home">
    <CompilerComponent />
  </div>
</template>

<script>
// @ is an alias to /src
import CompilerComponent from "@/components/CompilerComponent";

export default {
  name: "HomeView",
  components: {
    CompilerComponent,
  },
};
</script>
