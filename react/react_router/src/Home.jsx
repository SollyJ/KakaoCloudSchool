import React from "react";
import { Link } from "react-router-dom";

const Home = () => {
  return (
    <div>
      <h1>메인 페이지</h1>
      <Link to="/about">소개</Link>
    </div>
  );
};

export default Home;
