import React from "react";
import { SearchIcon } from "@we/components/Icons";
import Image from "next/image";
import { useSession } from "next-auth/react";
import { UserProfileButton } from "@we/features/users/components";

export const DashboardHeader: React.FC = React.memo(() => {
  const { data: sessionData } = useSession();
  return (
    <header className="relative z-40 h-24 w-full border-b bg-background px-2.5 text-gray-900 dark:border-gray-700 dark:text-gray-100 md:px-20">
      <div className="3xl:px-4 mx-auto flex h-full w-full max-w-screen-2xl items-center">
        <div className="flex w-full items-center justify-between">
          <div className="flex items-center gap-2 lg:hidden">
            <button
              type="button"
              className="focus:ring-primary-500 -m-2 inline-flex flex-shrink-0 -translate-y-px items-center gap-2 rounded-lg border border-none border-transparent bg-transparent p-2 text-sm font-semibold text-gray-600 text-opacity-90 hover:text-opacity-100 focus:outline-none disabled:cursor-not-allowed disabled:opacity-60 disabled:shadow-none dark:text-gray-400 dark:ring-offset-gray-900"
            >
              <span className="sr-only">Open sidebar</span>
              <SearchIcon />
            </button>
            <a
              className="flex items-center gap-1 text-xl font-semibold"
              data-testid="logo"
              href="/dashboard"
            >
              <span className="hidden dark:text-white sm:block"></span>
            </a>
          </div>
          <div className="hidden w-full max-w-[340px] md:block lg:max-w-xs">
            <label htmlFor="search" className="sr-only">
              Search
            </label>
            <form className="relative">
              <input
                id="search"
                name="search"
                className="focus:ring-primary-500 block w-full rounded-md border-0 border-transparent bg-placeholder py-2 pr-10 pl-3 focus:ring-2 sm:text-sm"
                placeholder="search for projects or playgrounds"
                type="search"
                required
              />
              <button
                type="submit"
                className="absolute inset-y-0 right-0 flex items-center pr-3 text-gray-400 hover:text-gray-500"
              >
                <SearchIcon />
              </button>
            </form>
          </div>
          <div className="opacity-100 transition-opacity duration-300 ease-linear sm:min-w-[150px]">
            <div className="opacity-100 transition-opacity duration-500 ease-linear">
              <div className="flex items-center justify-end sm:gap-1">
                <div className="ml-1.5">
                  <UserProfileButton />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>
  );
});
