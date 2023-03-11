import React from "react";
import { NextPageWithLayout } from "@we/types/ui";
import { Layout } from "@we/components";
import {
  PlaygroundsGrid,
  TemplatesGrid,
  usePlaygrounds,
  useTemplates,
} from "@we/features/resources";
import { useSession } from "next-auth/react";
import { CreatePlaygroundDialog } from "@we/features/resources";

const PlaygroundPage: NextPageWithLayout = () => {
  const {
    templates,
    createPlayground,
    fetchLoading: isFetching,
  } = useTemplates();
  const { playgrounds, deletePlayground, editTemplate } = usePlaygrounds();
  const { data: sessionData } = useSession();
  return (
    <div className="h-full bg-background px-20">
      <CreatePlaygroundDialog
        isOpen
        closeModal={() => {}}
        title="Create Playground"
      />
      <section className="pt-4 sm:mx-auto">
        <div className="">
          <h1 className="border-b border-gray-200 text-left text-lg font-semibold uppercase">
            Create playgrounds
          </h1>
          <div className="pt-3">
            <TemplatesGrid
              templates={templates}
              createPlayground={createPlayground}
              isFetching={isFetching}
            />
          </div>
        </div>
      </section>
      {sessionData && sessionData.user && (
        <section className="pb-8 sm:mx-auto">
          <div className="mt-12">
            <h1 className="border-b border-gray-200 text-left text-lg font-semibold uppercase">
              Manage playgrounds
            </h1>
            <PlaygroundsGrid
              playgrounds={playgrounds}
              handleDelete={deletePlayground}
              handleEdit={editTemplate}
              isFetching={isFetching}
            />
          </div>
        </section>
      )}
    </div>
  );
};

PlaygroundPage.getLayout = (page) => <Layout>{page}</Layout>;

export default PlaygroundPage;
