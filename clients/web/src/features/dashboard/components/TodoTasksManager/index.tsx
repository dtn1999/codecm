import { TodoTasksIcon } from "@we/components/Icons";
import React from "react";

export const TodoTasksManager: React.FC = React.memo(() => {
  return (
    <div className="flex grow flex-col gap-10 lg:col-span-4">
      <div className="flex w-full flex-col items-center rounded-2xl border border-gray-100 bg-gray-50 p-6 pr-0 shadow-sm dark:border-gray-700 dark:bg-gray-800">
        <div className="flex w-full items-center justify-between pb-3 pr-5">
          <span className="text-2xl font-bold">Todo List</span>
        </div>
        <div className="flex flex-col items-center gap-5 pt-3 pr-6">
          <div className="-mb-4 h-32 w-32">
            <TodoTasksIcon />
          </div>
          <span className="text-center text-sm text-gray-500 dark:text-gray-400">
            Add an activity to your list to get started
          </span>
          <button
            type="button"
            className="focus:ring-primary-500 bg-primary-600 hover:bg-primary-700 inline-flex flex-shrink-0 items-center gap-2 rounded-lg border border-transparent bg-gradient-to-r from-indigo-500 to-cyan-600 px-3 py-2 text-sm font-semibold text-white shadow focus:outline-none focus:ring-2 focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-60 disabled:shadow-none dark:ring-offset-gray-900"
          >
            Add a new activity
          </button>
        </div>
      </div>

      <div className="dark relative hidden w-full rounded-2xl bg-[#11102D] p-6 text-white shadow-sm dark:bg-black">
        <div className="flex flex-col gap-3">
          <div className="mt-3 h-5 w-20 rounded bg-white/10"></div>
          <div className="flex flex-col gap-0.5">
            <div className="mt-3 h-2.5 w-full rounded-md bg-white/10"></div>
            <div className="mt-3 h-2.5 w-full rounded-md bg-white/10"></div>
          </div>
        </div>
      </div>
    </div>
  );
});
