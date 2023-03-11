import { z } from "zod";

import { createTRPCRouter, publicProcedure } from "@we/server/api/trpc";

import { completionConfig, openai } from "@we/server/features";

export const chatRouter = createTRPCRouter({
  getHelp: publicProcedure
    .input(z.object({ prompt: z.string() }))
    .query(async ({ input }) => {
      try {
        const response = await openai.createCompletion({
          prompt: "write a simple hello world program in Rust",
          ...(completionConfig.basic as any),
        });
        console.log("response", response.data.choices);
      } catch (e) {
        console.error((e as any).data);
      }
      return {
        greeting: `Hello `,
      };
    }),
});
