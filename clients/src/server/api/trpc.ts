import { type CreateNextContextOptions } from "@trpc/server/adapters/next";

import { initTRPC, TRPCError } from "@trpc/server";
import superjson from "superjson";
import { getServerAuthSession } from "server/auth";

type CreateContextOptions = Record<string, never>;

const createInnerTRPCContext = async(_opts: CreateContextOptions) =>{
  return {
    session: _opts.session,
  };
};

export const createTRPCContext = async (_opts: CreateNextContextOptions) => {
  const {req, res} = _opts;
   const session:any = await getServerAuthSession({ req, res });
  return createInnerTRPCContext({ session } as any);
};


const t = initTRPC
.context<typeof createTRPCContext>()
.create({
  transformer: superjson,
  errorFormatter({ shape }) {
    return shape;
  },
});

export const createTRPCRouter = t.router;


const isAuthenticated = t.middleware(({ ctx, next }:any) => {
  if (!ctx.session || !ctx.session.user) {
    throw new TRPCError({ code: "UNAUTHORIZED" });
  }
  return next({
    ctx: {
      // infers the `session` as non-nullable
      session: { ...ctx.session, user: ctx.session.user },
    },
  });
});

/**
 * Public (unauthenticated) procedure
 *
 * This is the base piece you use to build new queries and mutations on your
 * tRPC API. It does not guarantee that a user querying is authorized, but you
 * can still access user session data if they are logged in.
 */
export const publicProcedure = t.procedure;

/**
 * Protected (authenticated) procedure
 *
 */
export const protectedProcedure = t.procedure.use(isAuthenticated);


