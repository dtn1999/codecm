import { CreatePlaygroundInput } from "@we/types/schemas";
import { trpc } from "@we/utils/api";
import { useSession, signIn } from "next-auth/react";
import { useRouter } from "next/router";
import React from "react";

export const useTemplates = () => {
  // state data
  const { status } = useSession();

  const {
    data,
    isError,
    isLoading: fetchLoading,
  } = trpc.templates.getAll.useQuery();
  const router = useRouter();

  const { mutateAsync, isLoading: createPlaygroundLoading } =
    trpc.playgrounds.create.useMutation();

  const createPlayground = async (request: CreatePlaygroundInput) => {
    if (status === "unauthenticated") {
      const response = await signIn("auth0");
      console.log("response is the following => ", response);
      return;
    }
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
