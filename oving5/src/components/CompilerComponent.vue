<template>
  <div id="container">
    <h1>C++ compile!</h1>
    <form class="content">
      <div class="input">
        <label for="inputArea">Input code:</label>
        <textarea
          v-model="inputCode"
          id="inputArea"
          width="100%"
          name="inputArea"
          rows="20"
        >
        </textarea>
      </div>
      <div class="input">
        <label for="inputArea">Output:</label>
        <textarea
          disabled=""
          id="outputArea"
          width="100%"
          name="outputArea"
          rows="20"
        ></textarea>
      </div>
      <button @click="submitForm">Compile</button>
      <p id="info"></p>
    </form>
  </div>
</template>

<script>
import { runCode } from "@/utils/apiUtils";

export default {
  name: "CompilerComponent",
  props: {
    msg: String,
  },
  data() {
    return {
      inputCode: "",
    };
  },
  methods: {
    async submitForm() {
      console.log(this.inputCode);
      let response = await runCode(this.inputCode);
      console.log(response);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}

*,
html {
  margin: 0;
  padding: 0;
  border: 0;
  font-family: "Trebuchet MS", Helvetica, sans-serif;
  color: white;
}
#container {
  background-color: rgb(236, 152, 42);
}
.content {
  margin: 10px;
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-gap: 10px;
}
h1 {
  font-size: 120px;
  padding: 10px;
}
.input {
  display: flex;
  flex-direction: column;
}
textarea {
  color: black;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid black;
  resize: none;
  font-family: monospace;
}
button {
  color: black;
  padding: 20px;
  border-radius: 5px;
  border: 1px solid black;
  cursor: pointer;
  font-size: 20px;
  font-weight: bold;
}
button:hover {
  background-color: cornsilk;
}
@media screen and (max-width: 600px) {
  h1 {
    font-size: 60px;
  }
  .content {
    grid-template-columns: 1fr;
  }
}
</style>
