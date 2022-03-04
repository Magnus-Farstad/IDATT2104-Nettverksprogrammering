import axios from "axios";

export function runCode(sourceCode) {
  return axios
    .post(`http://localhost:8086/compile`, sourceCode)
    .then((response) => {
      return response.data;
    });
}
