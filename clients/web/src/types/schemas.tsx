import { z } from "zod";
/*******************************************************************************
 * Schemas definitions
 ********************************************************************************/
export const GetByIdInputSchema = z.object({
  id: z.number(),
});

export const TemplateSchema = z.object({
  id: z.number(),
  name: z.string(),
  description: z.string(),
  imageUrl: z.string(),
  githubRepoUrl: z.string(),
});

export const PlaygroundSchema = z.object({
  id: z.number(),
  name: z.string(),
  description: z.string(),
  instanceUrl: z.string(),
  githubRepoUrl: z.string(),
  workspaceId: z.number(),
});

export const CreatePlaygroundInputSchema = z.object({
  name: z.string(),
  description: z.string(),
  githubRepoUrl: z.string(),
});

export const GetAllTemplatesResponseSchema = z.object({
  templates: z.array(TemplateSchema),
});

export const GetAllPlaygroundsResponseSchema = z.object({
  playgrounds: z.array(PlaygroundSchema),
});
/*******************************************************************************
 * Types definitions
 *****************************************************************************/
// From schemas
export type Template = z.infer<typeof TemplateSchema>;
export type Playground = z.infer<typeof PlaygroundSchema>;
export type CreatePlaygroundInput = z.infer<typeof CreatePlaygroundInputSchema>;
// Services Types
export type ResourceType = "templates" | "playgrounds";
export interface GetAllRequest {
  resourceType: ResourceType;
  path: string;
}

export interface GetByIdRequest<ID> extends GetAllRequest {
  id: ID;
}

export interface CreateRequest<D> extends GetAllRequest {
  data: D;
}

export interface UpdateRequest<D, ID> extends GetByIdRequest<ID> {
  data: D;
}

export interface DeleteRequest<ID> extends GetByIdRequest<ID> {}

export interface ApiResponse<D, E> {
  data: D;
  error: E;
  success: boolean;
  message: string;
}
