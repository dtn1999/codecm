import { Spinner } from "@we/components";
import { Playground } from "@we/types/schemas";
import React from "react";
import { PlaygroundCard } from "../Cards/PlaygroundCard";

interface Props {
  playgrounds: Playground[];
  handleDelete: (playgroundId: number) => void;
  handleEdit: () => void;
  isFetching: boolean;
}
export const PlaygroundsGrid: React.FC<Props> = React.memo(
  ({ playgrounds, handleDelete, handleEdit, isFetching }) => {
    return isFetching ? (
      <div className="flex h-full w-full items-center justify-center py-10">
        <Spinner />
      </div>
    ) : (
      <React.Fragment>
        {/* no playgrounds */}
        {playgrounds.length === 0 && (
          <div className="mx-auto w-full max-w-screen-2xl py-5">
            <div>
              <ul className="space-y-4">
                <h6 className="text-center text-sm italic text-gray-500">
                  No playgrounds to show
                </h6>
              </ul>
            </div>
          </div>
        )}
        {/* playgrounds grid */}
        {playgrounds.length > 0 && (
          <div className="mt-4 grid grid-cols-1 gap-4">
            {playgrounds.map((playground) => (
              <PlaygroundCard
                key={playground.id}
                handleDelete={handleDelete}
                handleEdit={handleEdit}
                {...playground}
              />
            ))}
          </div>
        )}
      </React.Fragment>
    );
  }
);
