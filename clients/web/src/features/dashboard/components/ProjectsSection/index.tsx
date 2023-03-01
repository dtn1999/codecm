import Link from "next/link";
import { DashboardProjectsSectionIcon } from "@we/components/Icons";
import React from "react";

export const ProjectsSection: React.FC = React.memo(() => {
  return (
    <div className="flex grow flex-col gap-10 lg:col-span-8">
      <div className="flex items-center justify-between">
        <span className="text-2xl font-bold">Projects</span>
        <Link
          className="text-sm text-gray-500 underline-offset-2 opacity-100 transition-opacity duration-500 hover:underline dark:text-gray-400"
          href="/playgrounds"
        >
          See all
        </Link>
      </div>
      <div className="flex w-full flex-col items-center gap-8 rounded-2xl border border-gray-100 bg-gray-50 px-8 py-6 shadow-sm dark:border-gray-700 dark:bg-gray-800 sm:p-8 lg:flex-row">
        <div className="w-40 translate-y-3">
          <DashboardProjectsSectionIcon />
        </div>
        <div className="flex flex-1 flex-col text-center lg:text-left">
          <span className="text-xl font-semibold">Build a project</span>
          <span className="mt-2 mb-5 text-gray-500 dark:text-gray-400">
            Pick a project according to your skillset and start building
            in-browser.
          </span>
          <a href="/projects">
            <button
              type="button"
              className="focus:ring-primary-500 bg-primary-600 hover:bg-primary-700 inline-flex w-full flex-shrink-0 items-center justify-center gap-2 rounded-lg border border-transparent px-3 py-2 text-sm font-semibold text-white shadow focus:outline-none focus:ring-2 focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-60 disabled:shadow-none dark:ring-offset-gray-900 lg:w-fit"
            >
              Explore projects
            </button>
          </a>
        </div>
      </div>
    </div>
  );
});
