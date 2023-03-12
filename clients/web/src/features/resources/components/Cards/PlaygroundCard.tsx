import React from "react";
import Image from "next/image";
import { IoOpenOutline } from "react-icons/io5";
import { Playground } from "@we/types/schemas";
import DeleteIcon from "@we/components/Icons/DeleteIcon";
import EditIcon from "@we/components/Icons/EditIcon";
import moment from "moment";

interface TemplateCardProps extends Playground {
  handleDelete: (playgroundId: number) => void;
  handleEdit: (playgroundId: number) => void;
}

export const PlaygroundCard: React.FC<TemplateCardProps> = React.memo(
  ({
    name,
    description,
    handleDelete,
    id,
    imageUrl,
    instanceUrl,
    createdDate,
  }) => (
    <React.Fragment>
      <div className="relative rounded bg-[#2A2A2A] p-3 hover:bg-[#1A1A1A]">
        <Image
          src={imageUrl}
          alt={name}
          width={50}
          height={50}
          className="my-2 object-contain mix-blend-lighten"
        />
        <h3 className="py-2 text-sm font-medium leading-5">{name}</h3>
        <span className="hidden flex-shrink-0 rounded-full bg-gray-100 px-2 py-0.5 text-xs font-medium text-gray-800 md:inline-block">
          {moment(new Date(createdDate)).fromNow()}
        </span>
        <p className="py-2 font-thin">{description}</p>
        <div className="-mt-px flex flex-shrink-0 divide-x divide-gray-200 text-white">
          <div className="flex w-0 flex-1 hover:text-teal-500">
            <button className="relative -mr-px inline-flex w-0 flex-1 items-center justify-center rounded-bl-lg border border-[transparent] py-2 text-sm font-medium hover:cursor-pointer">
              <IoOpenOutline className="text-lg font-bold" />
              <span className="ml-3">Open</span>
            </button>
          </div>
          <div className="flex w-0 flex-1 hover:text-indigo-500">
            <button className="relative -mr-px inline-flex w-0 flex-1 items-center justify-center rounded-bl-lg border border-[transparent] py-2 text-sm font-medium hover:cursor-pointer">
              <EditIcon />
              <span className="ml-3">Edit Title</span>
            </button>
          </div>
          <div className="-ml-px flex w-0 flex-1 hover:text-red-500">
            <button
              onClick={() => handleDelete(id)}
              className="relative inline-flex w-0 flex-1 items-center justify-center rounded-br-lg border border-[transparent] py-2 text-sm font-medium hover:cursor-pointer"
            >
              <DeleteIcon />
              <span className="ml-3">Delete</span>
            </button>
          </div>
        </div>
      </div>
    </React.Fragment>
  )
);
