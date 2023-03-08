import React from "react";
import { DashboardHeader } from "@we/components/Layout/DashboardHeader";
import { SideBar } from "@we/components/Layout/SideBar";
import { ContainerComponentProps } from "@we/types/ui";

export const Layout: React.FC<ContainerComponentProps> = React.memo(
  ({ children }) => {
    return (
      <div className="relative bg-[#0E0E0E] text-black dark:bg-gray-900 dark:text-white">
        <div className="relative flex flex-1">
          <div
            id="layout-desktop-sidebar-container"
            className="z-40 hidden lg:block"
          >
            <SideBar />
          </div>
          <div
            id="layout-content-container"
            className="flex w-full flex-1 flex-col"
          >
            <DashboardHeader />
            {children}
            <div className=""></div>
          </div>
        </div>
      </div>
    );
  }
);
