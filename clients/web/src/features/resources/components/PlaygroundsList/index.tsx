import { trpc } from "@we/utils/api";
import React from "react";
import { PlaygroundCard } from "../PlaygroundCard";

export const PlaygroundsList: React.FC = React.memo(() => {
  const { data, error } = trpc.playgroundsRouter.getAll.useQuery();
  const handleCardClick = () => {};
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
    <div className="grid grid-cols-1 gap-4 mt-4">
      {data?.playgrounds.map((playground) => (
        <PlaygroundCard
          key={playground.id}
          onClick={handleCardClick}
          {...playground}
        />
      ))}
    </div>
  );
});
