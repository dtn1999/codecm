import { SessionProvider } from "next-auth/react";

import { trpc } from "@we/utils/api";

import "@we/styles/globals.css";
import { AppPropsWithLayoutAndSession } from "@we/types/ui";

function MyApp({
  Component,
  session,
  pageProps,
}: AppPropsWithLayoutAndSession) {
  const getLayout = Component.getLayout ?? ((page) => page);
  return getLayout(
    <SessionProvider session={session}>
      <Component {...pageProps} />
    </SessionProvider>
  );
}

// TODO: fix this type
export default trpc.withTRPC(MyApp as any);
