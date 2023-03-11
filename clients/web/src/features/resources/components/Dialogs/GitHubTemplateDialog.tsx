import React from "react";
import { Dialog, Transition } from "@headlessui/react";
import { CloseIcon } from "@we/components/Icons";

interface Props {
  isOpen: boolean;
  closeModal: () => void;
}

export const GitHubTemplateDialog: React.FC<Props> = React.memo(
  ({ isOpen, closeModal }) => (
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
          <div className="flex min-h-full items-center justify-center p-4 text-center">
            <Transition.Child
              as={React.Fragment}
              enter="ease-out duration-300"
              enterFrom="opacity-0 scale-95"
              enterTo="opacity-100 scale-100"
              leave="ease-in duration-200"
              leaveFrom="opacity-100 scale-100"
              leaveTo="opacity-0 scale-95"
            >
              <Dialog.Panel className="h-full max-h-[50%] w-full max-w-3xl transform overflow-hidden rounded bg-background py-3 text-left align-middle transition-all">
                <Dialog.Title
                  as="h3"
                  className="flex items-center justify-between px-3 py-2 text-lg font-medium leading-6 text-white"
                >
                  New
                  <div className="flex items-center space-x-2">
                    <input
                      placeholder="search templates"
                      className="hidden bg-[#2A2A2A] p-1 placeholder:text-sm placeholder:font-light placeholder:capitalize"
                    />
                    <button onClick={closeModal}>
                      <CloseIcon />
                    </button>
                  </div>
                </Dialog.Title>
                <div className="mt-2 h-full max-h-[40%]"></div>
              </Dialog.Panel>
            </Transition.Child>
          </div>
        </div>
      </Dialog>
    </Transition>
  )
);
