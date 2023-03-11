import React from "react";
import Link from "next/link";
import { FcDonate } from "react-icons/fc";
import { BiHelpCircle } from "react-icons/bi";
import { MdOutlineFeedback } from "react-icons/md";
import { NAV_SECTIONS as SIDEBAR_SECTIONS } from "./config";
import meta from "@we/meta/site.json";
import { SearchIcon } from "@we/components/Icons";

export const SideBar = React.memo(() => (
  <aside className="sticky top-0 flex h-screen w-64 flex-1 flex-col overflow-hidden border-r border-gray-200 bg-background text-gray-900 dark:border-gray-700 dark:text-gray-100 lg:w-16 xl:w-64">
    <a
      className="inline-flex h-14 items-center gap-1 px-3 text-2xl font-bold lg:justify-center xl:justify-start"
      href="/"
    >
      <div className="inline-flex items-center gap-2 lg:hidden xl:inline-flex">
        <span className="text-xl font-semibold">{meta.name}</span>
      </div>
    </a>
    <form className="relative mb-1 px-3 md:hidden">
      <input
        id="search"
        name="search"
        className="block w-full rounded-md border border-gray-300 bg-white py-1 pr-6 pl-3 text-xs leading-5 placeholder-gray-500 focus:border-indigo-500 focus:placeholder-gray-400 focus:outline-none focus:ring-1 focus:ring-indigo-500 dark:border-gray-700 dark:bg-gray-800 md:py-1.5 md:text-sm"
        placeholder="Search for courses"
        type="search"
      />
      <button
        type="submit"
        className="absolute inset-y-0 right-3 flex items-center px-3 dark:bg-gray-500/10"
      >
        <SearchIcon />
      </button>
    </form>
    <nav
      className="relative z-20 flex max-h-max flex-1 flex-col overflow-y-auto overflow-x-hidden py-2 px-3 lg:px-2 xl:px-3"
      aria-label="Sidebar"
    >
      {SIDEBAR_SECTIONS.map((section, index) => (
        <div key={index}>
          <h3 className="mb-1.5 w-full pt-3 text-xs font-semibold capitalize tracking-wide text-gray-600 dark:text-gray-200 md:pt-0 md:text-sm lg:text-center lg:text-[11px] xl:text-start xl:text-sm">
            {section.title}
          </h3>
          <div className="space-y-1 pb-5 lg:px-1 xl:px-0">
            {section.children.map((child, index) => (
              <Link
                key={index}
                className="group flex items-center rounded-md px-3 py-2 text-xs font-medium text-gray-600 hover:bg-gray-200/40 hover:text-gray-800 dark:text-gray-400 dark:hover:bg-gray-700/30 dark:hover:text-gray-200 md:text-sm lg:justify-center lg:p-2 xl:justify-start xl:px-3 xl:py-2"
                target="_self"
                href={child.href}
              >
                {child.icon}
                <span className="group flex items-center gap-1.5 truncate lg:hidden xl:flex">
                  {child.name}
                </span>
              </Link>
            ))}
          </div>
        </div>
      ))}
    </nav>
    <div className="flex w-full flex-col items-start p-2">
      <button className="flex w-full items-center space-x-2 px-2 py-2 capitalize">
        <FcDonate className="text-lg" />
        <span>donate</span>
      </button>
      <button className="flex w-full items-center space-x-2 px-2 py-2 capitalize">
        <BiHelpCircle className="text-lg" />
        <span>help</span>
      </button>
      <button className="flex w-full items-center space-x-2 px-2 py-2 capitalize">
        <MdOutlineFeedback className="text-lg" />
        <span>Feedback</span>
      </button>
    </div>
  </aside>
));
