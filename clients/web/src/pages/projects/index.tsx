import React from "react";
import { NextPageWithLayout } from "@we/types/ui";
import { Layout } from "@we/components/Layout";

const ProjectsPage: NextPageWithLayout = () => {
  return (
    <div>
      <h1>Projects</h1>
    </div>
  );
};

ProjectsPage.getLayout = (page) => <Layout>{page}</Layout>;

export default ProjectsPage;
