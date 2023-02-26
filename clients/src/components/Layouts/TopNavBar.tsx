import React from "react";

export const TopNavBar: React.FC = React.memo(() => (
  <header className="relative z-40 h-14 w-full border-b border-gray-200 bg-white px-2.5 text-gray-900 dark:border-gray-700 dark:bg-gray-900 dark:text-gray-100 md:px-4">
    <div
      id="header-navs-container"
      className="3xl:px-4 mx-auto flex h-full w-full max-w-screen-2xl items-center"
    >
      <div className="flex w-full items-center justify-between">
        <div className="flex items-center gap-2 lg:hidden">
          <button
            type="button"
            className="focus:ring-primary-500 -m-2 inline-flex flex-shrink-0 flex-shrink-0 -translate-y-px items-center gap-2 rounded-lg border border-none border-transparent bg-transparent p-2 text-sm font-semibold text-gray-600 text-opacity-90 hover:text-opacity-100 focus:outline-none disabled:cursor-not-allowed disabled:opacity-60 disabled:shadow-none dark:text-gray-400 dark:ring-offset-gray-900"
          >
            <span className="sr-only">Open sidebar</span>
            <svg
              stroke="currentColor"
              fill="currentColor"
              stroke-width="0"
              viewBox="0 0 24 24"
              className="h-4 w-4 sm:h-5 sm:w-5"
              height="1em"
              width="1em"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path fill="none" d="M0 0h24v24H0z"></path>
              <path d="M3 18h18v-2H3v2zm0-5h18v-2H3v2zm0-7v2h18V6H3z"></path>
            </svg>
          </button>
          <a
            className="flex items-center gap-1 text-xl font-semibold"
            data-testid="logo"
            href="/dashboard"
          >
            <img
              alt="codedamn logo"
              loading="lazy"
              width="30"
              height="30"
              decoding="async"
              data-nimg="1"
              src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Ffavicons%2Ffavicon-96x96.png&amp;w=60&amp;q=70&amp;output=webp"
            />
            <span className="hidden dark:text-white sm:block">codedamn</span>
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
              <svg
                stroke="currentColor"
                fill="currentColor"
                stroke-width="0"
                viewBox="0 0 16 16"
                className="h-3.5 w-3.5"
                aria-hidden="true"
                height="1em"
                width="1em"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"></path>
              </svg>
            </button>
          </form>
        </div>
        <div className="opacity-100 transition-opacity duration-300 ease-linear sm:min-w-[150px]">
          <div className="opacity-100 transition-opacity duration-500 ease-linear">
            <div className="flex items-center justify-end sm:gap-1">
              <a
                className="relative inline-flex h-8 w-8 items-center justify-center rounded hover:bg-gray-50 dark:hover:bg-gray-800"
                href="/refer"
              >
                <svg
                  stroke="currentColor"
                  fill="currentColor"
                  stroke-width="0"
                  viewBox="0 0 24 24"
                  className="h-[19px] w-[19px] text-gray-500"
                  aria-hidden="true"
                  height="1em"
                  width="1em"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path d="M16 12h2v4h-2z"></path>
                  <path d="M20 7V5c0-1.103-.897-2-2-2H5C3.346 3 2 4.346 2 6v12c0 2.201 1.794 3 3 3h15c1.103 0 2-.897 2-2V9c0-1.103-.897-2-2-2zM5 5h13v2H5a1.001 1.001 0 0 1 0-2zm15 14H5.012C4.55 18.988 4 18.805 4 18V8.815c.314.113.647.185 1 .185h15v10z"></path>
                </svg>
                <span className="absolute bottom-[7px] left-[7px] flex h-[9px] w-[9px] items-center justify-center bg-white text-[10px] font-semibold text-gray-500 group-hover/wallet:bg-gray-100 dark:bg-gray-900 dark:text-gray-400 dark:group-hover/wallet:bg-gray-800">
                  <span className="animate-text -mt-px translate-y-px bg-gradient-to-r from-sky-300 via-indigo-400 to-fuchsia-400 bg-clip-text text-transparent">
                    €
                  </span>
                </span>
              </a>
              <div className="relative" data-headlessui-state="">
                <button
                  className="relative inline-flex h-8 w-8 items-center justify-center rounded hover:bg-gray-50 dark:hover:bg-gray-800"
                  type="button"
                  aria-expanded="false"
                  data-headlessui-state=""
                  id="headlessui-popover-button-:rd:"
                >
                  <svg
                    stroke="currentColor"
                    fill="currentColor"
                    stroke-width="0.75"
                    viewBox="0 0 1024 1024"
                    className="h-5 w-5 text-gray-500"
                    aria-hidden="true"
                    height="1em"
                    width="1em"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path d="M868 160h-92v-40c0-4.4-3.6-8-8-8H256c-4.4 0-8 3.6-8 8v40h-92a44 44 0 0 0-44 44v148c0 81.7 60 149.6 138.2 162C265.7 630.2 359 721.7 476 734.5v105.2H280c-17.7 0-32 14.3-32 32V904c0 4.4 3.6 8 8 8h512c4.4 0 8-3.6 8-8v-32.3c0-17.7-14.3-32-32-32H548V734.5C665 721.7 758.3 630.2 773.8 514 852 501.6 912 433.7 912 352V204a44 44 0 0 0-44-44zM184 352V232h64v207.6a91.99 91.99 0 0 1-64-87.6zm520 128c0 49.1-19.1 95.4-53.9 130.1-34.8 34.8-81 53.9-130.1 53.9h-16c-49.1 0-95.4-19.1-130.1-53.9-34.8-34.8-53.9-81-53.9-130.1V184h384v296zm136-128c0 41-26.9 75.8-64 87.6V232h64v120z"></path>
                  </svg>
                </button>
              </div>
              <div className="relative" data-headlessui-state="">
                <button
                  className="relative inline-flex h-8 w-8 items-center justify-center rounded hover:bg-gray-50 dark:hover:bg-gray-800"
                  type="button"
                  aria-expanded="false"
                  data-headlessui-state=""
                  id="headlessui-popover-button-:rf:"
                >
                  <svg
                    stroke="currentColor"
                    fill="currentColor"
                    stroke-width="0.75"
                    viewBox="0 0 1024 1024"
                    className="h-5 w-5 text-gray-500"
                    aria-hidden="true"
                    height="1em"
                    width="1em"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path d="M816 768h-24V428c0-141.1-104.3-257.7-240-277.1V112c0-22.1-17.9-40-40-40s-40 17.9-40 40v38.9c-135.7 19.4-240 136-240 277.1v340h-24c-17.7 0-32 14.3-32 32v32c0 4.4 3.6 8 8 8h216c0 61.8 50.2 112 112 112s112-50.2 112-112h216c4.4 0 8-3.6 8-8v-32c0-17.7-14.3-32-32-32zM512 888c-26.5 0-48-21.5-48-48h96c0 26.5-21.5 48-48 48zM304 768V428c0-55.6 21.6-107.8 60.9-147.1S456.4 220 512 220c55.6 0 107.8 21.6 147.1 60.9S720 372.4 720 428v340H304z"></path>
                  </svg>
                  <span className="bg-secondary-100 absolute top-1/2 left-1/2 h-[11px] w-[8px] -translate-x-1/2 -translate-y-[54%] animate-[pulse_3s_linear_infinite] rounded-t-full"></span>
                </button>
              </div>
              <div className="ml-1.5">
                <div
                  className="relative translate-y-1 text-left"
                  data-headlessui-state=""
                >
                  <button
                    className="relative inline-flex"
                    id="headlessui-menu-button-:rh:"
                    type="button"
                    aria-haspopup="menu"
                    aria-expanded="false"
                    data-headlessui-state=""
                  >
                    <img
                      alt="Danyls Ngongang"
                      loading="lazy"
                      width="36"
                      height="36"
                      decoding="async"
                      data-nimg="1"
                      className="h-6 w-6 rounded-full sm:h-8 sm:w-8"
                      src="https://wsrv.nl/?url=https%3A%2F%2Favatars.githubusercontent.com%2Fu%2F61282251%3Fv%3D4&amp;w=72&amp;q=70&amp;output=webp"
                    />
                    <span className="absolute top-0 right-0 block h-4 w-4 translate-x-1/3 -translate-y-1/4 sm:h-5 sm:w-5">
                      <div id="level-progress-wrapper" className="relative">
                        <svg
                          className="h-full w-full rotate-[120deg] -scale-x-100"
                          fill="black"
                          stroke-width="1.25"
                          viewBox="0 0 24 24"
                          xmlns="http://www.w3.org/2000/svg"
                          stroke-strokeLinecap="round"
                        >
                          <path
                            fill="none"
                            className="stroke-gray-300 dark:stroke-gray-600"
                            d="M21 16V8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73l7 4a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16z"
                          ></path>
                          <path
                            d="M21 16V8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73l7 4a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16z"
                            className="transition: stroke-dashoffset 1s 0s; stroke: rgb(56, 189, 248); stroke-dasharray: 60.8074; stroke-dashoffset: 0; ease-in-out"
                          ></path>
                        </svg>
                        <div className="absolute inset-0 flex h-full w-full flex-col items-center justify-center">
                          <span className="text-[8px] font-bold text-white">
                            1
                          </span>
                        </div>
                      </div>
                    </span>
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
