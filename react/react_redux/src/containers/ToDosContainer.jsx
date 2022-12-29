import React from "react";
import { connect } from "react-redux";
import todos, { changeInput, insert, toggle, remove } from "../modules/todos";
import Todos from "../components/Todos";

const ToDosContainer = ({
  input,
  todos,
  changeInput,
  insert,
  toggle,
  remove,
}) => {
  return (
    <Todos
      input={input}
      todos={todos}
      changeInput={changeInput}
      insert={insert}
      toggle={toggle}
      remove={remove}
    />
  );
};

export default connect(
  ({ todos }) => ({
    input: todos.input,
    todos: todos.todos,
  }),
  { changeInput, insert, toggle, remove }
)(ToDosContainer);
