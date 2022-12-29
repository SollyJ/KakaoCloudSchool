import logo from "./logo.svg";
import "./App.css";
import Iteration from "./Iteration";
import ErrorBoundary from "./ErrorBoundary";
import UserList from "./UserList";
import CreateUser from "./CreateUser";
import React, { useState, useRef, useMemo } from "react";

const countActiveUser = (users) => {
  console.log("사용자 수를 세기");
  return users.filter((user) => user.active).length;
};

function App() {
  // users배열에 수정이 생기면 리렌더링 될 수 있도록 state로 생성
  const [users, setUsers] = useState([
    { id: 1, username: "sl", email: "sl@naver.com", active: false },
    { id: 2, username: "hn", email: "hn@naver.com", active: true },
    { id: 3, username: "bw", email: "bw@gmail.com", active: true },
  ]);

  //변수를 생성
  const nextId = useRef(4);

  // 데이터 삽입 위한 state
  const [inputs, setInputs] = useState({
    username: "",
    email: "",
  });

  const onChange = (e) => {
    setInputs({ ...inputs, [e.target.name]: e.target.value });
  };

  const { username, email } = inputs;

  const onCreate = (e) => {
    const user = { id: nextId.current, username, email };

    setUsers([...users, user]); // 원래있던 users에 user를 합친다.
    setInputs({ username: "", email: "" }); // 입력칸은 초기화

    nextId.current += 1;
  };

  const onRemove = (id) => {
    // id가 일치하지 않는 데이터만 삭제
    // 실제로는 id가 일치하지 않는 데이터만 가지고 복제본을 만들어서 수정하는 것
    setUsers(users.filter((user) => user.id !== id));
  };

  // 수정하는 메서드
  // id에 해당하는 데이터의 active 속성 값을 반전
  const onToggle = (id) => {
    setUsers(
      users.map((user) =>
        user.id === id ? { ...user, active: !user.active } : user
      )
    );
  };

  // users에 변화가 생긴 경우만 함수를 호출
  // 그외의 경우는 결과를 복사하도록 수정
  const count = useMemo(() => countActiveUser(users), [users]);

  return (
    <div>
      <CreateUser
        username={username}
        email={email}
        onChange={onChange}
        onCreate={onCreate}
      />
      <UserList users={users} onRemove={onRemove} onToggle={onToggle} />
      <div>활성화된 유저 수: {count}</div>
    </div>
  );
}

export default App;
