import { z } from "zod";

import {
  createTRPCRouter,
  publicProcedure,
  protectedProcedure,
} from "@we/server/api/trpc";
import {
  GetAllTemplatesResponseSchema,
  Template,
  TemplateSchema,
} from "@we/types/schemas";

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
  gitHubTemplates: publicProcedure
    .input(z.object({ username: z.string() }))
    .output(z.object({ templates: z.array(TemplateSchema) }))
    .query(async ({ ctx: { resourceClient } }): Promise<any> => {
      const { data: templates } = await resourceClient.gitHubTemplates(
        "dtn1999"
      );
      return {
        templates,
      };
    }),
});
