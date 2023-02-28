import { createTRPCRouter } from "@we/server/api/trpc";
import { exampleRouter } from "@we/server/api/routers/example";
import { playgroundsRouter } from "@we/server/api/routers/playgrounds";
import { templatesRouter } from "@we/server/api/routers/templates";

/**
 * This is the primary router for your server.
 *
 * All routers added in /api/routers should be manually added here.
 */
export const appRouter = createTRPCRouter({
  example: exampleRouter,
  templatesRouter: templatesRouter,
  playgroundsRouter: playgroundsRouter,
});

// export type definition of API
export type AppRouter = typeof appRouter;
