import { LayoutProps } from "../../types/ui";
import { NavBar } from "./Layout.NavBar";

export default function ServiceLayout({ children }: LayoutProps) {
  return (
    <>
      <NavBar />
      <p className="bg-gray-200">global layout (Services)</p>
      {children}
    </>
  );
}
