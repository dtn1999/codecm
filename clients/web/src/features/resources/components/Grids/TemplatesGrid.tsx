import { Spinner } from "@we/components";
import { CreatePlaygroundInput, Template } from "@we/types/schemas";
import { trpc } from "@we/utils/api";
import { useRouter } from "next/router";
import React from "react";
import { ResourceTemplateCard } from "../Cards";
import { GitHubTemplateCard } from "../Cards";

interface Props {
  templates: Template[];
  createPlayground: (request: CreatePlaygroundInput) => Promise<void>;
  isFetching: boolean;
}
export const TemplatesGrid: React.FC<Props> = React.memo(
  ({ templates, createPlayground, isFetching }) => {
    return isFetching ? (
      <div className="py-10 flex h-full w-full items-center justify-center">
        <Spinner />
      </div>
    ) : (
      <div className="grid gap-4 pt-4 sm:grid-cols-2 md:grid-cols-5 xl:grid-cols-5">
        {templates.map((template) => (
          <ResourceTemplateCard
            key={template.id}
            onClick={createPlayground}
            {...template}
          />
        ))}
        {!isFetching && <GitHubTemplateCard />}
        {/* {isLoading && <LoadingScreen />} */}
      </div>
    );
  }
);
