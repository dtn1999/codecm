import React from "react";
import Image from "next/image";
import cn from "classnames";
import { GitHubTemplateDialog } from "../Dialogs";

interface Props {}

export const GitHubTemplateCard: React.FC<Props> = React.memo(({}) => {
  const [isOpen, setIsOpen] = React.useState(false);
  const closeModal = React.useCallback(() => {
    setIsOpen(false);
  }, []);

  const openModal = React.useCallback(() => {
    setIsOpen(true);
  }, []);

  return (
    <React.Fragment>
      <GitHubTemplateDialog isOpen={isOpen} closeModal={closeModal} />
      <div
        onClick={openModal}
        className="relative rounded bg-[#2A2A2A] p-3 hover:bg-[#1A1A1A]"
      >
        <Image
          src="https://w7.pngwing.com/pngs/914/758/png-transparent-github-social-media-computer-icons-logo-android-github-logo-computer-wallpaper-banner-thumbnail.png"
          alt="gihub card"
          width={30}
          height={30}
          className="my-2 object-contain mix-blend-lighten"
        />
        <h3 className="py-2 text-sm font-medium leading-5">
          Clone from GitHub
        </h3>
        <a
          href="#"
          className={cn(
            "absolute inset-0 rounded-md",
            "ring-blue-400 focus:z-10 focus:outline-none focus:ring-2"
          )}
        />
      </div>
    </React.Fragment>
  );
});
