/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.tsx"],
  theme: {
    extend: {
      colors: {
        primary: {
          DEFAULT: "#033E59",
          hover: "#3A7861",
          light: "#33975D38",
          dark: "#548583",
        },
        accent: {
          DEFAULT: "#d5b976",
        },
        alert: {
          DEFAULT: "#D30922",
        },
        black: "#161616",
        tertiary: "#929292",
        secondary: "#f0f0f0",
      },
      borderColor: {
        md: "4px",
      },
      boxShadow: {
        button: "0 5px 25px 0 rgb(0 0 0 / 25%)",
      },
      lineHeight: {
        11: "48px",
      },
      width: {
        17: "4.375rem",
      },
      maxWidth: {
        sm: "540px",
        md: "720px",
        lg: "960px",
        xl: "1570px",
        "1/2": "50%",
      },
      spacing: {
        15: "3.75rem",
      },
      screens: {
        // => @media (min-width: 640px) { ... }

        xs: "320px",

        sm: "576px",
        // => @media (min-width: 640px) { ... }

        smd: "540px",

        md: "768px",
        // => @media (min-width: 768px) { ... }

        lg: "992px",
        // => @media (min-width: 1024px) { ... }

        xl: "1200px",
        // => @media (min-width: 1280px) { ... }
      },
    },
  },
  plugins: [],
};
