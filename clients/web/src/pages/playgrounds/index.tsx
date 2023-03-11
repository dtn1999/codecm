import React from "react";
import { NextPageWithLayout } from "@we/types/ui";
import { Layout } from "@we/components";
import {
  PlaygroundsGrid,
  TemplatesGrid,
  GitHubTemplateDialog,
} from "@we/features/resources";

const PlaygroundPage: NextPageWithLayout = () => (
  <div className="h-full bg-background px-20">
    
    <section className="pt-4 sm:mx-auto">
      <div className="">
        <h1 className="border-b border-gray-200 text-left text-lg font-semibold uppercase">
          Create playgrounds
        </h1>
        <div className="pt-3">
          <TemplatesGrid />
        </div>
      </div>
    </section>
    <section className="pb-8 sm:mx-auto">
      <div className="mt-12">
        <h1 className="border-b border-gray-200 text-left text-lg font-semibold uppercase">
          Manage playgrounds
        </h1>
        <PlaygroundsGrid />
      </div>
    </section>
  </div>
);

PlaygroundPage.getLayout = (page) => <Layout>{page}</Layout>;

export default PlaygroundPage;
