import { CreatePlaygroundInput } from "@we/types/schemas";
import { trpc } from "@we/utils/api";
import { useRouter } from "next/router";
import React from "react";

export const useTemplates = () => {
  const {
    data,
    isError,
    isLoading: fetchLoading,
  } = trpc.templates.getAll.useQuery();
  console.log("all resource templates", data);
  const router = useRouter();
  const { mutateAsync, isLoading: createPlaygroundLoading } =
    trpc.playgrounds.create.useMutation();
  const createPlayground = async (request: CreatePlaygroundInput) => {
    const response = await mutateAsync(request);
    console.log("creation terminated with the following response", response);
    router.push({
      pathname: "/playgrounds/code-server",
      query: {
        codeServerSrc: response.playground.instanceUrl,
      },
    });
  };

  return {
    templates: data ? data.templates : [],
    isError,
    fetchLoading,
    createPlaygroundLoading,
    createPlayground,
  };
};
