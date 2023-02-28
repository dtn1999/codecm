import NextAuth from "next-auth";
import Auth0Provider from "next-auth/providers/auth0";

export const authOptions = {
  pages: {},
  providers: [
    Auth0Provider({
      clientId: String(process.env.AUTH0_CLIENT_ID),
      clientSecret: String(process.env.AUTH0_CLIENT_SECRET),
      issuer: String(process.env.AUTH0_ISSUER),
      authorization: {
        url: "https://dev-8qocg6o6c1fu6cxj.us.auth0.com/authorize",
        params: {
          scope: "openid profile email",
          audience: process.env.AUTH0_AUDIENCE,
        },
      },
    }),
  ],
  callbacks: {
    async session({ session, token, user }: any) {
      session.user.id = token.id;
      session.accessToken = token.accessToken;
      return session;
    },
    async jwt({ token, user, account, profile, isNewUser }: any) {
      if (user) {
        token.id = user.id;
      }
      if (account) {
        token.accessToken = account.access_token;
      }
      return token;
    },
  },
};
export default NextAuth(authOptions);
