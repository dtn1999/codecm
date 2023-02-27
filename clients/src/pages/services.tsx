import { NextPage } from "next";
import { useSession } from "next-auth/react";
import React from "react";

const ServicesPage: NextPage = () => {
  const { data } = useSession();
  console.log("data", data);
  return (
    <div>
      <h1>Services</h1>
    </div>
  );
};

export default ServicesPage;
