import React, { Component, useState } from "react";

/* // 클래스형 컴포넌트
class EventPractice extends Component {
  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
    this.handleClick = this.handleClick.bind(this);
  }

  state = {
    name: "",
    message: "",
  };

  //babel 이 인스턴스의 메서드로 변환을 자동으로 수행
  handleChange(e) {
    this.setState({
      [e.target.name]: e.target.value, // e.target은 이벤트가 발생한 객체를 의미
    });
  }

  handleClick(e) {
    alert(this.state.name + ":" + this.state.message);
    this.setState({
      name: "",
      message: "",
    });
  }

  render() {
    return (
      <>
        <h1>이벤트 연습</h1>
        <input
          type="text"
          name="name"
          placeholder="이름을 입력하세요"
          value={this.state.name}
          onChange={this.handleChange}
        />
        <input
          type="text"
          name="message"
          placeholder="메시지를 입력하세요"
          value={this.state.message}
          onChange={this.handleChange}
        />
        <button onClick={this.handleClick}>확인</button>
      </>
    );
  }
}*/

// 함수형 컴포넌트
const EventPractice = () => {
  //const [name, setName] = useState("");
  //const [message, setMessage] = useState("");
  //합쳐서 쓰는 방법
  const [form, setForm] = useState({
    userName: "",
    message: "",
  });
  const { userName, message } = form;

  const onChange = (e) => {
    // form을 복제해서 e.target.name에 해당하는 속성만 e.target.value로 수정
    // react에서는 state를 수정할 때 복제해서 수정한다.
    const nextForm = {
      ...form,
      [e.target.name]: e.target.value,
    };
    setForm(nextForm);
  };

  const onClick = (e) => {
    alert(userName + ":" + message);
    setForm({ userName: "", message: "" });
  };

  const onKeyPress = (e) => {
    if (e.Key === "Enter") {
      onClick();
    }
  };

  return (
    <>
      <input
        type="text"
        name="userName"
        value={userName}
        placeholder="이름을 입력하세요."
        onChange={onChange}
        onKeyPress={onKeyPress}
      />
      <input
        type="text"
        name="message"
        value={message}
        placeholder="메시지를 입력하세요."
        onChange={onChange}
        onKeyPress={onKeyPress}
      />
      <button onClick={onClick}>확인</button>
    </>
  );
};

export default EventPractice;
