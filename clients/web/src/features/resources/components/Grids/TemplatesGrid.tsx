import { LoadingScreen } from "@we/components";
import { CreatePlaygroundInput } from "@we/types/schemas";
import { trpc } from "@we/utils/api";
import { useRouter } from "next/router";
import React from "react";
import { ResourceTemplateCard } from "../Cards";
import { GitHubTemplateCard } from "../Cards";

export const TemplatesGrid: React.FC = React.memo(() => {
  const { data } = trpc.templates.getAll.useQuery();
  const router = useRouter();
  const { mutateAsync, isLoading } = trpc.playgrounds.create.useMutation();
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
        <ResourceTemplateCard
          key={template.id}
          onClick={handleCardClick}
          {...template}
        />
      ))}
      <GitHubTemplateCard />
      {isLoading && <LoadingScreen />}
    </div>
  );
});
