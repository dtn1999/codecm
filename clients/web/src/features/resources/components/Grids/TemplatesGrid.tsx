import { Spinner } from "@we/components";
import { CreatePlaygroundInput, Template } from "@we/types/schemas";
import React from "react";
import { ResourceTemplateCard } from "../Cards";
import { GitHubTemplateCard } from "../Cards";
import { CreatePlaygroundDialog } from "../Dialogs";

interface Props {
  templates: Template[];
  createPlayground: (request: CreatePlaygroundInput) => Promise<void>;
  isFetching: boolean;
}
export const TemplatesGrid: React.FC<Props> = React.memo(
  ({ templates, createPlayground, isFetching }) => {
    const [isOpen, setIsOpen] = React.useState(false);
    const closeModal = React.useCallback(() => setIsOpen(false), []);
    const openModal = React.useCallback(() => setIsOpen(true), []);
    return isFetching ? (
      <div className="flex h-full w-full items-center justify-center py-10">
        <Spinner />
      </div>
    ) : (
      <div className="grid gap-4 pt-4 sm:grid-cols-2 md:grid-cols-5 xl:grid-cols-5">
        {templates.map((template) => (
          <ResourceTemplateCard
            key={template.id}
            createPlayground={createPlayground}
            {...template}
          />
        ))}
        {!isFetching && <GitHubTemplateCard />}
        {/* {isLoading && <LoadingScreen />} */}
      </div>
    );
  }
);
