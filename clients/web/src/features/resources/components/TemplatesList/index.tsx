import { LoadingScreen } from "@we/components";
import { CreatePlaygroundInput } from "@we/types/schemas";
import { trpc } from "@we/utils/api";
import { useRouter } from "next/router";
import React from "react";
import { TemplateCard } from "../TemplateCard";

export const TemplatesList: React.FC = React.memo(() => {
  const { data } = trpc.templatesRouter.getAll.useQuery();
  const router = useRouter();
  const { mutateAsync, isLoading } =
    trpc.playgroundsRouter.create.useMutation();
  const handleCardClick = async (request: CreatePlaygroundInput) => {
    const response = await mutateAsync(request);
    console.log("creation terminated with the following response", response);
    router.push({
      pathname: "/playgrounds/code-server",
      query: {
        codeServerSrc: response.playground.instanceUrl,
      },
    });
  };
  return (
    <div className="grid gap-4 pt-4 sm:grid-cols-2 md:grid-cols-3 xl:grid-cols-4">
      {data?.templates.map((template) => (
        <TemplateCard
          key={template.id}
          onClick={handleCardClick}
          {...template}
        />
      ))}
      <div
        className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow"
        data-testid="playground-git"
      >
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
                Clone any Git Repo
              </p>
            </div>
          </div>
        </div>
      </div>
      {isLoading && <LoadingScreen />}
    </div>
  );
});
