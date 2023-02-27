import { handleAuth, handleLogin } from "@auth0/nextjs-auth0";
console.log("process", process.env);
export default handleAuth({
    login: handleLogin({
        authorizationParams: {
            audience: process.env.AUTH0_AUDIENCE,
            scope: "openid profile email"
        }
    })
});
