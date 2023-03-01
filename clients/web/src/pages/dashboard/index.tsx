import React from "react";
import { Layout } from "@we/components";
import {
  BootPlaygroundIcon,
  ExploreLearningPathsIcon,
  TodoTasksIcon,
} from "@we/components/Icons";
import { NextPageWithLayout } from "@we/types/ui";
import {
  PlaygroundsSection,
  ProjectsSection,
  TodoTasksManager,
} from "@we/features/dashboard/components";

const DashboardPage: NextPageWithLayout = () => {
  return (
    <div className="mx-auto w-full max-w-screen-2xl flex-grow px-4 py-10 pb-11 sm:pb-0">
      <div className="grid grid-cols-3 gap-16">
        <div className="col-span-2 flex w-full flex-col space-y-10">
          <PlaygroundsSection />
          <ProjectsSection />
        </div>
        <div>
          <TodoTasksManager />
        </div>
      </div>
    </div>
  );
};

DashboardPage.getLayout = (page) => <Layout>{page}</Layout>;

export default DashboardPage;
