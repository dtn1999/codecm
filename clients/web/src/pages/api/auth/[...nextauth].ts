import NextAuth from "next-auth";
import { authOptions } from "@we/server/auth";

export default NextAuth(authOptions);
