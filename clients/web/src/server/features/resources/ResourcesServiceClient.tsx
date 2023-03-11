import { env } from "@we/env.mjs";
import {
  ApiResponse,
  CreateRequest,
  DeleteRequest,
  GetAllRequest,
  GetByIdRequest,
  Template,
  UpdateRequest,
} from "@we/types/schemas";
import axios, { AxiosInstance } from "axios";
import { Octokit } from "octokit";

export class ResourceClient {
  private readonly axiosClient: AxiosInstance;
  private readonly octokit: Octokit;
  constructor(accessToken?: string) {
    this.axiosClient = axios.create({
      baseURL: String(env.RESOURCES_SERVER_URL),
      headers: {
        Authorization: accessToken ? `Bearer ${accessToken}` : undefined,
      },
    });
    this.octokit = new Octokit({});
  }

  public async getAll<O>(
    request: GetAllRequest,
    token?: string
  ): Promise<ApiResponse<O, any>> {
    const { resourceType } = request;
    const { data } = await this.axiosClient.get<ApiResponse<O, any>>(
      `${resourceType}`,
      {
        headers: {
          Authorization: token ? `Bearer ${token}` : undefined,
        },
      }
    );
    return data;
  }
  public async getById<O, ID>(
    request: GetByIdRequest<ID>,
    token?: string
  ): Promise<ApiResponse<O, any>> {
    const { resourceType, id } = request;
    const { data } = await this.axiosClient.get<ApiResponse<O, any>>(
      `${resourceType}/${id}`,
      {
        headers: {
          Authorization: token ? `Bearer ${token}` : undefined,
        },
      }
    );
    return data;
  }
  public async create<O, R>(
    request: CreateRequest<R>,
    token?: string
  ): Promise<ApiResponse<O, any>> {
    const { resourceType, data: requestPayload } = request;
    const { data } = await this.axiosClient.post<ApiResponse<O, any>>(
      `${resourceType}`,
      requestPayload,
      {
        headers: {
          Authorization: token ? `Bearer ${token}` : undefined,
        },
      }
    );
    return data;
  }
  public async update<O, R, ID>(
    request: UpdateRequest<R, ID>,
    token?: string
  ): Promise<ApiResponse<O, any>> {
    const { resourceType, id, data: requestPayload } = request;
    const { data } = await this.axiosClient.put<ApiResponse<O, any>>(
      `${resourceType}/${id}`,
      requestPayload,
      {
        headers: {
          Authorization: token ? `Bearer ${token}` : undefined,
        },
      }
    );
    return data;
  }
  public async delete<O, ID>(
    request: DeleteRequest<ID>,
    token?: string
  ): Promise<ApiResponse<O, any>> {
    const { resourceType, id } = request;
    const { data } = await this.axiosClient.delete<ApiResponse<O, any>>(
      `${resourceType}/${id}`,
      {
        headers: {
          Authorization: token ? `Bearer ${token}` : undefined,
        },
      }
    );
    return data;
  }

  public async gitHubTemplates(username: string): Promise<any> {
    const { data } = await this.octokit.request("GET /users/{username}/repos", {
      username,
    });
    return {
      data: data.map(this.mapRepoToTemplate),
    };
  }

  private mapRepoToTemplate({
    id,
    name,
    description,
    clone_url,
  }: any): Template {
    return {
      id,
      name,
      description,
      githubRepoUrl: clone_url,
      imageUrl:
        "https://w7.pngwing.com/pngs/914/758/png-transparent-github-social-media-computer-icons-logo-android-github-logo-computer-wallpaper-banner-thumbnail.png",
    };
  }
}
