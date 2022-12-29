import React from "react";
import { useEffect } from "react";

function User({ user, onRemove, onToggle }) {
  useEffect(() => {
    // 삽입과 수정시 호출
    console.log("컴포넌트가 화면에 나타남");

    // 함수를 리턴하면 컴포넌트가 사라질 때 호출
    return () => {
      console.log("컴포넌트가 사라짐");
      console.log(user);
    };
  }, [user]); // user가 바뀔때만 불러짐

  return (
    <div>
      <b
        onClick={(e) => onToggle(user.id)}
        style={{ cursor: "pointer", color: user.active ? "green" : "black" }}
      >
        {user.username}
      </b>
      <span>{user.email}</span>
      <button onClick={(e) => onRemove(user.id)}>삭제</button>
    </div>
  );
}

function UserList({ users, onRemove, onToggle }) {
  return (
    <>
      {users.map((user) => (
        <User
          user={user}
          key={user.id}
          onRemove={onRemove}
          onToggle={onToggle}
        />
      ))}
    </>
  );
}

export default UserList;
