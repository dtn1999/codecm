import { z } from "zod";

import { createTRPCRouter, publicProcedure } from "../trpc";


export const playgroundsRouter = createTRPCRouter({
  // Templates
  create: publicProcedure
    .mutation(async ({ }) => {
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