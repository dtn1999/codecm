import React from "react";
import Image from "next/image";
import { Menu, Transition } from "@headlessui/react";
import { signOut, useSession } from "next-auth/react";

export const UserProfileButton: React.FC = React.memo(() => {
  const { data: sessionData } = useSession();
  if (!sessionData || !sessionData?.user) {
    return <React.Fragment></React.Fragment>;
  }
  const { image } = sessionData?.user;
  return (
    <div className="relative translate-y-1 text-left">
      <Menu as="div" className="relative inline-block text-left">
        <Menu.Button
          className="relative inline-flex"
          id="headlessui-menu-button-:rh:"
          type="button"
          aria-haspopup="menu"
          aria-expanded="false"
          data-headlessui-state=""
        >
          {image ? (
            <Image
              alt="Danyls Ngongang"
              loading="lazy"
              width="36"
              height="36"
              decoding="async"
              data-nimg="1"
              className="h-6 w-6 rounded-full sm:h-8 sm:w-8"
              src={image || ""}
            />
          ) : (
            <span className="flex h-6 w-6 items-center justify-center rounded-full bg-gray-500 text-sm font-medium text-white sm:h-8 sm:w-8">
              {sessionData?.user?.name?.charAt(0)}
            </span>
          )}
        </Menu.Button>
        <Transition
          as={React.Fragment}
          enter="transition ease-out duration-100"
          enterFrom="transform opacity-0 scale-95"
          enterTo="transform opacity-100 scale-100"
          leave="transition ease-in duration-75"
          leaveFrom="transform opacity-100 scale-100"
          leaveTo="transform opacity-0 scale-95"
        >
          <Menu.Items className="absolute right-0 mt-2 w-28 origin-top-right divide-y divide-gray-100 rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
            <div className="px-1 py-1">
              <Menu.Item>
                {({ active }) => (
                  <button
                    type="button"
                    onClick={() => void signOut()}
                    className="flex w-full justify-start px-4 py-2 text-sm text-red-600 hover:bg-gray-100 hover:text-gray-900"
                  >
                    Logout
                  </button>
                )}
              </Menu.Item>
            </div>
          </Menu.Items>
        </Transition>
      </Menu>
    </div>
  );
});
