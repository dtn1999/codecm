import { type AppType } from "next/app";

import { api } from "../utils/api";

import "../styles/globals.css";
import "node_modules/xterm/css/xterm.css";
import 'react-reflex/styles.css'


const MyApp: AppType = ({ Component, pageProps }) => {
  return <Component {...pageProps} />;
};

export default api.withTRPC(MyApp);
