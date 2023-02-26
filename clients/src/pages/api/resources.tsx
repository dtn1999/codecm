import { resourceServiceClient } from "@we/services";
import { ApiResponse, RequestTypes } from "@we/services/types";
import { AxiosError } from "axios";
import { NextApiRequest, NextApiResponse } from "next";

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse
) {
  const requestPayload = JSON.parse(req.body);
  console.log(requestPayload);
  try {
    let data: ApiResponse<unknown, unknown>;
    const { type, resourceType, path, payload, id } = requestPayload;
    switch (requestPayload.type as RequestTypes) {
      case "get-all":
        data = await resourceServiceClient.getAll({ path, resourceType });
        break;
      case "get-by-id":
        data = await resourceServiceClient.getById({ resourceType, path, id });
        break;
      case "create":
        data = await resourceServiceClient.create({
          resourceType,
          path,
          data: payload,
        });
        break;
      case "update":
        data = await resourceServiceClient.update({
          resourceType,
          path,
          id,
          data: payload,
        });
        break;
      case "delete":
        data = await resourceServiceClient.delete({ resourceType, path, id });
        break;
      default:
        throw new Error("Invalid request type");
    }
    console.log("api answer with the following response ", data);
    res.status(200).json({ data, success: true });
  } catch (e) {
    console.log(e);

    res
      .status(200)
      .json({ success: false, data: (e as AxiosError).response?.data });
  }
}
