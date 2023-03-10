import React from "react";
import cn from "classnames";
interface Props {}
const LoadingScreen: React.FC<Props> = React.memo(({}) => {
  return (
    <div
      className={cn(
        "absolute inset-0 z-[2000] flex items-center justify-center bg-background opacity-60"
      )}
    >

      <span className="inline-flex h-10 w-10 animate-ping rounded-full bg-teal-700 opacity-75"></span>
    </div>
  );
});
export default LoadingScreen;
