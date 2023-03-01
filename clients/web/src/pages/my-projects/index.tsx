import React from "react";
import { NextPageWithLayout } from "@we/types/ui";
import { Layout } from "@we/components";

const MyProjectsPage: NextPageWithLayout = () => {
  return (
    <div>
      <h1>My Projects</h1>
    </div>
  );
};

MyProjectsPage.getLayout = (page) => <Layout>{page}</Layout>;

export default MyProjectsPage;
