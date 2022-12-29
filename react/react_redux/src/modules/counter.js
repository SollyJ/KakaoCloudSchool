import { createStore } from "redux";

// 초기상태 정의
const initialState = {
  number: 0,
  text: "",
  list: [],
};

// 액션타입 정의
const INCREASE = "counter/INCREASE";
const DECREASE = "counter/DECREASE";

// 액션 생성함수 정의
export const increase = () => ({
  type: INCREASE,
});

export const decrease = () => ({
  type: DECREASE,
});

// 리듀서 만들기
const counter = (state = initialState, action) => {
  switch (action.type) {
    case INCREASE:
      return { number: state.number + 1 };
    case DECREASE:
      return { number: state.number - 1 };
    default:
      return state;
  }
};

// 스토어 만들기

// 액션 호출

export default counter;
