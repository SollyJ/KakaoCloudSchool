import React, { Component } from "react";
import "./ValidationSample.css";

class ValidationSample extends Component {
  input = React.createRef();

  // state: 클래스 안의 멤버 변수나 함수 안의 지역 변수와 유사
  // state는 변경이 되면 화면에 바로 적용된다.
  state = {
    password: "",
    clicked: false,
    validated: false,
  };

  handleButtonClick = (e) => {
    this.setState({
      clicked: true,
      validated: this.state.password === "0000",
    });
    // input이 참조하는 객체에 focus를 설정
    this.input.current.focus();
  };

  handleChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value,
    });
  };

  render() {
    return (
      <>
        <input
          ref={this.input}
          type="password"
          value={this.state.password}
          name="password"
          onChange={this.handleChange}
          className={
            this.state.clicked
              ? this.state.validated
                ? "success"
                : "failure"
              : ""
          }
        />
        <button onClick={this.handleButtonClick}>검증하기</button>
      </>
    );
  }
}

export default ValidationSample;
