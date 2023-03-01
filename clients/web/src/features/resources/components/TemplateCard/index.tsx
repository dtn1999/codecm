import React from "react";
import Image from "next/image";

interface TemplateCardProps {
  name: string;
  description: string;
  imageUrl: string;
  onClick: () => void;
}

export const TemplateCard: React.FC<TemplateCardProps> = React.memo(
  ({ name, description, imageUrl }) => (
    <div className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow">
      <div className="flex items-center gap-4 truncate">
        <div className="flex-shrink-0">
          <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
            <span aria-hidden="true">
              <Image
                alt={name}
                loading="lazy"
                width={24}
                height={24}
                decoding="async"
                src={imageUrl}
              />
            </span>
          </div>
        </div>
        <div>
          <div>
            <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
              {name}
            </p>
            <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
              {description}
            </p>
          </div>
        </div>
      </div>
    </div>
  )
);
