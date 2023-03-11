import React from "react";
import { Dialog, Transition } from "@headlessui/react";
import Image from "next/image";
import cn from "classnames";
import { CloseIcon } from "@we/components/Icons";
import { trpc } from "@we/utils/api";
import { Spinner } from "@we/components";

interface Props {
  isOpen: boolean;
  closeModal: () => void;
}

export const GitHubTemplateDialog: React.FC<Props> = React.memo(
  ({ isOpen, closeModal }) => {
    const { data, isLoading, error } = trpc.templates.gitHubTemplates.useQuery({
      username: "dtn1999",
    });

    console.log(data, error);
    return (
      <Transition appear show={isOpen} as={React.Fragment}>
        <Dialog as="div" className="relative z-10" onClose={closeModal}>
          <Transition.Child
            as={React.Fragment}
            enter="ease-out duration-300"
            enterFrom="opacity-0"
            enterTo="opacity-100"
            leave="ease-in duration-200"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
          >
            <div className="fixed inset-0 bg-black opacity-10" />
          </Transition.Child>
          <div className="fixed inset-0 overflow-y-auto">
            <div className="flex h-full items-center justify-center p-4 text-center">
              <Transition.Child
                as={React.Fragment}
                enter="ease-out duration-300"
                enterFrom="opacity-0 scale-95"
                enterTo="opacity-100 scale-100"
                leave="ease-in duration-200"
                leaveFrom="opacity-100 scale-100"
                leaveTo="opacity-0 scale-95"
              >
                <Dialog.Panel className="h-full max-h-[60%] w-full max-w-3xl transform overflow-hidden rounded bg-background py-3 text-left align-middle transition-all">
                  <Dialog.Title
                    as="h3"
                    className="flex items-center justify-end px-3 py-2 text-lg font-medium leading-6 text-white"
                  >
                    <div className="flex w-[40%] items-center space-x-2">
                      <input
                        placeholder="search templates"
                        className="w-full bg-[#2A2A2A] p-1 placeholder:text-sm placeholder:font-light placeholder:capitalize"
                      />
                      <button onClick={closeModal}>
                        <CloseIcon />
                      </button>
                    </div>
                  </Dialog.Title>
                  <div className="mt-2 h-full">
                    {isLoading ? (
                      <div className="flex h-full w-full items-center justify-center">
                        <Spinner />
                      </div>
                    ) : (
                      <ul className="grid grid-cols-3 gap-3">
                        {data?.templates.map(({ id, name, imageUrl }) => (
                          <li
                            key={id}
                            className="relative bg-[#2A2A2A] p-3 hover:bg-[#1A1A1A]"
                          >
                            <Image
                              src={imageUrl}
                              alt={name}
                              width={30}
                              height={30}
                              className="my-2 object-contain"
                            />
                            <h3 className="text-sm font-medium leading-5">
                              {name}
                            </h3>
                            <a
                              href="#"
                              className={cn(
                                "absolute inset-0 rounded-md",
                                "ring-blue-400 focus:z-10 focus:outline-none focus:ring-2"
                              )}
                            />
                          </li>
                        ))}
                      </ul>
                    )}
                  </div>
                </Dialog.Panel>
              </Transition.Child>
            </div>
          </div>
        </Dialog>
      </Transition>
    );
  }
);
