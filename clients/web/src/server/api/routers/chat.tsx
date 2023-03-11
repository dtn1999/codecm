import { z } from "zod";

import {
  createTRPCRouter,
  publicProcedure,
} from "@we/server/api/trpc";

import {Configuration, OpenAIApi} from "openai"

export const chatRouter = createTRPCRouter({
  hello: publicProcedure
    .input(z.object({ text: z.string() }))
    .query(({ input }) => {
      return {
        greeting: `Hello ${input.text}`,
      };
    }),
});
