import { RESOURCES_SERVER_URL } from "@we/utils/env";
import axios, { AxiosInstance } from "axios";
import {
  GetAllRequest,
  GetByIdRequest,
  CreateRequest,
  UpdateRequest,
  DeleteRequest,
  ApiResponse,
} from "@we/services/types";

export class ResourceClient {
  private readonly axiosClient: AxiosInstance;
  constructor() {
    this.axiosClient = axios.create({
      baseURL: RESOURCES_SERVER_URL,
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
      `${resourceType}${path.startsWith("/") ? "" : `/${path}`}/${id}`
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
      `${resourceType}/${path}/${id}`,
      requestPayload
    );
    return data;
  }
  public async delete<O, ID>(
    request: DeleteRequest<ID>
  ): Promise<ApiResponse<O, any>> {
    const { resourceType, path, id } = request;
    const { data, status } = await this.axiosClient.delete<ApiResponse<O, any>>(
      `${resourceType}/${path}/${id}`
    );
    return data;
  }
}
