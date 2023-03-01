import { NextPage } from "next";
import { Session } from "next-auth";
import { AppProps } from "next/app";
import { ReactElement, ReactNode } from "react";

export interface ComponentBaseProps {
  className?: string;
}
export interface ContainerComponentProps extends ComponentBaseProps {
  children?: React.ReactNode;
}

export type NextPageWithLayout<P = {}, IP = P> = NextPage<P, IP> & {
  getLayout?: (page: ReactElement) => ReactNode;
};

type AppPropsWithLayout = AppProps & {
  Component: NextPageWithLayout;
};

export type AppPropsWithLayoutAndSession = AppPropsWithLayout & {
  session?: Session;
};
