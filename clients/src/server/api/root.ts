import { createTRPCRouter } from "./trpc";
import { infrastructureRouter } from "./routers/infrastructure";

/**
 * This is the primary router for your server.
 *
 * All routers added in /api/routers should be manually added here
 */
export const appRouter = createTRPCRouter({
  spaces: infrastructureRouter,
});

// export type definition of API
export type AppRouter = typeof appRouter;
