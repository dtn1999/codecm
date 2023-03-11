import React from "react";
import { DashboardHeader } from "@we/components/Layout/DashboardHeader";
import { SideBar } from "@we/components/Layout/SideBar";
import { ContainerComponentProps } from "@we/types/ui";
import { Seo } from "@we/features/seo";

export const Layout: React.FC<ContainerComponentProps> = React.memo(
  ({ children }) => {
    return (
      <React.Fragment>
        <Seo
          seo={{
            title: "lescodeursnelagerpas",
            description: "Discover how to code interactively",
            image: {
              filename: "https://lescodeursnelagerpas.com/images/og-image.png",
              alt: "lescodeursnelagerpas",
              url: "https://lescodeursnelagerpas.com",
            },
            socials: [],
          }}
        />
        <div className="relative bg-background text-black dark:bg-gray-900 dark:text-white">
          <div className="relative flex flex-1">
            <div className="z-40 hidden lg:block">
              <SideBar />
            </div>
            <div className="flex w-full flex-1 flex-col">
              <DashboardHeader />
              {children}
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
);
