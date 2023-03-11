import React from "react";
import cn from "classnames";
import { useFormContext } from "react-hook-form";
import { ComponentBaseProps } from "@we/types/ui";

interface Props extends ComponentBaseProps {
  name: string;
  label?: string;
  placeholder?: string;
  required?: boolean;
  row?: number;
}

export const FormTextarea: React.FC<Props> = ({
  name,
  label,
  placeholder,
  required,
  row,
  className,
}) => {
  const { register, getFieldState } = useFormContext();
  const { error, isDirty, isTouched } = getFieldState(name);
  if (!name) {
    return null;
  }
  return (
    <div className={cn("flex w-full flex-col")}>
      <textarea
        {...register(name, { required })}
        rows={4}
        placeholder={placeholder}
        className={cn(
          "my-2 w-full rounded border border-transparent bg-placeholder py-3 px-2 text-sm text-white placeholder:font-thin placeholder:text-white focus:rounded-none focus:border-black focus:outline-none",
          className
        )}
      />
      {error && isTouched && (
        <p className="font-extralight text-red-500">{error.message}</p>
      )}
    </div>
  );
};
