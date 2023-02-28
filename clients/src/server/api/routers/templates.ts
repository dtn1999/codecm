import { z } from "zod";

import { createTRPCRouter, publicProcedure } from "../trpc";


export const templatesRouter = createTRPCRouter({
  // Templates
  create: publicProcedure
    .query(async ({ ctx:{session} }) => {
      console.log(session)
      return { 
        accessToken: (session as any)?.accessToken,
      }
    }),
  getAll: publicProcedure
    .query(async ({ }) => {}),
  getById: publicProcedure
    .query(async ({ }) => {}),
  update: publicProcedure
    .mutation(async ({ }) => {}),
  delete: publicProcedure
    .mutation(async ({ }) => {}),
});
