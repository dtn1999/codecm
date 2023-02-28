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
    const { RESOURCES_SERVER_URL } = process.env;
    if (!RESOURCES_SERVER_URL) {
      throw new Error(
        "RESOURCES_SERVER_URL is required by ResourceClient to access the resources server but it is not defined"
      );
    }
    this.axiosClient = axios.create({
      baseURL: String(RESOURCES_SERVER_URL),
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
