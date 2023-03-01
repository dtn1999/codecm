import React from "react";
import Link from "next/link";
import { MenuIcon } from "@we/components/Icons";
import { signIn } from "next-auth/react";

export const HomeHeader: React.FC = React.memo(() => {
  return (
    <header className="relative z-40 h-16 w-full border-b-0 bg-transparent px-2.5 text-gray-900 dark:text-gray-100 md:px-20">
      <div className="3xl:px-4 mx-auto flex h-full w-full max-w-screen-2xl items-center">
        <div className="flex-1">
          <div className="flex items-center justify-between">
            <div className="flex items-center gap-2">
              <button
                type="button"
                className="focus:ring-primary-500 -m-2 inline-flex flex-shrink-0 items-center gap-2 rounded-lg border border-none border-transparent bg-transparent p-2 text-sm font-semibold text-gray-800 text-opacity-90 hover:text-opacity-100 focus:outline-none disabled:cursor-not-allowed disabled:opacity-60 disabled:shadow-none dark:text-gray-200 dark:ring-offset-gray-900 sm:translate-y-px md:hidden"
              >
                <span className="sr-only">Open menu</span>
                <MenuIcon />
              </button>
              <div className="flex items-center gap-2">
                <Link
                  className="flex items-center gap-1 text-xl font-semibold"
                  href="/"
                >
                  <span className="hidden dark:text-white sm:block">we</span>
                </Link>
              </div>
            </div>
            <div className="hidden items-center justify-between md:flex"></div>
            <div className="opacity-100 transition-opacity duration-300 ease-linear sm:min-w-[150px]">
              <div className="items-center gap-2">
                <button
                  onClick={() => void signIn("auth0")}
                  type="button"
                  className="focus:ring-primary-500 hover:bg-primary-700 inline-flex flex-shrink-0 items-center gap-2 rounded-md border border-transparent bg-primary px-2.5 py-1.5 text-sm font-semibold text-white shadow focus:outline-none focus:ring-2 focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-60 disabled:shadow-none dark:ring-offset-gray-900"
                >
                  Login
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>
  );
});
