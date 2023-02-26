import "../styles/globals.css";
import "react-reflex/styles.css";
import { LayoutProps } from "../types/ui";
export default function Layout({ children }: LayoutProps) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  );
}
