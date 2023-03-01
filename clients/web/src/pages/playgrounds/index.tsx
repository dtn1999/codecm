import { Layout } from "@we/components";
import { TemplatesList } from "@we/features/resources/components";
import { NextPageWithLayout } from "@we/types/ui";
import React from "react";

const PlaygroundPage: NextPageWithLayout = () => {
  return (
    <div className="px-20">
      <section className="pt-4 sm:mx-auto">
        <div className="">
          <h1 className="border-b border-gray-200 text-left text-lg font-semibold uppercase">
            Create playgrounds
          </h1>
          <div className="pt-3">
            <TemplatesList />
          </div>
        </div>
      </section>
      <section className="pb-8 sm:mx-auto">
        <div className="mt-12">
          <h1 className="border-b border-gray-200 text-left text-lg font-semibold uppercase">
            Manage playgrounds
          </h1>
          <div className="mx-auto grid max-w-[1800] gap-4 pt-4 sm:grid-cols-2 md:grid-cols-3 xl:grid-cols-4"></div>
        </div>
      </section>
    </div>
  );
};

PlaygroundPage.getLayout = (page) => <Layout>{page}</Layout>;

export default PlaygroundPage;
