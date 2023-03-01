import React from "react";
import Image from "next/image";
import { useSession } from "next-auth/react";

export const UserProfileButton: React.FC = React.memo(() => {
  const { data: sessionData } = useSession();
  if (!sessionData || !sessionData?.user) return null;
  const { image } = sessionData?.user;
  return (
    <div className="relative translate-y-1 text-left">
      <button
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
      </button>
    </div>
  );
});
