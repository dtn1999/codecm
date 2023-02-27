import { withPageAuthRequired } from "@auth0/nextjs-auth0";
import { useUser } from "@auth0/nextjs-auth0/client";

import { NextPage } from "next";
import React from "react";

const ServicesPage: NextPage = (props) => {
  console.log("props", props);
  const { user, error, isLoading } = useUser();
  console.log("data", user, error, isLoading);
  const fetAccessToken = async () => {
    const response = await fetch("/api/test");
    const data = await response.json();
    console.log("data", data);
  };
  React.useEffect(() => {
    fetAccessToken();
  }, []);
  return (
    <div>
      <h1>Services</h1>
    </div>
  );
};
export const getServerSideProps = withPageAuthRequired();
export default ServicesPage;
