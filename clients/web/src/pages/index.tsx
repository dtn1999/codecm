import { type NextPage } from "next";
import Link from "next/link";
import Head from "next/head";
import { signIn, signOut, useSession } from "next-auth/react";

import { api } from "@we/utils/api";
import { MenuIcon } from "@we/components/Icons";

const Home: NextPage = () => {
  const hello = api.example.hello.useQuery({ text: "from tRPC" });

  return (
    <>
      <Head>
        <title>We</title>
        <meta name="description" content="e-learning website for programmer" />
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <div className="h-full bg-[#0F1115]">
        <header className="relative z-40 h-14 w-full border-b-0 bg-transparent px-2.5 text-gray-900 dark:text-gray-100 md:px-4">
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
                      <span className="hidden dark:text-white sm:block">
                        we
                      </span>
                    </Link>
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

        <div className="flex h-full flex-col items-center justify-center overflow-hidden py-20 px-20">
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
                <Link
                  className="block w-full rounded-md bg-gradient-to-r from-indigo-500 to-cyan-600 px-4 py-3 text-center font-medium text-white shadow hover:bg-gradient-to-l md:text-lg"
                  href="/playgrounds"
                >
                  Explore All Playgrounds
                </Link>
              </div>
            </div>
          </div>
          <div className="w-full">
            <img
              src="https://codesandbox.io/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fimage.2b1cff1a.gif&w=1920&q=75"
              className="h-full w-full object-contain"
            />
          </div>
        </div>
      </div>
    </>
  );
};

export default Home;

const AuthShowcase: React.FC = () => {
  const { data: sessionData } = useSession();

  const { data: secretMessage } = api.example.getSecretMessage.useQuery(
    undefined, // no input
    { enabled: sessionData?.user !== undefined }
  );

  return (
    <div className="flex flex-col items-center justify-center gap-4">
      <p className="text-center text-2xl text-white">
        {sessionData && <span>Logged in as {sessionData.user?.name}</span>}
        {secretMessage && <span> - {secretMessage}</span>}
      </p>
      <button
        className="rounded-full bg-white/10 px-10 py-3 font-semibold text-white no-underline transition hover:bg-white/20"
        onClick={sessionData ? () => void signOut() : () => void signIn()}
      >
        {sessionData ? "Sign out" : "Sign in"}
      </button>
    </div>
  );
};
