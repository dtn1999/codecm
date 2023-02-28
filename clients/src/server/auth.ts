import { GetServerSidePropsContext } from "next";
import { getServerSession } from "next-auth";
import { authOptions } from "../pages/api/auth/[...nextauth]"

export const getServerAuthSession = async (ctx: {
    req: GetServerSidePropsContext["req"];
    res: GetServerSidePropsContext["res"];
  }) => {
    console.log("getServerAuthSession", authOptions)
    const session = await getServerSession(ctx.req, ctx.res,authOptions);
    return session;
  };