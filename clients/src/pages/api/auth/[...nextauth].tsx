import NextAuth, { AuthOptions } from "next-auth";
import Auth0Provider from "next-auth/providers/auth0";
console.log("process.env.AUTH0_ID", process.env.OAUTH0_ISSUER);
export const authOptions: AuthOptions = {
  providers: [
    Auth0Provider({
      clientId: String(process.env.OAUTH0_ID),
      clientSecret: String(process.env.OAUTH0_SECRET),
      issuer: String(process.env.OAUTH0_ISSUER),
      
    }),
  ],
  callbacks: {
    async jwt({ token, account }) {
      // Persist the OAuth access_token to the token right after signin
      if (account) {
        token.accessToken = account.access_token;
      }
      return token;
    },
    async session({ session, token, user }) {
      // Send properties to the client, like an access_token from a provider.
      const { accessToken } = token;
      return {
        ...session,
        accessToken,
      };
    },
  },
};

export default NextAuth(authOptions as any);
