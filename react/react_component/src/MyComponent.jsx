import React, { Component } from "react";
import PropTypes from "prop-types";

// // 클래스형 컴포넌트
// class MyComponent extends Component {
//   render() {
//     const { name, year, children } = this.props;
//     return (
//       <>
//         <div>나의 컴포넌트이름은 {name}입니다.</div>
//         <p>내가 태어난 해는 {year}입니다.</p>
//       </>
//     );
//   }
// }

// 함수형 컴포넌트
const MyComponent = (props) => {
  return (
    <>
      <div>나의 컴포넌트이름은 {props.name}입니다. </div>
      <div>태그 안의 내용은 "{props.children}"입니다. </div>
      <p>나는 {props.year}년에 태어났습니다.</p>
    </>
  );
};

MyComponent.propTypes = {
  name: PropTypes.string,
  year: PropTypes.number.isRequired,
};

MyComponent.defaultProps = {
  name: "기본값",
};

export default MyComponent;
