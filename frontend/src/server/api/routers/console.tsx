import { z } from "zod";
import { InfrastructureController } from "../../lib/axios";

import { createTRPCRouter, publicProcedure } from "../trpc";

const controller = new InfrastructureController()

export const infrastructureRouter = createTRPCRouter({
  create: publicProcedure
    .mutation(async ({ }) => {
      return controller.getInfrastructure();
    }),
});
