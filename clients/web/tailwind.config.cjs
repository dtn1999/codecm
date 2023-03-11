/** @type {import('tailwindcss').Config} */
const config = {
  content: ["./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        primary: {
          DEFAULT: "#595959",
        },
        background: {
          DEFAULT: "#151515",
        },
        placeholder: {
          DEFAULT: "#2A2A2A",
        },
      },
    },
  },
  plugins: [],
};

module.exports = config;
