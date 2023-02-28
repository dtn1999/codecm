import { type AppType } from "next/app";
import { SessionProvider } from "next-auth/react";
import { trpc } from "../utils/api";

import "../styles/globals.css";
import "react-reflex/styles.css";

const MyApp: AppType<any> = ({ Component, pageProps }) => {
  return (
    <SessionProvider session={pageProps.session}>
      <Component {...pageProps} />{" "}
    </SessionProvider>
  );
};

export default trpc.withTRPC(MyApp);
