import React from "react";
import styles from "./CSSModule.module.scss";
import classNames from "classnames/bind";

const cx = classNames.bind(styles); // cx안에서는 styles 생략가능

const CSSModule = () => {
  return (
    <div className={cx("inverted", "wrapper")}>
      처음 사용해보는
      <span className="something"> CSS module</span>
    </div>
  );
};

export default CSSModule;
