import React from "react";
import cn from "classnames";
import { useFormContext } from "react-hook-form";

interface Props {
  name: string;
  label?: string;
  placeholder?: string;
  required?: boolean;
  type?: HTMLInputElement["type"];
}

export const FormInput: React.FC<Props> = ({
  name,
  placeholder,
  required,
  type,
  label,
}) => {
  const { register, getFieldState } = useFormContext();
  const { error, isDirty, isTouched } = getFieldState(name);
  if (!name) {
    return null;
  }

  return (
    <div className={cn("flex w-full flex-col")}>
      <input
        {...register(name, { required: required ? true : false })}
        name={name}
        type={type?.toString().toLowerCase()}
        placeholder={placeholder || ""}
        className={cn(
          "my-2 w-full border border-transparent rounded bg-placeholder py-3 px-2 text-sm text-white placeholder:font-thin placeholder:text-white focus:outline-none"
        )}
      />
      {error && <p className="font-extralight text-red-500">{error.message}</p>}
    </div>
  );
};
