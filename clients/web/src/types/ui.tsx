export interface ComponentBaseProps {
    className?: string;
}
export interface ContainerComponentProps extends ComponentBaseProps {
    children?: React.ReactNode;
}