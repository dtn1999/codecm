import {
  BuildProjectIcon,
  DashboardIcon,
  PlaygroundsIcon,
  ProjectsIcon,
} from "@we/components/Icons";

export const NAV_SECTIONS = [
  {
    title: "",
    children: [
      {
        name: "Dashboard",
        href: "/dashboard",
        icon: <DashboardIcon />,
      },
      {
        name: "My Projects",
        href: "/my-projects",
        icon: <ProjectsIcon />,
      },
    ],
  },
  {
    title: "Practice",
    children: [
      {
        name: "Build Projects",
        href: "/projects",
        icon: <BuildProjectIcon />,
      },
      {
        name: "Playgrounds",
        href: "/playgrounds",
        icon: <PlaygroundsIcon />,
      },
    ],
  },
];
