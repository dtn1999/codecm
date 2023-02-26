import { AxiosError } from "axios";
import { RESOURCES_SERVER_URL } from "../../utils/env";
import { axiosClient } from "./axios";

interface CreatePlaygroundRequest {
  name: string;
  githubRepoUrl: string;
  description: string;
}

export const createWorkspace = async (request: CreatePlaygroundRequest) => {
  try {
    const { data } = await axiosClient.post(`/playgrounds`, request);
    return data;
  } catch (e) {
    console.error("Error creating workspace: ", (e as AxiosError).toJSON());
  }
};
