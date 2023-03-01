import React from "react";
import { TemplateCard } from "../TemplateCard";

export const TemplatesList: React.FC = React.memo(() => {
  return (
    <div className="grid gap-4 pt-4 sm:grid-cols-2 md:grid-cols-3 xl:grid-cols-4">
      <div className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow">
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="HTML/CSS"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Fhtml5.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                HTML/CSS
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                Vanilla HTML/CSS/JS playground
              </p>
            </div>
          </div>
        </div>
      </div>
      <div className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow">
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="React"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Freact.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                React
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                React playground using Vite
              </p>
            </div>
          </div>
        </div>
      </div>
      <div
        className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow"
        data-testid="playground-vue3-vite"
      >
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="Vue 3"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Fvue.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                Vue 3
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                Vue 3 playground using Vite
              </p>
            </div>
          </div>
        </div>
      </div>
      <div className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow">
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="Solidity"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Fsolidity.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                Solidity
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                Hardhat based solidity playground
              </p>
            </div>
          </div>
        </div>
      </div>
      <div
        className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow"
        data-testid="playground-python"
      >
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="Python"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Fpython.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                Python
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                Python 3 playground
              </p>
            </div>
          </div>
        </div>
      </div>
      <div
        className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow"
        data-testid="playground-java"
      >
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="Java"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Fjava.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                Java
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                Java playground
              </p>
            </div>
          </div>
        </div>
      </div>
      <div
        className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow"
        data-testid="playground-golang"
      >
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="Golang"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Fgolang.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                Golang
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                Golang playground
              </p>
            </div>
          </div>
        </div>
      </div>
      <div
        className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow"
        data-testid="playground-node"
      >
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="Node.js"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Fnode.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                Node.js
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                Node.js 14 playground
              </p>
            </div>
          </div>
        </div>
      </div>
      <TemplateCard />
      <div
        className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow"
        data-testid="playground-c-lang"
      >
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="C"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg-fixed%2Fc.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                C
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                C playground
              </p>
            </div>
          </div>
        </div>
      </div>
      <div
        className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow"
        data-testid="playground-next11"
      >
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="Next.js"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Fnext.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                Next.js
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                Next.js 12 playground
              </p>
            </div>
          </div>
        </div>
      </div>
      <div
        className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow"
        data-testid="playground-bun"
      >
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="Bun"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Fbun.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                Bun
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                Bun playground
              </p>
            </div>
          </div>
        </div>
      </div>
      <div
        className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow"
        data-testid="playground-php"
      >
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="PHP"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fcolored-tech-icons%2Fphp.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                PHP
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                PHP playground
              </p>
            </div>
          </div>
        </div>
      </div>
      <div
        className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow"
        data-testid="playground-rust"
      >
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="Rust"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Frust.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                Rust
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                Rust playground
              </p>
            </div>
          </div>
        </div>
      </div>
      <div
        className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow"
        data-testid="playground-kotlin"
      >
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="Kotlin"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Fkotlin.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                Kotlin
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                Kotlin playground
              </p>
            </div>
          </div>
        </div>
      </div>
      <div
        className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow"
        data-testid="playground-swift"
      >
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="Swift"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fimages%2Fsvg%2Fswift.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                Swift
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                Swift playground
              </p>
            </div>
          </div>
        </div>
      </div>
      <div
        className="group flex cursor-pointer flex-col justify-between rounded-lg border bg-white p-3 transition duration-150 ease-in-out hover:border-indigo-500 hover:shadow"
        data-testid="playground-git"
      >
        <div className="flex items-center gap-4 truncate">
          <div className="flex-shrink-0">
            <div className="inline-flex h-10 w-10 items-center justify-center rounded-md bg-gray-100 text-white group-hover:bg-gray-200/70">
              <span aria-hidden="true">
                <img
                  alt="From GitHub"
                  loading="lazy"
                  width="24"
                  height="24"
                  decoding="async"
                  data-nimg="1"
                  src="https://wsrv.nl/?url=https%3A%2F%2Fcodedamn.com%2Fassets%2Fsvg%2Fgithub_icon.svg&amp;w=48&amp;q=70&amp;output=webp"
                />
              </span>
            </div>
          </div>
          <div>
            <div>
              <p className="max-w-[200px] truncate text-base font-medium text-gray-900">
                From GitHub
              </p>
              <p className="mt-1 max-w-[200px] cursor-default truncate text-xs text-gray-500">
                Clone any Git Repo
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
});
