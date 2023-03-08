import React from "react";
import Image from "next/image";
import { Playground } from "@we/types/schemas";
import DeleteIcon from "@we/components/Icons/DeleteIcon";
import EditIcon from "@we/components/Icons/EditIcon";

interface TemplateCardProps extends Playground {
  onClick: () => void;
}

export const PlaygroundCard: React.FC<TemplateCardProps> = React.memo(
  ({ name, description, onClick }) => (
    <div className="flex w-full flex-shrink-0 flex-col items-center justify-between gap-4 rounded-md bg-white px-6 py-6 md:flex-row md:gap-0 md:space-x-6 md:py-2">
      <a
        href="/playground/prPz4MxmlmsfqHYm5tH39"
        className="flex-grow truncate"
      >
        <div className="flex flex-col items-center space-y-4 md:items-start">
          <div className="flex items-center gap-2 md:gap-3">
            <div className="mt-1.5 flex-shrink-0">
              <Image
                alt="Image icon for My playground"
                loading="lazy"
                width="32"
                height="32"
                decoding="async"
                data-nimg="1"
                src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Frust.svg&amp;w=64&amp;q=70&amp;output=webp"
              />
            </div>
            <h3 className="flex-shrink-0 grow truncate font-medium text-gray-900">
              {name}
            </h3>
            <span className="hidden flex-shrink-0 rounded-full bg-gray-100 px-2 py-0.5 text-xs font-medium text-gray-800 md:inline-block">
              a few seconds ago
            </span>
          </div>
        </div>
      </a>
      <div className="-mt-px flex w-64 flex-shrink-0 divide-x divide-gray-200 text-gray-700">
        <div className="flex w-0 flex-1 hover:text-indigo-500">
          <a className="relative -mr-px inline-flex w-0 flex-1 items-center justify-center rounded-bl-lg border border-[transparent] py-2 text-sm font-medium">
            <EditIcon />
            <span className="ml-3">Edit Title</span>
          </a>
        </div>
        <div className="-ml-px flex w-0 flex-1 hover:text-red-500">
          <a className="relative inline-flex w-0 flex-1 items-center justify-center rounded-br-lg border border-[transparent] py-2 text-sm font-medium">
            <DeleteIcon />
            <span className="ml-3">Delete</span>
          </a>
        </div>
      </div>
    </div>
  )
);
