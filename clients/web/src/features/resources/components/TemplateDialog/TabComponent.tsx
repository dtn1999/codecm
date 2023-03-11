import { useState } from "react";
import { Tab } from "@headlessui/react";
import { trpc } from "@we/utils/api";
import Image from "next/image";

//@ts-ignore
function classNames(...classes) {
  return classes.filter(Boolean).join(" ");
}

export default function TabComponent() {
  const { data, error } = trpc.templates.getAll.useQuery();
  let [categories] = useState({
    "Quick start": data ? data.templates : [],
    "Import from GiHub": [],
  });

  return (
    <div className="grid w-full grid-cols-4 gap-4 px-2 sm:px-0">
      <Tab.Group>
        <Tab.List className="flex flex-col space-x-1 p-1">
          {Object.keys(categories).map((category) => (
            <Tab
              key={category}
              className={({ selected }) =>
                classNames(
                  "flex w-full px-3 py-2.5 text-sm font-medium leading-5 text-white/80",
                  "ring-white ring-opacity-60 focus:outline-none",
                  selected
                    ? ""
                    : "text-gray-600 hover:bg-white/[0.12] hover:text-white"
                )
              }
            >
              {category}
            </Tab>
          ))}
        </Tab.List>
        <Tab.Panels className="col-span-3 mt-2">
          {Object.values(categories).map((posts, idx) => (
            <Tab.Panel
              key={idx}
              className={classNames(
                "bg-transparent px-3",
                "ring-opacity-60 ring-offset-2 focus:outline-none focus:ring-2"
              )}
            >
              <ul className="grid grid-cols-3 gap-3 text-white">
                {posts.map((post) => (
                  <li
                    key={post.id}
                    className="relative bg-[#2A2A2A] p-3 hover:bg-[#1A1A1A]"
                  >
                    <Image
                      src={post.imageUrl}
                      alt={post.name}
                      width={30}
                      height={30}
                      className="my-2 object-contain"
                    />
                    <h3 className="text-sm font-medium leading-5">
                      {post.name}
                    </h3>
                    <a
                      href="#"
                      className={classNames(
                        "absolute inset-0 rounded-md",
                        "ring-blue-400 focus:z-10 focus:outline-none focus:ring-2"
                      )}
                    />
                  </li>
                ))}
              </ul>
            </Tab.Panel>
          ))}
        </Tab.Panels>
      </Tab.Group>
    </div>
  );
}
