import React from "react";
import { Dialog, Transition } from "@headlessui/react";
import { CloseIcon } from "@we/components/Icons";
import { trpc } from "@we/utils/api";
import { DialogWrapper } from "@we/components";
import { ResourceTemplateCard } from "../Cards";

interface Props {
  isOpen: boolean;
  closeModal: () => void;
}

export const GitHubTemplateDialog: React.FC<Props> = React.memo(
  ({ isOpen, closeModal }) => {
    const { data, isLoading, error } = trpc.templates.gitHubTemplates.useQuery(
      {
        username: "dtn1999",
      },
      {
        enabled: isOpen,
        retry: false,
      }
    );
    console.log("github templates are the following => ", data);
    return (
      <DialogWrapper
        isOpen={isOpen}
        closeModal={closeModal}
        title="GitHub Templates"
      >
        <div className="flex w-full justify-end px-4">
          <div className="flex w-[40%] items-center space-x-2 text-white">
            <input
              placeholder="search templates"
              className="w-full bg-[#2A2A2A] p-1 px-2 placeholder:text-sm placeholder:font-light placeholder:capitalize"
            />
          </div>
        </div>
        <div className="grid w-full grid-cols-3 gap-10">
          {data?.templates.map((template) => (
            <ResourceTemplateCard
              //onClick={() => console.log("clicked")}
              key={template.id}
              {...template}
            />
          ))}
        </div>
      </DialogWrapper>
    );
  }
);
