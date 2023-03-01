import { Layout } from "@we/components";
import { NextPageWithLayout } from "@we/types/ui";
import React from "react";

const PlaygroundPage: NextPageWithLayout = () => {
  return (
    <div>
      <section className="px-5 pt-4 pb-8 sm:mx-auto">
        <div className="">
          <h1 className="border-b border-gray-200 text-left text-lg font-semibold uppercase">
            Create playgrounds
          </h1>
          <div className="mx-auto grid max-w-[1800] gap-4 pt-4 sm:grid-cols-2 md:grid-cols-3 xl:grid-cols-4"></div>
        </div>
      </section>
      <section className="px-5 pt-20 pb-8 sm:mx-auto">
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
