import React from "react";
import s from "./LoadingDots.module.css";

export const LoadingDots: React.FC = React.memo(() => {
  return (
    <span className={s.root}>
      <span />
      <span />
      <span />
    </span>
  );
});
