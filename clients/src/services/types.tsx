export type ResourceType = "templates" | "playgrounds";
export type RequestTypes =
  | "get-all"
  | "get-by-id"
  | "create"
  | "update"
  | "delete";
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

// api resources
export interface Template {
  id: number;
  name: string;
  description: string;
  imageUrl: string;
  githubRepoUrl: string;
}

export interface Playground {
  id: number;
  name: string;
  description: string;
  instanceUrl: string;
  githubRepoUrl: string;
  workspaceId: number;
}

export interface CreatePlaygroundDto {
  name: string;
  description: string;
  githubRepoUrl: string;
}
