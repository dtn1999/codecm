import { BootPlaygroundIcon } from "../../../components/Icons/BootPlaygroundIcon";
import { ExploreLearningPath } from "../../../components/Icons/ExploreLearningPath";
import { TodoTasksIcon } from "../../../components/Icons/TodoTasksIcon";

export default function Page() {
  return (
    <div className="mx-auto w-full max-w-screen-2xl flex-grow pb-11 sm:pb-0">
      <div className="grid gap-6 p-4 py-6 lg:grid-cols-12">
        <div className="flex grow flex-col gap-10 lg:col-span-8">
          <div className="flex items-center justify-between">
            <span className="text-2xl font-bold">Learning</span>
            <a
              className="text-sm text-gray-500 underline-offset-2 opacity-100 transition-opacity duration-500 hover:underline dark:text-gray-400"
              href="/playgrounds"
            >
              See all
            </a>
          </div>
          <div className="flex w-full flex-col items-center gap-8 rounded-2xl border border-gray-100 bg-gray-50 px-8 py-6 shadow-sm dark:border-gray-700 dark:bg-gray-800 sm:p-8 lg:flex-row">
            <div className="w-40">
              <ExploreLearningPath />
            </div>
            <div className="flex flex-1 flex-col text-center lg:text-left">
              <span className="text-xl font-semibold">
                You haven't enrolled in a course yet
              </span>
              <span className="mt-2 mb-5 text-gray-500 dark:text-gray-400">
                Accelerate your learning by starting a curated learning path
                that fits your interests.
              </span>
              <a href="/learning-paths">
                <button
                  type="button"
                  className="focus:ring-primary-500 bg-primary-600 hover:bg-primary-700 inline-flex w-full flex-shrink-0 items-center justify-center gap-2 rounded-lg border border-transparent px-3 py-2 text-sm font-semibold text-white shadow focus:outline-none focus:ring-2 focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-60 disabled:shadow-none dark:ring-offset-gray-900 lg:w-fit"
                >
                  Explore learning paths
                </button>
              </a>
            </div>
          </div>
          <div className="flex items-center justify-between">
            <span className="text-2xl font-bold">Playground</span>
            <a
              className="text-sm text-gray-500 underline-offset-2 opacity-100 transition-opacity duration-500 hover:underline dark:text-gray-400"
              href="/playgrounds"
            >
              See all
            </a>
          </div>
          <div className="flex w-full flex-col items-center gap-8 rounded-2xl border border-gray-100 bg-gray-50 px-8 py-6 shadow-sm dark:border-gray-700 dark:bg-gray-800 sm:p-8 lg:flex-row">
            <div className="w-40 translate-y-3">
              <BootPlaygroundIcon />
            </div>
            <div className="flex flex-1 flex-col text-center lg:text-left">
              <span className="text-xl font-semibold">
                Boot a playground IDE
              </span>
              <span className="mt-2 mb-5 text-gray-500 dark:text-gray-400">
                Pick a playground to build a project or code collaboratively
                with your friends.
              </span>
              <a href="/playgrounds">
                <button
                  type="button"
                  className="focus:ring-primary-500 bg-primary-600 hover:bg-primary-700 inline-flex w-full flex-shrink-0 items-center justify-center gap-2 rounded-lg border border-transparent px-3 py-2 text-sm font-semibold text-white shadow focus:outline-none focus:ring-2 focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-60 disabled:shadow-none dark:ring-offset-gray-900 lg:w-fit"
                >
                  Explore playgrounds
                </button>
              </a>
            </div>
          </div>

          <div className="flex flex-col gap-5">
            <div className="flex items-center justify-between">
              <span className="text-2xl font-bold">Projects</span>
              <a
                className="text-sm text-gray-500 underline-offset-2 opacity-0 transition-opacity duration-500 hover:underline dark:text-gray-400"
                href="/projects"
              >
                See all
              </a>
            </div>
            <ul className="grid gap-5 md:grid-cols-2">
              <li className="flex cursor-pointer flex-col gap-3 rounded-lg border border-gray-100 bg-gray-50 p-4 dark:border-gray-700 dark:bg-gray-800">
                <a
                  className="relative overflow-hidden"
                  href="/project/portfolio-website"
                >
                  <img
                    alt="Personal Portfolio Website"
                    loading="lazy"
                    width="512"
                    height="512"
                    decoding="async"
                    data-nimg="1"
                    className="aspect-[16/9] w-full rounded border border-gray-100 bg-gray-200 object-cover dark:border-gray-800"
                    src="https://wsrv.nl/?url=https%3A%2F%2Fraw.githubusercontent.com%2Fravi0900%2Fportfolio-website-codedamn%2Fmaster%2Fassets%2Fdesign%2Fpreview2.png&amp;w=1024&amp;q=70&amp;output=webp"
                  />
                  <div className="absolute inset-x-2 bottom-1.5 flex items-center gap-3">
                    <span className="bg-primary-100 text-primary-800 dark:bg-primary-800 dark:text-primary-100 border-secondary-200 !bg-secondary-100 !text-secondary-800 inline-flex w-fit items-center gap-1.5 rounded border px-3 py-0.5 text-sm font-semibold capitalize">
                      medium
                    </span>
                  </div>
                </a>
                <div className="flex flex-1 flex-col">
                  <span className="text-lg font-semibold">
                    Personal Portfolio Website
                  </span>
                  <span className="mt-1 mb-2 text-sm capitalize text-gray-500 dark:text-gray-400">
                    <span>HTML/CSS&nbsp;&nbsp;•&nbsp;&nbsp;</span>
                    <span>JavaScript&nbsp;&nbsp;•&nbsp;&nbsp;</span>14 days ago
                  </span>
                </div>
              </li>
            </ul>
          </div>
        </div>
        <div className="flex grow flex-col gap-10 lg:col-span-4">
          <div className="flex w-full flex-col items-center rounded-2xl border border-gray-100 bg-gray-50 p-6 pr-0 shadow-sm dark:border-gray-700 dark:bg-gray-800">
            <div className="flex w-full items-center justify-between pb-3 pr-5">
              <span className="text-2xl font-bold">Todo List</span>
            </div>
            <div className="flex flex-col items-center gap-5 pt-3 pr-6">
              <div className="-mb-4 h-32 w-32">
                <TodoTasksIcon />
              </div>
              <span className="text-center text-sm text-gray-500 dark:text-gray-400">
                Add an activity to your list to get started
              </span>
              <button
                type="button"
                className="focus:ring-primary-500 bg-primary-600 hover:bg-primary-700 inline-flex flex-shrink-0 items-center gap-2 rounded-lg border border-transparent px-3 py-2 text-sm font-semibold text-white shadow focus:outline-none focus:ring-2 focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-60 disabled:shadow-none dark:ring-offset-gray-900"
              >
                Add a new activity
              </button>
            </div>
          </div>

          <div className="dark relative hidden w-full rounded-2xl bg-[#11102D] p-6 text-white shadow-sm dark:bg-black">
            <div className="flex flex-col gap-3">
              <div className="mt-3 h-5 w-20 rounded bg-white/10"></div>
              <div className="flex flex-col gap-0.5">
                <div className="mt-3 h-2.5 w-full rounded-md bg-white/10"></div>
                <div className="mt-3 h-2.5 w-full rounded-md bg-white/10"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
