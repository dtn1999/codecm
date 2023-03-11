import NextAuth from "next-auth";
import { authOptions } from "@we/server/features";

export default NextAuth(authOptions);
