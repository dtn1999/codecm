import { LayoutProps } from "../../types/ui";
import { TopNavBar } from "./Layout.Header";
import { SideBar } from "./Layout.SideBar";

export default function DiscoverLayout({ children }: LayoutProps) {
  return (
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
          <main>
          {children}
          </main>
          <div className=""></div>
        </div>
      </div>
    </div>
  );
}
