import { z } from "zod";

import {
  createTRPCRouter,
  publicProcedure,
  protectedProcedure,
} from "@we/server/api/trpc";
import {
  CreatePlaygroundInputSchema,
  CreatePlaygroundResponse,
  CreatePlaygroundResponseSchema,
  GetAllPlaygroundsResponse,
  GetAllPlaygroundsResponseSchema,
  GetAllTemplatesResponse,
  Playground,
} from "@we/types/schemas";

export const playgroundsRouter = createTRPCRouter({
  getAll: protectedProcedure
    .output(GetAllPlaygroundsResponseSchema)
    .query(
      async ({
        input,
        ctx: { resourceClient, session },
      }): Promise<GetAllPlaygroundsResponse> => {
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
          playgrounds: playgrounds as Playground[],
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
  create: protectedProcedure
    .input(CreatePlaygroundInputSchema)
    .output(CreatePlaygroundResponseSchema)
    .mutation(
      async ({ input, ctx: { resourceClient, session } }): Promise<any> => {
        console.log("session in create", session);
        const {
          data: playground,
          error,
          success,
          message,
        } = await resourceClient.create(
          {
            resourceType: "playgrounds",
            path: "/playgrounds",
            data: input,
          },
          session.accessToken
        );
        console.log(playground, error, success, message);
        return {
          playground,
        };
      }
    ),
  update: publicProcedure
    .input(z.object({ text: z.string() }))
    .query(({ input }) => {
      return {
        greeting: `Hello ${input.text}`,
      };
    }),
  delete: protectedProcedure
    .input(z.object({ playgroundId: z.number() }))
    .mutation(
      async ({ input: { playgroundId }, ctx: { resourceClient, session } }) => {
        const { data, error, success, message } = await resourceClient.delete(
          {
            resourceType: "playgrounds",
            id: playgroundId,
            path: ``,
          },
          session.accessToken
        );
        console.log(data, error, success, message);
        return {
          data,
        };
      }
    ),
  getSecretMessage: protectedProcedure.query(() => {
    return "you can now see this secret message!";
  }),
});
