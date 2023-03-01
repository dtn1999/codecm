import React from "react";
import { SearchIcon } from "@we/components/Icons";
import Image from "next/image";

export const Header = React.memo(() => (
  <header className="relative z-40 h-14 w-full border-b border-gray-200 bg-white px-2.5 text-gray-900 dark:border-gray-700 dark:bg-gray-900 dark:text-gray-100 md:px-4">
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
        <div className="hidden w-full max-w-[240px] md:block lg:max-w-xs">
          <label htmlFor="search" className="sr-only">
            Search
          </label>
          <form className="relative">
            <input
              id="search"
              name="search"
              className="focus:ring-primary-500 block w-full rounded-md border-0 border-transparent bg-gray-100 py-1.5 pr-10 pl-3 focus:ring-2 dark:bg-gray-800 sm:text-sm"
              placeholder="Search for courses"
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
                <div className="relative translate-y-1 text-left">
                  <button
                    className="relative inline-flex"
                    id="headlessui-menu-button-:rh:"
                    type="button"
                    aria-haspopup="menu"
                    aria-expanded="false"
                    data-headlessui-state=""
                  >
                    <Image
                      alt="Danyls Ngongang"
                      loading="lazy"
                      width="36"
                      height="36"
                      decoding="async"
                      data-nimg="1"
                      className="h-6 w-6 rounded-full sm:h-8 sm:w-8"
                      src="https://wsrv.nl/?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F61282251%3Fv%3D4&amp;w=72&amp;q=70&amp;output=webp"
                    />
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>
));
