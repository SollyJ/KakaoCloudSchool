import React, { Component } from "react";

class Iteration extends Component {
  state = {
    names: ["react"],
    name: "",
  };

  // input에 입력하면 name state의 값을 변경하는 이벤트 처리 함수
  handleChange = (e) => {
    this.setState({
      name: e.target.value,
    });
  };

  // name의 값을 names에 추가해주는 함수
  handleInsert = (e) => {
    this.setState({
      names: this.state.names.concat(this.state.name), // 배열을 복제해서 연결하는 concat이용
      name: "",
    });
  };

  // 데이터 삭제 함수
  // index를 매개변수로 받아서 삭제
  handleRemove = (index) => {
    // 삭제하기 전에 대화상자 출력
    let result = window.confirm("정말로 삭제");
    if (result === false) {
      return;
    }

    const { names } = this.state; // const names = this.state.names

    /*// slice 이용한 삭제
    this.setState({
      names: [names.slice(0, index), names.slice(index + 1, names.length)],
    });*/

    // filter 이용한 삭제
    this.setState({
      names: names.filter((item, e) => e !== index), // 넘어온 인덱스와 배열의 인덱스가 다른 것만 추출
    });
  };

  render() {
    const nameList = this.state.names.map((name, index) => (
      <li key={index}>
        {name}
        <button onClick={() => this.handleRemove(index)}>삭제</button>
      </li>
    ));
    return (
      <>
        <input onChange={this.handleChange} value={this.state.name} />
        <button onClick={this.handleInsert}>추가</button>
        <ul>{nameList}</ul>
      </>
    );
  }
}

export default Iteration;
