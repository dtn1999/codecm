import React from "react";
import Image from "next/image";

interface TemplateCardProps {
  title: string;
  description: string;
  image: string;
  onClick: () => void;
}

export const TemplateCard: React.FC<TemplateCardProps> = React.memo(() => (
  <div className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow">
    <div className="flex items-center gap-4 truncate">
      <div className="flex-shrink-0">
        <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
          <span aria-hidden="true">
            <img
              alt="C++"
              loading="lazy"
              width="24"
              height="24"
              decoding="async"
              data-nimg="1"
              src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Fcpp.svg&amp;w=48&amp;q=70&amp;output=webp"
            />
          </span>
        </div>
      </div>
      <div>
        <div>
          <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
            C++
          </p>
          <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
            C++ playground
          </p>
        </div>
      </div>
    </div>
  </div>
));
