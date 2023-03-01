import { z } from "zod";

import {
  createTRPCRouter,
  publicProcedure,
  protectedProcedure,
} from "@we/server/api/trpc";
import { GetAllTemplatesResponseSchema, Template } from "@we/types/schemas";

export const templatesRouter = createTRPCRouter({
  getAll: publicProcedure
    .output(GetAllTemplatesResponseSchema)
    .query(async ({ ctx: { resourceClient } }): Promise<any> => {
      const { data: templates, success } = await resourceClient.getAll({
        resourceType: "templates",
        path: "",
      });
      console.log(templates);
      return {
        templates,
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
