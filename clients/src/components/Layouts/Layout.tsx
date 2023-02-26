import React from "react";
import { SideBar } from "./SideBar";
import { TopNavBar } from "./TopNavBar";
interface Props {
  children: React.ReactNode;
}
export const Layout: React.FC<Props> = ({ children }) => (
  <div className="relative bg-white text-black dark:bg-gray-900 dark:text-white">
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
        <TopNavBar />
        {children}
        <div className=""></div>
      </div>
    </div>
  </div>
);
