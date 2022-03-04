<template>
  <div id="container">
    <h2>C++ compile & run!</h2>
    <form class="content" @submit.prevent="submitForm">
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
        <div id="buttonContainer">
          <button id="compileButton">Compile</button>
        </div>
      </div>
      <div class="input">
        <label for="inputArea">Output:</label>
        <textarea
          disabled=""
          id="outputArea"
          width="100%"
          name="outputArea"
          rows="20"
          v-model="output"
        ></textarea>
      </div>
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
      inputCode:
        "#include <iostream>\n" +
        "using namespace std;\n" +
        "int main()\n" +
        "{\n" +
        "  {\n" +
        '    cout << "Hello meg" << endl;\n' +
        "  }\n" +
        "}",
      output: "",
    };
  },
  methods: {
    async submitForm() {
      //console.log(this.inputCode);
      const apiCallObject = {
        sourceCode: this.inputCode,
        output: "",
      };
      //console.log(apiCallObject);
      let response = await runCode(apiCallObject);
      console.log(response);

      this.output = response.output;
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
  background-color: rgb(84, 83, 81);
  width: 800px;
  margin: 80px auto;
  box-shadow: 3px 5px 5px 5px rgba(194, 194, 194, 0.96);
  border-radius: 10px;
}
.content {
  margin: 10px;
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-gap: 10px;
}
h2 {
  font-size: 40px;
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
  box-shadow: 2px 3px 3px 3px;
}
#buttonContainer {
  justify-content: center;
}
button {
  color: black;
  padding: 20px;
  border-radius: 5px;
  border: 1px solid black;
  cursor: pointer;
  font-size: 20px;
  font-weight: bold;
  margin-top: 10px;
  margin-bottom: 0;
  box-shadow: 2px 3px 3px 3px;
  width: 50%;
}
button:hover {
  background-color: cornsilk;
  transform: scale(101%);
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
