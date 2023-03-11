import React from "react";
import { type NextPage } from "next";
import Link from "next/link";
import Head from "next/head";
import { Header } from "@we/components";
import { useSession } from "next-auth/react";
import { useRouter } from "next/router";

const Home: NextPage = () => {
  const { data } = useSession();
  const router = useRouter();
  React.useEffect(() => {
    if (data) {
      router.push("/dashboard");
    }
  }, []);
  return (
    <>
      <Head>
        <title>We</title>
        <meta name="description" content="e-learning website for programmer" />
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <div className="h-full bg-background">
        <Header />
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
        </div>
      </div>
    </>
  );
};

export default Home;
