import React from "react";
import { createWorkspace } from "../../server/lib/resources";

interface TemplateCardProps {
  name: string;
  description: string;
  image: {
    url: string;
  };
}
export const TemplateCard: React.FC<TemplateCardProps> = React.memo(
  ({ name, description, image }) => (
    <div
      onClick={async () => {
        try {
          const requestData = {
            name: name,
            githubRepoUrl: "https://github.com/dtn1999/pomodoro.git",
            description: description,
          };

          const data = await (
            await fetch("/api/playground", {
              method: "POST",
              body: JSON.stringify(requestData),
            })
          ).json();
          console.log("responded with ", data);
          window.open(data.instanceUrl, "_blank");
        } catch (e) {
          console.log(e);
        }
      }}
      className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow"
    >
      <div className="flex items-center gap-4 truncate">
        <div className="flex-shrink-0">
          <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
            <span aria-hidden="true">
              <img
                alt="React"
                src={image.url}
                width="24"
                height="24"
                decoding="async"
                data-nimg="1"
                loading="lazy"
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
