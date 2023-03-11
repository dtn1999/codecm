import React from "react";
import cn from "classnames";
import { GitHubTemplateDialog } from "../Dialogs";
interface Props {}

export const GitHubTemplateCard: React.FC<Props> = React.memo(({}) => {
  const [isOpen, setIsOpen] = React.useState(false);
  const closeModal = React.useCallback(() => {
    setIsOpen(false);
  }, []);
  return (
    <React.Fragment>
      <GitHubTemplateDialog isOpen={isOpen} closeModal={closeModal} />
      <div className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow">
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="From GitHub"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fsvg%2Fgithub_icon.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                From GitHub
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                Clone Your Git Repo
              </p>
            </div>
          </div>
        </div>
      </div>
    </React.Fragment>
  );
});
