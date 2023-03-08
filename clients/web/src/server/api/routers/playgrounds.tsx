import { z } from "zod";

import {
  createTRPCRouter,
  publicProcedure,
  protectedProcedure,
} from "@we/server/api/trpc";
import { CreatePlaygroundInputSchema } from "@we/types/schemas";

export const playgroundsRouter = createTRPCRouter({
  getAll: protectedProcedure 
    .input(CreatePlaygroundInputSchema)
    .query(({ input, ctx:{ resourceClient, session} }) => {
      resourceClient.getAll({ resourceType: "playgrounds", path: "/playgrounds" });
      return {
        greeting: `Hello ${input}`,
      };
    }),
  getById: publicProcedure
    .input(z.object({ text: z.string() }))
    .query(({ input }) => {
      return {
        greeting: `Hello ${input.text}`,
      };
    }),
  create: publicProcedure
    .input(z.object({ text: z.string() }))
    .query(({ input }) => {
      return {
        greeting: `Hello ${input.text}`,
      };
    }),
  update: publicProcedure
    .input(z.object({ text: z.string() }))
    .query(({ input }) => {
      return {
        greeting: `Hello ${input.text}`,
      };
    }),
  getSecretMessage: protectedProcedure.query(() => {
    return "you can now see this secret message!";
  }),
});
