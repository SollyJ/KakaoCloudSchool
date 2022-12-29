import "./App.css";
import ToDoTemplate from "./components/ToDoTemplate/ToDoTemplate";
import ToDoInsert from "./components/ToDoInsert/ToDoInsert";
import ToDoList from "./components/ToDoList/ToDoList";
import { useState, useRef, useCallback } from "react";

const createBulkTodos = () => {
  const array = [];
  for (let i = 1; i <= 2000; i++) {
    array.push({
      id: i,
      text: `할 일 ${i}`,
      checked: false,
    });
  }
  return array;
};

function App() {
  // useState에 데이터를 생성하는 함수를 대입하면 데이터가 만들어 질 때 마다 re-rendering을 한다.
  // 함수이름만을 대입해야 함수를 전부 수행하고 한번만 re-rendering함
  const [todos, setTodos] = useState(createBulkTodos);

  const nextId = useRef(2001);

  // 삽입 함수
  // todos에 변화가 생길때만 콜백함수 호출
  const onInsert = useCallback((text) => {
    const todo = {
      id: nextId.current,
      text,
      checked: false,
    };
    setTodos((todos) => todos.concat(todo));
    nextId.current += 1;
  }, []);

  // 삭제 함수
  const onRemove = useCallback((id) => {
    setTodos((todos) => todos.filter((todo) => todo.id !== id));
  }, []);

  // 수정 함수
  // id값과 매개변수로 받은 id가 일치하면 checked를 반전
  const onToggle = useCallback((id) => {
    setTodos((todos) =>
      todos.map((todo) =>
        todo.id === id ? { ...todo, checked: !todo.checked } : todo
      )
    );
  }, []);

  return (
    <>
      <ToDoTemplate>
        <ToDoInsert onInsert={onInsert} />
        <ToDoList todos={todos} onRemove={onRemove} onToggle={onToggle} />
      </ToDoTemplate>
    </>
  );
}

export default App;
