import { createNextApiHandler } from "@trpc/server/adapters/next";

import { env } from "@we/env.mjs";
import { createTRPCContext } from "@we/server/api/trpc";
import { appRouter } from "@we/server/api/root";

// export API handler
export default createNextApiHandler({
  router: appRouter,
  createContext: createTRPCContext,
  onError:
    env.NODE_ENV === "development"
      ? ({ path, error }) => {
          console.error(
            `❌ tRPC failed on ${path ?? "<no-path>"}: ${error.message}`,
          );
        }
      : undefined,
});
