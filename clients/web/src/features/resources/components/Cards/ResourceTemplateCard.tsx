import React from "react";
import Image from "next/image";
import cn from "classnames";
import { CreatePlaygroundInput, Template } from "@we/types/schemas";

interface TemplateCardProps extends Template {
  onClick: (requestPayload: CreatePlaygroundInput) => void;
}

export const ResourceTemplateCard: React.FC<TemplateCardProps> = React.memo(
  ({ name, description, imageUrl, onClick, githubRepoUrl }) => (
    <div
      onClick={() =>
        onClick({
          name,
          description,
          imageUrl,
          githubRepoUrl,
        })
      }
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
      <a
        href="#"
        className={cn(
          "absolute inset-0 rounded-md",
          "ring-blue-400 focus:z-10 focus:outline-none focus:ring-2"
        )}
      />
    </div>
  )
);
