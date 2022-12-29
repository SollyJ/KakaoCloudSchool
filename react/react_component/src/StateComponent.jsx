import React, { Component, useState } from "react";

// class StateComponent extends Component {
//   // 생성자
//   constructor(props) {
//     // 상위 클래스의 생성자 호출
//     super(props);
//     this.state = { number: 0 };
//   }

//   render() {
//     return (
//       <>
//         <p> 숫자: {this.state.number}</p>
//         <button
//           // 이벤트처리
//           onClick={(e) => {
//             // state의 값 변경
//             this.setState((prevState) => {
//               return { number: this.state.number + 1 };
//             });
//           }}
//         >
//           증가
//         </button>
//       </>
//     );
//   }
// }

const StateComponent = () => {
  // 함수형 컴포넌트에서 state를 생성하는 방법
  const [message, setMessage] = useState("");

  const onClickEnter = (e) => {
    setMessage("헤이😜");
  };
  const onClickLeave = (e) => {
    setMessage("잘가🥺");
  };

  const [color, setColor] = useState("red");

  return (
    <>
      <button onClick={onClickEnter}>등장</button>
      <button onClick={onClickLeave}>퇴장</button>
      <h1 style={color}>{message}</h1>
    </>
  );
};

export default StateComponent;
