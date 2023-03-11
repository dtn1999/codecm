import React from "react";
import { Dialog, Transition } from "@headlessui/react";
import { CloseIcon } from "@we/components/Icons";
import { trpc } from "@we/utils/api";
import { DialogWrapper } from "@we/components";

interface Props {
  isOpen: boolean;
  closeModal: () => void;
}

export const GitHubTemplateDialog: React.FC<Props> = React.memo(
  ({ isOpen, closeModal }) => {
    const { data, isLoading, error } = trpc.templates.gitHubTemplates.useQuery({
      username: "dtn1999",
    });

    return (
      <DialogWrapper
        isOpen={isOpen}
        closeModal={closeModal}
        title="GitHub Templates"
      >
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
              <div className="fixed inset-0 bg-black opacity-50" />
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
                  </Dialog.Panel>
                </Transition.Child>
              </div>
            </div>
          </Dialog>
        </Transition>
      </DialogWrapper>
    );
  }
);
