import React, { useCallback } from "react";
import ToDoListItem from "../ToDoListItem/ToDoListItem";
import "./ToDoList.scss";
import { List } from "react-virtualized";

const ToDoList = ({ todos, onRemove, onToggle }) => {
  // 하나의 항목을 렌더링하기 위한 함수를 생성
  const rowRenderer = useCallback(
    ({ index, key, style }) => {
      // 출력할 데이터 가져오기
      const todo = todos[index];

      return (
        <ToDoListItem
          todo={todo}
          key={todo.id}
          onRemove={onRemove}
          onToggle={onToggle}
          style={style}
        />
      );
    },
    [todos, onRemove, onToggle]
  );

  return (
    <List
      className="ToDoList"
      width={512}
      height={513}
      rowCount={todos.length}
      rowHeight={57}
      rowRenderer={rowRenderer}
      list={todos}
      style={{ outline: "none" }}
    />
  );
};

export default ToDoList;
