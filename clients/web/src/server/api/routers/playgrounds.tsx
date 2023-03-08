import { z } from "zod";

import {
  createTRPCRouter,
  publicProcedure,
  protectedProcedure,
} from "@we/server/api/trpc";
import {
  CreatePlaygroundInputSchema,
  GetAllPlaygroundsResponseSchema,
  Playground,
} from "@we/types/schemas";

export const playgroundsRouter = createTRPCRouter({
  getAll: protectedProcedure
    .output(GetAllPlaygroundsResponseSchema)
    .query(
      async ({ input, ctx: { resourceClient, session } }): Promise<any> => {
        console.log("session", session);
        const {
          data: playgrounds,
          error,
          message,
        } = await resourceClient.getAll(
          {
            resourceType: "playgrounds",
            path: "/playgrounds",
          },
          session.accessToken
        );
        console.log(playgrounds, error, message);
        return {
          playgrounds,
        };
      }
    ),
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
