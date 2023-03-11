import React from "react";
import { useSession } from "next-auth/react";
import { Playground } from "@we/types/schemas";
import { trpc } from "@we/utils/api";

export const usePlaygrounds = () => {
  const [playgrounds, setPlaygrounds] = React.useState<Playground[]>([]);
  const [fetchPlaygroundEnabled, setFetchPlaygroundEnabled] =
    React.useState(false);
  const { data: sessionData } = useSession();
  const deletePlayground = React.useCallback(async (id: number) => {}, []);
  const editTemplate = React.useCallback(async (id: number) => {}, []);
  const { data, isLoading: fetchingPlaygrounds } =
    trpc.playgrounds.getAll.useQuery(undefined, {
      enabled: fetchPlaygroundEnabled,
      placeholderData: [],
    });

  React.useEffect(() => {
    if (!sessionData || !sessionData.user) {
      setPlaygrounds([]);
    } else {
    }
  }, [sessionData]);

  React.useEffect(() => {
    if (data && data.playgrounds) {
      setPlaygrounds(data.playgrounds);
    }
  }, [data]);

  return {
    fetchingPlaygrounds,
    playgrounds,
    deletePlayground,
    editTemplate,
  };
};
