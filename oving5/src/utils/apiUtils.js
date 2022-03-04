import axios from "axios";

export function runCode(sourceCode) {
  return axios
    .post(`http://localhost:8085/login`, sourceCode)
    .then((response) => {
      return response.data;
    });
}
