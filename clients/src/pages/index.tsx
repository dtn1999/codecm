/* eslint-disable @typescript-eslint/no-misused-promises */
import React from "react";
import { type NextPage } from "next";
import { signIn, useSession } from "next-auth/react";
import { useRouter } from "next/router";

const Home: NextPage = () => {
  const { data } = useSession();
  const router = useRouter();
  React.useEffect(() => {
    console.log("data in Home", data);
    if (data) {
      router.push("/dashboard");
    }
  }, []);
  return (
    <div className="h-full bg-[#1E1E1E]">
      <header className="relative z-40 h-14 w-full border-b-0 bg-transparent px-2.5 text-gray-900 dark:text-gray-100 md:px-4">
        <div
          id="header-navs-container"
          className="3xl:px-4 mx-auto flex h-full w-full max-w-screen-2xl items-center"
        >
          <div className="flex-1">
            <div className="flex items-center justify-between">
              <div className="flex items-center gap-2">
                <button
                  type="button"
                  className="focus:ring-primary-500 -m-2 inline-flex flex-shrink-0 flex-shrink-0 items-center gap-2 rounded-lg border border-none border-transparent bg-transparent p-2 text-sm font-semibold text-gray-800 text-opacity-90 hover:text-opacity-100 focus:outline-none disabled:cursor-not-allowed disabled:opacity-60 disabled:shadow-none dark:text-gray-200 dark:ring-offset-gray-900 sm:translate-y-px md:hidden"
                >
                  <span className="sr-only">Open menu</span>
                  <svg
                    stroke="currentColor"
                    fill="currentColor"
                    stroke-width="0"
                    viewBox="0 0 24 24"
                    className="h-5 w-5 -scale-x-100"
                    height="1em"
                    width="1em"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path fill="none" d="M0 0h24v24H0V0z"></path>
                    <path d="M3 18h13v-2H3v2zm0-5h10v-2H3v2zm0-7v2h13V6H3zm18 9.59L17.42 12 21 8.41 19.59 7l-5 5 5 5L21 15.59z"></path>
                  </svg>
                </button>
                <div className="flex items-center gap-2">
                  <a
                    className="flex items-center gap-1 text-xl font-semibold"
                    data-testid="logo"
                    href="/"
                  >
                    <span className="hidden dark:text-white sm:block">we</span>
                  </a>
                </div>
              </div>
              <div className="hidden items-center justify-between md:flex"></div>
              <div className="opacity-100 transition-opacity duration-300 ease-linear sm:min-w-[150px]">
                <div className="items-center gap-2">
                  <button
                    onClick={() => signIn("auth0")}
                    type="button"
                    className="focus:ring-primary-500 hover:bg-primary-700 inline-flex flex-shrink-0 items-center gap-2 rounded-md border border-transparent bg-sky-700 px-2.5 py-1.5 text-sm font-semibold text-white shadow focus:outline-none focus:ring-2 focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-60 disabled:shadow-none dark:ring-offset-gray-900"
                  >
                    Get Started
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </header>

      <div className="flex h-full flex-row items-center justify-center overflow-hidden py-20">
        <div className="grid max-w-7xl grid-cols-2 gap-10">
          <div className="max-w-3xl flex-1 sm:text-center lg:flex lg:items-center lg:text-left">
            <div className="lg:py-24">
              <h1 className="mt-4 text-4xl font-extrabold tracking-tight text-white sm:mt-5 sm:text-6xl lg:mt-6 xl:text-6xl">
                <span className="block">Discover how to code</span>
                <span className="block bg-gradient-to-r from-indigo-200 to-cyan-400 bg-clip-text pb-3 text-transparent">
                  Interactively
                </span>
              </h1>
              <p className="mt-3 text-base text-gray-300 sm:mt-5 sm:text-xl lg:text-lg xl:text-xl">
                Acquire practical skills by building projects and practicing
                your programming knowledge from the comfort of your web browser
                without the need to switch to any external software or IDE.
              </p>
              <div className="mt-10 sm:mt-12">
                <a
                  className="block w-full rounded-md bg-gradient-to-r from-indigo-500 to-cyan-600 px-4 py-3 text-center font-medium text-white shadow hover:bg-gradient-to-l md:text-lg"
                  href="/learning-paths"
                >
                  Explore All Roadmaps
                </a>
              </div>
            </div>
          </div>
          <div className="w-full">
            <img
              src="https://code.visualstudio.com/assets/docs/remote/vscode-server/server-connected.png"
              className="h-full w-full object-contain"
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
