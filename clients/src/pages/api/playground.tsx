import { NextApiRequest, NextApiResponse } from "next";
import { axiosClient } from "../../server/lib/axios";
import { createWorkspace } from "../../server/lib/resources";

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse
) {
  const requestPayload = JSON.parse(req.body);
  console.log(requestPayload);
  try {
    const {
      data: { data },
    } = await axiosClient.post(`/playgrounds`, {
      ...requestPayload,
    });
    console.log("api answer with the following response ", data);
    res.status(200).json(data);
  } catch (e) {
    console.log(e);
    res.status(200).json({ success: false });
  }
}
