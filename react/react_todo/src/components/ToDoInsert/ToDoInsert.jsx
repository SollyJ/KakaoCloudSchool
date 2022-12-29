import React, { useState, useCallback } from "react";
import { MdAddCircle } from "react-icons/md";
import "./ToDoInsert.scss";

const ToDoInsert = ({ onInsert }) => {
  const [value, setValue] = useState("");

  // 입력내용이 변경될 때 호출될 함수
  const onChange = useCallback((e) => {
    setValue(e.target.value);
  }, []);

  // form에서 submit 이벤트가 발생하면 호출될 함수
  // form안에서 submit버튼 누를때 뿐만 아니라 엔터를 쳐도 처리
  const onSubmit = useCallback(
    (e) => {
      const result = window.confirm(`${value}을 추가하시겠습니까?`);
      if (result === false) {
        e.preventDefault();
        return;
      }
      onInsert(value); // 데이터 삽입
      setValue(""); // input초기화
      // form의 submit이나 a태그는 화면 전체를 갱신하기 때문에 이전 내용을 모두 읽어버리기 때문에 디폴트 이벤트를 방지한다.
      e.preventDefault();
    },
    [onInsert, value]
  );

  return (
    <form className="ToDoInsert" onSubmit={onSubmit}>
      <input placeholder="+ add a new task" value={value} onChange={onChange} />
      <button type="submit">
        <MdAddCircle />
      </button>
    </form>
  );
};

export default ToDoInsert;
