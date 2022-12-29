import React, { useCallback } from "react";
import cn from "classnames";

import {
  MdCheckBox,
  MdCheckBoxOutlineBlank,
  MdRemoveCircleOutline,
} from "react-icons/md";
import "./ToDoListItem.scss";

const ToDoListItem = ({ todo, onRemove, onToggle, style }) => {
  const { id, text, checked } = todo;

  // 삭제 함수
  const onDeleteItem = useCallback(
    (e) => {
      const result = window.confirm(text + "를 정말로 삭제하시겠습니까?");
      if (result) {
        onRemove(id);
      }
    },
    [onRemove, id, text]
  );

  return (
    <div className="ToDoListItem-virtualized" style={style}>
      <div className="ToDoListItem">
        <div
          className={cn("checkbox", { checked })}
          onClick={(e) => onToggle(id)}
        >
          {checked ? <MdCheckBox /> : <MdCheckBoxOutlineBlank />}
          <div className="text">{text}</div>
        </div>
        <div className="remove" onClick={onDeleteItem}>
          <MdRemoveCircleOutline />
        </div>
      </div>
    </div>
  );
};

export default React.memo(ToDoListItem);
