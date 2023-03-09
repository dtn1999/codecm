import React from "react";
import { NextPage } from "next";

const SinglePlaygroundPage: NextPage = () => {
  return (
    <div className="absolute inset-0 bg-sky-200">
      <iframe
        src="http://localhost:9437"
        className="absolute inset-0 h-full w-full"
      />
    </div>
  );
};

export default SinglePlaygroundPage;
