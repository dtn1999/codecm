import { trpc } from "@we/utils/api";
import React from "react";
import { PlaygroundCard } from "../PlaygroundCard";

export const PlaygroundsList: React.FC = React.memo(() => {
  const { data, error } = trpc.playgrounds.getAll.useQuery();
  const { mutateAsync } = trpc.playgrounds.delete.useMutation();
  const handleDeleteClick = async (playgroundId: number) => {
    const { data } = await mutateAsync({ playgroundId });
    console.log(data);
  };
  console.log(data, error);
  return !data || data.playgrounds.length === 0 ? (
    <div className="mx-auto w-full max-w-screen-2xl py-5">
      <div>
        <ul className="space-y-4">
          <h6 className="text-center text-sm italic text-gray-500">
            No playgrounds to show
          </h6>
        </ul>
      </div>
    </div>
  ) : (
    <div className="mt-4 grid grid-cols-1 gap-4">
      {data?.playgrounds.map((playground) => (
        <PlaygroundCard
          key={playground.id}
          handleDelete={handleDeleteClick}
          handleEdit={() => {}}
          {...playground}
        />
      ))}
    </div>
  );
});
