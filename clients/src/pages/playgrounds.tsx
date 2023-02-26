import React from "react";
import { NextPage } from "next";
import { Layout } from "../components/Layouts/Layout";
import { TemplateCard } from "../components/Card/TemplateCard";

const templates = [
  {
    name: "React",
    href: "/playgrounds/react",
    description: "React playground with Vite",
    image: {
      url: "https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Freact.svg&w=48&q=70&output=webp",
    },
  },
  {
    name: "Vue3",
    description: "Vue3 playground with Vite",
    image: {
      url: "https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Fvue.svg&amp;w=48&amp;q=70&amp;output=webp",
    },
  },
  {
    name: "Next.js",
    description: "Next.js playground with Vite",
    image: {
      url: "https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Fnext.svg&amp;w=48&amp;q=70&amp;output=webp",
    },
  },
  {
    name: "From GitHub",
    description: "Create a playground from a GitHub repository",
    image: {
      url: "https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fsvg%2Fgithub_icon.svg&amp;w=48&amp;q=70&amp;output=webp",
    },
  },
];

const PlaygroundsPage: NextPage = () => {
  return (
    <Layout>
      <section className="px-5 pt-20 pb-8 sm:mx-auto">
        <h1 className="block text-center text-3xl font-extrabold md:text-5xl">
          Playgrounds
        </h1>
        <p className="mx-auto mt-5 w-full max-w-3xl text-center text-sm text-gray-600 md:text-xl">
          Playgrounds by codedamn are free in-browser IDE environments. Use them
          to code collaboratively with your friends, without downloading
          anything on your computer.
        </p>
        <div className="mt-12">
          <h1 className="border-b border-gray-200 text-left text-lg font-semibold uppercase">
            Create playgrounds
          </h1>
          <div className="mx-auto grid max-w-[1800] gap-4 pt-4 sm:grid-cols-2 md:grid-cols-3 xl:grid-cols-4">
            {templates.map((template, index) => (
              <TemplateCard key={index} {...template} />
            ))}
          </div>
        </div>
      </section>
    </Layout>
  );
}
const e = ""

export default PlaygroundsPage;
