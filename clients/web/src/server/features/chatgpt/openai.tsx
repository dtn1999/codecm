import { env } from "@we/env.mjs";
import { Configuration, CreateCompletionRequest, OpenAIApi } from "openai";

const configuration = new Configuration({
  apiKey: env.OPENAI_API_KEY,
});

export const openai = new OpenAIApi(configuration);
export const completionConfig: Record<
  string,
  Omit<CreateCompletionRequest, "prompt">
> = {
  basic: {
    model: "text-davinci-003",
    temperature: 0,
    max_tokens: 3000,
    top_p: 1,
    frequency_penalty: 0.5,
    presence_penalty: 0,
  },
};
