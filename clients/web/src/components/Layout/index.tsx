import React from "react";
import {Header} from "@we/components/Layout/Header";
import {SideBar} from "@we/components/Layout/SideBar";
export const Layout = React.memo(() => {
  return (
    <div className="">
        <Header />
        <main>
            <SideBar/>
            {}
        </main>
    </div>
  );
});
