import { Template } from "@we/services/types";
import React from "react";

interface TemplateCardProps extends Template {}
export const TemplateCard: React.FC<TemplateCardProps> = React.memo(
  ({ name, description, imageUrl, githubRepoUrl }) => {
    const handleClick = async () => {
      const requestData = {
        type: "create",
        resourceType: "playgrounds",
        path: "",
        payload: {
          name,
          githubRepoUrl,
          description,
        },
      };
      try {
        console.log(
          "creating a playground for following template: ",
          JSON.stringify(requestData)
        );
        const {
          data: { data },
        } = await (
          await fetch("/api/resources", {
            method: "POST",
            body: JSON.stringify(requestData),
          })
        ).json();
        console.log("responded with ", data);
        const userResponse = confirm(
          "Playground created successfully. Open it now?"
        );
        if (userResponse) {
          console.log("opening ", data.instanceUrl);
          window.open(data.instanceUrl);
        }
      } catch (e) {
        console.log(e);
      }
    };

    return (
      <div
        onClick={handleClick}
        className="group max-w-[300px] cursor-pointer flex-col rounded-sm border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow"
      >
        <div className="flex flex-col items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="React"
                  src={imageUrl}
                  className="w-full"
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
    );
  }
);
