import { Layout } from "@we/components/Layout";
import { NextPageWithLayout } from "@we/types/ui";
import React from "react";

const PlaygroundPage: NextPageWithLayout = () => {
  return (
    <div>
      <h1>Playground</h1>
    </div>
  );
};

PlaygroundPage.getLayout = (page) => <Layout>{page}</Layout>;

export default PlaygroundPage;
