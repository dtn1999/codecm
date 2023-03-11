import React from "react";
import { Dialog, Transition } from "@headlessui/react";
import { CloseIcon } from "@we/components/Icons";
import { DialogProps } from "@we/types/ui";

export const DialogWrapper: React.FC<DialogProps> = React.memo(
  ({ isOpen, closeModal, children, title }) => (
    <Transition appear show={isOpen} as={React.Fragment}>
      <Dialog as="div" className="relative z-[3000]" onClose={closeModal}>
        <Transition.Child
          as={React.Fragment}
          enter="ease-out duration-300"
          enterFrom="opacity-0"
          enterTo="opacity-100"
          leave="ease-in duration-200"
          leaveFrom="opacity-100"
          leaveTo="opacity-0"
        >
          <div className="fixed inset-0 bg-black opacity-60" />
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
              <Dialog.Panel className="max-h-[60%] w-full max-w-3xl transform overflow-hidden rounded bg-background py-3 text-left align-middle transition-all">
                <Dialog.Title
                  as="h3"
                  className="flex items-center justify-between px-3 py-2 text-lg font-medium leading-6 text-white"
                >
                  <span>{title}</span>
                  <button onClick={closeModal}>
                    <CloseIcon />
                  </button>
                </Dialog.Title>
                {children}
              </Dialog.Panel>
            </Transition.Child>
          </div>
        </div>
      </Dialog>
    </Transition>
  )
);
