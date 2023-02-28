import { createTRPCRouter } from "./trpc";
import { templatesRouter } from "./routers/templates";
import { playgroundsRouter } from "./routers/playgrounds";

/**
 * This is the primary router for your server.
 *
 * All routers added in /api/routers should be manually added here
 */
export const appRouter = createTRPCRouter({
  templates: templatesRouter,
  playgrounds: playgroundsRouter,
});

// export type definition of API
export type AppRouter = typeof appRouter;
