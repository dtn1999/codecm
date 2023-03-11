/* eslint-disable @next/next/no-sync-scripts */
import { Html, Head, Main, NextScript } from "next/document";

export default function Document() {
  return (
    <Html>
      <Head>
        {/* <script
          id="cookieyes"
          type="text/javascript"
          src="https://cdn-cookieyes.com/client_data/c2fb2efa6e966d304bf5638b/script.js"
        ></script> */}
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link
          href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap"
          rel="stylesheet"
        />
        <link rel="icon" href="/favicon.ico" />
        <link
          rel="stylesheet"
          href="https://cdn.syncfusion.com/ej2/material.css"
          type="text/css"
        />
      </Head>
      <body className="flex min-h-full w-full grow flex-col">
        <Main />
        <NextScript />
        {/*  */}
        <div id="modal-root" />
      </body>
    </Html>
  );
}
