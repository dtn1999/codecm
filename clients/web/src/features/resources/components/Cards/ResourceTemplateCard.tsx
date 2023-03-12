import React from "react";
import Image from "next/image";
import cn from "classnames";
import { CreatePlaygroundInput, Template } from "@we/types/schemas";
import { CreatePlaygroundDialog } from "../Dialogs";

interface TemplateCardProps extends Template {
  createPlayground: (request: CreatePlaygroundInput) => Promise<void>;
}

export const ResourceTemplateCard: React.FC<TemplateCardProps> = React.memo(
  ({ name, imageUrl, createPlayground, description, githubRepoUrl }) => {
    const [isOpen, setIsOpen] = React.useState(false);
    const closeModal = React.useCallback(() => setIsOpen(false), []);
    const openModal = React.useCallback(() => setIsOpen(true), []);
    return (
      <React.Fragment>
        <div
          onClick={openModal}
          className="relative rounded bg-[#2A2A2A] p-3 hover:bg-[#1A1A1A]"
        >
          <Image
            src={imageUrl}
            alt={name}
            width={50}
            height={50}
            className="my-2 object-contain mix-blend-lighten"
          />
          <h3 className="py-2 text-sm font-medium leading-5">{name}</h3>
        </div>
        <CreatePlaygroundDialog
          isOpen={isOpen}
          closeModal={closeModal}
          title="Create Playground"
          template={{ name, imageUrl, githubRepoUrl, description }}
          createPlayground={createPlayground}
        />
      </React.Fragment>
    );
  }
);
