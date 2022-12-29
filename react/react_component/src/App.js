import logo from "./logo.svg";
import "./App.css";
import React, { Component, useCallback, useState, useRef } from "react";
import StateComponent from "./StateComponent";
import EventPractice from "./EventPractice";
import ValidationSample from "./ValidationSample";
import produce from "immer";

// class App extends Component {
//   render() {
//     return (
//       <div>
//         <MyComponent></MyComponent>
//       </div>
//     );
//   }
// }

function App() {
  // let original = { name: "jsl", nickname: ["리리", "라솔"] };

  // // 얕은 복사
  // let weakcopy = { ...original };
  // weakcopy.nickname[0] = "리리솔리"; // 얕은 복사 후 원본의 배열 데이터를 바꾸면 바뀐다.
  // console.log(original);
  // console.log(weakcopy);

  // // 깊은 복사
  // // 데이터만 깊은 복제
  // // 함수를 깊은 복제를 하려고 한다면 함수를 직접 구현하거나 외부 라이브러리의 도움을 받아야 한다.
  // let deepcopy = JSON.parse(JSON.stringify(original));
  // deepcopy.nickname[1] = "솔라시도"; // 깊은 복사를 했으므로 원본은 안바뀐다.
  // console.log(original);
  // console.log(deepcopy);

  const nextId = useRef(1); // 컴포넌트 안에서 사용할 변수 생성
  const [form, setForm] = useState({ name: "", number: "" });
  const [data, setData] = useState({
    array: [],
    uselessValue: null,
  });

  // input에 입력받는 경우 입력하는 데이터가 변경될 때 state를 수정해주는 함수
  // const onChange = useCallback(
  //   (e) => {
  //     setForm({ ...form, [e.target.name]: [e.target.value] });
  //   },
  //   [form]
  // );

  const onChange = useCallback((e) => {
    // draft가 form의 복제본이 되고
    // draft를 수정하면 immer가 알아서 form에 데이터를 전송한다.
    setForm(
      produce((draft) => {
        draft[e.target.name] = e.target.value;
      })
    );
  }, []);

  // form에 submit을 눌렀을때 데이터를 보내줌
  const onSubmit = useCallback(
    (e) => {
      e.preventDefault(); // 기본적으로 제공되는 이벤트를 수행하지 않음

      const info = {
        id: nextId.current,
        name: form.name,
        number: form.number,
      };

      setData({ ...data, array: data.array.concat(info) });
      setForm({ name: "", number: "" });

      nextId.current += 1;
    },
    [data, form.name, form.number]
  );

  // 항목을 삭제하는 함수
  const onRemove = useCallback(
    (id) => {
      setData({ ...data, array: data.array.filter((info) => info.id !== id) });
    },
    [data]
  );

  return (
    <div>
      {/* <StateComponent name="compo" year={1998}>
        태그 안의 내용
      </StateComponent> */}
      <form onSubmit={onSubmit}>
        <input
          name="name"
          placeholder="이름을 입력하세요"
          value={form.name}
          onChange={onChange}
        />
        <input
          name="number"
          placeholder="번호를 입력하세요"
          value={form.number}
          onChange={onChange}
        />
        <button type="submit">등록</button>
      </form>

      <div>
        <ul>
          {data.array.map((info) => (
            <li key={info.id} onClick={() => onRemove(info.id)}>
              {info.name}({info.number})
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default App;
