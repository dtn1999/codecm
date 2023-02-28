import { env } from "@we/env.mjs";
import {
  ApiResponse,
  CreateRequest,
  DeleteRequest,
  GetAllRequest,
  GetByIdRequest,
  UpdateRequest,
} from "@we/types/schemas";
import axios, { AxiosInstance } from "axios";

export class ResourceClient {
  private readonly axiosClient: AxiosInstance;

  constructor(accessToken?: string) {
    this.axiosClient = axios.create({
      baseURL: String(env.RESOURCES_SERVER_URL),
      headers: {
        Authorization: accessToken ? `Bearer ${accessToken}` : undefined,
      },
    });
  }

  public async getAll<O>(request: GetAllRequest): Promise<ApiResponse<O, any>> {
    const { resourceType, path } = request;
    const { data, status } = await this.axiosClient.get<ApiResponse<O, any>>(
      `${resourceType}`
    );
    return data;
  }
  public async getById<O, ID>(
    request: GetByIdRequest<ID>
  ): Promise<ApiResponse<O, any>> {
    const { resourceType, path, id } = request;
    const { data, status } = await this.axiosClient.get<ApiResponse<O, any>>(
      `${resourceType}/${id}`
    );
    return data;
  }
  public async create<O, R>(
    request: CreateRequest<R>
  ): Promise<ApiResponse<O, any>> {
    const { resourceType, path, data: requestPayload } = request;
    const { data, status } = await this.axiosClient.post<ApiResponse<O, any>>(
      `${resourceType}`,
      requestPayload
    );
    return data;
  }
  public async update<O, R, ID>(
    request: UpdateRequest<R, ID>
  ): Promise<ApiResponse<O, any>> {
    const { resourceType, path, id, data: requestPayload } = request;
    const { data, status } = await this.axiosClient.put<ApiResponse<O, any>>(
      `${resourceType}/${id}`,
      requestPayload
    );
    return data;
  }
  public async delete<O, ID>(
    request: DeleteRequest<ID>
  ): Promise<ApiResponse<O, any>> {
    const { resourceType, path, id } = request;
    const { data, status } = await this.axiosClient.delete<ApiResponse<O, any>>(
      `${resourceType}/${id}`
    );
    return data;
  }
}
