import logo from "./logo.svg";
import "./App.css";
import React from "react";
import CSSModule from "./CSSModule";
import styles from "./App.scss";
import Button from "./components/Button/Button";
import StyledComponent from "./components/StyledComponent/StyledComponent";
import classNames from "classnames/bind";
import axios from "axios";

const cx = classNames.bind(styles);

function App() {
  return (
    <div>
      <button
        onClick={(e) => {
          // 첫번째 방법 ajax
          /*let request = new XMLHttpRequest();
          request.open("GET", "https://jsonplaceholder.typicode.com/users");
          request.send("");
          request.addEventListener("load", () => {
            let data = JSON.parse(request.responseText);
            console.log(data);
          });
          request.addEventListener("error", (error) => {
            console.error(error);
          });*/

          // 두번째 방법 fetch api
          /*fetch("https://jsonplaceholder.typicode.com/users")
            .then((response) => response.json())
            .then((data) => console.log(data))
            .catch((error) => console.log(error.message));*/

          // 세번째 방법 axios
          axios
            .get("https://jsonplaceholder.typicode.com/users")
            .then((response) => console.log(response.data))
            .catch((error) => console.log(error));
        }}
      >
        다운로드
      </button>
    </div>
  );
}

export default App;
