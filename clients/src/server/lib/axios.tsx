import axios, { AxiosError } from "axios";
import { RESOURCES_SERVER_URL } from "../../utils/env";

export const axiosClient = axios.create({
  baseURL: RESOURCES_SERVER_URL,
});

export interface Playground {
  instanceUrl: string;
}

export interface APIError {
  message: string;
}

export class InfrastructureController {
  constructor() {
    console.log("InfrastructureController");
  }
  public async getInfrastructure(): Promise<Playground | APIError> {
    try {
      const response = await axiosClient.post("/playground");
      return {
        instanceUrl: (response.data as { instanceUrl: string }).instanceUrl,
      };
    } catch (e) {
      console.log(e);
      return {
        message: (e as { message: string }).message,
      };
    }
  }
}
